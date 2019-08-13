import java.awt.Image;
import oop.ex2.*;

/**
 * An abstract object for all space ships for the SpaceWars game.
 *
 * @author Nadav
 */
public abstract class SpaceShip{

    /** A physics object, which is all the physical data of the spacecraft. */
    private SpaceShipPhysics physics;

    /** The maximum amount of energy a ship can have. */
    private int maxEnergy;

    /** The amount of current energy the ship has. */
    protected int curEnergy;

    /** The amount of life the ship has. */
    private int health;

    /** Does the ship have shields at the moment */
    protected boolean isShieldsUp;

    /** Count the rounds after shooting. */
    protected int shotCaunter = 0;


    /**
     * constructor.
     * Initializes all variables to the initial state of the ship.
     */
    public void SpaceShip(){
        this.physics = new SpaceShipPhysics();
        this.maxEnergy = 210;
        this.curEnergy = 190;
        this.health = 22;
        this.isShieldsUp = false;
    }

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game){
        curEnergy = Math.max(curEnergy+1, maxEnergy);
        shotCaunter--;
    }

    /**
     * This method is called every time a collision with this ship occurs
     */
    public void collidedWithAnotherShip(){
        if (isShieldsUp){
            maxEnergy += 18;
            curEnergy += 18;
        }
        else gotHit();
    }

    /**
     * This method is called whenever a ship has died. It resets the ship's
     * attributes, and starts it at a new random position.
     */
    public void reset(){
        SpaceShip();
    }

    /**
     * Checks if this ship is dead.
     *
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {
        if (health < 0) return true;
        return false;
    }


    /**
     * Gets the physics object that controls this ship.
     *
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        return physics;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {
        if (!isShieldsUp){
            health --;
            maxEnergy = Math.max(0,maxEnergy-10);
            curEnergy = Math.min(curEnergy, maxEnergy);
        }
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     *
     * @return the image of this ship.
     */
    public Image getImage(){
        if (isShieldsUp) return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
        return GameGUI.ENEMY_SPACESHIP_IMAGE;
    }

    /**
     * Attempts to fire a shot.
     *
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
        if (curEnergy >= 19 && shotCaunter < 0){
            game.addShot(physics);
            curEnergy -= 19;
            shotCaunter = 7;
        }
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
        if (curEnergy >= 3) {
            isShieldsUp = true;
            curEnergy -= 3;
        }
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
        if (curEnergy >= 140) {
            physics = new SpaceShipPhysics();
            curEnergy -= 140;
        }
    }

    /**
     * A function that calculates the angle and distance to the nearest ship
     * @param game the game object.
     * @return Array of double. Place 0 is the distance to the ship in the vicinity.  1 is the angle to it.
     */
    public double[] getCloserInfo(SpaceWars game){
        SpaceShipPhysics closerPhysics = game.getClosestShipTo(this).getPhysics();

        double distans = physics.distanceFrom(closerPhysics);
        double angeleToCloser = physics.angleTo(closerPhysics);

        double[] allInfo = {distans, angeleToCloser};
        return allInfo;
    }
}
