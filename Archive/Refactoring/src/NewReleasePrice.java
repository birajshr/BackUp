
public class NewReleasePrice extends Price{

    public int getPriceCode() {

        return Movie.NEW_RELEASE;

    }

	public int getFrequentRenterPoints(int daysRented) {
		// TODO Auto-generated method stub
		if(daysRented > 1)
		{
			return 2;
		}
		else
			return 1;
	}
    
    
	@Override
	protected double getCharge(int daysRented) {
		//determine amounts for each line
	
	    double result = 0;
	
	    
	
	        result += daysRented * 3;
	
	        
	    return result;
	
	}

}