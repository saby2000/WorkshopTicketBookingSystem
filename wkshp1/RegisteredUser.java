package wkshp1;
import java.io.*;
import java.util.*;
import java.sql.*;
public class RegisteredUser extends User {
	String query="";
	Connection con;
	Statement st;
	RegisteredUser(){
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ticketbooking","root","");
		st=con.createStatement();
		}catch(Exception ec) {
			System.out.println("Did not connect to the database successfully");
		}
	}
	public boolean login(int admin,String uid,String pwd) {
		ResultSet rs;
		String cuid;
		String cpwd;
		int flag=0;
		//System.out.println(admin+uid+pwd);
		try {
		query="SELECT*FROM user";
		rs = st.executeQuery(query);
		//rs.next();
		while(rs.next()) {
			cuid=rs.getString("uid");
			cpwd=rs.getString("pwd");
			//System.out.println(cuid+cpwd);
			if(cuid.equals(uid)&&cpwd.equals(pwd)) {
				flag=1;
				break;
			}
			
		}
		st.close();
		con.close();
		if(flag==0) {
			Scanner in=new Scanner(System.in);
			displayError();
			System.out.println("Did you forget your password?[Y/N]");
			char c=in.next().charAt(0);
			if(c=='Y'||c=='y') {
				if(forgotPassword(uid)) {
					System.out.println("Password has been reset to userid");
					return true;
				}else {
					System.out.println("Password has not been reset.");
					return false;
				}
			}else {
				return false;
			}
		}else {
			return true;
		}
		}catch(Exception eb) {
			System.out.println("Error with the query");
			return false;
		}
		
		
		
	}
	public boolean forgotPassword(String uid) {
		try {
			//UPDATE user SET pwd = 'B180551CS' where uid = 'B18';
			query="UPDATE user SET pwd = '"+uid+"' where uid = '"+uid+"'";
			//System.out.println(query);
			Connection conk=DriverManager.getConnection("jdbc:mysql://localhost:3306/ticketbooking","root","");
			Statement stk=conk.createStatement();
			//System.out.println("here");
			int rs = stk.executeUpdate(query);	
			//System.out.println("here");
			return true;
		}catch(SQLException ec) {
			//System.out.println("here");
			return false;
		}
	}
	public void displayError() {
		System.out.println("DisplayError.Incorrect Password or userid.");
	}
	public void viewBookings(String uid) {
		try {
		System.out.println("Name of Workshop \t  Status \t \t Booking time");
		query="SELECT*FROM ticket";
		ResultSet rs = st.executeQuery(query);
		//rs.next();
		while(rs.next()){
		//	System.out.println(uid+" "+rs.getString("uid"));
			if(rs.getString("uid").equals(uid)){
				int wid=rs.getInt("wid");
				int stat=rs.getInt("status");
				String time=rs.getString("bookingtime");
				Statement sm=con.createStatement();
				query="SELECT wname FROM workshop WHERE wid="+wid;
				ResultSet rt = sm.executeQuery(query);
				rt.next();
				String wname=rt.getString("wname");
				String stus="";
				if(stat==0) {
					stus="Cancelled";
				}else {
					stus="Booked";
				}
				System.out.println(wname+"\t \t \t  "+stus+" \t \t "+time);
			}
			
		}
		
		}catch(Exception en) {
			System.out.println("SQL Error");
		}
		//st=con.createStatement();
		
	}
	public boolean deleteUser() {
		try {
			query="SELECT*FROM user";
			ResultSet rs = st.executeQuery(query);
			//rs.next();
			String a,c,d;
			//int sz;
			int id=1;
			while(rs.next()) {
				
					a=rs.getString("uid");
					//b=rs.getString("seats");
					//sz=rs.getInt("seats");
					c=rs.getString("fname");
					d=rs.getString("pwd");
					System.out.println("\t User "+ id);
					id++;
					System.out.println("Registration Number: "+ a);
					System.out.println("Name: "+ c);
				id++;
				
			}
			Scanner in=new Scanner(System.in);
			System.out.println("Enter the registration number of the user to be deleted: ");
			String roll=in.next();
			query="DELETE FROM user WHERE uid='"+roll+"'";
			int rsi = st.executeUpdate(query);
			in.close();
			st.close();
			con.close();
			return true;
		}catch(Exception ed) {
			System.out.println("SQL Error");
			return false;
		}
		
	}
	@Override
	void viewWorkshop(){
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
