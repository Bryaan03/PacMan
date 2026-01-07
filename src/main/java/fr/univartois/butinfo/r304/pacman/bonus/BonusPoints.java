package fr.univartois.butinfo.r304.pacman.bonus;

import fr.univartois.butinfo.r304.pacman.model.PacmanGame;
import fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated;
import fr.univartois.butinfo.r304.pacman.model.animated.PacMan;
import fr.univartois.butinfo.r304.pacman.model.animated.PointsState;
import fr.univartois.butinfo.r304.pacman.view.Sprite;

/**
 * Le type BonusPoints
 *
 * @author pasca
 *
 * @version 0.1.0
 */
public class BonusPoints extends AbstractAnimated implements IBonus {
    /**
     * Crée une nouvelle instance de AbstractAnimated.
     *
     * @param game      Le jeu dans lequel l'objet animé évolue.
     * @param xPosition La position en x initiale de l'objet animé.
     * @param yPosition La position en y initiale de l'objet animé.
     * @param sprite    L'instance de {@link Sprite} représentant l'objet animé.
     */
    public BonusPoints(PacmanGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
    }

    /**
     * Applique le bonus au PacMan
     * @param pacMan Le PacMan à qui appliquer le bonus
     */
    @Override
    public void applyBonus(PacMan pacMan) { pacMan.changeState(new PointsState(pacMan));}

    /**
     * Gère la collision entre ce bonus et Pac-Man.
     *
     * Lorsque Pac-Man entre en collision avec ce bonus, celui-ci délègue
     * le traitement au mécanisme de gestion des collisions de Pac-Man
     * via l’appel à {@code onCollisionWith(IBonus)}.
     *
     *
     * @param other L'objet Pac-Man en collision avec le bonus.
     */
    @Override
    public void onCollisionWith(PacMan other) {
        other.onCollisionWith((IBonus) this);
    }
}
