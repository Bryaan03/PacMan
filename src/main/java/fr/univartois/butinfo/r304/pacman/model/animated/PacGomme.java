/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 tibot
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;

import fr.univartois.butinfo.r304.pacman.model.PacmanGame;
import fr.univartois.butinfo.r304.pacman.view.Sprite;


/**
 * La classe {@code PacGomme} représente une pac-gomme dans le jeu Pac-Man.
 * <p>
 * Une pac-gomme est un objet fixe que Pac-Man peut manger lorsqu’il entre en
 * collision avec elle. Lorsqu’une pac-gomme est mangée, elle signale à la
 * partie en cours qu’elle a été consommée afin de mettre à jour le score et
 * éventuellement vérifier la fin du niveau.
 * </p>
 *
 * <p>
 * Les autres objets animés (fantômes, bonus, murs, etc.) n’ont aucun effet
 * particulier lors d’une collision avec une pac-gomme.
 * </p>
 *
 * @author tibot
 * @version 2025
 */
public class PacGomme extends AbstractAnimated {
    /**
     * Le nombre de points rapportés par une pac-gomme lorsqu’elle est mangée.
     */
	public static final int POINTS = 100;
	/**
     * Crée une nouvelle instance de {@code PacGomme}.
     *
     * @param game      La partie de Pac-Man à laquelle appartient cette pac-gomme.
     * @param xPosition La position horizontale de la pac-gomme sur la carte.
     * @param yPosition La position verticale de la pac-gomme sur la carte.
     * @param sprite    Le sprite utilisé pour représenter la pac-gomme à l’écran.
     */
	public PacGomme(PacmanGame game, double xPosition, double yPosition, Sprite sprite) {
		super(game, xPosition, yPosition, sprite);
	}

	/**
	 * Gère la collision avec Pacman.
	 *
	 * @param other l'objet Pacman.
	 */
	@Override
	public void onCollisionWith(PacMan other) {
		other.onCollisionWith(this);
	}
}
