package fr.univartois.butinfo.r304.pacman.level;

import java.util.List;

import fr.univartois.butinfo.r304.pacman.model.animated.Ghost;
import fr.univartois.butinfo.r304.pacman.model.animated.PacMan;
import fr.univartois.butinfo.r304.pacman.model.map.GameMap;
import fr.univartois.butinfo.r304.pacman.view.ISpriteStore;

/**
 * Le type ILevel
 *
 * @author pasca
 *
 * @version 0.1.0
 */
public interface ILevel {
	/**
	 * @param width La longueur
	 * @param height La hauteur
	 * @param store Le magasin de sprite
	 * @return La map
	 */
	GameMap createMap(int width, int height, ISpriteStore store);
	/**
	 * @return La liste des bonuses
	 */
	List<String> getBonuses();
	/**
	 * @param ghosts La liste des fantômes
	 * @param player Le joueur
	 */
	void configureGhosts(List<Ghost> ghosts, PacMan player);
	/**
	 * @return Le prochain level
	 */
	ILevel changeLevel();
}
