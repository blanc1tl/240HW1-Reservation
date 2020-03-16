package reservation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
/*
 * Author:		Tyler Blanchard
 * Date:		9/6/2018
 * Description:		This program takes reservations from the user
 * 					and print out a diagram that showing which seat 
 * 					is taken and which seat is not
 */
public class ReservationMain {

	public static void main(String[] args) throws IOException {
		
		// variables
		int rowNumber;
		int secondRowNumber;
		String seatLetter;
		String secondSeatLetter;
		String reservationAmount;
		String closeTogether;
		String firstName;
		String lastName;
		int seatL = 0;
		int secondSeatL = 0;
		
		int[][] seatArray = new int[7][5];
		// set up an array
		List ar = new ArrayList <CustomerSeat> ();
		
		// get information of a text file
		File fi = new File("reservationList.txt");
		
		// scan objects from a text file
		Scanner sc = new Scanner(fi);
		
		// add delimiters when scanning from text file
		sc.useDelimiter("[\t|,|\n|\r]+");
		
		// add the text file's content on to the arrayList
		while(sc.hasNext()) {
			firstName = sc.next();
			lastName = sc.next();
			rowNumber = sc.nextInt();
			seatLetter = sc.next();
			
			CustomerSeat reservation = new CustomerSeat(firstName, lastName, rowNumber, seatLetter);
			
			if (seatLetter.equals("a"))
			{
				seatL = 0;
				
			}
			if (seatLetter.equals("b"))
			{
				seatL = 1;
				
			}if (seatLetter.equals("c"))
			{
				seatL = 2;
				
			}
			if (seatLetter.equals("d"))
			{
				seatL = 3;
				
			}
			
			// marks the place were the seat is reserved
			seatArray[rowNumber][seatL]= 1;
			
			ar.add(reservation);
		}
		
		// scan inputs from the user
		Scanner scnr = new Scanner(System.in);
		
		// determine if the customer wants to reserve one or two seats
		System.out.println("Do you want one or two seats (enter one or two):");
		reservationAmount = scnr.nextLine();

		if (reservationAmount.contains("one")) {
			System.out.println("Enter your first name.");
			firstName = scnr.nextLine();

			System.out.println("Enter your last name.");
			lastName = scnr.nextLine();
			
			System.out.println("Enter the seat letter you want (goes from 'a' to 'd').");
			seatLetter = scnr.nextLine();
			
			System.out.println("Enter the row number you want (goes from '1' to '5').");
			rowNumber = scnr.nextInt();
			
			CustomerSeat reservation = new CustomerSeat(firstName, lastName, rowNumber, seatLetter);
			
			if (seatLetter.equals("a"))
			{
				seatL = 0;
				
			}
			if (seatLetter.equals("b"))
			{
				seatL = 1;
				
			}if (seatLetter.equals("c"))
			{
				seatL = 2;
				
			}
			if (seatLetter.equals("d"))
			{
				seatL = 3;
				
			}
			
			// check to see if seat is reserved
			if (seatArray[rowNumber][seatL] == 1) {
				System.out.println("Seat is already reserved");
			}
			else {
				ar.add(reservation);
				seatArray[rowNumber][seatL] = 1;
				System.out.println("Your seat is reserved");
			}
			
		}
		else if (reservationAmount.contains("two")) {
			System.out.println("Enter your first name.");
			firstName = scnr.nextLine();

			System.out.println("Enter your last name.");
			lastName = scnr.nextLine();

			System.out.println("Enter the seat letter for the first seat you want (goes from 'a' to 'd').");
			seatLetter = scnr.nextLine();

			System.out.println("Enter the row number for the first seat you want (goes from '1' to '5').");
			rowNumber = scnr.nextInt();
			
			CustomerSeat reservation = new CustomerSeat(firstName, lastName, rowNumber, seatLetter);
			
			if (seatLetter.equals("a"))
			{
				seatL = 0;
				
			}
			if (seatLetter.equals("b"))
			{
				seatL = 1;
				
			}if (seatLetter.equals("c"))
			{
				seatL = 2;
				
			}
			if (seatLetter.equals("d"))
			{
				seatL = 3;
				
			}
			
			// check to see if the first seat is reserved
			if (seatArray[rowNumber][seatL] == 1) {
				System.out.println("Seat is already reserved");
			}
			else {
				ar.add(reservation);
				seatArray[rowNumber][seatL] = 1;
				System.out.println("Your first seat has been reserved. Pick the second");
				System.out.println("");
			}
			
			System.out.println("Enter the row number for the second seat you want (goes from '1' to '5').");
			secondRowNumber = scnr.nextInt();
			
			System.out.println("Enter the seat letter for the second seat you want (goes from 'a' to 'd').");
			secondSeatLetter = scnr.next();
			
			CustomerSeat secondReservation = new CustomerSeat(firstName, lastName, secondRowNumber, secondSeatLetter);
			
			// seat a seat letter for the second reservation
			if (secondSeatLetter.equals("a"))
			{
				secondSeatL = 0;
				
			}
			if (secondSeatLetter.equals("b"))
			{
				secondSeatL = 1;
				
			}if (secondSeatLetter.equals("c"))
			{
				secondSeatL = 2;
				
			}
			if (secondSeatLetter.equals("d"))
			{
				secondSeatL = 3;
				
			}
			
			// check to see if the second seat is reserved
			if (seatArray[secondRowNumber][secondSeatL] == 1) {
				System.out.println("Seat is already reserved");
			}
			else {
				// test to see if seat are next to each other. If not ask if they want to cancel second seat reservation
				if (secondRowNumber != rowNumber || (secondSeatLetter.length() != seatLetter.length() - 1 || secondSeatLetter.length() != seatLetter.length() + 1)) {
					System.out.println("The two seat are not next to eachother.");
					System.out.println("Do you still want to reserve the second seat(yes or no)?");
					closeTogether = scnr.next();
					
					if (closeTogether.contains("yes")) {
						seatArray[rowNumber][secondSeatL] = 1;
						ar.add(secondReservation);
						System.out.println("Your seocnd seat is reserved.");
					}
					// cancel both seat reservations
					else {
						System.out.println("Both seat reservations has been canceled.");
						ar.remove(reservation);
						seatArray[rowNumber][seatL] = 0;
					}
				}
				else {
					seatArray[rowNumber][secondSeatL] = 1;
					ar.add(secondReservation);
					System.out.println("Your second seat is reserved.");
				}
			}
		}
		else {
			// if they don't type one or two into the program
			System.out.println("You didn't type one or two. Restart the program");	
		}
		
		System.out.println("");
		System.out.println("Here is a diagram of the seating (X means unavailable and 0 means available) ");
		System.out.println("");
		
		//print out a diagram that shows which seat is taken and which seat is not
		for (int row = 2; row < seatArray.length; row++) {
		    for (int seat = 1; seat < seatArray[row].length; seat++) {
		        System.out.print(" ");
		        if (seatArray[row - 1][seat - 1] == 0) {
		            System.out.print("0");
		        } else {
		            System.out.print("X");
		        }
		    }
		    System.out.println("");
		} 

	}

}
