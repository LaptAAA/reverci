package reverci;

import reverci.view.ViewMessages;

import java.util.Scanner;

public class InputConsole {
    Scanner scanner;
    InputConsole(Scanner s) {
        scanner = s;
    }

    /**
     * Получить координату X
     * @param s строка, из которой надо получить координату.
     * @return координата по Х (значение от 0 до 7).
     */
    int getX(String s) {
        s = s.replaceAll("[^A-H]", "");
        char[] letters = s.toCharArray();
        int x = Character.getNumericValue(letters[0] - 17);
        return x;
    }

    /**
     * Получить координату Y.
     * @param s строка, из которой надо получить координату.
     * @return координата по Y (значение от 0 до 7).
     */
    int getY(String s) {
        s = s.replaceAll("[^1-8]", "");
        char[] letters = s.toCharArray();
        int i1 = Character.getNumericValue(letters[0]);
        return 8 - i1;
    }

    /**
     * Проверка, что строка содержит число от 1 до 8.
     * @param s строка, которую нужно проверить.
     * @return true - строка содержит хотя бы одно число из диапазона, false - не имеет.
     */
    boolean haveNumber(String s) {
        s = s.replaceAll("[^1-8]", "");
        if (s.equals("")) {
            return false;
        }
        return true;
    }

    /**
     * Проверка, что строка содержит букву от A до H.
     * @param s строка, которую нужно проверить.
     * @return true - строка содержит хотя бы одно букву из диапазона, false - не имеет.
     */
    boolean haveLetter(String s) {
        s = s.replaceAll("[^A-H]", "");
        if (s.equals("") || s.length() > 1) {
            return false;
        }
        return true;
    }

    /**
     * Получить координаты.
     * @return int[] - пара координат, где сначала координата по X, затем координата по Y.
     */
    int[] getCoordinats()  {
        ViewMessages.printRequestCoordinates();
        boolean flag = true;
        String s = getString();

        while (flag) {
            s = s.replaceAll("[^A-H1-8]", "");
            if (!haveNumber(s) || !haveLetter(s)) {
                ViewMessages.printCoordinatesError();
                s = getString();
            } else {
                flag = false;
            }
        }
        return new int[]{getX(s), getY(s)};
    }

    /**
     * Получить вариант режима игры.
     * @return номер варианта игры.
     */
    public int getVar()  {
        String s = getString();
        boolean flag = true;
        while (flag) {
            s = s.replaceAll("[^1-4]", "");
            if (s.equals("")) {
                ViewMessages.printGameVariantError();
                s = getString();
            } else {
                flag = false;
            }
        }
        char[] letters = s.toCharArray();
        int i = Character.getNumericValue(letters[0]);
        return i;
    }

    /**
     * Получить строку, состоящую из заглавных букв.
     * @return строка, состоящую из заглавных букв.
     */
    String getString() {
        String s = scanner.nextLine().toUpperCase();
        return s;
    }

    /**
     * Получить ответ на вопрос об отмене хода.
     * @return true - игрок хочет отменить ход, false - не хочет.
     */
    boolean getBoolean()  {
        boolean answer = false;
        ViewMessages.printRequestReturn("Y");
        String s = getString();
        s = s.replaceAll("[^Y]", "");
        System.out.println(s);
        if (s.equals("Y")) {
            answer = true;
        }
        return answer;
    }
}
