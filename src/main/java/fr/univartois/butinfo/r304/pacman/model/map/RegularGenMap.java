/**
 * Ce fichier fait partie du projet pac-man.
 *
 * (c) 2025 pasca
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.map;

import fr.univartois.butinfo.r304.pacman.view.ISpriteStore;
import fr.univartois.dpprocessor.designpatterns.decorator.DecoratorDesignPattern;

/**
 * Le type RegularGenMap
 *
 * @author pasca
 *
 * @version 0.1.0
 */
@DecoratorDesignPattern(IGenMap.class)
public class RegularGenMap implements IGenMap {
    /**
     * La map
     */
    IGenMap map;
    
    /**
     * Crée une nouvelle instance de RegularGenMap.
     * @param map La map
     */
    public RegularGenMap(IGenMap map) {
        this.map = map;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.map.IGenMap#generateMap(int, int, fr.univartois.butinfo.r304.pacman.view.ISpriteStore)
     */
    @Override
    public GameMap generateMap(int width, int height, ISpriteStore spriteStore) {
        GameMap generatedMap = map.generateMap(width, height, spriteStore);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i%2==0 && (j>1 && j < width-2))
                    generatedMap.setAt(i, j, new Cell(new Wall(spriteStore.getSprite("wall"))));
            }
        }
        return generatedMap;
    }
}

