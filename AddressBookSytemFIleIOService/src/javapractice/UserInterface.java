package javapractice;

import java.util.Scanner;
import java.util.Set;

public class UserInterface {
    private static UserInterface instance;

    private UserInterface() {
    }

    public static UserInterface getInstance() {
        if(instance == null)
            instance = new UserInterface();
        return instance;
    }

    public void print(Set<Contact> contactList) {
        for (Contact contact : contactList) {
            System.out.println(contact);
        }
    }

    public int showMenu() {
        System.out.println("Select an option: \n1.Create 2.Print 3.Edit 4.Remove 5.Save 6.View 7.Exit");
        Scanner get = new Scanner(System.in);
        return get.nextInt();
    }

    public int showEditMenu() {
        System.out.println("""
                Select an Option to edit:
                1.LastName
                2.Address
                3.City
                4.State
                5.Pin-Code
                6.PhoneNumber
                7.EmailId
                8.Return to Menu\s""");
        Scanner get = new Scanner(System.in);
        return get.nextInt();
    }
}

