package javapractice;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class AddressBookStorage {
    private static AddressBookStorage instance;
    private HashMap<String,AddressBook> addressBookLibrary = new HashMap<>();

    private AddressBookStorage() {
    }

    public static AddressBookStorage getInstance() {
        if(instance == null)
            instance = new AddressBookStorage();
        return instance;
    }

    public boolean addressBookNameValidCheck(String name) {
        return !addressBookLibrary.containsKey(name);
    }

    public boolean contactNameDuplicateCheck(String name) {
        return addressBookLibrary.values().stream().anyMatch(addressBook -> addressBook.contactNameValidCheck(name));
    }

    public void printContactBelongToCity(String city) {
        List<List<Contact>> cityContacts = addressBookLibrary.values().stream().map(addressBook -> addressBook.getContactBelongToACity(city)).collect(Collectors.toList());
        if(!cityContacts.isEmpty())
            System.out.println("Contacts belong to " + city + " are: \n" + cityContacts);
        else
            System.out.println("There are no contacts belong to " + city);
    }

    public void printContactBelongToState(String state) {
        List<List<Contact>> stateContacts = addressBookLibrary.values().stream().map(addressBook -> addressBook.getContactBelongToAState(state)).collect(Collectors.toList());
        if(!stateContacts.isEmpty())
            System.out.println("Contacts belong to " + state + " are: \n" + stateContacts);
        else
            System.out.println("There are no contacts belong to " + state);
    }

    public long getContactCountOfCity(String city) {
        List<List<Contact>> cityContacts = addressBookLibrary.values().stream().map(addressBook -> addressBook.getContactBelongToACity(city)).collect(Collectors.toList());
        return cityContacts.size();
    }

    public long getContactCountOfState(String state) {
        List<List<Contact>> stateContacts = addressBookLibrary.values().stream().map(addressBook -> addressBook.getContactBelongToAState(state)).collect(Collectors.toList());
        return stateContacts.size();
    }

    public void addAddressBook(String key, AddressBook addressBook) {
        addressBookLibrary.put(key, addressBook);
    }

    public void removeAddressBook(String key) {
        addressBookLibrary.remove(key);
    }

    public AddressBook getAddressBook(String key) {
        return addressBookLibrary.get(key);
    }

    public HashMap<String, AddressBook> getAddressBookLibrary() {
        return addressBookLibrary;
    }
}
