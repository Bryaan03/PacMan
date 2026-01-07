package fr.univartois.butinfo.r304.pacman.level;

import java.util.List;

/**
 * Le type LevelHard
 *
 * @author pasca
 *
 * @version 0.1.0
 */
public class LevelHard extends AbstractLevel{

    /**
     * Instance unique du niveau difficile (pattern Singleton).
     */
	private static final LevelHard INSTANCE = new LevelHard();
	
    /**
     * Retourne l’instance unique du niveau difficile.
     *
     * @return L’unique instance de {@code LevelHard}.
     */
	public static LevelHard getInstance() {
		return INSTANCE;
	}
	
    /**
     * Constructeur privé empêchant l’instanciation externe du niveau difficile.
     */
	private LevelHard() {
		super(List.of("BonusPoints", "BonusSpeed", "BonusSuper"));
	}
}