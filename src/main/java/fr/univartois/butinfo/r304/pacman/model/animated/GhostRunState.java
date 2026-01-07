/**
 * Ce fichier fait partie du projet pac-man2.
 *
 * (c) 2025 pasca
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;

/**
 * Le type GhostRunState
 *
 * @author pasca
 *
 * @version 0.1.0
 */
public class GhostRunState implements GhostState{
    /**
     * Les skins des ghosts dans cette état
     */
    private String[] skins = {"ghosts/afraid/1", "ghosts/afraid/2"};
    
    /**
     * ghost le fantôme
     */
    private Ghost ghost;
    
    /**
     * Durée de l'état RUN
     */
    private static final int RUN_DURATION = 5000;
    
    /**
     * L'attribut elapsedTime (durée écoulé)
     */
    private double elapsedTime = 0;
    
    /**
     * Crée une nouvelle instance de GhostRunState.
     * @param ghost Le fantôme
     */
    public GhostRunState(Ghost ghost) {
        this.ghost = ghost;
        
        ghost.setSkins(skins);
    }
    
    @Override
    public boolean onStep(long delta) {
        elapsedTime += delta;

        if (elapsedTime >= RUN_DURATION) {
            InterfaceDeplacementStrategie strategie = ghost.getOldStrategie();
            if (strategie != null) {
                ghost.setStrategie(strategie);
                ghost.setOldStrategie(null);
            }
            ghost.changeState(new GhostInvulnerableState(ghost));
            return false;
        }

        return true;
    }
    
    @Override
    public void makeDamage(PacMan pacman) {
     // Intentionally left blank
    }
    
    @Override
    public GhostState changeState() {
        return this;
    }
    
    @Override
    public int getSpeed() {
        return 80;
    }
}

