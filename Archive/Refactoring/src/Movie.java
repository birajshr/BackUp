
public class Movie {
	private String _title;
	private Price _price;
	public static final int NEW_RELEASE = 1;
	public static final int REGULAR = 0;
	public static final int CHILDRENS = 2;

	public Movie(String title, int priceCode) {
        _title = title;
        setPriceCode(priceCode);
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String t) {
		this._title = t;
	}

	public int getPriceCode() {
		return _price.getPriceCode();
	}

	public void setPriceCode(int arg) {
		   switch (arg) {

	        case REGULAR:

	            _price = new RegularPrice();

	            break;

	        case CHILDRENS:

	            _price = new ChildrensPrice();

	            break;

	        case NEW_RELEASE:

	            _price = new NewReleasePrice();

	            break;

	        default:

	            throw new IllegalArgumentException("Incorrect Price Code");

	        }
	}
	
	/**
	 * @deprecated Use {@link Price#getCharge(Movie,int)} instead
	 */
	double getCharge(int daysRented) {
		return _price.getCharge(daysRented);
	}

	int getFrequentRenterPoints(int priceCode, int daysRented) {
//			int frequentRenterPoints = 0;
//			// add frequent renter points
//			frequentRenterPoints ++;
//			// add bonus for a two day new release rental
//			if ((priceCode == Movie.NEW_RELEASE)
//			        && daysRented > 1) frequentRenterPoints++;
//			return frequentRenterPoints;
		
		return _price.getFrequentRenterPoints(daysRented);
	}	
		
}