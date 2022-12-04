package reverci.view;

/**
 * Класс, отвечающий за декоративное оформление игрового процесса в консоли.
 */
public class GameProcessDecorator {
    /**
     * Оформление для меню.
     */
    private static final String menu = "⟣⟢⟣⟢⟣⟢⟣⟢⟣⟢⟣⟢ МЕНЮ ⟣⟢⟣⟢⟣⟢⟣⟢⟣⟢⟣⟢";
    /**
     * Оформление для конца игры.
     */
    private static final String end = "⟣⟢⟣⟢⟣⟢⟣⟢⟣⟢ КОНЕЦ ИГРЫ ⟣⟢⟣⟢⟣⟢⟣⟢⟣⟢";
    /**
     * Оформление для предыдущего хода.
     */
    private static final String previousMove = "⟣⟢⟣⟢⟣⟢⟣⟢⟣ ПРЕДЫДУЩИЙ ХОД ⟢⟣⟢⟣⟢⟣⟢⟣⟢";
    /**
     * Оформление для хода белых.
     */
    private static final String witheMove =    "▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒ ХОД БЕЛЫХ ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒";
    /**
     * Оформление для хода черных.
     */
    private static final String blackMove =    "░░░░░░░░░░░░░░░░ ХОД ЧЁРНЫХ ░░░░░░░░░░░░░░░";

    /**
     * Печать оформления хода.
     * @param color цвет походившего игрока.
     */
    public static void printMove(boolean color) {
        if (color) {
            System.out.println("\n" + GameProcessDecorator.witheMove + "\n");
        } else {
            System.out.println("\n" + GameProcessDecorator.blackMove + "\n");
        }
    }

    /**
     * Печать оформления меню.
     */
    public static void printMenu() {
        System.out.println(menu);
    }

    /**
     * Печать оформления конца игры.
     */
    public static void printEnd() {
        System.out.println(end);
    }

    /**
     * Печать оформления предыдущего хода.
     */
    public static void printPreviousMove() {
        System.out.println(previousMove);
    }
}
