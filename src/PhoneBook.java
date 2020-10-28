import java.util.Scanner;

public class PhoneBook {

    public static void main(String[] args) {
        //Добавить считывание ввода пользователя в цикле


        Scanner scanner = new Scanner(System.in);
        String name;
        String number;
        String[][] book = new String[5][2];


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
        //if last index elements(columns) don't equal null, create new array with original row length + 5 new rows
        if (book[book.length - 1][0] != null && book[book.length - 1][1] != null) {
            int newLength = book.length + 5;
            String[][] newBook = new String[newLength][2];

            //copy old records to new array
            for (int i = 0; i < book.length; i++) {
                newBook[i][0] = book[i][0];
                newBook[i][1] = book[i][1];
            }

            //change to old name (newBook -> book)
            book = newBook;
        }

        //search for next empty(null) index
        for (int i = 0; i < book.length; i++) {
            //if, for some reason, row has only a name or a number, we treat it as it was empty
            if (book[i][0] == null || book[i][1] == null) {
                //add name and number
                book[i][0] = name;
                book[i][1] = number;
                break;
            }
        }

        //add logic
    }

    public static void list(String[][] book) {
        //print phone book
        for (int i = 0; i < book.length; i++) {
            String name = book[i][0];
            String number = book[i][1];
            //check if both name and number filled, don't show otherwise
            if (name != null && number != null) {
                String message = "Name is " + name + ", number is" + " " + number;
                System.out.println(message);
            }
        }
    }

    public static int findIndexByName(String[][] book, String name) {
        for (int i = 0; i < book.length; i++) {
            if (book[i][0] != null && book[i][0].equals(name)) {
                return i;
            }
        }
        return -1;
    }

}
