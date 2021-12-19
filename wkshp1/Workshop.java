package wkshp1;
import java.io.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;
public class Workshop {
	private int id;
	private String name;
	private String location;
	private int capacity=1;//total number of seats
	private int availableSeats;
	//ArrayList<String> time;
	String query="";
	Connection con;
	Statement st;
	public Workshop() {
			try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ticketbooking","root","");
			}catch(Exception ec) {
				System.out.println("Did not connect to the database successfully");
			}
	}
	public boolean updateWorkshop(){
		try {
			query="SELECT*FROM workshop where status=1";
			st=con.createStatement();
			ResultSet rs = st.executeQuery(query);
			//rs.next();
			String a,c,d,e,f;
			int sz;
			int id=1;
			int wid;
			while(rs.next()) {
				//System.out.println("lmao"+rs.getString("wname")+rs.getInt("status"));
				if(rs.getInt("status")==1) {
					a=rs.getString("wname");
				//	System.out.println(a);
					sz=rs.getInt("seats");
					//System.out.println(sz);
					c=rs.getString("descr");
				//	System.out.println(c);
					d=rs.getString("stime");
				//	System.out.println(d);
					e=rs.getString("etime");
				//	System.out.println(e);
					f=rs.getString("venue");
				//	System.out.println(f);
					wid=rs.getInt("wid");
					System.out.println("\t Workshop "+ id);
					id++;
					System.out.println("ID Number: "+wid );
					System.out.println("Name: "+ a);
					System.out.println("Seats left: "+ sz);
					System.out.println("Description: "+ c);
					System.out.println("Start time: "+ d);
					System.out.println("End time: "+ e);
					System.out.println("Venue: "+ f);
					
				}
				
			}
			Scanner in=new Scanner(System.in);
			System.out.println("Enter the ID Number of the workshop.");
			int nid=in.nextInt();
			System.out.println(nid);
			in.nextLine();
			System.out.println("Re enter all the details of the workshop. ");
			System.out.print("Name: ");
			String nm=in.nextLine();
			System.out.println(nm);
			System.out.print("Seats: ");
			int nsz=in.nextInt();
			in.nextLine();
			System.out.println(nsz);
			System.out.print("Description: ");
			String ndesc=in.nextLine();
			System.out.println(ndesc);
			System.out.println("The format for time is YYYY-MM-DD HH:MI:SS . ");
			System.out.print("Start time: ");
			String nstme=in.nextLine();
			System.out.println(nstme);
			System.out.print("End time: ");
			String netme=in.nextLine();
			System.out.println(netme);
			System.out.print("Venue: ");
			String nven=in.nextLine();
			System.out.println(nven);
			st=con.createStatement();
			query="UPDATE workshop SET wname = '"+ nm + "', seats= '"+nsz+"', descr= '"+ndesc+"', stime= '"+nstme+"', etime= '"+netme+"', venue= '"+nven+"'"+ " WHERE wid ="+nid;
			int rsn = st.executeUpdate(query);
			System.out.print("Updated successfully! ");
			in.close();
			st.close();
			con.close();
			return true;
			}catch(SQLException e) {
				System.out.println("SQL Error");
				return false;
			}
	}
	public boolean bookTicket(String uid){
		RegisteredUser ru=new RegisteredUser();
		//be careful you opened another connection inside viewWorkshop
		ru.viewWorkshop();
		System.out.println("Enter Workshop Number");
		Scanner in=new Scanner(System.in);
		int wid=in.nextInt();
		//System.out.println(wid);
		try {
			st=con.createStatement();
			query="SELECT*FROM workshop";
			ResultSet rs = st.executeQuery(query);
			rs.next();
			String b,c,d,e,f;
			String a="";
			int sz=0;
			int id =1;
			while(rs.next()) {
				//System.out.println(rs.getInt("Status"));
				if(rs.getInt("Status")==1) {
					
					a=rs.getString("wname");
					//b=rs.getString("seats");
					sz=rs.getInt("seats");
					c=rs.getString("descr");
					d=rs.getString("stime");
					e=rs.getString("etime");
					f=rs.getString("venue");
					//System.out.println("\t Workshop "+ id);
					if(id==wid) {
						//System.out.println();
						a=rs.getString("wid");
						break;
					}
					id++;
					/**
					System.out.println("Name: "+ a);
					System.out.println("Seats left: "+ sz);
					System.out.println("Description: "+ c);
					System.out.println("Start time: "+ d);
					System.out.println("End time: "+ e);
					System.out.println("Venue: "+ f);
					**/
				}
			}
			//System.out.println(a);
			sz--;
			st=con.createStatement();
			query="SELECT COUNT(*) FROM ticket";
			ResultSet rsi = st.executeQuery(query);
			rsi.next();
			int count=rsi.getInt(1);
			count++;
			//System.out.println(count);
			st=con.createStatement();
			query="INSERT INTO ticket (tid,uid,wid,status) VALUES ('"+count+"','"+uid+"','"+a+"','1')";
			int rsf = st.executeUpdate(query);
			Statement stk=con.createStatement();
			//UPDATE workshop SET seats ='49' WHERE wid=1
			query="UPDATE workshop SET seats ='"+sz+"' WHERE wid="+a;//update ticket
			int rsk = stk.executeUpdate(query);
			//rsf.next();
			in.close();
			st.close();
			con.close();
			return true;
			}catch(Exception e) {
				System.out.println("SQL Error");
				return false;
			}
		//INSERT INTO ticket (tid,uid,wid,status) VALUES ('1','B180551CS','2','1');
	}
	public boolean deleteWorkshop(){
		try {
			query="SELECT*FROM workshop where status=1";
			st=con.createStatement();
			ResultSet rs = st.executeQuery(query);
			//rs.next();
			String a,c,d,e,f;
			int sz;
			int id=1;
			int wid;
			while(rs.next()) {
				//System.out.println("lmao"+rs.getString("wname")+rs.getInt("status"));
				if(rs.getInt("status")==1) {
					a=rs.getString("wname");
				//	System.out.println(a);
					sz=rs.getInt("seats");
					//System.out.println(sz);
					c=rs.getString("descr");
				//	System.out.println(c);
					d=rs.getString("stime");
				//	System.out.println(d);
					e=rs.getString("etime");
				//	System.out.println(e);
					f=rs.getString("venue");
				//	System.out.println(f);
					wid=rs.getInt("wid");
					System.out.println("\t Workshop "+ id);
					id++;
					System.out.println("ID Number: "+wid );
					System.out.println("Name: "+ a);
					System.out.println("Seats left: "+ sz);
					System.out.println("Description: "+ c);
					System.out.println("Start time: "+ d);
					System.out.println("End time: "+ e);
					System.out.println("Venue: "+ f);
					
				}
				
			}
			Scanner in=new Scanner(System.in);
			System.out.println("Enter the ID Number of the workshop.");
			int nid=in.nextInt();
			query="UPDATE workshop SET status = 0 where wid = "+ nid;
			st=con.createStatement();
			int rse = st.executeUpdate(query);
			return true;
		}catch(Exception e) {
			System.out.println("SQL Error");
			return false;
		}
	}
	public boolean addWorkshop(){
		
		try {
			Scanner in=new Scanner(System.in);
			System.out.print("Name of the Workshop: ");
			String name=in.nextLine();
			System.out.println(name);
			System.out.print("Seat capacity: ");
			int cap=in.nextInt();
			System.out.println(cap);
			in.nextLine();
			System.out.print("Description: ");
			String desc=in.nextLine();
			System.out.println(desc);
			System.out.println("The format for time is YYYY-MM-DD HH:MI:SS . ");
			System.out.print("Start time: ");
			String stime=in.nextLine();
			System.out.println(stime);
			System.out.print("End time: ");
			String etime=in.nextLine();
			System.out.println(etime);
			System.out.print("Venue: ");
			String ven=in.nextLine();
			System.out.println(ven);
			int flag=0;
			query="SELECT*FROM workshop";
			st=con.createStatement();
			ResultSet rs = st.executeQuery(query);
			int id=1;
			while(rs.next()) {
				id++;
				String newstime=rs.getString("stime");
				String newetime=rs.getString("etime");
				DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
				Date dms=dateFormat.parse(newstime);
				Date dme=dateFormat.parse(newetime);
				Date s=dateFormat.parse(stime);
				Date e=dateFormat.parse(etime);
				if(dms.before(e)&&e.before(dme)) {
					if(ven.equals(rs.getString("venue"))) {
						flag=1;
						break;
					}
				}
			}
			System.out.println("ID "+id);
			if(flag==1) {
				System.out.println("Scheduling conflict.");
				return false;
			}else {
				query="INSERT INTO workshop VALUES ('"+id+"','"+name+"','"+cap+"','"+desc+"','"+stime+"','"+etime+"','"+ven+"','1')";
				//System.out.println(query);
				st=con.createStatement();
				int rsi = st.executeUpdate(query);
				System.out.println("Workshop updated successfully.");
				return true;
			}
			}catch(Exception e) {
				System.out.println("SQL Error");
				return false;
			}
			
		}

}
