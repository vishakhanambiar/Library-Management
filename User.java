package Library;
import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;
public class User implements Comparable<Integer> {
	int id;
    String name;
    boolean val;
    LocalDateTime issue;
    LocalDateTime ret;
    public User(int id, String name,boolean val)
    {
        this.id=id;
        this.name=name;
        this.val=val;
    }
    public User()
    {
        this.id=0;
        this.name="";
        this.issue=null;
        this.ret=null;
       
    }
    public int getId() {
        return id;
    }
    public int compareTo( Integer i) {
       if (this.id == i) {
           return 0;
       } else if (this.id < i) {
           return -1;
       }
       return 1;
    }
    static Scanner in=new Scanner(System.in);
    void issueBook(int y,int flag,boolean val,String name)
    {
    	System.out.println();
        if(flag==1)
        {
            System.out.println("You can only issue a book after returning the previously issued book.");
            System.out.println();
            return;
        }
        System.out.println("Enter the book id that you want to issue.");
        int i=in.nextInt();
        Set<Entry<Book, User>> s=library.h.entrySet();
        Iterator<Entry<Book, User>> it=s.iterator();
            while(it.hasNext())
            {
            	Map.Entry m=(Map.Entry)it.next();
                Book obj=(Book)m.getKey();
                if(obj.id==i)
                {
                    System.out.print("The book is currently issued by someone else and should be available by: ");
                    User u=(User)library.h.get(obj);
                    System.out.println(u.ret);
                    System.out.println();
                    return;
                }
            }
            Iterator<Book> vt=library.v.iterator();
            while(vt.hasNext())
            {
                Book obj=(Book)vt.next();
                if(obj.id==i)
                {
                    User u=new User(y,name,val);
                    System.out.println("Book available.");
                    obj.issue=false;
                    
                    u.issue=LocalDateTime.now();
                    if(val==true)
                    {
                        u.ret=LocalDateTime.now().plusMinutes(2);
                        library.st.add(y);
                    }
                    else
                    {
                    	u.ret=LocalDateTime.now().plusMinutes(1);
                    	library.c.add(y);
                    }
                    
                    library.h.put(obj,u);
                    System.out.println("Book Issued successfully.");
                    System.out.println();
                    return;
                }
            }
            System.out.println("Book not found");  
            System.out.println();
    }
    void check_return(User obj)
    {
    	if((obj.ret).compareTo(LocalDateTime.now())==-1)
    	{
    		System.out.println("You have returned the book after the specified return date. Pay a fine of Rs.100.");
    		return;
    	}
    	else
    	{
    		System.out.println("Congratulations on returning the book in time.");
    		return;
    	}
    }
   int check(int id)
	{
	   Set<Entry<Book, User>> s=library.h.entrySet();
       Iterator<Entry<Book, User>> it=s.iterator();
       User obj=new User();
       
           while(it.hasNext())
           {
           	Map.Entry m=(Map.Entry)it.next();
               obj=(User)m.getValue();

               if(obj.id==id)
               {
               	System.out.println("The book you have currently issued is: ");
                   Book objb=(Book)m.getKey();
                   System.out.println("BOOK ID\t\t\t"+"BOOK NAME\t\t\t"+"BOOK AUTHOR\t\t\t"+"ISSUE DATE\t\t\t\t"+"RETURN DATE");
                   System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
                   System.out.println(objb.id+"\t\t\t"+objb.name+"\t\t\t\t"+objb.author+"\t\t\t\t"+obj.issue+"\t\t\t"+obj.ret);
                   System.out.println();
                   return 1;
               }
           }
           return 0;
	}
    void available(int id)
	{
    	Iterator<Book> vt=library.v.iterator();
        while(vt.hasNext())
        {
            Book obj=(Book)vt.next();
            if(obj.id==id)
            {
            	obj.issue=true;
                System.out.println("Book returned successfully.");
                return;
            }
        }
        System.out.println("An error occured while returning the book.");
	}
   void returnBook(int id,int flag)
    {
	   System.out.println();
       if(flag==0)
       {
           System.out.println("You don't have any unreturned book.");
           System.out.println();
           return;
       }
       System.out.println("The book you have returned is:");
       System.out.println();
       Set<Entry<Book, User>> s=library.h.entrySet();
       Iterator<Entry<Book, User>> it=s.iterator();
       User obj=new User();
       
           while(it.hasNext())
           {
           	Map.Entry m=(Map.Entry)it.next();
               obj=(User)m.getValue();

               if(obj.id==id)
               {
                   Book objb=(Book)m.getKey();
                   System.out.println("BOOK ID\t\t\t"+"BOOK NAME\t\t\t"+"BOOK AUTHOR\t\t\t"+"ISSUE DATE\t\t\t\t"+"RETURN DATE");
                   System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
                   System.out.println(objb.id+"\t\t\t"+objb.name+"\t\t\t\t"+objb.author+"\t\t\t\t"+obj.issue+"\t\t\t"+obj.ret);
                   System.out.println();
                   check_return(obj);
                   System.out.println();
                   available(objb.id);
                   System.out.println();
                   library.h.remove(objb);
                   return;
               }
           }
    }
    boolean check_Id(int y,boolean val)
    {
    	  if(val==true)
    	   	{
    	   		boolean b;
    	   		b=library.c.contains(y);
    	   		if(b==true)
    	   		{
    	   			System.out.println("same as student id");
    	   			return false;
    	   		}
    	   	}
    	   	if(val==false)
    	   	{
    	   		boolean b;
    	   		b=library.st.contains(y);
    	   		if(b==true)
    	   		{
    	   			System.out.println("same as staff id");
    	   			return false;
    	   		}
    	   	}
    	   	return true;
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
   void searchBook()
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
