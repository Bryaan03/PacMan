/**
 * Ce logiciel est distribué à des fins éducatives.
 *
 * Il est fourni "tel quel", sans garantie d'aucune sorte, explicite
 * ou implicite, notamment sans garantie de qualité marchande, d'adéquation
 * à un usage particulier et d'absence de contrefaçon.
 * En aucun cas, les auteurs ou titulaires du droit d'auteur ne seront
 * responsables de tout dommage, réclamation ou autre responsabilité, que ce
 * soit dans le cadre d'un contrat, d'un délit ou autre, en provenance de,
 * consécutif à ou en relation avec le logiciel ou son utilisation, ou avec
 * d'autres éléments du logiciel.
 *
 * (c) 2022-2025 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model;

import fr.univartois.butinfo.r304.pacman.bonus.BonusPoints;
import fr.univartois.butinfo.r304.pacman.bonus.BonusSpeed;
import fr.univartois.butinfo.r304.pacman.bonus.BonusSuper;
import fr.univartois.butinfo.r304.pacman.level.ILevel;
import fr.univartois.butinfo.r304.pacman.level.LevelFactory;
import fr.univartois.butinfo.r304.pacman.model.animated.*;
import fr.univartois.butinfo.r304.pacman.model.map.Cell;
import fr.univartois.butinfo.r304.pacman.model.map.GameMap;
import fr.univartois.butinfo.r304.pacman.view.ISpriteStore;
import fr.univartois.butinfo.r304.pacman.view.Sprite;
import javafx.animation.AnimationTimer;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * La classe {@link PacmanGame} gère une partie du jeu Pac-Man.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public final class PacmanGame {

	/**
	 * Le génarateur de nombres aléatoires utilisé dans le jeu.
	 */
	public static final Random RANDOM = new Random();

	/**
	 * La vitesse de déplacement du joueur (en pixels/s).
	 */
	public static final int DEFAULT_SPEED = 150;

	/**
	 * La largeur de la carte du jeu (en pixels).
	 */
	private final int width;

	/**
	 * La hauteur de la carte du jeu (en pixels).
	 */
	private final int height;

	/**
	 * L'instance de {@link ISpriteStore} permettant de créer les {@link Sprite} du
	 * jeu.
	 */
	private final ISpriteStore spriteStore;

	/**
	 * La carte du jeu.
	 */
	private GameMap gameMap;

	/**
	 * Le personnage du joueur.
	 */
	private PacMan player;

	/**
	 * Le nombre de fantômes initialement dans le jeu.
	 */
	private int nbGhosts;

	/**
	 * Le nombre de pac-gommes initialement dans le jeu.
	 */
	private int nbGums;

	/**
	 * La liste des objets mobiles du jeu.
	 */
	private final List<IAnimated> movingObjects = new CopyOnWriteArrayList<>();

	/**
	 * La liste des objets animés du jeu.
	 */
	private final List<IAnimated> animatedObjects = new CopyOnWriteArrayList<>();

	/**
	 * La liste des Ghost du jeu.
	 */
	private final List<Ghost> ghostObjects = new CopyOnWriteArrayList<>();

	/**
	 * L'animation du jeu, qui s'assure que les différents objets évoluent.
	 */
	private final AnimationTimer animation = new GameAnimation(movingObjects, animatedObjects);

	/**
	 * Probabilité qu'une mega-gum spawn
	 */
	private static final double MEGA_GUM_PROBA = 0.04;
	
	/**
	 * Probabilité qu'un bonus spawn
	 */
	private static final double BONUS_PROBA = 0.02;

	/**
	 * Le contrôleur du jeu.
	 */
	private IPacmanController controller;

	/**
	 * Le level du jeu
	 */
	private ILevel currentLevel;

	/**
	 * Méthode pour set le level
	 * @param level Le level choisi
	 */
	public void setLevel(ILevel level) {
		this.currentLevel = level;
	}

	/**
	 * Crée une nouvelle instance de PacmanGame.
	 *
	 * @param gameWidth   La largeur de la carte du jeu.
	 * @param gameHeight  La hauteur de la carte du jeu.
	 * @param spriteStore L'instance de {@link ISpriteStore} permettant de créer les
	 *                    {@link Sprite} du jeu.
	 * @param nbGhosts    Le nombre de fantômes dans le jeu.
	 */
	public PacmanGame(int gameWidth, int gameHeight, ISpriteStore spriteStore, int nbGhosts) {
		this.width = gameWidth;
		this.height = gameHeight;
		this.spriteStore = spriteStore;
		this.nbGhosts = nbGhosts;
		this.currentLevel = LevelFactory.getLevel(1);
	}

	/**
	 * Modifie le contrôleur avec lequel interagir pour mettre à jour l'affichage.
	 *
	 * @param controller Le contrôleur avec lequel interagir.
	 */
	public void setController(IPacmanController controller) {
		this.controller = controller;
	}

	/**
	 * Donne l'instance de {@link ISpriteStore} permettant de créer les
	 * {@link Sprite} du jeu.
	 *
	 * @return L'instance de {@link ISpriteStore} permettant de créer les
	 *         {@link Sprite} du jeu.
	 */
	public ISpriteStore getSpriteStore() {
		return spriteStore;
	}

	/**
	 * Donne la largeur de la carte du jeu (en pixels).
	 *
	 * @return La largeur de la carte du jeu.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Donne la hauteur de la carte du jeu (en pixels).
	 *
	 * @return La hauteur de la carte du jeu.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Prépare une partie de Pac-Man avant qu'elle ne démarre.
	 */
	public void prepare() {
		gameMap = createMap();
		controller.prepare(gameMap);
	}

	/**
	 * Crée la carte du jeu, en respectant les dimensions de la fenêtre.
	 *
	 * @return La carte du jeu ayant été créée.
	 */
	private GameMap createMap() {
		return currentLevel.createMap(width, height, spriteStore);
	}

	/**
	 * Démarre la partie de Pac-Man.
	 */
	public void start() {
		prepare();
		createAnimated();
		initStatistics();
		animation.start();
	}

	/**
	 * Set le pacman
	 * @param pacman Le joueur
	 */
	public void setPacman(PacMan pacman) {
		removeAnimated(player);

		this.player = pacman;

		addAnimated(pacman);
		spawnAnimated(pacman);
	}

	/**
	 * Crée les différents objets animés présents au début de la partie.
	 */
	private void createAnimated() {
	    clearAnimated();

	    // Joueur
	    player = new PacMan(this, 0, 0,
	            spriteStore.getSprite("pacman/closed", "pacman/half-open", "pacman/open"));
	    animatedObjects.add(player);
	    spawnAnimated(player);

	    // Pac-gommes / méga-gommes / bonus
	    List<Cell> emptyCells = gameMap.getEmptyCells();
	    for (Cell cell : emptyCells) {
	        nbGums++;

	        double chance = Math.random();

	        if (chance < MEGA_GUM_PROBA) {
	            MegaGomme megaGomme = new MegaGomme(
	                    this,
						(cell.getColumn() * spriteStore.getSpriteSize()),
						(cell.getRow() * spriteStore.getSpriteSize()),
	                    spriteStore.getSprite("megagum")
	            );
	            animatedObjects.add(megaGomme);
	            addAnimated(megaGomme);

	        } else if (chance < MEGA_GUM_PROBA + BONUS_PROBA) {
	        	List<String> bonuses = currentLevel.getBonuses();
	        	String bonusName = bonuses.get(RANDOM.nextInt(bonuses.size()));
	        	
        		if (bonusName.equals("BonusPoints")) {
    	            BonusPoints bonusPoints = new BonusPoints(
    	                    this,
							(cell.getColumn() * spriteStore.getSpriteSize()),
							(cell.getRow() * spriteStore.getSpriteSize()),
    	                    spriteStore.getSprite("bonus/apple")
    	            );
    	            
    	            animatedObjects.add(bonusPoints);
    	            addAnimated(bonusPoints);
        		}
        		else if (bonusName.equals("BonusSpeed")) {
    	            BonusSpeed bonusSpeed = new BonusSpeed(
    	                    this,
							(cell.getColumn() * spriteStore.getSpriteSize()),
							(cell.getRow() * spriteStore.getSpriteSize()),
    	                    spriteStore.getSprite("bonus/melon")
    	            );
    	            
    	            animatedObjects.add(bonusSpeed);
    	            addAnimated(bonusSpeed);
        		}
        		else if (bonusName.equals("BonusSuper")) {
    	            BonusSpeed bonusSpeed = new BonusSpeed(
    	                    this,
							(cell.getColumn() * spriteStore.getSpriteSize()),
							(cell.getRow() * spriteStore.getSpriteSize()),
    	                    spriteStore.getSprite("bonus/melon")
    	            );
    	            
    	            BonusPoints bonusPoints = new BonusPoints(
    	                    this,
							(cell.getColumn() * spriteStore.getSpriteSize()),
							(cell.getRow() * spriteStore.getSpriteSize()),
    	                    spriteStore.getSprite("bonus/apple")
    	            );
    	            
    	            BonusSuper bonusSuper = new BonusSuper(
    	                    this,
							(cell.getColumn() * spriteStore.getSpriteSize()),
							(cell.getRow() * spriteStore.getSpriteSize()),
    	                    spriteStore.getSprite("bonus/orange")
    	            );
					bonusSuper.add(bonusPoints);
					bonusSuper.add(bonusSpeed);

    	            animatedObjects.add(bonusSuper);
    	            addAnimated(bonusSuper);
        		}
	        } else {
	            PacGomme gomme = new PacGomme(
	                    this,
						(cell.getColumn() * spriteStore.getSpriteSize()),
						(cell.getRow() * spriteStore.getSpriteSize()),
	                    spriteStore.getSprite("pacgum")
	            );
	            animatedObjects.add(gomme);
	            addAnimated(gomme);
	        }
	    }

	    // Fantômes
	    String[] ghostColors = {"blue", "orange", "pink", "red"};
	    for (int i = 0; i < nbGhosts; i++) {
            Sprite ghostSprite = spriteStore.getSprite("ghosts/" + ghostColors[i] + "/1");
            
            Ghost ghost = new Ghost(this, 0, 0, ghostSprite, i);
            ghostObjects.add(ghost);
            animatedObjects.add(ghost);
            spawnAnimated(ghost);

	    }
	    currentLevel.configureGhosts(ghostObjects, player); // assignation des stratégies et démarrage
	}

	/**
	 * Initialise les statistiques de cette partie.
	 */
	private void initStatistics() {
		controller.bindLife(player.getHealthProperty());
		controller.bindScore(player.getScoreProperty());
	}

	/**
	 * Fait apparaître un objet animé sur la carte du jeu.
	 *
	 * @param animated L'objet à faire apparaître.
	 */
	private void spawnAnimated(IAnimated animated) {
		List<Cell> spawnableCells = gameMap.getEmptyCells();
		if (!spawnableCells.isEmpty()) {
			Cell cell = spawnableCells.get(RANDOM.nextInt(spawnableCells.size()));
			animated.setX((cell.getColumn() * spriteStore.getSpriteSize()));
			animated.setY((cell.getRow() * spriteStore.getSpriteSize()));
			addMoving(animated);
		}
	}

	/**
	 * Déplace le personnage du joueur vers le haut.
	 */
	public void moveUp() {
		player.setDesiredDirection("UP");
	}

	/**
	 * Déplace le personnage du joueur vers la droite.
	 */
	public void moveRight() {
		player.setDesiredDirection("RIGHT");
	}

	/**
	 * Déplace le personnage du joueur vers le bas.
	 */
	public void moveDown() {
		player.setDesiredDirection("DOWN");
	}

	/**
	 * Déplace le personnage du joueur vers la gauche.
	 */
	public void moveLeft() {
		player.setDesiredDirection("LEFT");
	}

	/**
	 * Arrête le déplacement du joueur.
	 */
	public void stopMoving() {
		player.setVerticalSpeed(0);
		player.setHorizontalSpeed(0);
	}

	/**
	 * Donne la cellule à la position donnée sur la carte.
	 *
	 * @param x La position en x de la cellule.
	 * @param y La position en y de la cellule.
	 *
	 * @return La cellule à la position donnée.
	 */
	public Cell getCellAt(int x, int y) {
		// On traduit cette position en position dans la carte.
		int row = y / spriteStore.getSpriteSize();
		int column = x / spriteStore.getSpriteSize();

		// On récupère enfin la cellule à cette position dans la carte.
		return gameMap.getAt(row, column);
	}

	/**
	 * Ajoute un objet mobile dans le jeu.
	 *
	 * @param object L'objet à ajouter.
	 */
	public void addMoving(IAnimated object) {
		movingObjects.add(object);
		addAnimated(object);
	}

	/**
	 * Ajoute un objet animé dans le jeu.
	 *
	 * @param object L'objet à ajouter.
	 */
	public void addAnimated(IAnimated object) {
		animatedObjects.add(object);
		controller.addAnimated(object);
		object.onSpawn();
	}

	/**
	 * Supprime un objet animé dans le jeu.
	 *
	 * @param object L'objet à supprimer.
	 */
	public void removeAnimated(IAnimated object) {
		animatedObjects.remove(object);
		movingObjects.remove(object);
		object.onDespawn();
		object.onDestruction();
	}

	/**
	 * Supprime tous les objets animé dans le jeu.
	 */
	private void clearAnimated() {
		for (IAnimated animated : animatedObjects) {
			animated.onDespawn();
			animated.onDestruction();
		}
		animatedObjects.clear();
		movingObjects.clear();
	}

	/**
	 * Indique que le joueur a mangé une pac-gomme.
	 *
	 * @param gum La pac-gomme qui a été mangée.
	 */
	public void pacGumEaten(IAnimated gum) {
		nbGums--;
		removeAnimated(gum);

		if (nbGums <= 0) {
			gameOver("YOU WIN!");
			clearAnimated();
			setLevel(currentLevel.changeLevel());
		}
	}

	/**
	 * Indique que le joueur a mangé une mega-gomme.
	 *
	 * @param gum La mega-gomme qui a été mangée.
	 */
	public void megaGumEaten(IAnimated gum) {
		nbGums--;
		removeAnimated(gum);

		if (nbGums <= 0) {
			gameOver("YOU WIN!");
			clearAnimated();
			setLevel(currentLevel.changeLevel());
		}

		for (Ghost ghost : ghostObjects) {
			InterfaceDeplacementStrategie strategie = ghost.getOldStrategie();
			if (strategie != null) {
				ghost.setStrategie(strategie);
				ghost.setOldStrategie(null);
			}
			ghost.changeState(new GhostVulnerableState(ghost));
		}
	}

	/**
	 * Termine la partie lorsque le joueur est tué.
	 */
	public void playerIsDead() {
		gameOver("YOU HAVE BEEN KILLED!");
	}

	/**
	 * Termine la partie en cours.
	 *
	 * @param message Le message indiquant le résultat de la partie.
	 */
	private void gameOver(String message) {
		animation.stop();
		controller.gameOver(message);
	}

}
