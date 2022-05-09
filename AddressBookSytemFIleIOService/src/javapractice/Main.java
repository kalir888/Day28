package javapractice;

import java.util.Scanner;
import java.util.Set;

public class Main {
    AddressBookStorage addressBookStorage = AddressBookStorage.getInstance();

    public void handleUserMenuOption(int choice) {
        Scanner get = new Scanner(System.in);

        switch(choice) {
            case 1:
                create();
                break;
            case 2:
                System.out.println("Select \n1.print Contact\n2.print Contacts\n3.print Count of Contacts");
                int printChoice = get.nextInt();
                switch(printChoice) {
                    case 1:
                        System.out.println("Enter the name of an Address Book");
                        String bookName = get.next();
                        if (!addressBookStorage.addressBookNameValidCheck(bookName)) {
                            AddressBook addressBook = addressBookStorage.getAddressBook(bookName);
                            UserInterface userInterface = UserInterface.getInstance();
                            userInterface.print(addressBook.getContactList());
                        }else
                            System.out.println("Address Book name not found");
                        break;
                    case 2:
                        System.out.println("""
                                Select
                                1.Belong to an Address book
                                2.Belong to a City
                                3.Belong to a State\s""");
                        printChoice = get.nextInt();
                        switch(printChoice) {
                            case 1:
                                System.out.println("Enter the Address Book name");
                                AddressBook addressBook = addressBookStorage.getAddressBook(get.next());
                                System.out.println("""
                                        Select:
                                        1.Normal
                                        2.Sorted by Name
                                        3.Sorted by City
                                        4.Sorted by State               
                                        5.Sorted by Pin\s""");
                                switch(get.nextInt()) {
                                    case 1:
                                        UserInterface userInterface = UserInterface.getInstance();
                                        userInterface.print(addressBook.getContactList());
                                        break;
                                    case 2:
                                        addressBook.printSortedContactsByName();
                                        break;
                                    case 3:
                                        addressBook.printSortedContactsByCity();
                                        break;
                                    case 4:
                                        addressBook.printSortedContactsByState();
                                        break;
                                    case 5:
                                        addressBook.printSortedContactsByPin();
                                        break;
                                    default:
                                        System.out.println("Invalid Option");
                                        break;
                                }
                                break;
                            case 2:
                                System.out.println("Enter the City name");
                                addressBookStorage.printContactBelongToCity(get.next());
                                break;
                            case 3:
                                System.out.println("Enter the State name");
                                addressBookStorage.printContactBelongToState(get.next());
                                break;
                            default:
                                System.out.println("Invalid Print option");
                                break;
                        }
                        break;
                    case 3:
                        System.out.println("""
                                select an option:
                                1.Contact Count of a City
                                2.Contact Count of a State
                                3.Contact Count of an AddressBook\s""");
                        int countChoice = get.nextInt();
                        switch(countChoice) {
                            case 1:
                                System.out.println("Enter the City");
                                String city = get.next();
                                System.out.println("Contact count of " + city + " is " + addressBookStorage.getContactCountOfCity(city));
                                break;
                            case 2:
                                System.out.println("Enter the State");
                                String state = get.next();
                                System.out.println("Contact count of " + state + " is " + addressBookStorage.getContactCountOfState(state));
                                break;
                            case 3:
                                System.out.println("Enter the AddressBook name");
                                String name = get.next();
                                System.out.println("Contact count of " + name + " is " + addressBookStorage.getAddressBook(name).getContactList().size());
                                break;
                            default:
                                System.out.println("Invalid Count Option");
                                break;
                        }
                        break;
                    default:
                        System.out.println("Invalid Print option");
                        break;
                }
                break;
            case 3:
                System.out.println("Enter the name of an Address Book");
                String bookNameToEdit = get.next();
                if(!addressBookStorage.addressBookNameValidCheck(bookNameToEdit)) {
                    AddressBook addressBook = addressBookStorage.getAddressBook(bookNameToEdit);
                    System.out.println("Enter First Name of the Contact to edit");
                    Contact contactToEdit = addressBook.getContact(get.next());
                    if(contactToEdit != null)
                        edit(contactToEdit);
                    else
                        System.out.println("Contact Name not found in this Address Book");
                }else
                    System.out.println("Address Book name not found");
                break;
            case 4:
                System.out.println("Enter the name of an Address Book");
                String bookNameToRemoveContact = get.next();
                if(!addressBookStorage.addressBookNameValidCheck(bookNameToRemoveContact)) {
                    AddressBook addressBook = addressBookStorage.getAddressBook(bookNameToRemoveContact);
                    System.out.println("Enter First Name of the Contact to remove");
                    Contact contactToRemove = addressBook.getContact(get.next());
                    if(contactToRemove != null)
                        addressBook.remove(contactToRemove);
                    else
                        System.out.println("Contact Name not found in this AddressBook");
                }else
                    System.out.println("Address Book name not found");
                break;
            case 5:
                System.out.println("Enter the Address Book Name");
                String bookName = get.next();
                AddressBook addressBook = addressBookStorage.getAddressBook(bookName);
                AddressBookIOService addressBookIOService = AddressBookIOService.getInstance();
                addressBookIOService.writeData(addressBook.getContactList());
                break;
            case 6:
                AddressBookIOService addressBookIOService1 = AddressBookIOService.getInstance();
                addressBookIOService1.printData();
                break;
            case 7:
                System.out.println("Exiting from menu");
                break;
            default:
                System.out.println("Invalid Menu option");
                break;
        }
    }

