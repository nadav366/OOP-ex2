import oop.ex2.GameGUI;
import java.awt.*;

/**
 * A class that is a human driver space ship.
 * extends from a spaceship.
 *
 * @author Nadav
 */
public class SpaceShipHuman extends SpaceShip {

    /**
     * constructor. Running the constructor of SpaceShip.
     */
    public SpaceShipHuman(){
        this.SpaceShip();
    }

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game){
        GameGUI curGUI = game.getGUI();

        if (curGUI.isTeleportPressed()) teleport(); // do teleport

        // do move
        int turn = 0;
        if (curGUI.isRightPressed()) turn-= 1;
        if (curGUI.isLeftPressed()) turn += 1;
        getPhysics().move(curGUI.isUpPressed(), turn);

        // Wearing shield
        isShieldsUp = false;
        if (curGUI.isShieldsPressed()) shieldOn();

        if (curGUI.isShotPressed()) fire(game); // get shoot
        super.doAction(game);
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     *
     * @return the image of this ship.
     */
    public Image getImage(){
        if (isShieldsUp) return GameGUI.SPACESHIP_IMAGE_SHIELD;
        return GameGUI.SPACESHIP_IMAGE;
    }
}
