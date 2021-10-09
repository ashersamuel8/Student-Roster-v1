import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.InputMismatchException;


public class TuitionManager {
	
	private Scanner inputObj;				
	private String inputString;				
	private StringTokenizer input;			
	String command;							
	public static Roster roster;	


	public void run() {
		
		System.out.println("Tuition Manager starts running.");
		System.out.println();
		
		roster = new Roster();
		
	
		while(true) {
			
			try {	
			
				inputObj = new Scanner(System.in);			
		
				inputString = inputObj.nextLine();	
				
				//Input String is Tokenized by the delimiter ","
				input = new StringTokenizer(inputString, ",");		
		 		
				//gets the input command
				command = input.nextToken();
		 		
				if(command.equals("Q")) break;
				
				readInput();
		 
			} catch(Exception e){
			
				System.out.println("Missing Information");
			
			}
		 
		} 
		
		System.out.println("Tuition Manager Terminated");		
		
	}
	
	
	private void readInput(){
		
		switch(command) {
		case "AR":							
				 addResidentStudent();
				 break;
		
		case "AN":							
				 addNonResidentStudent();
				 break;
				 
		case "AT":						
				 addTriStateStudent();
				 break;
	
		case "AI":							
				 addInternationalStudent();
				 break;
	
		case "R":							
				 removeResidentStudent();
				 break;
			
		
		case "C":							
				 calculateTuition();
				 break;
		
		case "T":							
				 payTuition();
				 break;
				 
		case "S":
				 studyAbroad();
				 break;
				
		case "F":		
				 setFinancialAid();
				 break;
				
		case "P":
				 print();
				 break;
			
		case "PN":
				 printByStudentName();
				 break;
				
		case "PT":
				 printByPaymentDate();
				 break;
			
		default:
			
			System.out.println("Invalid command!");
			
		}
		
		return;
	}
	
	
	private void addResidentStudent() {
		
		try {
			
			String studentName = input.nextToken();
			String majorString = input.nextToken();
			int credits = Integer.parseInt(input.nextToken());
			
			if(credits < 3) {
				System.out.println("Minimum credit hours is 3");
				return;
			}
			else if(credits > 24) {
				System.out.println("Credit hours exceed the maximum 24");
				return;
			}
			
			Major major = getMajor(majorString);

			if(major == null) {
				System.out.println("Enter a valid major");
				return;
			}
			
			Resident newStudent = new Resident(studentName, major, credits);
			
			if (roster.add(newStudent)) { 
				System.out.println("Student added.");
			}
			else {
				System.out.println("Student already exists in the roster.");
			}
			
		
		} catch(NumberFormatException e) {
			
			System.out.println("Enter a valid number of credits");
			
		} catch(InputMismatchException e) {
			
			System.out.println("Missing data in command line");
			
		}
		
		return;
		
	}
	
	private void addNonResidentStudent() {
		
		try {
			
			String studentName = input.nextToken();
			String majorString = input.nextToken();
			int credits = Integer.parseInt(input.nextToken());
			
			if(credits < 3) {
				System.out.println("Minimum credit hours is 3");
				return;
			}
			else if(credits > 24) {
				System.out.println("Credit hours exceed the maximum 24");
				return;
			}
			
			Major major = getMajor(majorString);

			if(major == null) {
				System.out.println("Enter a valid major");
				return;
			}
			
			NonResident newStudent = new NonResident(studentName, major, credits);
			
			if (roster.add(newStudent)) { 
				System.out.println("Student added.");
			}
			else {
				System.out.println("Student already exists in the roster.");
			}
			
		
		} catch(NumberFormatException e) {
			
			System.out.println("Enter a valid number of credits");
			
		} catch(InputMismatchException e) {
			
			System.out.println("Missing data in command line");
			
		}
		
		return;
		
	}
	
