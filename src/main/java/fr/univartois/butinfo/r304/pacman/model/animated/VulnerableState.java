/**
 * Ce fichier fait partie du projet pac-man2.
 *
 * (c) 2025 tibot
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;


/**
 * Le type VulnerableState
 *
 * @author tibot
 *
 * @version 0.1.0
 */
public class VulnerableState extends AbstractPacManState {

    /**
     * Crée un nouvel état vulnérable pour le Pac-Man donné.
     *
     * @param pacman Le Pac-Man auquel cet état est associé.
     */
    public VulnerableState(PacMan pacman) {
        super(pacman);
    }

    public boolean onStep() {
        return true;
    }

    public int getDuration() {
        return 0;
    }
}
