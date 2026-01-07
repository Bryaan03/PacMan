package fr.univartois.butinfo.r304.pacman.level;

import fr.univartois.butinfo.r304.pacman.model.map.GameMap;
import fr.univartois.butinfo.r304.pacman.model.map.GenMap;
import fr.univartois.butinfo.r304.pacman.view.ISpriteStore;

import java.util.List;

/**
 * Le type LevelEasy
 *
 * @author pasca
 *
 * @version 0.1.0
 */
public class LevelEasy extends AbstractLevel {

    /**
     * Instance unique du niveau facile (pattern Singleton).
     */
    private static final LevelEasy INSTANCE = new LevelEasy();

    /**
     * Retourne l'unique instance du niveau facile.
     *
     * @return L'instance Singleton de {@code LevelEasy}.
     */
    public static LevelEasy getInstance() {
        return INSTANCE;
    }

    /**
     * Constructeur privé empêchant l’instanciation externe (Singleton).
     */
    private LevelEasy() {
        super(List.of("BonusPoints"));
    }

    /**
     * Génère la carte du niveau facile.
     *
     * <p>La carte est produite à l’aide du générateur {@link GenMap}, en tenant
     * compte de la taille des sprites afin de calculer les dimensions exactes de la grille.</p>
     *
     * @param width  Largeur totale en pixels disponible pour la carte.
     * @param height Hauteur totale en pixels disponible pour la carte.
     * @param store  Le magasin de sprites permettant de connaître la taille d’un sprite.
     *
     * @return Une instance de {@link GameMap} correspondant au niveau facile.
     */
    @Override
    public GameMap createMap(int width, int height, ISpriteStore store) {
        GenMap genMap = GenMap.getInstance();
        return genMap.generateMap(width / store.getSpriteSize(), height / store.getSpriteSize(), store);
    }

    @Override
    public ILevel changeLevel() {
        return LevelFactory.getLevel(2);
    }

}