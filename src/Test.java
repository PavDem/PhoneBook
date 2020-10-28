import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = "A";
        String line2 = "B";

        /*
        char first = line.charAt(0);
        char second = line.charAt(1);

        if (first > second) {
            System.out.println(first + " > " + second);
        } else {
            System.out.println(second + " > " + first);
        }
        */
        if (line.compareTo(line2) > 1) {
            System.out.println(line + " < " + line2);
        } else {
            System.out.println(line + " > " + line2);
        }
    }
}
