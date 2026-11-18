
public class Predator extends Entity {
    
	public Predator(Location location) {
        super(location);
    }
	
    @Override
    public char getSymbol() {
        return 'P';
    }
    
    @Override 
    public void takeTurn(World world){
    	
    }

}
