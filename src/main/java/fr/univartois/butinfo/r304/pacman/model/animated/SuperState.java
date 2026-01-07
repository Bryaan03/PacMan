package fr.univartois.butinfo.r304.pacman.model.animated;

/**
 * Le type SuperState
 *
 * @author pasca
 *
 * @version 0.1.0
 */
public class SuperState extends AbstractPacManState {

    /** Durée de l'état invulnérable en millisecondes */
    private static final int DURATION = 3000;

    /**
     * Constructeur de l'état invulnérable
     *
     * @param pacman Le PacMan associé à cet état
     */
    public SuperState(PacMan pacman) {
         super(pacman);
    }

    public int getDuration() {
        return DURATION;
    }

    /**
     * Change l'état de Pac-Man
     */
    @Override
    public PacManState changeState() {
        return this;
    }

    /**
     * Retourne la vitesse de Pac-Man en état Super
     *
     * @return La vitesse en pixels par seconde
     */
    @Override
    public int getSpeed() {
        return 125; // Vitesse augmentée
    }

    /**
     * Retourne les points gagnés en état super
     *
     * @return Le nombre de points
     */
    @Override
    public int getPoints() { return 125; }
}
