/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 tibot
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;

/**
 * Stratégie de déplacement d'Inky.
 * 
 * 
 * Inky adopte une approche plus imprévisible que Pinky :
 * au lieu de poursuivre directement Pac-Man, il tente de se positionner
 * légèrement à côté ou derrière lui, afin de participer à un effet d'encerclement.
 * 
 * 
 *
 * Le déplacement d’Inky est calculé à partir de la position et de la direction
 * actuelle de Pac-Man. Contrairement à Pinky qui anticipe la trajectoire
 * de Pac-Man vers l’avant, Inky se décale latéralement ou diagonalement
 * pour rendre ses mouvements moins prévisibles et plus difficiles à éviter.
 * 
 * 
 * 
 * Cette classe implémente l’interface InterfaceDeplacementStrategie
 * et est utilisée pour définir une stratégie de déplacement spécifique aux
 * fantômes de type Inky.
 * 
 * 
 * @author tibot
 * 
 * @version 1.0
 */
public class DeplacementInky implements InterfaceDeplacementStrategie {
	 /**
     * Instance unique de la stratégie Inky.
     */
    private static final DeplacementInky INSTANCE = new DeplacementInky();

    /**
     * Retourne l'unique instance de {@code DeplacementInky}.
     *
     * @return L'instance unique de la stratégie Inky.
     */
    public static DeplacementInky getInstance() {
        return INSTANCE;
    }

    /**
     * Constructeur privé du Singleton.
     *
     * <p>
     * Empêche toute instanciation externe, garantissant que
     * seule l'instance unique est utilisée pour les fantômes Inky.
     * </p>
     */
    private DeplacementInky() {}
    
    /**
     * Déplace le fantôme Inky en fonction de la position actuelle de Pac-Man.
     * 
     * 
     * Inky ne se contente pas de suivre Pac-Man : il calcule une position
     * légèrement décalée par rapport à la direction actuelle du joueur,
     * afin de simuler une tentative d’encerclement. Cela rend son comportement
     * complémentaire à celui de Pinky, qui anticipe la trajectoire directe.
     * 
     * 
     * @param ghost Le fantôme à déplacer (Inky).
     * @param pacman Le personnage de Pac-Man servant de référence.
     * @param delta Le temps écoulé depuis la dernière mise à jour, en millisecondes.
     */
    @Override
    public void deplacerGhost(Ghost ghost, PacMan pacman, long delta, int speed) {
        // Exemple simple : vise une position légèrement diagonale à Pac-Man
        double targetX = pacman.getX() + pacman.getHorizontalSpeed() * 2;
        double targetY = pacman.getY() + pacman.getVerticalSpeed() * 2;

        // Décalage latéral pour créer un effet d’encerclement
        if (pacman.getVerticalSpeed() < 0) {
            targetX += 20; // Pac-Man monte → Inky se place à droite
        } else if (pacman.getVerticalSpeed() > 0) {
            targetX -= 20; // Pac-Man descend → Inky se place à gauche
        }

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
