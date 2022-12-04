package reverci.view;

/**
 * Класс, отвечающий за декоративное оформление игрового процесса в консоли.
 */
public class GameProcessDecorator {
    private static final String menu = "⟣⟢⟣⟢⟣⟢⟣⟢⟣⟢⟣⟢ МЕНЮ ⟣⟢⟣⟢⟣⟢⟣⟢⟣⟢⟣⟢";
    private static final String end = "⟣⟢⟣⟢⟣⟢⟣⟢⟣⟢ КОНЕЦ ИГРЫ ⟣⟢⟣⟢⟣⟢⟣⟢⟣⟢";
    private static final String previousMove = "⟣⟢⟣⟢⟣⟢⟣⟢⟣ ПРЕДЫДУЩИЙ ХОД ⟢⟣⟢⟣⟢⟣⟢⟣⟢";
    private static final String witheMove =    "▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒ ХОД БЕЛЫХ ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒";
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
