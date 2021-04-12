import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.sql.*;

public class Flightdata {
    public static void main(String[] args) {

// Create Variable for the connection string
        String connectionUrl = "jdbc:sqlserver://localhost....XXXXXXXX";
// Declare JDBC Objects
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

// Establish the connection
            Class.forName(".com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
                conn = DriverManager.getConnection(connectionUrl);

// Create & Execute SQL Query to return the data
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);

            String SQL ("CREATE TABLE Flights (\n" +
                    " FlightId int, \n" +
                    " flight reservation varchar(6),\n" +
                    " outflightno varchar(5),\n" +
                    " outflightclass varchar(10),\n" +
                    " outdeparttime varchar(8),\n" +
                    " outdepartdate varchar(10),\n" +
                    " outcarriercode varchar(2),\n" +
                    " outdeparttime varchar(8),\n " +
                    " outbookingclass varchar(10),\n" +
                    " outarrivaltime varchar(8),\n" +
                    " outarrivaldate varchar(10),\n" +
                    " originalprice varchar(8),\n" +
                    " originalcurrency varchar(6),\n " +
                    " oneway varchar(1),\n" +
                    " inflightno varchar(5),\n" +
                    " inflightclass varchar(10),\n" +
                    " outdepartdate varchar(10),\n" +
                    " indeparttime varchar(8),\n " +
                    " indepartdate varchar(10),\n" +
                    " indepartcode varchar(3),\n" +
                    " incarriercode varchar(3),\n" +
                    " inbookingclass varchar(10),\n" +
                    " inarrivecode varchar(3),\n " +
                    " inarrivaltime varchar(8),\n" +
                    " inarrivaldate varchar(10),\n" +
                    " id varchar(7),\n" +
                    " destair varchar(3),\n" +
                    " depair varchar(3),\n " +
                    " carrier varchar(10),\n"
            );
        } catch (Exception e){
            e.printStackTrace();

        } catch (SQLException ex) {
//            Handle any errors
            ex.printStackTrace();
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                }
                stmt = null;
            }
        }
    }