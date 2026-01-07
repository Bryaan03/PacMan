package fr.univartois.butinfo.r304.pacman.bonus;

import fr.univartois.butinfo.r304.pacman.model.animated.PacMan;

/**
 * Le type IBonus
 *
 * @author pasca
 *
 * @version 0.1.0
 */
public interface IBonus {

    /** Applique le bonus au PacMan
     * @param pacMan Le PacMan à qui appliquer le bonus
     */
    void applyBonus(PacMan pacMan);
}
