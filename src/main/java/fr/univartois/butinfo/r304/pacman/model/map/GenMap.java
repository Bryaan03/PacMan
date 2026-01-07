/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 bryan
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.map;

import fr.univartois.butinfo.r304.pacman.view.ISpriteStore;
import fr.univartois.dpprocessor.designpatterns.strategy.StrategyDesignPattern;
import fr.univartois.dpprocessor.designpatterns.strategy.StrategyParticipant;

/**
 * Le type GenMap
 *
 * @author bryan
 *
 * @version 0.1.0
 */
@StrategyDesignPattern(strategy = IGenMap.class, participant = StrategyParticipant.IMPLEMENTATION)
public class GenMap implements IGenMap {
    /**
     * Instance unique du générateur de cartes.
     * <p>
     * Elle est créée une seule fois au chargement de la classe, assurant
     * un Singleton « eager initialization ».
     * </p>
     */
    private static final GenMap INSTANCE = new GenMap();

    /**
     * Retourne l'unique instance de {@code GenMap}.
     *
     * @return L'instance unique du générateur de cartes.
     */
    public static GenMap getInstance() {
        return INSTANCE;
    }

    /**
     * Constructeur privé du Singleton.
     *
     * <p>
     * Son accès est restreint afin d’empêcher la création d’autres instances
     * en dehors de la classe. L’unique manière d’obtenir une instance est
     * d’utiliser {@link #getInstance()}.
     * </p>
     */
    private GenMap() {}
    
    /**
     * Genere une carte avec des murs tout autour et un chemin a l'interieur
     * 
     * @param width largeur de la carte
     * @param height hauteur de la carte
     * @param spriteStore Les Sprites
     * 
     * @return la carte generée
     */
    @Override
    public GameMap generateMap(int width, int height, ISpriteStore spriteStore) {
        GameMap map = new GameMap(height, width);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1)
                    map.setAt(i, j, new Cell(new Wall(spriteStore.getSprite("wall"))));
                else
                    map.setAt(i, j, new Cell((spriteStore.getSprite("path"))));
            }
        }

        return map;
    }

}
