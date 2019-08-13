/**
 * A class that is a basher space ship.
 * extends from a spaceship.
 *
 * @author Nadav
 */
public class SpaceShipBasher extends SpaceShip {

    /**
     * constructor. Running the constructor of SpaceShip.
     */
    public SpaceShipBasher(){
        this.SpaceShip();
    }

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game){
        double [] closerInfo = getCloserInfo(game);

        // do move
        int turn = 0;
        if (closerInfo[1] < 0) turn = -1;
        else if (closerInfo[1] > 0) turn = 1;
        getPhysics().move(true, turn);

        // Wearing shields
        isShieldsUp = false;
        if (closerInfo[0] <= 0.19) shieldOn();

        super.doAction(game);
    }
}
