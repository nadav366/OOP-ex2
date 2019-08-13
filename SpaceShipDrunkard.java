import java.util.Random;

/**
 * A class that is a drunkard space ship.
 * extends from a spaceship.
 *
 * @author Nadav
 */
public class SpaceShipDrunkard extends SpaceShip {
    /** Object for random choices */
    private Random randomGen = new Random();

    /** Count how many rounds the current mod has. */
    private int moodCaunter = 0;

    /** Sets which direction to use in the current mode. */
    private int turn = 0;

    /** Sets does accelerate, in the current state. */
    private boolean accel = false;

    /**
     * constructor. Running the constructor of SpaceShip.
     */
    public SpaceShipDrunkard(){
        this.SpaceShip();
    }

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game){
        selectMode();

        if (randomGen.nextBoolean() && moodCaunter < 50) teleport(); // do teleport
        getPhysics().move(accel,turn); // do move
        if (randomGen.nextBoolean() && moodCaunter < 50) fire(game);// get shoot

        super.doAction(game);
        moodCaunter--;
    }

    /* A function that updates the drunkard's mode of movement. */
    private void selectMode() {
        if (moodCaunter < 0){ // Select New Mode
            turn = randomGen.nextInt(3)-1;
            moodCaunter = 120;
            accel = false;
        }
        else if (moodCaunter < 50) { // Outside the mode
            turn = 0;
            accel = true;
        }
    }
}