	private void addTriStateStudent() {
	

		try {
			
			String studentName = input.nextToken();
			String majorString = input.nextToken();
			int credits = Integer.parseInt(input.nextToken());
			String state = input.nextToken();
			
			if(credits < 3) {
				System.out.println("Minimum credit hours is 3");
				return;
			}
			else if(credits > 24) {
				System.out.println("Credit hours exceed the maximum 24");
				return;
			}
			

			if (!state.toUpperCase().equals("NY") || !state.toUpperCase().equals("CT")) {
				
				System.out.println("Enter a valid State");
				return;
				
			}
			
			Major major = getMajor(majorString);

			if(major == null) {
				System.out.println("Enter a valid major");
				return;
			}
			
			TriState newStudent = new TriState(studentName, major, credits, state.toUpperCase());
			
			if (roster.add(newStudent)) { 
				System.out.println("Student added.");
			}
			else {
				System.out.println("Student already exists in the roster.");
			}
			
		
		} catch(NumberFormatException e) {
			
			System.out.println("Enter a valid number of credits");
			
		} catch(InputMismatchException e) {
			
			System.out.println("Missing data in command line");
			
		}
		
		return;
		
	}
	
	private void addInternationalStudent() {

		try {
			
			String studentName = input.nextToken();
			String majorString = input.nextToken();
			int credits = Integer.parseInt(input.nextToken());
			boolean isStudyAbroad = false;
			
			if(credits < 3) {
				System.out.println("Minimum credit hours is 3");
				return;
			}
			else if(credits > 24) {
				System.out.println("Credit hours exceed the maximum 24");
				return;
			}
			
			Major major = getMajor(majorString);
			
			if(major == null) {
				System.out.println("Enter a valid major");
				return;
			}
			
			if(input.hasMoreTokens()) {
				
				String isStudyAbroadString = input.nextToken();
				
				if(isStudyAbroadString.toLowerCase().equals("true")) isStudyAbroad = true;
				
				else if (!isStudyAbroadString.toLowerCase().equals("false")) {
					System.out.println("Enter a valid Study abroad status");
					return;
					
				}
		
			}
			
			International newStudent = new International(studentName, major, credits, isStudyAbroad);
			
			
			if (roster.add(newStudent)) { 
				System.out.println("Student added.");
			}
			else {
				System.out.println("Student already exists in the roster.");
			}
			
		
		} catch(NumberFormatException e) {
			
			System.out.println("Enter a valid number of credits");
			
		} catch(InputMismatchException e) {
			
			System.out.println("Missing data in command line");
			
		}
		
		return;
		
	}
	
	private Major getMajor(String majorString) {
		
		Major major = null;
		
		if(majorString.toUpperCase().equals("CS")) major = Major.CS;
		
		else if(majorString.toUpperCase().equals("IT")) major = Major.IT;
		
		else if(majorString.toUpperCase().equals("BA")) major = Major.BA;
		
		else if(majorString.toUpperCase().equals("EE")) major = Major.EE;
		
		else if(majorString.toUpperCase().equals("ME")) major = Major.ME;
		
		return major;
		
		
	}
	
	private void removeResidentStudent() {
		
		try {
			
			String studentName = input.nextToken();
			String majorString = input.nextToken();
			Major major = getMajor(majorString);
			
			Student removeStudent = new Student(studentName, major);
				
			if(roster.remove(removeStudent)) System.out.println("Student removed");
			
			else System.out.println("Student doesnt exist");
					
			
		} catch(InputMismatchException e) {
			
			System.out.println("Missing data in command line");
			
		}
		
		
		
	}
	
	private void calculateTuition() {
		
		roster.calculateTuition();
		System.out.println("Calculation Completed");
		
	}
	
	private void payTuition() {
		
		try {
			String name = input.nextToken();
			Major major = getMajor(input.nextToken());
			double fees = Double.parseDouble(input.nextToken());
			Date paymentDate = new Date(input.nextToken());
		
			if(!paymentDate.isValid()) {
			
				System.out.println("Enter a valid Date");
				return;
			
			}
			
			if(fees == 0) System.out.println("Invalid payment amount");
		
			switch(roster.payTuition(name, major, fees, paymentDate)) {
			
				case 1:
					System.out.println("Amount is greater than amount due");
					break;
					
				case 0:
					System.out.println("Payment applied");
					break;
					
				case -1:
					System.out.println("Student does not exist");
					break;
			}
			
		} catch(Exception e) {
			
			System.out.println(e);
			
		}
	}
	
