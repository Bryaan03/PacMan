package fr.univartois.butinfo.r304.pacman.model.map;

import fr.univartois.butinfo.r304.pacman.view.ISpriteStore;
import fr.univartois.dpprocessor.designpatterns.strategy.StrategyDesignPattern;
import fr.univartois.dpprocessor.designpatterns.strategy.StrategyParticipant;

/**
 * Le type IGenMap
 *
 * @author pasca
 *
 * @version 0.1.0
 */
@StrategyDesignPattern(strategy = IGenMap.class, participant = StrategyParticipant.INTERFACE)
public interface IGenMap {
    /**
     * @param width La longueur
     * @param height La largeur
     * @param spriteStore Le magasin de sprite
     * @return Une map générée
     */
    GameMap generateMap(int width, int height, ISpriteStore spriteStore);
}
