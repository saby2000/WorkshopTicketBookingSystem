package wkshp1;
import java.io.*;
import java.util.*;
import java.sql.*;
public class MyApp {


	public static void main(String[] args) {
		int admin=0;
		//step 2b
		String uid="";
		Scanner in=new Scanner(System.in);
		System.out.println("Welcome to NITC Workshop Ticket Booking Ticket ! !");
		System.out.println("Enter the option number below:- \n 1. Login \n 2. Register \n 3. View Workshops");
		int choice=in.nextInt();
		if(choice==1) {
			System.out.println("Enter the option number below:- \n 1. Login as customer\n 2. Login as admin");
			choice=in.nextInt();
			if(choice==1) {
				//login functions followed by verify password then display login error
				System.out.println("Enter login details. ");
				System.out.print("UserID: ");
				uid=in.next();
				System.out.print("Password: ");
				String pwd=in.next();
				RegisteredUser ru=new RegisteredUser();
				admin=0;
				if(ru.login(admin,uid,pwd)==false) {
					in.close();
					return;
				}
				System.out.println("User logged in successfully!");
				System.out.println("Enter the option number below:- \n 1. Delete existing booking \n 2. View booking history \n 3. Book ticket. \n 4. View workshops. \n");
				choice=in.nextInt();
				if(choice==1) {
					Ticket t=new Ticket();
					//display bookings and select a booking
					if(t.deleteBooking(uid)) {
						System.out.println("Booking deleted successfully.");
					}else {
						System.out.println("Booking deleted failed.");
					}
					
				}else if(choice==2) {
					RegisteredUser riu=new RegisteredUser();
					riu.viewBookings(uid);
					//System.out.println("");
				}else if(choice==3) {
					Workshop w=new Workshop();
					if(w.bookTicket(uid)) {
						System.out.println("Ticket booked successfully.");
					}else {
						System.out.println("Ticket booked failed.");
					}
				}else if(choice==4) {
					RegisteredUser rau=new RegisteredUser();
					rau.viewWorkshop();
				}else {
					System.out.println("Incorrect Input. ");
				}
			}else if(choice==2) {
				//in.nextLine();
				System.out.println("Enter login details. ");
				System.out.print("UserID: ");
				 uid=in.next();
				System.out.print("Password: ");
				String pwd=in.next();
				//System.out.println(uid+pwd);
				//RegisteredUser ru=new RegisteredUser();
				//admin=1;
				//ru.login(admin,uid,pwd);
				if(uid.equals("admin")&&pwd.equals("admin")) {
					System.out.println("User logged in successfully!");
					System.out.println("Enter the option number below:- \n 1. Update details of a workshop \n 2. Delete a workshop. \n 3. Add a workshop. \n 4. Delete a user. \n");
					choice=in.nextInt();

					if(choice==1) {
						Workshop w=new Workshop();
						//update workshop details
						in.nextLine();
						if(w.updateWorkshop()==false) {
							System.out.println("Workshop update failed.");
						}
						//System.out.println("Workshop updated successfully.");
					}else if(choice==2) {
						Workshop w=new Workshop();
						//update workshop details
						if(w.deleteWorkshop()) {
							System.out.println("Workshop deleted successfully.");
						}else {
							System.out.println("Workshop deleted failed.");
						}
					}else if(choice==3) {
						Workshop w=new Workshop();
						//update  workshop details
						if(w.addWorkshop()) {
						System.out.println("Workshop added successfully.");
						}else {
							System.out.println("Workshop added failed.");
						}
					}else if(choice==4) {
						RegisteredUser rbu=new RegisteredUser();
						if(rbu.deleteUser()) {
							System.out.println("Deleted user successfully.");
						}else {
							System.out.println("Deleted user failed.");
						}
					}else {
						System.out.println("Incorrect Input. ");
					}
				}else {
					System.out.println("Incorrect Username or password. ");
				}
				
			}else {
				System.out.println("Incorrect Input");
			}
		}else if(choice==2) {
			in.nextLine();
			GuestUser gu=new GuestUser();
			System.out.println("Enter the following details");
			System.out.print(" 1.Full Name: ");
			String name=in.nextLine();
			System.out.print(" 2.Roll Number: ");
			String roll=in.nextLine();
			System.out.print(" 3.Enter Password: ");
			String pwd=in.nextLine();
			System.out.print(" Confirm Password: ");
			String pwd2=in.nextLine();
			if(pwd.equals(pwd2)) {
				gu.register(name,roll,pwd);
				System.out.println("User successfully registered. ");
			}else {
				System.out.println("Password not matching. ");
			}
		}else if(choice==3) {
			//System.out.println("Before reg");
			RegisteredUser ru=new RegisteredUser();
			ru.viewWorkshop();
		}else {
			System.out.println("Incorrect Input");
		}
		in.close();
		
	}

}
