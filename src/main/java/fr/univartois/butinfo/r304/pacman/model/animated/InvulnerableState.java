package fr.univartois.butinfo.r304.pacman.model.animated;

/**
 * Le type InvulnerableState
 *
 * @author pasca
 *
 * @version 0.1.0
 */
public class InvulnerableState extends AbstractPacManState {

    /** Durée de l'état invulnérable en millisecondes */
    private static final int DURATION = 5000;

    /**
     * Constructeur de l'état invulnérable
     *
     * @param pacman Le PacMan associé à cet état
     */
    public InvulnerableState(PacMan pacman) {
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
     * Ne fait pas de dégâts car Pac-Man est invulnérable
     */
    @Override
    public void makeDamage() {
     // Intentionally left blank
    }

    /**
     * Indique que Pac-Man est Vulnérable
     *
     * @return false, car Pac-Man est invulnérable dans cet état
     */
    @Override
    public boolean isVulnerable() {
        return false;
    }

    /**
     * Retourne la vitesse de Pac-Man en état invulnérable
     *
     * @return La vitesse en pixels par seconde
     */
    @Override
    public int getSpeed() {
        return 200; // Vitesse augmentée en état invulnérable
    }
}
