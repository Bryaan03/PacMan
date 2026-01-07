package fr.univartois.butinfo.r304.pacman.model.animated;

public abstract class AbstractPacManState implements PacManState {

    /**
     * Le PacMan associé à cet état.
     */
    protected PacMan pacman;

    /**
     * Le temps écoulé dans cet état en millisecondes.
     */
    private double elapsedTime = 0;


    /**
     * Constructeur de l'état abstrait du PacMan.
     *
     * @param pacman
     */
    protected AbstractPacManState(PacMan pacman) {
        this.pacman = pacman;
    }

    /**
     * Inflige des dégâts au PacMan.
     */
    public void makeDamage(){
        pacman.makeDamage();
    }

    /**
     * Durée de l'état en millisecondes.
     * @return
     */
    public abstract int getDuration();

    /**
     * Gère le passage du temps pour l'état.
     *
     * @param delta Delta
     * @return
     */
    public boolean onStep(long delta) {
        elapsedTime += delta;
        int duration = getDuration();
        if (elapsedTime >= duration) {
            pacman.changeState(new VulnerableState(pacman));
            return false;
        }
        return true;
    }

    /**
     * Change l'état du PacMan.
     *
     * @return
     */
    public PacManState changeState() {
        return new InvulnerableState(pacman);
    }

    /**
     * Indique si le PacMan est vulnérable.
     *
     * @return
     */
    public boolean isVulnerable() {
        return true;
    }

    /**
     * Vitesse du PacMan dans cet état.
     *
     * @return
     */
    public int getSpeed() {
        return 100;
    }

    /**
     * Points obtenus en mangeant un fantôme dans cet état.
     *
     * @return
     */
    public int getPoints() {
        return 100;
    }
}
