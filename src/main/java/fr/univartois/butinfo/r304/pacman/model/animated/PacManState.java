package fr.univartois.butinfo.r304.pacman.model.animated;


/**
 * Interface représentant l'état de Pac-Man
 */
public interface PacManState {
    /**
     * Effectue le dégât
     */
    void makeDamage();
    /**
     * @return L'état de pacman
     */
    PacManState changeState();
    /**
     * Fonction qui gère le mouvement
     * @param delta Delta
     * @return true si le mouvement est effectué
     */
    boolean onStep(long delta);
    /**
     * @return true si pacman est vulnérable
     */
    boolean isVulnerable();
    /**
     * @return La vitesse
     */
    int getSpeed();
    /**
     * @return Les points
     */
    int getPoints();
}
