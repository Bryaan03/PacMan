
package fr.univartois.butinfo.r304.pacman.model.animated;

import fr.univartois.butinfo.r304.pacman.model.PacmanGame;
import fr.univartois.butinfo.r304.pacman.view.Sprite;
import javafx.animation.AnimationTimer;

/**
 * La classe {@code RepresentationFantome} représente un fantôme dans le jeu Pac-Man.
 * Un fantôme se déplace aléatoirement dans le labyrinthe et change de direction
 * après un certain délai ou lorsqu'il rencontre un mur.
 */
public class Ghost extends AbstractAnimated {

    /**
     * L'identifiant unique du fantôme.
     */
    private int id;

    /**
     * Minuterie d'animation pour le mouvement périodique.
     */
    private AnimationTimer timer;

    /**
     * Intervalle de temps entre chaque action du fantôme (en secondes).
     */
    private double intervalSeconds;

    /**
     * Action à exécuter périodiquement (déplacement, etc.).
     */
    private Runnable action;

    /**
     * Accumulateur de temps pour la gestion de l'intervalle.
     */
    private double accumulator;

    /**
     * Stratégie de déplacement courante du fantôme.
     */
    private InterfaceDeplacementStrategie strategie;
    
    /**
     * Ancienne stratégie du fantôme (utile pour les états spéciaux).
     */
    private InterfaceDeplacementStrategie oldStrategie;
    
    /**
     * État actuel du fantôme (invulnérable, vulnérable, etc.).
     */
    private GhostState state;
    
    /**
     * Sprite original du fantôme.
     */
    private Sprite originalSprite;

    /**
     * Crée un nouveau fantôme.
     *
     * @param game Jeu auquel appartient le fantôme.
     * @param xPosition Position horizontale initiale.
     * @param yPosition Position verticale initiale.
     * @param sprite Sprite du fantôme.
     * @param id Identifiant du fantôme.
     */
    public Ghost(PacmanGame game, double xPosition, double yPosition, Sprite sprite, int id) {
        super(game, xPosition, yPosition, sprite);
        this.id = id;
        this.intervalSeconds = 1.0;
        this.setOriginalSprite(sprite);
        this.state = new GhostInvulnerableState(this);

        this.timer = new AnimationTimer() {

            long previousTime = 0;

            @Override
            public void handle(long now) {
                if (previousTime == 0) {
                    previousTime = now;
                    return;
                }

                double deltaTime = (now - previousTime) / 1_000_000_000.0;
                previousTime = now;
                accumulator += deltaTime;

                if (accumulator >= intervalSeconds) {
                    action.run();
                    accumulator -= intervalSeconds;
                }
            }

        };
    }
    
    /**
     * L'id du fantôme
     * @return L'ID du fantôme
     */
    public int getID() {
    	return id;
    }

    /**
     * Démarre le fantôme (activation de la minuterie).
     */
    public void start() {
        timer.start();
    }

    /**
     * Définit l'action à exécuter périodiquement.
     *
     * @param action Action du fantôme.
     */
    public void setAction(Runnable action) {
        this.action = action;
    }

    /**
     * Arrête le fantôme (désactivation de la minuterie).
     */
    public void stop() {
        timer.stop();
    }
    
    /**
     * Change l'état actuel du fantôme.
     *
     * @param state1 Nouvel état du fantôme.
     */
    public void changeState(GhostState state1) {
        this.state = state1;
    }
    
    /**
     * Set le sprite original
     *
     * @param originalSprite Le sprite original
     */
    public void setOriginalSprite(Sprite originalSprite) {
        this.originalSprite = originalSprite;
    }
    
    /**
     * Récupérer le sprite original
     * @return Le sprite original
     */
    public Sprite getOriginalSprite() {
        return originalSprite;
    }
    
    /**
     * Change le skin du fantôme.
     *
     * @param skins Liste de chemins de sprites.
     */
    public void setSkins(String[] skins) {
        Sprite ghostSprite = game.getSpriteStore().getSprite(skins);
        setSprite(ghostSprite);
    }

    /**
     * Change la stratégie de déplacement du fantôme.
     *
     * @param strategie Nouvelle stratégie.
     */
    public void setStrategie(InterfaceDeplacementStrategie strategie) {
        this.strategie = strategie;
    }
    
    /**
     * @return strategie La strategie du mouvement
     */
    public InterfaceDeplacementStrategie getStrategie() {
        return strategie;
    }
    
    /**
     * Set l'ancienne strategie
     * @param oldStrategie L'ancienne strategie
     */
    public void setOldStrategie(InterfaceDeplacementStrategie oldStrategie) {
        this.oldStrategie = oldStrategie;
    }
    
    /**
     * @return oldStrategie L'ancienne strategie
     */
    public InterfaceDeplacementStrategie getOldStrategie() {
        return oldStrategie;
    }

    /**
     * Déplace le fantôme selon la stratégie courante et gère les obstacles.
     *
     * @param delta Temps écoulé depuis la dernière mise à jour.
     * @param pacman Instance de Pac-Man pour les stratégies ciblant le joueur.
     */
    public void deplacerFantome(long delta, PacMan pacman) {
        if (strategie == null) return;

        strategie.deplacerGhost(this, pacman, delta, getSpeed());

        boolean moved = onStep(delta);

        if (!moved) {
            double testSpeed = 60;

            double[][] directions = {
                    {testSpeed, 0},
                    {-testSpeed, 0},
                    {0, testSpeed},
                    {0, -testSpeed}
            };

            boolean free = false;

            for (double[] dir : directions) {
                horizontalSpeed = dir[0];
                verticalSpeed = dir[1];
                if (onStep(delta)) {
                    free = true;
                    break;
                }
            }

            if (!free) {
                horizontalSpeed = 0;
                verticalSpeed = 0;
            }
        }
    }

    /**
     * Gère une collision avec Pac-Man.
     *
     * @param other Pac-Man en collision.
     */
    @Override
    public void onCollisionWith(PacMan other) {
        this.state.makeDamage(other);
        this.changeState(this.state.changeState());
    }
    
    /**
     * Inflige des dégâts à Pac-Man.
     *
     * @param pacman Pac-Man cible.
     */
    public void makeDamage(PacMan pacman) {
        pacman.onCollisionWith(this);
    }
    
    /**
     * Met à jour l'état du fantôme à chaque étape.
     *
     * @param delta Temps écoulé depuis la dernière mise à jour.
     * @return true si l'objet a pu se déplacer, false sinon.
     */
    @Override
    public boolean onStep(long delta) {
       state.onStep(delta);
       
       return super.onStep(delta);
    }
    
    /**
     * Retourne la vitesse de déplacement du fantôme selon son état.
     *
     * @return Vitesse en pixels par seconde.
     */
    public int getSpeed() {
        return state.getSpeed();
    }



}

