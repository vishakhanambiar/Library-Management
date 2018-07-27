package Library;


import java.util.*;
import java.io.*;

public class Admin {
	Scanner in=new Scanner(System.in);
	void addBook()
	{
		System.out.println("Enter the book id");
        int i=in.nextInt();       
      Iterator<Book> it=library.v.iterator();
      while(it.hasNext())
      {
          Book obj=(Book)it.next();
          if(obj.id==i)
          {
          	System.out.println();
          	System.out.println("Already existing");
              System.out.println();
              return;
          }
      }
          {
          	System.out.println("Enter the book name");
          	  in.nextLine();
                String nm=in.nextLine();
          	System.out.println("Enter the book author");
          	  String au=in.nextLine();
              Book b=new Book(i,nm,au,false);
              library.v.add(b);
          }
	}
	void removeBook()
	{
	    System.out.println("Enter the book id");
        int bid=in.nextInt();
        Set s=library.h.entrySet();
        Iterator it=s.iterator();
            while(it.hasNext())
            {
            	Map.Entry m=(Map.Entry)it.next();
                Book obj=(Book)m.getKey();
                if(obj.id==bid)
                {
                	 System.out.println("Currently issued");
                    return;
                }
            }
            Iterator<Book> vt=library.v.iterator();
            while(vt.hasNext())
            {
                Book obj=(Book)vt.next();
                if(obj.id==bid)
                {
                	 System.out.println("Book Found");
                    library.v.remove(obj);
                    System.out.println("Book Removed Successfully");
                    return;                    
                }
            }
            System.out.println("Book not found");
	}
	void searchBook()
	{
		System.out.println("enter the book id:");
		int ch=in.nextInt();
		Iterator<Book> it=library.v.iterator();
	      while(it.hasNext())
	      {
	          Book obj=(Book)it.next();
	          if(obj.id==ch)
	          {
	          	System.out.println();
	          	System.out.println("BOOK ID\t\t\t"+"BOOK NAME\t\t\t"+"BOOK AUTHOR\t\t\t"+"BOOK AVAILABILITY");
	          	System.out.println(obj.id+"\t\t\t"+obj.name+"\t\t\t\t"+obj.author+"\t\t\t\t"+obj.issue);
	              System.out.println();
	              return;
	          }
	      }
	      System.out.println("not found");
	}
	void display()
	{
		 System.out.println("BOOK ID\t\t\t"+"BOOK NAME\t\t\t"+"BOOK AUTHOR\t\t\t"+"BOOK AVAILABILITY");
	       System.out.println("------------------------------------------------------------------------------------------------------------------");
	       Iterator<Book> it=library.v.iterator();
	       while(it.hasNext())
	       {
	           Book obj=(Book)it.next();
	           System.out.println(obj.id+"\t\t\t"+obj.name+"\t\t\t\t"+obj.author+"\t\t\t\t"+obj.issue);
	       }
	       System.out.println();
	}

}
