/**
 * A class that is a Runner driver space ship.
 * extends from a spaceship.
 *
 * @author Nadav
 */
public class SpaceShipRunner extends SpaceShip {

    /**
     * constructor. Running the constructor of SpaceShip.
     */
    public SpaceShipRunner(){
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

        if (closerInfo[0] < 0.25 && Math.abs(closerInfo[1]) < 0.23) teleport(); // do teleport

        // do move
        int turn = 0;
        if (closerInfo[1] < 0) turn = 1;
        else if (closerInfo[1] > 0) turn = -1;
        getPhysics().move(true, turn);

        super.doAction(game);

    }
}
