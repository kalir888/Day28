package javapractice;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

public class AddressBookIOService {
    private static AddressBookIOService instance;
    public static String fileName = "ContactBook.txt";

    private AddressBookIOService() {
    }

    public static AddressBookIOService getInstance() {
        if(instance == null)
            instance = new AddressBookIOService();
        return instance;
    }

    public void writeData(Set<Contact> contacts) {
        StringBuffer contactBuffer = new StringBuffer();
        contacts.forEach(contact -> {
            String contactData = contact.toString().concat("\n");
            contactBuffer.append(contactData);
        });

        try {
            Files.write(Paths.get(fileName),contactBuffer.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printData() {
        try {
            Files.lines(new File(fileName).toPath()).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
