/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 tibot
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;

/**
 * Stratégie de déplacement de Pinky.
 * 
 *
 * Cette classe implémente une stratégie de déplacement pour le fantôme Pinky.
 * Contrairement à Blinky qui poursuit directement Pac-Man, Pinky cherche à 
 * anticiper sa trajectoire en se positionnant quelques cases devant lui. 
 * 
 * 
 * 
 * Le point cible est calculé en fonction de la position actuelle de Pac-Man 
 * et de sa direction (déduite de sa vitesse horizontale et verticale). 
 * Cela donne l’impression que Pinky tente d’encercler Pac-Man 
 * en lui coupant la route.
 * 
 * 
 *
 * Cette classe implémente l’interface {@link InterfaceDeplacementStrategie}, 
 * utilisée pour définir différents comportements de déplacement 
 * pour les fantômes du jeu.
 * 
 * 
 * @author tibot
 * @version 1.0
 */
public class DeplacementPinky implements InterfaceDeplacementStrategie {
	 /**
     * Instance unique de la stratégie Pinky.
     */
    private static final DeplacementPinky INSTANCE = new DeplacementPinky();

    /**
     * Retourne l'unique instance de {@code DeplacementPinky}.
     *
     * @return L'instance unique de la stratégie Pinky.
     */
    public static DeplacementPinky getInstance() {
        return INSTANCE;
    }

    /**
     * Constructeur privé du Singleton.
     *
     * <p>
     * Empêche toute instanciation externe, garantissant que
     * seule l'instance unique est utilisée pour les fantômes Pinky.
     * </p>
     */
    private DeplacementPinky() {}
    
    /**
     * Déplace le fantôme Pinky en fonction de la position et de la direction de Pac-Man.
     * 
     *
     * Pinky calcule une position cible située environ deux cases 
     * devant Pac-Man selon la direction actuelle de celui-ci, 
     * puis ajuste sa vitesse horizontale ou verticale 
     * pour s’en approcher progressivement.
     * 
     * 
     * @param ghost Le fantôme à déplacer (ici Pinky).
     * @param pacman Le personnage de Pac-Man, dont la position et la direction sont utilisées comme référence.
     * @param delta Le temps écoulé depuis la dernière mise à jour (en millisecondes).
     */
    @Override
    public void deplacerGhost(Ghost ghost, PacMan pacman, long delta, int speed) {
        // On se place 2 cases devant Pac-Man selon sa direction
        double targetX = pacman.getX() + pacman.getHorizontalSpeed() * 2;
        double targetY = pacman.getY() + pacman.getVerticalSpeed() * 2;

        double dx = targetX - ghost.getX();
        double dy = targetY - ghost.getY();

        if (Math.abs(dx) > Math.abs(dy)) {
            ghost.setHorizontalSpeed(dx > 0 ? speed : -speed);
            ghost.setVerticalSpeed(0);
        } else {
            ghost.setVerticalSpeed(dy > 0 ? speed : -speed);
            ghost.setHorizontalSpeed(0);
        }

        ghost.onStep(delta);
        
        
        
    }
}
