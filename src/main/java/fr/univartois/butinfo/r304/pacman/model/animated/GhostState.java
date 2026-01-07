/**
 * Ce fichier fait partie du projet pac-man2.
 *
 * (c) 2025 pasca
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;


/**
 * Le type GhostState
 *
 * @author pasca
 *
 * @version 0.1.0
 */
public interface GhostState {
    /**
     * @return Le state du ghost
     */
    GhostState changeState();
    /**
     * Effectue le dégât
     * @param pacman Le joueur
     */
    void makeDamage(PacMan pacman);
    /**
     * Fonction qui gère le mouvement
     * @param delta Delta
     * @return un boolean (true si il a bouge)
     */
    boolean onStep(long delta);
    /**
     * @return La vitesse du joueur
     */
    int getSpeed();
}

