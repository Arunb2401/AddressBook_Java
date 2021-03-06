package addressBook;

import java.util.Scanner;

public class AddressBookMainClass {
	public static void main(String[] args) {
		System.out.println("Welcome to Address Book Program");
		Scanner scan = new Scanner(System.in);
		AddressBookContactDetails contactPerson = new AddressBookContactDetails();
		AddressBookOperations addressBookOperation = new AddressBookOperations();

		boolean terminate = false;
		while (terminate != true) {
			System.out.println("1: To add new Contact person");
			System.out.println("2: To Print all information");
			System.out.println("3: To Update the existing information");
			System.out.println("4: To Delete the contact");
			System.out.println("5: To short the Address First Name");
			System.out.println("6: To sort the Address by Zip Code");
			System.out.println("7: Search by city");
			System.out.println("8: Search by State");

			int option = scan.nextInt();
			scan.close();
			switch (option) {
			case 1:
				contactPerson = addressBookOperation.newAddressBook();
				System.out.println(addressBookOperation.save(contactPerson.getPhone(), contactPerson));
				break;
			case 2:
				addressBookOperation.printAllDetails();
				break;
			case 3:
				System.out.println("enter the Phone Number to update");
				long key = scan.nextLong();
				contactPerson = addressBookOperation.newAddressBook();
				addressBookOperation.updateValue(key, contactPerson);
				break;
			case 4:
				System.out.println("Enter the Phone Number to delete address");
				long phoneToDelete = scan.nextLong();
				addressBookOperation.deleteAddress(phoneToDelete);
				break;
			case 5:
				addressBookOperation.sortByfirstName();
				break;
			case 6:
				addressBookOperation.sortByZipCode();
				break;
			case 7:
				System.out.println("enter the City to Search");
				String inputcity = scan.next();
				addressBookOperation.searchByCity(inputcity, contactPerson);
				break;
			case 8:
				System.out.println("enter the State to Search");
				String inputState = scan.next();
				addressBookOperation.searchByState(inputState, contactPerson);
				break;

			default:
				System.out.println("Please select valid option");
				break;
			}

		}

	}
}
//AddressBook Contact Details -------------------
public class AddressBookContactDetails {

	private String firstName;
	private String lastName;
	private String Address;
	private String city;
	private String state;
	private int zip;
	private long phone;

	public String getfirstName() {
		return firstName;
	}

	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getlastName() {
		return lastName;
	}

	public void setlastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "AddressBook{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", Address='"
				+ Address + '\'' + ", city='" + city + '\'' + ", state='" + state + '\'' + ", zip=" + zip + ", phone="
				+ phone + '}';
	}
}

//AddressBook Operations------------------------------
public class AddressBookOperations implements AddressBookInterface {

	HashMap<Long, AddressBookContactDetails> addressBookContainer = new HashMap<Long, AddressBookContactDetails>();
	Scanner input = new Scanner(System.in);

	public HashMap<Long, AddressBookContactDetails> save(long phoneNumber, AddressBookContactDetails AddressBook) {
		addressBookContainer.put(phoneNumber, AddressBook);
		return addressBookContainer;
	}

	@Override
	public AddressBookContactDetails newAddressBook() {
		AddressBookContactDetails AddressBook = new AddressBookContactDetails();
		System.out.println("Enter the First Name");
		String firstName = input.next();
		System.out.println("Enter the Last Name");
		String lastName = input.next();
		System.out.println("Enter The Address");
		String address = input.next();
		System.out.println("Enter the city");
		String city = input.next();
		System.out.println("Enter the state");
		String state = input.next();
		System.out.println("Enter the zip Code");
		int zip = input.nextInt();
		System.out.println("Enter the Phone number");
		long phone = input.nextLong();
		AddressBook.setfirstName(firstName);
		AddressBook.setlastName(lastName);
		AddressBook.setAddress(address);
		AddressBook.setCity(city);
		AddressBook.setState(state);
		AddressBook.setZip(zip);
		AddressBook.setPhone(phone);
		return AddressBook;
	}

	@Override
	public void printAllDetails() {
		Set keys = addressBookContainer.keySet();
		Iterator iterate = keys.iterator();
		Collection<AddressBookContactDetails> getValues = addressBookContainer.values();
		iterate = getValues.iterator();
		while (iterate.hasNext()) {
			System.out.println("Details are : " + iterate.next());
		}
	}

	@Override
	public HashMap updateValue(long key, AddressBookContactDetails addressBook) {

		if (addressBookContainer.containsKey(key) == false) {
			System.out.println("Key Not Found");
		} else if (addressBookContainer.containsKey(key) == true) {
			addressBookContainer.put(key, addressBook);
			System.out.println("Values Updated");
		}
		return addressBookContainer;
	}

	@Override
	public void deleteAddress(long key) {

		if (addressBookContainer.containsKey(key) == false) {
			System.out.println("Invalid Key !!!!!!!");
		} else {
			addressBookContainer.remove(key);
			System.out.println(key + " Deleted Successfully");

		}

	}

	@Override
	public void sortByfirstName() {
		List<AddressBookContactDetails> value = new ArrayList<>();
		if (addressBookContainer != null) {
			value.addAll(addressBookContainer.values());
			Collections.sort(value, new Comparator<AddressBookContactDetails>() {
				public int compare(AddressBookContactDetails Person1, AddressBookContactDetails Person2) {
					return Person1.getfirstName().compareTo(Person2.getfirstName());
				}
			});
		}
		for (AddressBookContactDetails valueList : value) {
			System.out.println(valueList);
		}
	}

	@Override
	public void sortByZipCode() {

		List<AddressBookContactDetails> value = new ArrayList<>();
		if (addressBookContainer != null) {
			value.addAll(addressBookContainer.values());

			Collections.sort(value, new Comparator<AddressBookContactDetails>() {
				public int compare(AddressBookContactDetails Person1, AddressBookContactDetails Person2) {
					return Person1.getZip() - Person2.getZip();
				}
			});
		}
		for (AddressBookContactDetails sortedValue : value) {
			System.out.println(sortedValue);
		}
	}

	public void searchByCity(String icity, AddressBookContactDetails addressBook) {

		Set<?> keys = addressBookContainer.keySet();
		Iterator<?> iterate = keys.iterator();
		Collection<AddressBookContactDetails> getValues = addressBookContainer.values();
		iterate = getValues.iterator();
		while (iterate.hasNext()) {
			System.out.println(addressBook.getCity() + "  " + icity);
			if (addressBook.getCity().equals(icity)) {
				System.out.println("Details are : " + addressBook.getfirstName() + "  " + addressBook.getlastName()
						+ "  " + addressBook.getState() + "  " + addressBook.getPhone() + " " + addressBook.getZip());
			}
			iterate.hasNext();
			break;
		}

	}

	public void searchByState(String inputState, AddressBookContactDetails addressBook) {

		Set<?> keys = addressBookContainer.keySet();
		Iterator<?> iterate = keys.iterator();
		Collection<AddressBookContactDetails> getValues = addressBookContainer.values();
		iterate = getValues.iterator();
		while (iterate.hasNext()) {
			System.out.println(addressBook.getState() + "  " + inputState);
			if (addressBook.getState().equals(inputState)) {
				System.out.println("Details are : " + addressBook.getfirstName() + "  " + addressBook.getlastName()
						+ "  " + addressBook.getPhone() + "  " + addressBook.getCity() + "  " + addressBook.getZip());

			}
			iterate.hasNext();
			break;
		}
	}
}

