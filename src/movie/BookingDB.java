package movie;

import java.sql.*;

public class BookingDB {
    public static void addBooking(String movieName,int noOfTickets,int id) throws SQLException {
        Connection con = DBConnection.getConnection();
        String query = "insert into booked_tickets values(?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1,movieName);
        ps.setInt(2,noOfTickets);
        ps.setInt(3,id);

        id++;
        ps.executeUpdate();
        con.close();
    }

    public static void cancelBooking(String movieName, int noOfTickets,int id) throws SQLException {
        Connection con = DBConnection.getConnection();
        String query = "delete from booked_tickets where id = " + id + " and Movie_Name = '" + movieName +"'" + " and Tickets = " + noOfTickets;
        Statement st = con.createStatement();
        int res = st.executeUpdate(query);
        if(res<=0){
            System.out.println("Tickets Not Booked");
        }else{
            String query1 = "select Available_Tickets from movie_details where Name = '" + movieName +"'";
            Statement sts = con.createStatement();
            ResultSet rs= sts.executeQuery(query1);
            rs.next();
            int available = rs.getInt(1);
            String deleteQuery = "update movie_details set Available_Tickets = ? where Name = ?";
            PreparedStatement ps = con.prepareStatement(deleteQuery);
            ps.setInt(1,available+noOfTickets);
            ps.setString(2,movieName);
            ps.executeUpdate();
            System.out.println("Tickets Canceled!");
        }
    }

    public static void showBooking() throws SQLException{
        Connection con = DBConnection.getConnection();
        String query = "select * from booked_tickets ORDER BY id DESC LIMIT 1";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            System.out.println("Movie Name : " + rs.getString(1));
            System.out.println("No of Tickets : " + rs.getInt(2));
            System.out.println("TICKET ID : " + rs.getInt(3));
        }
        System.out.println();
    }
}
