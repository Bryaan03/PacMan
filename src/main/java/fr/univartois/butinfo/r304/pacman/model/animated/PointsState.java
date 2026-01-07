/**
 * Ce fichier fait partie du projet pac-man2.
 *
 * (c) 2025 tibot
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;

/**
 * La classe {@code VulnerableState} représente l'état "vulnérable" du Pac-Man.
 *
 * Dans cet état, Pac-Man peut subir des dégâts lorsqu'il entre en contact avec
 * un ennemi. Ce comportement est généralement temporaire : une fois l'état
 * terminé, Pac-Man redevient invulnérable.
 *
 * Cette classe implémente l'interface {@link PacManState}, ce qui permet de
 * gérer le comportement de Pac-Man selon le principe du **pattern State**.
 *
 * @author tibot
 * @version 0.1.0
 */
public class PointsState extends AbstractPacManState {

    /**
     * Durée totale durant laquelle cet état reste actif (en millisecondes).
     */
    private static final int DURATION = 5000;

    /**
     * Crée un nouvel état de gain de points pour Pac-Man.
     *
     * @param pacman L'instance de Pac-Man à laquelle cet état est appliqué.
     */
    public PointsState(PacMan pacman) {
        super(pacman);
    }

    public int getDuration() {
        return DURATION;
    }

    /**
     * Retourne le nombre de points que Pac-Man gagne dans cet état.
     *
     * @return Le gain de points spécifique (ici : 150).
     */
    @Override
    public int getPoints() {
        return 150;
    }
}
