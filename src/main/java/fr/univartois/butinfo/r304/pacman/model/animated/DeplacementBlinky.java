package fr.univartois.butinfo.r304.pacman.model.animated;

/**
 * Le type DeplacementBlinky
 *
 * @author pasca
 *
 * @version 0.1.0
 */
public class DeplacementBlinky implements InterfaceDeplacementStrategie {
	 /**
     * Instance unique de la stratégie Blinky.
     */
    private static final DeplacementBlinky INSTANCE = new DeplacementBlinky();

    /**
     * Retourne l'unique instance de {@code DeplacementBlinky}.
     *
     * @return L'instance unique de la stratégie Blinky.
     */
    public static DeplacementBlinky getInstance() {
        return INSTANCE;
    }

    /**
     * Constructeur privé du Singleton.
     *
     * <p>
     * Empêche toute instanciation externe, garantissant que
     * seule l'instance unique est utilisée pour les fantômes Blinky.
     * </p>
     */
    private DeplacementBlinky() {}
    
    @Override
    public void deplacerGhost(Ghost ghost, PacMan pacman, long delta, int speed) {
        // Récupérer les positions actuelles
        double ghostX = ghost.getX();
        double ghostY = ghost.getY();
        double pacmanX = pacman.getX();
        double pacmanY = pacman.getY();

        // Calculer la différence de position
        double dx = pacmanX - ghostX;
        double dy = pacmanY - ghostY;

        // Initialiser la vitesse (pixels/sec)

        // Réinitialiser la vitesse actuelle
        ghost.setHorizontalSpeed(0);
        ghost.setVerticalSpeed(0);

        // Choisir l’axe de déplacement principal
        if (Math.abs(dx) > Math.abs(dy)) {
            // Se déplace horizontalement
            if (dx > 0) {
                ghost.setHorizontalSpeed(speed);
            } else if (dx < 0) {
                ghost.setHorizontalSpeed(-speed);
            }
        } else {
            // Se déplace verticalement
            if (dy > 0) {
                ghost.setVerticalSpeed(speed);
            } else if (dy < 0) {
                ghost.setVerticalSpeed(-speed);
            }
        }

        // Le déplacement sera exécuté automatiquement par la méthode onStep() du fantôme
    }
}
