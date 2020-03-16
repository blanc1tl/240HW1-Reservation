package reservation;

public class CustomerSeat {
	
	private String seatLetter;
	private int rowNumber;
	private String firstName;
	private String lastName;
	
	// add a constructor
	public CustomerSeat(String firstName, String lastName, int rowNumber, String seatLetter) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.rowNumber = rowNumber;
		this.seatLetter = seatLetter;
		
	}
	
}
