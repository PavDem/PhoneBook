import java.util.Scanner;

public class PhoneBook {

    public static void main(String[] args) {
        //Добавить считывание ввода пользователя в цикле


        Scanner scanner = new Scanner(System.in);
        String name;
        String number;
        String[][] book = new String[5][2];
        //Первая колонка - name, вторая - number

        name = scanner.next();

        if (!checkName(name))
            return;



        name = formatName(name);


        if (!checkPhoneNumber(name))
            return;

        number = scanner.next();

        number = formatPhoneNumber(number);

        add(book, name, number);

    }


    public static boolean checkName(String name) {
        return true;
    }

    public static boolean checkPhoneNumber(String phoneNumber) {
        return true;
    }

    public static String formatName(String name) {
        return "";
    }

    public static String formatPhoneNumber(String number) {
        return "";
    }

    public static void add(String[][] book, String name, String number) {
        //add logic
    }

    public static void list(String[][] book) {
        //print phone book
    }

    public static int findIndexByName(String[][] book, String name) {
        for (int i = 0; i < book.length; i++) {
            if(book[i][0] == name) {
                return i;
            }
        }
        return -1;
    }

}
