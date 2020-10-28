import java.util.Scanner;

public class PhoneBook {

    public static void main(String[] args) {
        //Добавить считывание ввода пользователя в цикле


        Scanner scanner = new Scanner(System.in);
        String name;
        String number;
        String[][] book = new String[5][2];
        String failMessage = "Failed, try again";
        boolean isFinished = false;

        while (!isFinished) {

            //get name from command line
            name = scanner.nextLine();

            //check if name satisfy minimal format requirements
            if (!checkName(name)) {
                System.out.println(failMessage);
                continue;
            }
            //format name
            name = formatName(name);

            //get number from command line
            number = scanner.nextLine();

            //check if number satisfy minimal format requirements
            if (!checkPhoneNumber(number)) {
                System.out.println(failMessage);
                continue;
            }

            //format number
            number = formatPhoneNumber(number);

            //add new record too book
            add(book, name, number);

            list(book);

        }

    }


    public static boolean checkName(String name) {
        //check if name satisfy minimal format requirements
        String[] words = name.trim().split(" ");
        return words.length == 3;
    }

    private static String formatName(String name) {
        String[] words = name.trim().split(" ");
        sortByLength(words);
        String result = "";
        for (int i = 0; i < words.length; i++) {
            String str = words[i];
            char firstChar = str.charAt(0);
            if (!Character.isUpperCase(firstChar)) {
                result += Character.toUpperCase(firstChar) + str.substring(1) + " ";
            } else {
                result += str + "";
            }
        }
        return result.trim();
    }

    private static void sortByLength(String[] words) {
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = 0; j < words.length - 1; j++) {
                if (words[j].length() > words[j + 1].length()) {
                    String tmp = words[j];
                    words[j] = words[j + 1];
                    words[j + 1] = tmp;
                }
            }
        }
    }

    public static boolean checkPhoneNumber(String phoneNumber) {
        String clean = phoneNumber.replaceAll("[^0-9]", "");
        return clean.length() == 11;
    }

    public static String formatPhoneNumber(String number) {
        String clean = number.replaceAll("[^0-9]", "");
        String result = "+7" + " " + clean.substring(1, 4) + " " +
                clean.substring(4, 7) + " " + clean.substring(7, 9) + " " + clean.substring(9);

        return result;
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
                String message = name + ": " + number;
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
