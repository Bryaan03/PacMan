/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 pasca
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;

import fr.univartois.butinfo.r304.pacman.model.IAnimated;
import fr.univartois.butinfo.r304.pacman.bonus.IBonus;
import fr.univartois.butinfo.r304.pacman.model.PacmanGame;
import fr.univartois.butinfo.r304.pacman.view.Sprite;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * La classe {@code PacMan} représente le personnage principal du jeu Pac-Man.
 * Elle hérite de {@link AbstractAnimated} et gère les propriétés liées
 * à la santé et au score du joueur, ainsi que les interactions avec d'autres entités animées.
 *
 * @author pasca
 * @version 0.1.0
 */
public class PacMan extends AbstractAnimated {

    /**
     * La santé actuelle du Pac-Man (points de vie).
     */
    private IntegerProperty health;

    /**
     * Le score actuel du Pac-Man.
     */
    private IntegerProperty score;

    /**
     * L'état courant du Pac-Man (vulnérable ou invulnérable).
     */
    private PacManState state;

    /**
     * La direction désirée par le joueur (utilisée pour le déplacement).
     */
    private String desiredDirection = "LEFT";

    /**
     * Crée une nouvelle instance de {@code PacMan}.
     *
     * @param game      Le jeu auquel appartient ce personnage.
     * @param xPosition La position horizontale initiale du Pac-Man.
     * @param yPosition La position verticale initiale du Pac-Man.
     * @param sprite    Le sprite représentant le Pac-Man à l'écran.
     */
    public PacMan(PacmanGame game, double xPosition,
                  double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
        health = new SimpleIntegerProperty();
        score = new SimpleIntegerProperty();
        this.health.set(3); // santé initiale
        this.score.set(0);    // score initial
        this.state = new VulnerableState(this); // état initial vulnérable
    }

    /**
     * Définit la direction souhaitée par le joueur.
     *
     * @param desiredDirection La direction désirée ("UP", "DOWN", "LEFT", "RIGHT").
     */
    public void setDesiredDirection(String desiredDirection) {
        this.desiredDirection = desiredDirection;
    }

    /**
     * Mise à jour de Pac-Man à chaque étape du jeu.
     * Gère le déplacement en fonction de la direction désirée et de l'état actuel.
     *
     * @param delta Le temps écoulé depuis la dernière mise à jour (en millisecondes).
     * @return {@code true} si le déplacement a été effectué correctement.
     */
    @Override
    public boolean onStep(long delta) {
        state.onStep(delta);

        int speed = getSpeed();
        if (canMoveTo(desiredDirection, 5)) {
            switch (desiredDirection) {
                case "LEFT" -> { setHorizontalSpeed(-speed); setVerticalSpeed(0); }
                case "RIGHT" -> { setHorizontalSpeed(speed); setVerticalSpeed(0); }
                case "UP" -> { setVerticalSpeed(-speed); setHorizontalSpeed(0); }
                case "DOWN" -> { setVerticalSpeed(speed); setHorizontalSpeed(0); }
                default -> throw new IllegalArgumentException("Invalid direction: " + desiredDirection);
            }
        }

        return super.onStep(delta);
    }

    /**
     * Retourne la santé actuelle du Pac-Man.
     *
     * @return La valeur de la santé (entre 0 et 100).
     */
    public int getHealth() {
        return health.get();
    }

    /**
     * Retourne la vitesse de déplacement actuelle du Pac-Man, dépendant de son état.
     *
     * @return La vitesse en pixels par seconde.
     */
    public int getSpeed() {
        return state.getSpeed();
    }

    /**
     * Retourne le score actuel du Pac-Man.
     *
     * @return Le score du joueur.
     */
    public int getScore() {
        return score.get();
    }

    /**
     * Retourne la propriété JavaFX associée à la santé du Pac-Man.
     * Utile pour le data binding dans l'interface graphique.
     *
     * @return La propriété {@link IntegerProperty} représentant la santé.
     */
    public IntegerProperty getHealthProperty() {
        return health;
    }

    /**
     * Retourne la propriété JavaFX associée au score du Pac-Man.
     * Utile pour le data binding dans l'interface graphique.
     *
     * @return La propriété {@link IntegerProperty} représentant le score.
     */
    public IntegerProperty getScoreProperty() {
        return score;
    }

    /**
     * Gère une collision générique entre Pac-Man et un autre objet animé.
     * Cette méthode délègue le traitement de la collision à l'autre entité.
     *
     * @param other L'autre entité animée avec laquelle la collision a eu lieu.
     */
    @Override
    public void onCollisionWith(IAnimated other) {
        other.onCollisionWith(this);
    }

    /**
     * Change l'état actuel du Pac-Man.
     *
     * @param state1 Le nouvel état à appliquer.
     */
    public void changeState(PacManState state1) {
        this.state = state1;
    }

    /**
     * Gère la collision du Pac-Man avec un fantôme.
     * Applique les dégâts et change éventuellement l'état.
     *
     * @param other Le fantôme avec lequel la collision a eu lieu.
     */
    @Override
    public void onCollisionWith(Ghost other) {
        this.state.makeDamage();
        this.changeState(this.state.changeState());
    }

    /**
     * Inflige des dégâts à Pac-Man.
     * Si la santé tombe à 0, la partie se termine.
     */
    public void makeDamage() {
        health.set(getHealth() - 1);
        if (getHealth() <= 0)
            game.playerIsDead();
    }

    /**
     * Gère la collision du Pac-Man avec une Pac-Gomme.
     * Augmente le score et notifie le jeu que la gomme a été mangée.
     *
     * @param gum La Pac-Gomme ramassée par Pac-Man.
     */
    @Override
    public void onCollisionWith(PacGomme gum) {
        if (state.isVulnerable()) {
            score.set(score.get() + state.getPoints());
            game.pacGumEaten(gum);
        }
    }

    /**
     * Gère la collision du Pac-Man avec une Mega-Gomme.
     * Augmente le score et déclenche l'effet des Mega-Gommes sur les fantômes.
     *
     * @param gum La Mega-Gomme ramassée par Pac-Man.
     */
    @Override
    public void onCollisionWith(MegaGomme gum) {
        if (state.isVulnerable()) {
            score.set(score.get() + MegaGomme.POINTS);
            game.megaGumEaten(gum);
        }
    }

    /**
     * Gère la collision du Pac-Man avec un bonus.
     * @param bonus Le bonus ramassée par Pac-Man.
     */
    @Override
    public void onCollisionWith(IBonus bonus) {
        if (state.isVulnerable()) {
            bonus.applyBonus(this);
            game.pacGumEaten((IAnimated)bonus);
        }
    }

    /**
     * Méthode appelée lorsque Pac-Man mange un fantôme.
     * Augmente le score de 10 points.
     */
    public void onEatenGhost() {
        score.set(score.get() + 10);
    }
}