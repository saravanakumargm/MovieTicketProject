package movie;

import java.sql.*;

public class MovieDB {

    public void addMovieToDb(String name, String duration, int rating, int availableTickets) throws Exception{
       // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb","root","");
        PreparedStatement ps = connection.prepareStatement("insert into movie_details values(?,?,?,?)");
        ps.setString(1,name);
        ps.setString(2,duration);
        ps.setInt(3,rating);
        ps.setInt(4,availableTickets);
        ps.executeUpdate();
        connection.close();
    }

    public boolean isAvailable(String movieName,int noOfTickets) throws SQLException {
        Connection con = DBConnection.getConnection();
        String query = "SELECT Available_Tickets FROM `movie_details` WHERE Name = '" + movieName + "'";
        Statement st = con.createStatement();
        ResultSet rs= st.executeQuery(query);
        rs.next();
        int available = rs.getInt(1);
        return noOfTickets<available;
    }

    public static void setTickets(String movieName,int noOfTikets) throws SQLException {
        Connection con = DBConnection.getConnection();
        String query1 = "select Available_Tickets from movie_details where Name = '" + movieName +"'";
        Statement st = con.createStatement();
        ResultSet rs= st.executeQuery(query1);
        rs.next();
        int available = rs.getInt(1);
        String query = "update movie_details set Available_Tickets = ? where Name = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1,available-noOfTikets);
        ps.setString(2,movieName);
        ps.executeUpdate();
        con.close();
    }

    public static int getID() throws SQLException {
        Connection con = DBConnection.getConnection();
        String query= "select id from movie_id";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        return rs.getInt(1);
    }

    public static void setID() throws SQLException {
        Connection con = DBConnection.getConnection();
        String query= "select id from movie_id";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        int id = rs.getInt(1);
        int newId = id+1;
        String setQuery = "update movie_id set id = "+ newId;
        st.executeUpdate(setQuery);
    }
}
