/**
 * Ce fichier fait partie du projet pac-man2.
 *
 * (c) 2025 pasca
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;


/**
 * Le type GhostVulnerableState
 *
 * @author pasca
 *
 * @version 0.1.0
 */
public class GhostVulnerableState implements GhostState {

    /**
     * Les skins utilisés pour représenter le fantôme dans cet état.
     */
    private String[] skins = {"ghosts/hurt/1", "ghosts/hurt/2"};

    /**
     * Durée de l'état vulnérable en millisecondes.
     */
    private static final int VULNERABLE_DURATION = 5000; // (en MS)

    /**
     * Le fantôme associé à cet état.
     */
    private Ghost ghost;

    /**
     * Temps écoulé depuis l'entrée dans cet état.
     */
    private double elapsedTime = 0;

    /**
     * Crée une nouvelle instance de {@code GhostVulnerableState}.
     * Initialise les skins du fantôme pour cet état.
     *
     * @param ghost Le fantôme auquel cet état est appliqué.
     */
    public GhostVulnerableState(Ghost ghost) {
        this.ghost = ghost;
        ghost.setSkins(skins);
    }

    /**
     * Mise à jour de l'état à chaque étape du jeu.
     * Incrémente le temps écoulé et passe à l'état suivant si la durée est dépassée.
     *
     * @param delta Le temps écoulé depuis la dernière mise à jour (en millisecondes).
     * @return {@code true} si le fantôme reste dans cet état, {@code false} si l'état change.
     */
    @Override
    public boolean onStep(long delta) {
        elapsedTime += delta;

        if (elapsedTime >= VULNERABLE_DURATION) {
            ghost.changeState(new GhostEndVulnerableState(ghost));
            return false;
        }

        return true;
    }

    /**
     * Retourne l'état vers lequel le fantôme passe après un changement éventuel.
     *
     * @return Un nouvel état {@code GhostRunState} représentant le comportement normal du fantôme.
     */
    @Override
    public GhostState changeState() {
        return new GhostRunState(ghost);
    }

    /**
     * Gère les dégâts causés à Pac-Man lorsque le fantôme est mangé.
     * Change temporairement la stratégie de déplacement du fantôme et met à jour le score du joueur.
     *
     * @param pacman Le Pac-Man qui a mangé le fantôme.
     */
    @Override
    public void makeDamage(PacMan pacman) {
        InterfaceDeplacementStrategie strategie = DeplacementAlt.getInstance();
        ghost.setOldStrategie(ghost.getStrategie());
        ghost.setStrategie(strategie);
        pacman.onEatenGhost();
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