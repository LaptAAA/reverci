package reverci.view;

/**
 * Класс, отвечающий за декоративное оформление игрового процесса в консоли.
 */
public class GameProcessDecorator {
    private static final String MENU = "⟣⟢⟣⟢⟣⟢⟣⟢⟣⟢⟣⟢ МЕНЮ ⟣⟢⟣⟢⟣⟢⟣⟢⟣⟢⟣⟢";
    private static final String END = "⟣⟢⟣⟢⟣⟢⟣⟢⟣⟢ КОНЕЦ ИГРЫ ⟣⟢⟣⟢⟣⟢⟣⟢⟣⟢";
    private static final String PREVIOUS_MOVE = "⟣⟢⟣⟢⟣⟢⟣⟢⟣ ПРЕДЫДУЩИЙ ХОД ⟢⟣⟢⟣⟢⟣⟢⟣⟢";
    private static final String WHITE_MOVE = "▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒ ХОД БЕЛЫХ ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒";
    private static final String BLACK_MOVE = "░░░░░░░░░░░░░░░░ ХОД ЧЁРНЫХ ░░░░░░░░░░░░░░░";

    /**
     * Печать оформления хода.
     *
     * @param color цвет походившего игрока.
     */
    public static void printMove(boolean color) {
        if (color) {
            System.out.println("\n" + GameProcessDecorator.WHITE_MOVE + "\n");
        } else {
            System.out.println("\n" + GameProcessDecorator.BLACK_MOVE + "\n");
        }
    }

    /**
     * Печать оформления меню.
     */
    public static void printMenu() {
        System.out.println(MENU);
    }

    /**
     * Печать оформления конца игры.
     */
    public static void printEnd() {
        System.out.println(END);
    }

    /**
     * Печать оформления предыдущего хода.
     */
    public static void printPreviousMove() {
        System.out.println(PREVIOUS_MOVE);
    }
}
