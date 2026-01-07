package fr.univartois.butinfo.r304.pacman.level;

import fr.univartois.butinfo.r304.pacman.model.animated.DeplacementBlinky;
import fr.univartois.butinfo.r304.pacman.model.animated.Ghost;
import fr.univartois.butinfo.r304.pacman.model.animated.PacMan;
import fr.univartois.butinfo.r304.pacman.model.map.GameMap;
import fr.univartois.butinfo.r304.pacman.model.map.GenMap;
import fr.univartois.butinfo.r304.pacman.model.map.RegularGenMap;
import fr.univartois.butinfo.r304.pacman.view.ISpriteStore;

import java.util.List;

public abstract class AbstractLevel implements ILevel {

    /**
     * Les bonus disponibles dans le niveau.
     */
    protected final List<String> bonuses;

    /**
     * Constructeur de la classe AbstractLevel.
     *
     * @param bonuses La liste des bonus disponibles dans le niveau.
     */
    protected AbstractLevel(List<String> bonuses) {
        this.bonuses = bonuses;
    }

    /**
     * Crée une carte de jeu.
     *
     * @param width La largeur de la carte.
     * @param height La hauteur de la carte.
     * @param store Le magasin de sprites.
     * @return La carte de jeu générée.
     */
    public GameMap createMap(int width, int height, ISpriteStore store) {
        GenMap genMap = GenMap.getInstance();
        RegularGenMap regularMap = new RegularGenMap(genMap);
        return regularMap.generateMap(width / store.getSpriteSize(), height / store.getSpriteSize(), store);
    }

    /**
     * Obtient la liste des bonus disponibles dans le niveau.
     *
     * @return La liste des bonus.
     */
    public List<String> getBonuses() {
        return bonuses;
    }

    /**
     * Configure les fantômes pour le niveau.
     *
     * @param ghosts La liste des fantômes.
     * @param player Le joueur PacMan.
     */
    public void configureGhosts(List<Ghost> ghosts, PacMan player) {
        DeplacementBlinky deplacementBlinky = DeplacementBlinky.getInstance();

        for (Ghost g : ghosts) {
            g.setStrategie(deplacementBlinky);
            g.setAction(() -> g.deplacerFantome(16, player));
            g.start();
        }
    }

    /**
     * Change le niveau.
     *
     * @return Le niveau actuel.
     */
    public ILevel changeLevel() {
        return this;
    }
}
