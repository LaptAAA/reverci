package reverci.view;

import reverci.model.Chip;

import java.util.Set;

/**
 * Класс, отвечающий за оформление сообщений в консоль.
 */
public class Messages {
    public static final char[] HORIZONTAL_COORDINATES = "ABCDEFGH".toCharArray();
    private static final String MENU =
            """
                    Выберите действие:
                    1. Игра с компьютером (сложность: легкая);
                    2. Игра с компьютером (сложность: высокая);
                    3. Игра на двух игроков;
                    4. Выход.""";
    private static final String PROMPT = ChipDecorator.WHITE + " - белая фишка,\n"
            + ChipDecorator.BLACK + " - черная фишка,\n"
            + (ChipDecorator.stringPossible()) + " - доступный ход.";

    public static void printErrorMove() {
        System.out.println("На текущем ходе нельзя поставить фишку на эту клетку.\n" +
                "Попробуйте еще раз.");
    }

    public static void printSkipping(boolean color) {
        if (color) {
            System.out.println("Белые пропускают ход.");
        } else {
            System.out.println("Чёрные пропускают ход.");
        }

    }

    public static void printResults(int resultBlack, int resultWithe) {
        System.out.println("Игра окончена!");
        if (resultBlack > resultWithe) {
            System.out.println("Победили чёрные!\n");
        } else if (resultBlack < resultWithe) {
            System.out.println("Победили белые!\n");
        } else {
            System.out.println("Ничья!\n");
        }
        System.out.println("Счёт чёрных в игре: " + resultBlack);
        System.out.println("Счёт белых в игре: " + resultWithe);
    }

    public static void printHello() {
        System.out.println("\nДобро пожаловать в игру \"Реверси\"!\n");
    }

    public static void printBestScore(int maxScoreBlack, int maxScoreWithe) {

        System.out.println("\nТекущий лучший чёрных счет за все игры: " + maxScoreBlack);
        System.out.println("Текущий лучший белых счет за все игры: " + maxScoreWithe);
        System.out.println();
    }

    public static void printMenu() {
        System.out.println(MENU);
    }

    public static void printPrompt() {
        System.out.println(PROMPT);
    }

    public static void printMove(boolean color, int x, int y) {
        if (color) {
            System.out.println("Белые походили на: " + HORIZONTAL_COORDINATES[x] + (8 - y));
        } else {
            System.out.println("Чёрные походили на: " + HORIZONTAL_COORDINATES[x] + (8 - y));
        }

    }

    public static void printVariants(Set<Chip> chips) {
        System.out.println("Возможные варианты хода: ");
        for (Chip chip : chips) {
            int x = chip.getX() + 1;
            int y = chip.getY();
            System.out.printf("%c%d ", HORIZONTAL_COORDINATES[x - 1], (8 - y));
        }
        System.out.println("\n");
    }

    public static void printRequestCoordinates() {
        System.out.println("Введите координаты клетки,\nв которую хотите поставить фишку\n(пример: A1 или 1А или a1 или 1a):");
    }

    public static void printCoordinatesError() {
        System.out.println("Введите координаты правильно!");
    }

    public static void printGameVariantError() {
        System.out.println("Введите правильный номер действия!");
    }

    public static void printRequestReturn(String returnMove) {
        System.out.println("Хотите сделать ход назад?\nВведите " + returnMove + " (да) или что угодно (нет):");
    }

    public static void printTerminationConditions() {
        System.out.println("Чтобы завершить программу в любой момент введите Ctrl D.");
    }

    public static void printEarlyTermination() {
        System.out.println("Вы вышли из \"Реверси\".");
    }


}
