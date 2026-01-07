package fr.univartois.butinfo.r304.pacman.bonus;

import fr.univartois.butinfo.r304.pacman.model.PacmanGame;
import fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated;
import fr.univartois.butinfo.r304.pacman.model.animated.PacMan;
import fr.univartois.butinfo.r304.pacman.view.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * Le type BonusSuper
 *
 * @author pasca
 *
 * @version 0.1.0
 */
public class BonusSuper extends AbstractAnimated implements IBonus {
    /**
     * Liste des bonus composant le bonus super
     */
    private final List<IBonus> bonuses = new ArrayList<>();

    /**
     * Constructeur du bonus super
     * @param game Le jeu
     * @param xPosition La position en x
     * @param yPosition La position en y
     * @param sprite Le sprite
     * @param bonuses Les bonus à combiner
     */
    public BonusSuper(PacmanGame game, double xPosition, double yPosition, Sprite sprite, IBonus ... bonuses) {
        super(game, xPosition, yPosition, sprite);
    }

    /**
     * Ajoute un bonus à la liste des bonus composant ce super bonus.
     *
     * <p>Chaque bonus ajouté sera appliqué lorsque Pac-Man entrera en collision
     * avec ce super bonus.</p>
     *
     * @param bonus Le bonus à ajouter.
     */
    public void add(IBonus bonus) {
        bonuses.add(bonus);
    }

    /**
     * Applique tous les bonus composant le bonus super au PacMan
     * @param pacMan Le PacMan à qui appliquer les bonus
     */
    @Override
    public void applyBonus(PacMan pacMan) {
        for (IBonus bonus : bonuses) {
            bonus.applyBonus(pacMan);
        }
    }

    /**
     * Gère la collision entre ce bonus et Pac-Man.
     *
     * Lorsque Pac-Man entre en collision avec ce bonus, celui-ci délègue
     * le traitement au mécanisme de gestion des collisions de Pac-Man
     * via l’appel à {@code onCollisionWith(IBonus)}.
     *
     *
     * @param other L'objet Pac-Man en collision avec le bonus.
     */
    @Override
    public void onCollisionWith(PacMan other) {
        other.onCollisionWith((IBonus) this);
    }
}
