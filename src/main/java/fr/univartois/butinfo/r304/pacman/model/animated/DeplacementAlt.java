package fr.univartois.butinfo.r304.pacman.model.animated;

/**
 * Le type DeplacementAlt
 *
 * @author pasca
 *
 * @version 0.1.0
 */
public class DeplacementAlt implements InterfaceDeplacementStrategie {
	 /**
     * Instance unique de la stratégie alternative.
     */
    private static final DeplacementAlt INSTANCE = new DeplacementAlt();

    /**
     * Retourne l'unique instance de {@code DeplacementAlt}.
     *
     * @return L'instance unique de la stratégie alternative.
     */
    public static DeplacementAlt getInstance() {
        return INSTANCE;
    }

    /**
     * Constructeur privé du Singleton.
     *
     * <p>
     * Empêche toute instanciation externe, garantissant que
     * seule l'instance unique est utilisée dans le jeu.
     * </p>
     */
    private DeplacementAlt() {}
	
    @Override
    public void deplacerGhost(Ghost ghost, PacMan pacman, long delta, int speed) {
        // Calculer la différence de position
        double dx = (pacman.getX() - ghost.getX());
        double dy = (pacman.getY() - ghost.getY());

        // Réinitialiser la vitesse actuelle
        ghost.setHorizontalSpeed(0);
        ghost.setVerticalSpeed(0);

        boolean close = Math.abs(dx) + Math.abs(dy) < 200;

        boolean horizontal = Math.abs(dx) > Math.abs(dy);

        int direction = (close? -1 : 1) * speed;

        if (horizontal) {
            ghost.setHorizontalSpeed(dx > 0 ? direction : -direction);
        } else {
            ghost.setVerticalSpeed(dy > 0 ? direction : -direction);
        }
    }
}
