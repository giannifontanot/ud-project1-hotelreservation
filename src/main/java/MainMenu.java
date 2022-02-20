import java.util.Scanner;

public class MainMenu {

    public static void main(String[] args) {

int userOption=5;
        do {
            System.out.println("1. Find and Reserve a Room");
            System.out.println("2. See my Reservations");
            System.out.println("3. Create an Account");
            System.out.println("4. Admin");
            System.out.println("5. Exit");
            System.out.println("\nSelect an Option from the Menu:");
            Scanner scanner = new Scanner(System.in);
            userOption=scanner.nextInt();
        }while(userOption <= 4);
    }
}
