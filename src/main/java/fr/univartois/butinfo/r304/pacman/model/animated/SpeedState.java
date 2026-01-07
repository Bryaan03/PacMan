package fr.univartois.butinfo.r304.pacman.model.animated;

/**
 * Le type SpeedState
 *
 * @author pasca
 *
 * @version 0.1.0
 */
public class SpeedState extends AbstractPacManState {

    /** Durée de l'état invulnérable en millisecondes */
    private static final int DURATION = 5000;

    /**
     * Constructeur de l'état invulnérable
     *
     * @param pacman Le PacMan associé à cet état
     */
    public SpeedState(PacMan pacman) {
        super(pacman);
    }

    public int getDuration() {
        return DURATION;
    }

    /**
     * Retourne la vitesse de Pac-Man en état invulnérable
     *
     * @return La vitesse en pixels par seconde
     */
    @Override
    public int getSpeed() {
        return 200; // Vitesse augmentée
    }
}
