/**
 * A class that is a Special driver space ship.
 * extends from a spaceship.
 *
 * @author Nadav
 */
public class SpaceShipSpecial extends SpaceShip {

    /**  A variable that determines whether the ship is currently in attack mood */
    private boolean attackMood = true;

    /**
     * constructor. Running the constructor of SpaceShip.
     */
    public SpaceShipSpecial() {
        this.SpaceShip();
    }

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        double [] closerInfo = getCloserInfo(game);

        // Change mode
        if (curEnergy > 40) attackMood = true;
        if (curEnergy < 20) attackMood = false;

        // do move
        if (attackMood) {
            int turn = 0;
            if (closerInfo[1] < 0) turn = -1;
            else if (closerInfo[1] > 0) turn = 1;
            getPhysics().move(true, turn);
        }
        else {
            int turn = 0;
            if (closerInfo[1] < 0) turn = 1;
            else if (closerInfo[1] > 0) turn = -1;
            getPhysics().move(true, turn);
        }

        // Wearing shields
        isShieldsUp = false;
        if (closerInfo[0] <= 0.10) shieldOn();

        // get shoot
        if (Math.abs(closerInfo[1]) <= 0.21 &&  closerInfo[0] <0.2){
            fire(game);
            shotCaunter = 0;
        }

        super.doAction(game);
    }
}