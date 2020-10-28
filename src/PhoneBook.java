import java.util.Scanner;

public class PhoneBook {

    public static void main(String[] args) {
        //Добавить считывание ввода пользователя в цикле


        Scanner scanner = new Scanner(System.in);
        String nameOrNumber;
        String number;
        String[][] book = new String[1][2];
        String failMessage = "Failed, try again";
        boolean isFinished = false;

        while (!isFinished) {

            //get name from command line

            nameOrNumber = scanner.nextLine();

            //check if name satisfy minimal format requirements
            if (!checkName(nameOrNumber) && !checkPhoneNumber(nameOrNumber)) {
                //check if it match phone number.
                // If it does, formatting phone number and searching name by phone number
                messenger(0);
                continue;
            } else if (checkPhoneNumber(nameOrNumber)) {
                //if it match number format, searching name matches and showing them
                number = formatPhoneNumber(nameOrNumber);
                int index = getIndexByNumber(book, number);
                System.out.println(book[index][0]);
                continue;
            }

            //format name
            nameOrNumber = formatName(nameOrNumber);

            //check if name already exist, -1 means no matches have been found
            int index = getIndexByName(book, nameOrNumber);
            if (index != -1) {
                //print number if match found, start new cycle
                number = book[index][1];
                System.out.println(number);
                continue;
            }

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
            book = add(book, nameOrNumber, number);

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

    public static String[][] add(String[][] book, String name, String number) {
        //if last index elements(columns) don't equal null, create new array with original row length + 5 new rows
        if (book[book.length - 1][0] != null && book[book.length - 1][1] != null) {
            int newLength = book.length + 1;
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

        //return new bigger array
        return book;
    }


    public static void list(String[][] book) {

        //sorting
        sortByAlphabeticalOrder(book);

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


    //at first i wanted to merge printNumberByName with getIndexByName and printNameByNumber with getIndexByNumber
    //but then i decided to keep them separated
    public static void printNumberByName(String[][] book, String name) {
        int index = getIndexByName(book, name);
        System.out.println(book[index][0]);
    }

    public static void printNameByNumber(String[][] book, String number) {
        int index = getIndexByName(book, number);
        System.out.println(book[index][1]);
    }


    public static int getIndexByName(String[][] book, String name) {
        for (int i = 0; i < book.length; i++) {
            if (book[i][0] != null && book[i][0].equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public static int getIndexByNumber(String[][] book, String name) {
        for (int i = 0; i < book.length; i++) {
            if (book[i][1] != null && book[i][1].equals(name)) {
                return i;
            }
        }
        return -1;
    }

    //sorting by ByAlphabetical Order with help of String.compareTo
    public static void sortByAlphabeticalOrder(String[][] book) {
        boolean needSort = true;
        while (needSort) {
            needSort = false;
            for (int j = 0; j < book.length - 1; j++) {
                if (book[j][0].compareTo(book[j + 1][0]) > 1) {
                    String tmp = book[j][0];
                    book[j][0] = book[j + 1][0];
                    book[j + 1][0] = tmp;
                    needSort = true;
                    break;
                }
            }
        }
    }

    //ToDo
    //method with all messages
    public static void messenger(int code) {
        //array with messages
        String[] messages = new String[10];
        messages[0] = "Name doesn't match format, try again ";

        System.out.println(messages[code]);
    }

}