    private void edit(Contact contact) {
        int updateChoice = 0;
        UserInterface userInterface = UserInterface.getInstance();
        while(updateChoice <= 8) {
            if(updateChoice == 8) {
                System.out.println("Returning to Menu");
                break;
            }
            updateChoice = userInterface.showEditMenu();
            handleUserEditOption(updateChoice,contact);
        }
    }

    private void handleUserEditOption(int updateChoice, Contact contact) {
        Scanner get = new Scanner(System.in);
        switch(updateChoice) {
            case 1:
                System.out.println("Enter the new Last Name");
                contact.lastName = get.next();
                break;
            case 2:
                System.out.println("Enter the new Address");
                contact.address = get.next();
                break;
            case 3:
                System.out.println("Enter the new City");
                contact.city = get.next();
                break;
            case 4:
                System.out.println("Enter the new State");
                contact.state = get.next();
                break;
            case 5:
                System.out.println("Enter the new Pin-code");
                contact.pinCode = get.next();
                break;
            case 6:
                System.out.println("Enter the new PhoneNumber");
                contact.phoneNumber = get.next();
                break;
            case 7:
                System.out.println("Enter the new Email Id");
                contact.emailId = get.next();
                break;
            default:
                System.out.println("Invalid Option");
                break;
        }
    }

    private void create() {
        Scanner get = new Scanner(System.in);
        System.out.println("Select to create new\n1.AddressBook\n2.Contact");
        int userChoice = get.nextInt();
        switch(userChoice) {
            case 1:
                System.out.println("Enter the Name of Address Book\nnote: don't give space in name");
                String name = get.next();
                if(addressBookStorage.addressBookNameValidCheck(name)) {
                    AddressBook addressBook = new AddressBook();
                    addressBookStorage.addAddressBook(name, addressBook);
                }else
                    System.out.println("Address book name already exist");
                break;
            case 2:
                System.out.println("Enter the name of an Address Book");
                String bookName = get.next();
                if (!addressBookStorage.addressBookNameValidCheck(bookName)) {
                    AddressBook addressBook = addressBookStorage.getAddressBook(bookName);
                    System.out.println("Enter the First Name");
                    String nameToAdd = get.next();
                    if(!addressBookStorage.contactNameDuplicateCheck(nameToAdd)) {
                        Contact contact = new Contact(nameToAdd);
                        String SKIP = "1";
                        System.out.println("Enter the Last Name\n to skip the step press 1");
                        String temp = get.next();
                        if (!temp.equals(SKIP))
                            contact.lastName = temp;
                        System.out.println("Enter the Address\n to skip the step press 1");
                        temp = get.next();
                        if (!temp.equals(SKIP))
                            contact.address = temp;
                        System.out.println("Enter the city\n to skip the step press 1");
                        temp = get.next();
                        if (!temp.equals(SKIP))
                            contact.city = temp;
                        System.out.println("Enter the state\n to skip the step press 1");
                        temp = get.next();
                        if (!temp.equals(SKIP))
                            contact.state = temp;
                        System.out.println("Enter the pin-code\n to skip the step press 1");
                        temp = get.next();
                        if (!temp.equals(SKIP))
                            contact.pinCode = temp;
                        System.out.println("Enter the phone number");
                        contact.phoneNumber = get.next();
                        System.out.println("Enter the email id\n to skip the step press 1");
                        temp = get.next();
                        if (!temp.equals(SKIP))
                            contact.emailId = temp;
                        addressBook.add(contact);
                    }else
                        System.out.println("Contact name already exist");
                }else
                    System.out.println("Address Book name not found");
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Address Book System");

        UserInterface userInterface = UserInterface.getInstance();
        Main main = new Main();

        int choice = 0;
        while(choice < 7) {
            choice = userInterface.showMenu();
            main.handleUserMenuOption(choice);
        }
    }
}
