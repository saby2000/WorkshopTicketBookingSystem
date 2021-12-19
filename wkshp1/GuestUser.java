package wkshp1;
import java.io.*;
import java.util.*;
import java.sql.*;
public class GuestUser extends User {
	private String query="";
	Connection con;
	Statement st;
	GuestUser(){
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ticketbooking","root","");
		st=con.createStatement();
		}catch(Exception ea) {
			
		}
	}
	
	boolean register(String name,String roll,String pwd) {
		try {
		query="INSERT INTO user VALUES ('"+roll+"','"+name+"','"+pwd+"')";
		int rs = st.executeUpdate(query);
		st.close();
		con.close();
		return true;
		}catch(Exception e) {
			System.out.println("SQL Error");
			return false;
		}
	}
	@Override
	void viewWorkshop() {
		try {
			query="SELECT*FROM workshop";
			ResultSet rs = st.executeQuery(query);
			rs.next();
			String a,c,d,e,f;
			int sz;
			int id =1;
			while(rs.next()) {
				if(rs.getInt("Status")==1) {
					
					a=rs.getString("wname");
					//b=rs.getString("seats");
					sz=rs.getInt("seats");
					c=rs.getString("descr");
					d=rs.getString("stime");
					e=rs.getString("etime");
					f=rs.getString("venue");
					System.out.println("\t Workshop "+ id);
					id++;
					System.out.println("Name: "+ a);
					System.out.println("Seats left: "+ sz);
					System.out.println("Description: "+ c);
					System.out.println("Start time: "+ d);
					System.out.println("End time: "+ e);
					System.out.println("Venue: "+ f);
					
				}
				
			}
			st.close();
			con.close();
			}catch(Exception e) {
				System.out.println("SQL Error");
			}
	}

}
