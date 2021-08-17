import java.util.Scanner;

class AddressBook
{
	public String firstName;
	public String lastName;
	public String address;
	public String city;
	public String state;
	public int zip;
	public int phoneNumber;
	public String email;
	
	public AddressBook(String firstName,String lastName,String address,String city,String state,int zip,int phoneNumber,String email)
	{
		this.firstName=firstName;
		this.lastName=lastName;
		this.address=address;
		this.city=city;
		this.state=state;
		this.zip=zip;
		this.phoneNumber=phoneNumber;
		this.email=email;
	}
}
public class AddressBookMain
{
	public AddressBook abm[]=new AddressBook[10];
	public static int I=0;
	public void addPerson()
	{
		String firstName;
		String lastName;
		String address;
		String city;
		String state;
		int zip;
		int phoneNumber;
		String email;
		Scanner sc=new Scanner(System.in);
		Scanner scc=new Scanner(System.in);
		System.out.println("Enter first name :");
		firstName=sc.nextLine();
		System.out.println("Enter Last name :");
		lastName=sc.nextLine();
		System.out.println("Enter address name :");
		address=sc.nextLine();
		System.out.println("Enter city name :");
		city=sc.nextLine();
		System.out.println("Enter state name :");
		state=sc.nextLine();
		System.out.println("Enter zip :");
		zip=sc.nextInt();
		System.out.println("Enter phone number :");
		phoneNumber=sc.nextInt();
		System.out.println("Enter email :");
		email=scc.nextLine();
		abm[I++]=new AddressBook(firstName,lastName,address,city,state,zip,phoneNumber,email);
		System.out.println("Contact added Successfully");
	}
	
	public static void main(String[] args) 
	{
		boolean condition=true;
		AddressBookMain obj=new AddressBookMain();
	
		System.out.println("Thank you");
	}

}
