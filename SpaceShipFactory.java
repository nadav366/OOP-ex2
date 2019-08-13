/**
 * A class that initializes all space ships, depending on the user's choice.
 *
 * @author Nadav
 */
public class SpaceShipFactory {

    /**
     * A function that accepts the user's choice, creates the maximum ships and saves them in the array.
     *
     * @param args An array of strings represents the ships the user has selected.
     * @return An array of ships, all the ships for the game.
     */
    public static SpaceShip[] createSpaceShips(String[] args) {
        SpaceShip[] allShips = new SpaceShip[args.length];
        for(int i=0; i<args.length; i++){
            switch (args[i].charAt(0)){
                case 'r':
                    allShips[i] = new SpaceShipRunner();
                    break;
                case 'b':
                    allShips[i] = new SpaceShipBasher();
                    break;
                case 'a':
                    allShips[i] = new SpaceShipAggressive();
                    break;
                case 'h':
                    allShips[i] = new SpaceShipHuman();
                    break;
                case 'd':
                    allShips[i] = new SpaceShipDrunkard();
                    break;
                case 's':
                    allShips[i] = new SpaceShipSpecial();
                    break;
            }
        }
        return allShips;
    }
}
