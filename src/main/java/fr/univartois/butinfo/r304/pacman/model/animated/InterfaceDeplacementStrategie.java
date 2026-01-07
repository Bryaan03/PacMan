package fr.univartois.butinfo.r304.pacman.model.animated;

/**
 * Le type InterfaceDeplacementStrategie
 *
 * @author pasca
 *
 * @version 0.1.0
 */
public interface InterfaceDeplacementStrategie {
    /**
     * L’interface {DeplacementFantomeStrategy} définit le contrat
     * que doivent respecter toutes les stratégies de déplacement des fantômes
     * dans le jeu Pac-Man.
     *
     * Elle permet de séparer la logique de déplacement (poursuite, fuite,
     * aléatoire, etc.) de la classe {@link Ghost} afin de pouvoir changer
     * facilement le comportement d’un fantôme sans modifier son code.
     *
     * @author tibot duquesne
     * @param ghost Le fantôme
     * @param pacman Le joueur
     * @param delta Delta
     * @param speed La vitesse
     */
    void deplacerGhost(Ghost ghost, PacMan pacman, long delta, int speed);
}
