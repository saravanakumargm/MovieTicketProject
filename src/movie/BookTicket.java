package movie;
import java.util.Scanner;
public class BookTicket {
    public static void main(String[] args) throws Exception {
        Movies movies = new Movies();
        Scanner sc = new Scanner(System.in);
        Booking booking = new Booking();
        movies.showMovies();
       try{
           while (true) {
               showMenu();
               int userResponse = sc.nextInt();
               booking.execute(userResponse,booking);
           }
       }catch (Exception e){
           System.out.println(e);
       }
    }
    public static void showMenu(){
        System.out.println(" 1.Book Ticket \n2.Cancel Ticket \n3.Show Movies \n4.Exit");
    }

}
