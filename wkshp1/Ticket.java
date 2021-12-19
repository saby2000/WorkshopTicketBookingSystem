package wkshp1;
import java.sql.*;
import java.io.*;
import java.util.*;
public class Ticket {
	private int id;
	private String bookingTime;
	private String Workshop;
	//numberofSeats=1;
	private String query="";
	Connection con;
	Statement st;
	public Ticket() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ticketbooking","root","");
			st=con.createStatement();
			}catch(Exception ec) {
				System.out.println("Did not connect to the database successfully");
			}
	}
	public void getTicketinfo(String uid) {
		
	}
	public boolean deleteBooking(String uid) {
		try {
			Scanner in=new Scanner(System.in);
			getTicketinfo(uid);
			query="SELECT*FROM ticket where status=1";
			ResultSet rs = st.executeQuery(query);
			//rs.next();
			while(rs.next()){
			//	System.out.println(uid+" "+rs.getString("uid"));
				if(rs.getString("uid").equals(uid)){
					int tid=rs.getInt("tid");
					int stat=rs.getInt("status");
					String time=rs.getString("bookingtime");
					Statement sm=con.createStatement();
					query="SELECT*FROM workshop WHERE wid="+tid;
					ResultSet rt = sm.executeQuery(query);
					rt.next();
					String wname=rt.getString("wname");
					System.out.println("Ticket ID: "+tid+"\nWorkshop Name: "+wname+"\nBooking time: "+time);
				}
				
			}
			System.out.println("Enter the ticket ID");
			int tid=in.nextInt();
			Statement stm=con.createStatement();
			query="UPDATE ticket SET status = 0 WHERE tid = "+tid;
			int rsi = stm.executeUpdate(query);
			return true;
			}catch(Exception en) {
				System.out.println("SQL Error");
				return false;
			}
	}

}
