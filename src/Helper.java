import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Helper {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final char[] CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    public static void waitEnter() {
        System.out.print("Enter to continues...");
        SCANNER.nextLine();
    }

    public static int getInt(String statement) {
        System.out.print(statement);

        while (true) {
            try {
                return Integer.parseInt(SCANNER.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Please input again: ");
            }
        }
    }

    public static int getInt(String statement, int min, int max) {
        int value = getInt(statement);

        return min <= value && value <= max ? value : getInt(String.format("Please input from %d to %d: ", min, max));
    }

    public static int[] getRange(String statement) {
        System.out.print(statement);

        while (true) {
            try {
                String[] values = (SCANNER.nextLine() + " ").split(",");

                int[] range = new int[2];
                range[0] = values[0].trim().length() == 0 ? 0 : Integer.parseInt(values[0].trim());
                range[1] = values[1].trim().length() == 0 ? Integer.MAX_VALUE : Integer.parseInt(values[1].trim());

                return range;
            } catch (Exception e) {
                System.out.print("Please input again: ");
            }
        }
    }

    public static String getStringAcceptNull(String statement) {
        System.out.print(statement);

        return SCANNER.nextLine().trim();
    }

    public static String getString(String statement) {
        while (true) {
            String temp = getStringAcceptNull(statement);

            if (!temp.isEmpty()) {
                return temp;
            }
        }
    }

    public static String generateRandomString() {
        StringBuilder stringBuffer = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            stringBuffer.append(getRandomChar());
        }

        return stringBuffer.toString();
    }

    private static char getRandomChar() {
        return CHARSET[ThreadLocalRandom.current().nextInt(36)];
    }
}