	private void studyAbroad() {
		try {
			
			String name = input.nextToken();
			Major major = getMajor(input.nextToken());
			boolean studyAbroadStatus = Boolean.parseBoolean(input.nextToken());
			
			
			
			switch(roster.setStudyAbroadStatus(name, major, studyAbroadStatus)) {
			
			case 1:
				System.out.println("Tuition Updated");
				break;
				
			case 0:
				System.out.println("Enter a new status");
				break;
				
			case -1:
				System.out.println("COuldnt find the international student");
				break;
		}
			
				
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	private void setFinancialAid() {
		
		try {
			
			String name = input.nextToken();
			Major major = getMajor(input.nextToken());
			double aid = Double.parseDouble(input.nextToken());
			

			switch(roster.calculateFinancialAid(name, major, aid)) {
			
			case 1:
				System.out.println("Tuition Updated");
				break;
				
			case 0:
				System.out.println("Financial Aid already Granted");
				break;
				
			case -1:
				System.out.println("Parttime students are not eligible for financial aid");
				break;
				
			case -2:
				System.out.println("Student does not exist");
				break;
				
			}
			
			
			
			
			
		} catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	private void print() {
		
		roster.print();
		
	}
	
	private void printByStudentName() {
		
		roster.printByStudentName();
		
	}
	
	private void printByPaymentDate() {
		
		roster.printByPaymentDate();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	
//	private void addCommand() {
//		
//		//creates the attributes of album
//		String newTitle = input.nextToken();
//		String newArtist = input.nextToken();
//		String newGenreString = input.nextToken();
//		Date newReleaseDate = new Date(input.nextToken());
//		
//		//Checks if the Date entered is Valid. 
//		if(!newReleaseDate.isValid()) {
//			System.out.println("Invalid Date!");
//			return;
//		}
//		
//		Genre newGenre;
//		
//		//Creates a Genre object based on the input value
//		
//		if (newGenreString.equals("Classical") || newGenreString.equals("classical")) {	
//			newGenre = Genre.Classical;				
//		}
//		else if (newGenreString.equals("Country") || newGenreString.equals("country")) {	
//			newGenre = Genre.Country;
//		}
//		else if (newGenreString.equals("Jazz") || newGenreString.equals("jazz")) {	
//			newGenre = Genre.Jazz;
//		}
//		else if (newGenreString.equals("Pop") || newGenreString.equals("pop")) {	
//			newGenre = Genre.Pop;
//		}
//		else {
//			newGenre = Genre.Unknown;
//		}
//		
//		//Instantiates a new Album Object called newAlbum
//		Album newAlbum = new Album(newTitle, newArtist, newGenre, newReleaseDate, true);
//		
//		//Adds the new album to the collection
//		if (collection.add(newAlbum)) { 
//			System.out.println(newAlbum.toString() + " >> added.");
//		}
//		else {
//			System.out.println(newAlbum.toString() + " >> is already in the collection.");
//		}
//		
//		return;
//		
//	}
//	
//	/**
//	 * Creates a new temporary album object with the input attributes and passes them to 
//	 * Collection.remove(Album album) method to remove the album from the collection
//	 */
//	private void deleteCommand() {
//		
//	
//		String title = input.nextToken();
//		String artist = input.nextToken();
//		Album delAlbum = new Album(title, artist);
//		
//		if (collection.remove(delAlbum)) {
//			System.out.println(title + "::" + artist + " >> deleted.");
//		}
//		else {
//			System.out.println(title + "::" + artist + " >> is not in the collection.");
//		}
//		
//		return;
//		
//	}
//	
//	/**
//	 * This method creates a new temporary album object with the given attributes 
//	 * and passes it to Collection.lend(Album album) method 
//	 */
//	private void lendCommand() {
//		
//		String title = input.nextToken();
//		String artist = input.nextToken();
//		Album lendAlbum = new Album(title, artist);
//		
//			
//			if (collection.lendingOut(lendAlbum)) {
//				System.out.println(title + "::" + artist + " >> lending out and set to not available.");
//			}
//			else {
//				System.out.println(title + "::" + artist + " >> is not available.");
//			}
//	}
//	
//	/**
//	 * This method creates a temporary album object with the given attributes 
//	 * and passes it to Collection.returnAlbum(Album album) method. 
//	 */
//	private void returnCommand() {
//		
//		String title = input.nextToken();
//		String artist = input.nextToken();
//		Album returningAlbum = new Album(title, artist);
//			
//			if (collection.returnAlbum(returningAlbum)) {
//				System.out.println(title + "::" + artist + " >> returing and set to available.");
//			}
//			else {
//				System.out.println(title + "::" + artist + " >> return cannot be completed.");
//			}
//		
//	}	
	
}
