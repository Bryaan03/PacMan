package fr.univartois.butinfo.r304.pacman.level;

import fr.univartois.butinfo.r304.pacman.model.animated.*;

import java.util.List;

/**
 * Le type LevelMedium
 *
 * @author pasca
 *
 * @version 0.1.0
 */
public class LevelMedium extends AbstractLevel {

    /**
     * L’unique instance du niveau intermédiaire (pattern Singleton).
     */
    private static final LevelMedium INSTANCE = new LevelMedium();

    /**
     * Retourne l’unique instance du niveau intermédiaire.
     *
     * @return L'instance Singleton de {@code LevelMedium}.
     */
    public static LevelMedium getInstance() {
        return INSTANCE;
    }

    /**
     * Constructeur privé empêchant toute instanciation externe.
     */
    private LevelMedium() {
        super(List.of("BonusPoints", "BonusSpeed"));
    }

    /**
     * Configure les fantômes pour le niveau intermédiaire.
     *
     * <p>Contrairement au niveau facile, les fantômes utilisent ici des stratégies
     * de déplacement différentes, inspirées de celles du jeu Pac-Man original :</p>
     *
     * <ul>
     *     <li>{@link DeplacementInky}    pour le fantôme avec ID 0</li>
     *     <li>{@link DeplacementAlt}     pour le fantôme avec ID 1</li>
     *     <li>{@link DeplacementPinky}   pour le fantôme avec ID 2</li>
     *     <li>{@link DeplacementBlinky}  pour le fantôme avec ID 3</li>
     * </ul>
     *
     * <p>Chaque fantôme reçoit également une action périodique définie par
     * {@code deplacerFantome(16, player)} puis est démarré via {@code start()}.</p>
     *
     * @param ghosts Liste des fantômes à configurer.
     * @param player Le joueur {@link PacMan}, utilisé par certaines stratégies.
     */
    @Override
    public void configureGhosts(List<Ghost> ghosts, PacMan player) {
        DeplacementBlinky deplacementBlinky = DeplacementBlinky.getInstance();
        DeplacementPinky deplacementPinky = DeplacementPinky.getInstance();
        DeplacementInky deplacementInky = DeplacementInky.getInstance();
        DeplacementAlt deplacementAlt = DeplacementAlt.getInstance();

        for (Ghost g : ghosts) {
            switch (g.getID()) {
                case 0:
                    g.setStrategie(deplacementInky);
                    break;
                case 1:
                    g.setStrategie(deplacementAlt);
                    break;
                case 2:
                    g.setStrategie(deplacementPinky);
                    break;
                case 3:
                    g.setStrategie(deplacementBlinky);
                    break;
                default:
                    throw new IllegalArgumentException("ID de fantôme inconnu : " + g.getID());
            }

            g.setAction(() -> g.deplacerFantome(16, player));
            g.start();
        }
    }

    /**
     * Indique le niveau suivant à charger après completion du niveau intermédiaire.
     *
     * <p>Ici, le niveau suivant correspond au niveau 3, récupéré via
     * {@link LevelFactory#getLevel(int)}.</p>
     *
     * @return Le prochain niveau à jouer.
     */
    @Override
    public ILevel changeLevel() {
        return LevelFactory.getLevel(3);
    }

}