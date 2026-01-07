package fr.univartois.butinfo.r304.pacman.model;

import fr.univartois.butinfo.r304.pacman.bonus.IBonus;
import fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated;
import fr.univartois.butinfo.r304.pacman.model.animated.Ghost;
import fr.univartois.butinfo.r304.pacman.model.animated.GhostSpeedState;
import fr.univartois.butinfo.r304.pacman.model.animated.PacMan;
import fr.univartois.butinfo.r304.pacman.view.Sprite;

/**
 * Le type BonusGhostSpeed
 *
 * @author pasca
 *
 * @version 0.1.0
 */
public class BonusGhostSpeed extends AbstractAnimated implements IBonus {
    /**
     * Crée une nouvelle instance de AbstractAnimated.
     *
     * @param game      Le jeu dans lequel l'objet animé évolue.
     * @param xPosition La position en x initiale de l'objet animé.
     * @param yPosition La position en y initiale de l'objet animé.
     * @param sprite    L'instance de {@link Sprite} représentant l'objet animé.
     */
    protected BonusGhostSpeed(PacmanGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
    }

    /**
     * Applique le bonus au PacMan
     * @param pacMan Le PacMan à qui appliquer le bonus
     */
    @Override
    public void applyBonus(PacMan pacMan) {
     // Intentionally left blank
    }

    /**
     * Applique le bonus au Ghost
     * @param ghost Le Ghost à qui appliquer le bonus
     */
    public void applyBonus(Ghost ghost) {ghost.changeState(new GhostSpeedState(ghost));}
}
