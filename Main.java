package Library;
import java.io.*;
import java.util.*;
import java.net.*;
public class Main {
	static Scanner in=new Scanner(System.in);
	public static void main(String args[])
    {
		library l=new library();
        Main m=new Main();
     
         do
         {
        	 System.out.println("\nWELCOME TO THE LIBRARY MANAGEMENT SYSTEM");
        	 System.out.println("\n @@@@@@@@@@@@@@@@ MENU @@@@@@@@@@@@@@@@@");
        	 System.out.println("\n Enter Login as: \n 1.Admin \n 2.User");
         	
         	int choice=in.nextInt();
         	switch(choice)
         	{
         	case 1: 
         		m.admin(l);
         		break;
         	case 2:
         		m.user(l);
         		break;
         	default: 
         		System.out.println("Invalid Input.");
         	} 
         }while(true);
      }
	public static void admin(library l)
    {
		Admin ad=new Admin();
        int option;
        do
        {
        System.out.println("1.Add a book");
        System.out.println("2.Remove a book");
        System.out.println("3.Display all books");
        System.out.println("4.Search a book");
        
        System.out.println("5.Logout");
        //System.out.println("6.staff list");
        option=in.nextInt();
        switch(option)
        {
        case 1:
            ad.addBook();
            break;
        case 2:
            ad.removeBook();
            break;
        case 3:
            ad.display();
            break;
        case 4:
        	ad.searchBook();
        	break;
        case 5:
            return;
       
        default:
        	System.out.println("Invalid Input.");
        }
        }while(option!=5);
    }
	public static void user(library l)
    {
		User u=new User();
        int flag;
        String n="";
        boolean b;
        boolean log;
        int sid;
        int decision;
        do
        {
        	System.out.println("Login as 1.Staff or 2.Student");
    	int login=in.nextInt();
        if(login==1)
        {
        	System.out.println("Enter staff id");
            log=true;
        }
        else 
        {
        	System.out.println("Enter student id");
        	log=false;
        }
            
      
   	 	sid=in.nextInt();
       b= u.check_Id(sid,log);
        }while(!b);
        int opt;
        do
        {
        	
        	flag=u.check(sid);
        
        	System.out.println("1.Issue a book");
        	System.out.println("2.Return a book");
        	System.out.println("3.Display all books");
        	System.out.println("4.Search a book:");
        	System.out.println("5.Logout");
	        decision=in.nextInt();
        switch(decision)
        {
        case 1:
        	System.out.println();
            u.issueBook(sid,flag,log,n);
            break;
        case 2:
        	System.out.println();
            u.returnBook(sid,flag);
            break;
        case 3:
        	System.out.println();
            u.display();
            break;
        case 4:
        	u.searchBook();
        	break;
        case 5: 
        	return;
        default:
        	System.out.println("Invalid Input.");
        }
        }while(decision!=5);
       
    }
}