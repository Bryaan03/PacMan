/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 tibot
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;

import java.util.Random;


/**
 * La classe {DeplacementAleatoireStrategy} implémente une stratégie
 * de déplacement aléatoire pour les fantômes.
 *
 * Le fantôme choisit périodiquement une direction au hasard (horizontale ou verticale)
 * ainsi qu’une vitesse comprise entre 50 et 150 unités. Il ne se déplace jamais
 * en diagonale
 *
 * Ce comportement correspond au déplacement actuel des fantômes dans le jeu
 * de base, avant l’introduction des comportements spécifiques (Blinky, Pinky, etc.).
 *
 * @author Duquesne Tibot
 */
public class DeplacementAleatoireStrategie implements InterfaceDeplacementStrategie {
    /**
     * Générateur aléatoire pour choisir direction et vitesse.
     */
    private final Random random = new Random();

    /**
     * 
     */
    @Override
    public void deplacerGhost(Ghost ghost, PacMan pacman, long delta, int speed) {
        // Vitesse aléatoire 
        int vitesse = speed + random.nextInt(100);
        boolean horizontal = random.nextBoolean();

        if (horizontal) {
            // Déplacement horizontal : vers la gauche ou la droite
            ghost.setHorizontalSpeed(random.nextBoolean() ? vitesse : -vitesse);
            ghost.setVerticalSpeed(0);
        } else {
            // Déplacement vertical : vers le haut ou le bas
            ghost.setVerticalSpeed(random.nextBoolean() ? vitesse : -vitesse);
            ghost.setHorizontalSpeed(0);
        }

        // Met à jour la position du fantôme dans le labyrinthe
        ghost.onStep(delta);
    }

}

