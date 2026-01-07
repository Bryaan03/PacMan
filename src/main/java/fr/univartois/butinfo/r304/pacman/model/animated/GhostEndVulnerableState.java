/**
 * Ce fichier fait partie du projet pac-man2.
 *
 * (c) 2025 pasca
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;

/**
 * Le type GhostEndVulnerableState
 *
 * @author pasca
 *
 * @version 0.1.0
 */
public class GhostEndVulnerableState implements GhostState {

    /**
     * Les skins du fantôme pendant cette phase de fin de vulnérabilité.
     */
    private String[] skins = {"ghosts/hurt/1", "ghosts/afraid/1"};

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
     * Crée une nouvelle instance de {@code GhostEndVulnerableState}.
     * Définit les skins correspondants au fantôme.
     *
     * @param ghost Le fantôme auquel cet état est appliqué.
     */
    public GhostEndVulnerableState(Ghost ghost) {
        this.ghost = ghost;
        ghost.setSkins(skins);
    }

    /**
     * Mise à jour de l'état à chaque étape du jeu.
     * Quand le temps écoulé dépasse la durée de fin de vulnérabilité, 
     * le fantôme change d'état pour redevenir invulnérable.
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
     * Gestion des dégâts infligés à Pac-Man.
     * Dans cet état, le fantôme ne peut pas infliger de dégâts.
     *
     * @param pacman Le Pac-Man concerné.
     */
    @Override
    public void makeDamage(PacMan pacman) {
     // Intentionally left blank
    }

    /**
     * Retourne l'état vers lequel le fantôme passe après un changement éventuel.
     * Ici, il s'agit de l'état de course normal.
     *
     * @return Une nouvelle instance de {@code GhostRunState}.
     */
    @Override
    public GhostState changeState() {
        return new GhostRunState(ghost);
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
