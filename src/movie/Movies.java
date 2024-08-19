package movie;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
public class Movies {
    MovieDB db = new MovieDB();

    public Movies() throws SQLException {
    }

    public void createMovie(String name,String duration, int rating, int availableTickets) throws Exception {
        db.addMovieToDb(name,duration,rating,availableTickets);
    }
    public void showMovies() throws Exception {
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();
        String query = "select * from movie_details";
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            System.out.println("Movie Name : " + rs.getString(1));
            System.out.println("Duration : " + rs.getString(2));
            System.out.println("Rating : " + rs.getInt(3));
            System.out.println("Available Tickets : " + rs.getInt(4));
            System.out.println();
        }
    }


}
