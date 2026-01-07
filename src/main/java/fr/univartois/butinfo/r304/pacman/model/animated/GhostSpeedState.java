package fr.univartois.butinfo.r304.pacman.model.animated;

/**
 * Le type GhostSpeedState
 *
 * @author pasca
 *
 * @version 0.1.0
 */
public class GhostSpeedState implements GhostState {

    /**
     * Le fantôme associé à cet état.
     */
    private Ghost ghost;

    /**
     * Durée totale de l'état de fin de vulnérabilité (en millisecondes).
     */
    private static final int END_VULNERABLE_DURATION = 3000;

    /**
     * Temps écoulé depuis le début de l'état.
     */
    private double elapsedTime = 0;

    /**
     * Crée une nouvelle instance de {@code GhostInvulnerableState}.
     * Réinitialise le sprite du fantôme à son sprite original.
     *
     * @param ghost Le fantôme auquel cet état est appliqué.
     */
    public GhostSpeedState(Ghost ghost) {
        this.ghost = ghost;
        ghost.setSprite(ghost.getOriginalSprite());
    }

    /**
     * Retourne l'état vers lequel le fantôme passe après un changement éventuel.
     * Dans cet état, le fantôme reste invulnérable, donc il retourne lui-même.
     *
     * @return L'instance actuelle de {@code GhostInvulnerableState}.
     */
    @Override
    public GhostState changeState() {
        return new GhostRunState(ghost);
    }

    /**
     * Mise à jour de l'état à chaque étape du jeu.
     * Quand le temps écoulé dépasse la durée de fin de vulnérabilité,
     * le fantôme change d'état pour redevenir invulnérable sans malus .
     *
     * @param delta Le temps écoulé depuis la dernière mise à jour (en millisecondes).
     * @return {@code false} si l'état doit changer, {@code true} sinon.
     */
    @Override
    public boolean onStep(long delta) {
        elapsedTime += delta;

        if (elapsedTime >= END_VULNERABLE_DURATION) {
            ghost.changeState(new GhostInvulnerableState(ghost));
            return false;
        }

        return true;
    }

    /**
     * Gère les dégâts infligés à Pac-Man si nécessaire.
     * Dans cet état, le fantôme peut encore infliger des dégâts normalement.
     *
     * @param pacman Le Pac-Man concerné par la collision.
     */
    @Override
    public void makeDamage(PacMan pacman) {
        ghost.makeDamage(pacman);
    }

    /**
     * Retourne la vitesse de déplacement du fantôme dans cet état.
     *
     * @return La vitesse en pixels par seconde (30 ici).
     */
    @Override
    public int getSpeed() {
        return 30;
    }
}
