package fr.univartois.butinfo.r304.pacman.level;
/**
 * Le type LevelFactory
 *
 * @author pasca
 *
 * @version 0.1.0
 */
public class LevelFactory {

	private LevelFactory() {
		throw new IllegalStateException("Utility class");
	}

	// Récupérer le level du jeu
	/**
	 * @param number Le level choisi
	 * @return Le level choisi
	 */
	public static ILevel getLevel(int number) {
		return switch (number) {
		case 2 -> LevelMedium.getInstance();
		case 3 -> LevelHard.getInstance();
		default -> LevelEasy.getInstance();
		};
	}
}
