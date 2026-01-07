/**
 * Ce fichier fait partie du projet pac-man2.
 *
 * (c) 2025 pasca
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;


/**
 * Le type GhostInvulnerableState
 *
 * @author pasca
 *
 * @version 0.1.0
 */
public class GhostInvulnerableState implements GhostState {

    /**
     * Le fantôme associé à cet état.
     */
    private Ghost ghost;

    /**
     * Crée une nouvelle instance de {@code GhostInvulnerableState}.
     * Réinitialise le sprite du fantôme à son sprite original.
     *
     * @param ghost Le fantôme auquel cet état est appliqué.
     */
    public GhostInvulnerableState(Ghost ghost) {
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
        return this;
    }

    /**
     * Mise à jour de l'état à chaque étape du jeu.
     * Comme le fantôme est invulnérable, il reste dans cet état.
     *
     * @param delta Le temps écoulé depuis la dernière mise à jour (en millisecondes).
     * @return Toujours {@code true}, l'état ne change pas.
     */
    @Override
    public boolean onStep(long delta) {
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
     * @return La vitesse en pixels par seconde (60 ici).
     */
    @Override
    public int getSpeed() {
        return 60;
    }
}

