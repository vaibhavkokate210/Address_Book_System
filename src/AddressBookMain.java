import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class AddressBookMain
{
	Map<String,ArrayList<AddressBook>> addressBookSet=new HashMap<String,ArrayList<AddressBook>>();
	ArrayList<AddressBook> addressBook;
	public static String PathName = "file.txt";
	public static String PathCsvName = "file-csv.csv";
	public void addPerson()
	{
		String firstName;
		String lastName;
		String address;
		String city;
		String state;
		String zip;
		String phoneNumber;
		String email;
		Scanner sc=new Scanner(System.in);
		Scanner scc=new Scanner(System.in);
		System.out.println("Enter first name :");
		firstName=sc.nextLine();
		final String first=firstName;
		System.out.println("Enter Last name :");
		lastName=sc.nextLine();
		final String last=lastName;
		for(Map.Entry<String, ArrayList<AddressBook>> record : this.addressBookSet.entrySet())
		{
 			ArrayList<AddressBook> book=record.getValue();
			for(AddressBook abook:book)
			{
			       if(book.stream().anyMatch(ob->ob.firstName.equals(first)&&ob.lastName.equals(last)))
			       {
			    	   System.out.println("person already exit .......please enter another name");
			    	   System.out.println("Enter first name :");
			    	   firstName=sc.nextLine();
			    	   System.out.println("Enter Last name :");
			   		   lastName=sc.nextLine();
			    	   break;
			       } 	   
			}
			book.stream().sorted(Comparator.comparing(AddressBook::getFirstName)).collect(Collectors.toList());
		}
		
		System.out.println("Enter address name :");
		address=sc.nextLine();
		System.out.println("Enter city name :");
		city=sc.nextLine();
		System.out.println("Enter state name :");
		state=sc.nextLine();
		System.out.println("Enter zip :");
		zip=sc.nextLine();
		System.out.println("Enter phone number :");
		phoneNumber=sc.nextLine();
		System.out.println("Enter email :");
		email=scc.nextLine();
		AddressBook ab=new AddressBook(firstName,lastName,address,city,state,zip,phoneNumber,email);
		this.addressBook.add(ab);
		System.out.println("Contact added Successfully");
	}
	public void editPerson()
	{
		System.out.println("Enter first name to edit contact");
		Scanner sc=new Scanner(System.in);
		String name=(String)sc.nextLine();
		for(Map.Entry<String, ArrayList<AddressBook>> record : this.addressBookSet.entrySet())
		{
 			ArrayList<AddressBook> book=record.getValue();
			for(AddressBook abook:book)
			{
			       if(abook.firstName.equals(name))
			       {
			    	   boolean bk=true;
						
						while(bk)
						{
							System.out.println("Which field do you want to edit : ");
						System.out.println("1. First Name     2. Last Name       3. Address        4. City ");
						System.out.println("5. State          6. Zip             7. Phone Number   8. Email ");
						System.out.println("9. Don't want Editing");
						int ch;
						Scanner sc1=new Scanner(System.in);
						ch=sc1.nextInt();
						switch(ch)
						{
						case 1:System.out.println("Enter new first name :");
						        Scanner sc2=new Scanner(System.in);
								String fn=sc2.nextLine();
								abook.firstName=fn;
								break;
						case 2:System.out.println("Enter new last name :");
						        String ln=sc1.nextLine();
						        abook.lastName=ln;
						        break;
						case 3:System.out.println("Enetr new address :");
						        String add=sc1.nextLine();
						        abook.address=add;
					         	break;
						case 4:System.out.println("Enetr new City :");
						        String ct=sc1.nextLine();
						        abook.city=ct;
						        break;
						case 5:System.out.println("Enetr new State :");
						        String st=sc1.nextLine();
						        abook.state=st;
						        break;
						case 6:System.out.println("Enetr new Zip:");
						        String zp=sc1.nextLine();
						        abook.zip=zp;
						        break;
						case 7:System.out.println("Enetr new Phone number :");
					        	String pn=sc1.nextLine();
					        	abook.phoneNumber=pn;
						        break;
						case 8:System.out.println("Enetr new Email :");
						        String em=sc1.nextLine();
						        abook.email=em;
						        break;
						case 9:bk=false;
								break;
			           }
			    	   
			          }
			       }
			 }				
		}
	}
	public void deletePerson()
	{
		System.out.println("Enter first name do you want to delete contact :");
		Scanner ip=new Scanner(System.in);
		String name=ip.nextLine();
		int index=0;
		for(Map.Entry<String, ArrayList<AddressBook>> record : this.addressBookSet.entrySet())
		{
 			ArrayList<AddressBook> book=record.getValue();
			for(AddressBook abook:book)
			{
			       if(abook.firstName.equals(name))
			    	   index=book.indexOf(abook);
			}
			book.remove(index);
		}
		

	}
	public void viewAllContacts()
	{
		for(Map.Entry<String, ArrayList<AddressBook>> record : this.addressBookSet.entrySet())
		{
 			System.out.println("*********************************************"+record.getKey()+"***************************************");
			ArrayList<AddressBook> book=record.getValue();
			System.out.println("========================================================================================");
			System.out.println("First Name\tLast Name\tAddressCity\tState\tZip\tPhone Number\tEmail");
			System.out.println("========================================================================================");
			book.stream().sorted(Comparator.comparing(AddressBook::getFirstName)).forEach(ob->System.out.println(ob.firstName+"\t\t"+ob.lastName+"\t\t"+ob.address+"\t"+ob.city+"\t"+ob.state+"\t"+ob.zip+"\t"+ob.phoneNumber+"\t"+ob.email));;
			System.out.println("========================================================================================");
		}
	}
	public void searchByCityOrState()
	{
		System.out.println("Enter city or state of person which u want :");
		Scanner ip=new Scanner(System.in);
		final String cityOrState=ip.nextLine();
		for(Map.Entry<String, ArrayList<AddressBook>> record : this.addressBookSet.entrySet())
		{
 			ArrayList<AddressBook> book=record.getValue();
 			boolean match=book.stream().anyMatch(ob->ob.city.equals(cityOrState)||ob.state.equals(cityOrState));
 			if(match)
 				System.out.println("Person found in "+cityOrState+" city/State ");
 			else
 				System.out.println("Person not found in city/state "+cityOrState);
		}
	}
	public void viewByCityOrState()
	{
		System.out.println("Enter city or state of person which u want view:");
		Scanner ip=new Scanner(System.in);
		final String cityOrState=ip.nextLine();
		int count=0;
		for(Map.Entry<String, ArrayList<AddressBook>> record : this.addressBookSet.entrySet())
		{
 			ArrayList<AddressBook> book=record.getValue();
 			book.stream().filter(ob->ob.city.equals(cityOrState)||ob.state.equals(cityOrState)).forEach(ob->System.out.println("Person = "+ob.firstName+ " "+ob.lastName+" City/State ="+cityOrState+"\n"));
		}
     }
	
	public void countByCityOrState()
	{
		System.out.println("Enter city or state of person which u want count:");
		Scanner ip=new Scanner(System.in);
		String cityOrState=ip.nextLine();
		long count=0;
		for(Map.Entry<String, ArrayList<AddressBook>> record : this.addressBookSet.entrySet())
		{
 			ArrayList<AddressBook> book=record.getValue();
 			count=book.stream().filter(ob->ob.city.equals(cityOrState)||ob.state.equals(cityOrState)).count();
		}
		if(count!=0)
			System.out.println("City/State = "+cityOrState+" Count = "+count);
		else
			System.out.println("No reocord found for city/state "+cityOrState);
	}
	public void sortByCityOrStateOrZip()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Which way u want to sort :\n1. City\n2. State\n3. Zip");
		int num=sc.nextInt();
		if(num==1)
		{
		      for(Map.Entry<String, ArrayList<AddressBook>> record : this.addressBookSet.entrySet())
		      {
 		        	System.out.println("*********************************************"+record.getKey()+"***************************************");
 		        	ArrayList<AddressBook> book=record.getValue();
 		        	System.out.println("========================================================================================");
 		        	System.out.println("First Name\tLast Name\tAddressCity\tState\tZip\tPhone Number\tEmail");
 		        	System.out.println("========================================================================================");
 		        	book.stream().sorted(Comparator.comparing(AddressBook::getCity)).forEach(ob->System.out.println(ob.firstName+"\t\t"+ob.lastName+"\t\t"+ob.address+"\t"+ob.city+"\t"+ob.state+"\t"+ob.zip+"\t"+ob.phoneNumber+"\t"+ob.email));;
 		        	System.out.println("========================================================================================");
		       }
		}
		else if(num==2)
		{
			 for(Map.Entry<String, ArrayList<AddressBook>> record : this.addressBookSet.entrySet())
		      {
		        	System.out.println("*********************************************"+record.getKey()+"***************************************");
		        	ArrayList<AddressBook> book=record.getValue();
		        	System.out.println("========================================================================================");
		        	System.out.println("First Name\tLast Name\tAddressCity\tState\tZip\tPhone Number\tEmail");
		        	System.out.println("========================================================================================");
		        	book.stream().sorted(Comparator.comparing(AddressBook::getState)).forEach(ob->System.out.println(ob.firstName+"\t\t"+ob.lastName+"\t\t"+ob.address+"\t"+ob.city+"\t"+ob.state+"\t"+ob.zip+"\t"+ob.phoneNumber+"\t"+ob.email));;
		        	System.out.println("========================================================================================");
		       }
		}
		else if(num==3)
		{
			for(Map.Entry<String, ArrayList<AddressBook>> record : this.addressBookSet.entrySet())
		      {
		        	System.out.println("*********************************************"+record.getKey()+"***************************************");
		        	ArrayList<AddressBook> book=record.getValue();
		        	System.out.println("========================================================================================");
		        	System.out.println("First Name\tLast Name\tAddressCity\tState\tZip\tPhone Number\tEmail");
		        	System.out.println("========================================================================================");
		        	book.stream().sorted(Comparator.comparing(AddressBook::getZip)).forEach(ob->System.out.println(ob.firstName+"\t\t"+ob.lastName+"\t\t"+ob.address+"\t"+ob.city+"\t"+ob.state+"\t"+ob.zip+"\t"+ob.phoneNumber+"\t"+ob.email));;
		        	System.out.println("========================================================================================");
		       }
		}
	}
	
	public void WriteInFile() throws IOException
	{
        File fileToSaveObject=new File("C:\\Users\\Jeeva\\Desktop\\BridgeLabz-java\\AddressBookSystem\\Text File\\AddressBook.txt");
        if(fileToSaveObject.exists())
        {
        	System.out.println("Files exists to writing ");
        }
        else
        	fileToSaveObject.createNewFile();
        
        FileOutputStream fileOut = new FileOutputStream(fileToSaveObject);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(addressBookSet);
        out.close();
        fileOut.close(); 
	}
	
	public void readingFromFile() throws IOException, ClassNotFoundException 
	{
        File fileToReadObject=new File("C:\\Users\\Jeeva\\Desktop\\BridgeLabz-java\\AddressBookSystem\\Text File\\AddressBook.txt");
        FileInputStream fileIn = new FileInputStream(fileToReadObject);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        if(fileToReadObject.exists())
        {
             Map<String,ArrayList<AddressBook>> address= (Map<String, ArrayList<AddressBook>>) in.readObject(); 
             for(Map.Entry<String, ArrayList<AddressBook>> record : address.entrySet())
     		{
      			System.out.println("*********************************************"+record.getKey()+"***************************************");
     			ArrayList<AddressBook> book=record.getValue();
     			System.out.println("========================================================================================");
     			System.out.println("First Name\tLast Name\tAddressCity\tState\tZip\tPhone Number\tEmail");
     			System.out.println("========================================================================================");
     			book.stream().sorted(Comparator.comparing(AddressBook::getFirstName)).forEach(ob->System.out.println(ob.firstName+"\t\t"+ob.lastName+"\t\t"+ob.address+"\t"+ob.city+"\t"+ob.state+"\t"+ob.zip+"\t"+ob.phoneNumber+"\t"+ob.email));;
     			System.out.println("========================================================================================");
     		}
        }
        else
        {
        	System.out.println("File in not exist");
        }
        in.close();
        fileIn.close();
    }
	
	public void writeDataInCSV() throws IOException 
	{
         Path path = Paths.get("C:\\Users\\Jeeva\\Desktop\\BridgeLabz-java\\AddressBookSystem\\Text File\\AddressBookCSV.csv");
    CSVWriter csvWriter = new CSVWriter(new FileWriter("C:\\Users\\Jeeva\\Desktop\\BridgeLabz-java\\AddressBookSystem\\Text File\\AddressBookCSV.csv"));
    if (Files.exists(path)){
        System.out.println("File Exists, so appending it ");
    }
    else {
        System.out.println("File not exists, So creating new file ");
        Files.createFile(path);
    }

    ArrayList<String[]> data = new ArrayList<>();
    data.add(new String[] {"FirstName","LastName" ,"Address" ,"City","State","ZipCode","Phone Number","EmailID"});
    for (int i = 0; i < addressBook.size(); i++) {
        data.add( new String[] {addressBook.get(i).firstName , addressBook.get(i).lastName , addressBook.get(i).address ,
        		addressBook.get(i).city, addressBook.get(i).state , addressBook.get(i).zip , addressBook.get(i).phoneNumber , addressBook.get(i).email});
    }
    csvWriter.writeAll(data);
    csvWriter.close();
}
	
	public void readFromCSVFile() throws CsvValidationException, IOException 
	{
        Path path = Paths.get("C:\\Users\\Jeeva\\Desktop\\BridgeLabz-java\\AddressBookSystem\\Text File\\AddressBookCSV.csv");
        File file = new File("C:\\Users\\Jeeva\\Desktop\\BridgeLabz-java\\AddressBookSystem\\Text File\\AddressBookCSV.csv");
        Scanner inputStream = new Scanner(file);
        while (inputStream.hasNext()){
            String data = inputStream.next();
            System.out.println(data);
        }
        inputStream.close();
    }
	
	public void writingInJSONFile() throws IOException {
        Path path = Paths.get("C:\\Users\\Jeeva\\Desktop\\BridgeLabz-java\\AddressBookSystem\\Text File\\AddressBookJSON.json");
        Gson gson = new Gson();
        ArrayList<String > tp = new ArrayList<>();

        if (Files.exists(path))
            System.out.println("File exists already and will append");
        else {
            System.out.println("Files does not exists");
            Files.createFile(path);
        }

        for (int i=0; i<addressBook.size(); i++){
            tp.add(gson.toJson(addressBook.get(i)));
        }
        System.out.println(tp);
        FileWriter fw = new FileWriter(String.valueOf(path));

        for (String str : tp)
            fw.write(str + "\n");
        fw.close();

        System.out.println("Written file Successfully");
    }
	 public void readingFromJSONFile() throws IOException 
	 {
	        Path path = Paths.get("C:\\Users\\Jeeva\\Desktop\\BridgeLabz-java\\AddressBookSystem\\Text File\\AddressBookJSON.json");
	        if (!Files.exists(path))
	            System.out.println("File not exists");
	        else {
	            System.out.println(Files.readAllLines(path));
	        }
	    }
	public static void main(String[] args) throws Exception 
	{	
		AddressBookMain abm=new AddressBookMain();
		int i=1;
		while(true)
		{
			System.out.println("Enter Address book name");
			Scanner sc1=new Scanner(System.in);
			String name=sc1.nextLine();
    		boolean condition=true;
    		abm.addressBook=new ArrayList<AddressBook>();
    	    abm.addressBookSet.put(name, abm.addressBook);
		    while(condition)
            {
			     int choice = 0;
			     System.out.println("1. Add person \n2. Edit person \n3. Delete person \n4. View all contacts\n5. Search By City/State\n6. View By City/State\n7. Count By City/State\n8. Sort By City/State/Zip\n9. Write Into file\n10. Read from file\n11. Write In CSV\n12. Read from CSV\n13. Write in JSON\n14. Read from JSON\n15. Exit");
			     System.out.println("Enter your choice :");
			     Scanner sc=new Scanner(System.in);
			     choice=sc.nextInt();
			     switch(choice)
			     {
			        case 1:abm.addPerson();
					       break;
			        case 2:abm.editPerson();
					       break;
			        case 3:abm.deletePerson();
					       break;
			        case 4:abm.viewAllContacts();
					       break;
			        case 5:abm.searchByCityOrState();
			        	   break;
			        case 6:abm.viewByCityOrState();
			        		break;
			        case 7:abm.countByCityOrState();
			               break;
			        case 8:abm.sortByCityOrStateOrZip();
			               break;
			        case 9:abm.WriteInFile();
			        		break;
			        case 10:abm.readingFromFile();
			        		break;
			        case 11:abm.writeDataInCSV();
			        		break;
			        case 12:abm.readFromCSVFile();
			        		break;
			        case 13:abm.writingInJSONFile();
			        		break;
			        case 14:abm.readingFromJSONFile();
			        		break;
			        case 15:condition=false;
			               break;
		     	 }
	         }
		   
		    System.out.println("Do you want add another address book : y/n");
		    String choice=sc1.nextLine();
		    if(choice.equals("y"))
		    {
		    	i++;
		    	continue;
		    }
		    else
		    {
		    	break;
		    }
		}
		System.out.println("Thanks you");
	}

}
