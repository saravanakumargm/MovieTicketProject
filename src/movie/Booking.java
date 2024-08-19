package movie;

import java.sql.SQLException;
import java.util.Scanner;

class Booking{
    Movies movies = new Movies();
    int[][] matrix = new int[5][5];
    Scanner sc  = new Scanner(System.in);
    protected int noOfTickets=0;
    protected String movieName = "hulk";


    Booking() throws SQLException {
    }

    //private int ticketNo = 1;
    public void execute(int userResponse,Booking booking) throws Exception {
        switch (userResponse){
            case 1:
                MovieDB movies1 = new MovieDB();
                System.out.println("Enter Movie Name : ");
                movieName = sc.next();
                System.out.println("Enter number of tickets : ");
                this.noOfTickets = sc.nextInt();
                movieName = movieName.toLowerCase();
                if(movies1.isAvailable(movieName,noOfTickets)){
                    BookingDB.addBooking(movieName,noOfTickets,MovieDB.getID());
                    MovieDB.setID();
                    System.out.println("Processing...");
                    Thread.sleep(1000);
                    System.out.println("Tickets Booked!");
                    BookingDB.showBooking();
                    MovieDB.setTickets(movieName,noOfTickets);
                }else {
                    System.out.println("Sorry tickets sold out!");
                }
                break;
            case 2:
                System.out.println("Enter Movie Name : ");
                movieName = sc.next();
                System.out.println("Enter number of tickets : ");
                noOfTickets = sc.nextInt();
                System.out.println("Enter ID : ");
                int id = sc.nextInt();
                BookingDB.cancelBooking(movieName,noOfTickets,id);
                break;
            case 3:
                movies.showMovies();
                break;
            case 4:
                System.exit(0);
            default:
                System.out.println("Invalid input");
        }
    }
}
