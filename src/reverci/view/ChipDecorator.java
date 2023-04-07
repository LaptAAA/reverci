package reverci.view;

import reverci.model.Chip;

/**
 * Класс, отвечающий за декоративное оформление фишек в консоли.
 */
public class ChipDecorator {
    /**
     * Основной цвет для печати.
     */
    private static final String DEFAULT_COLOR = "\u001B[0m";
    /**
     * Цвет для возможных фишек.
     */
    private static final String POSSIBLE_COLOR = "\u001B[32m";
    /**
     * Обозначение белой фишки.
     */
    public static final char WHITE = '●';
    /**
     * Обозначение чёрной фишки.
     */
    public static final char BLACK = '◯';
    /**
     * Обозначение возможной фишки.
     */
    public static final char POSSIBLE = '◎';
    /**
     * Обозначение не существующей фишки.
     */
    private static final char EMPTY = ' ';

    /**
     * Получить обозначение возможной фишки в нужном цвете.
     *
     * @return обозначение возможной фишки в нужном цвете.
     */

    public static String stringPossible() {
        return POSSIBLE_COLOR + POSSIBLE + DEFAULT_COLOR;
    }

    /**
     * Получить обозначение фишки.
     *
     * @param chip фишка
     * @return чаровое обозначение фишки.
     */
    public static char getChipDesign(Chip chip) {
        if (chip.getExistence()) {
            if (chip.getColor()) {
                return WHITE;
            } else {
                return BLACK;
            }
        } else {
            if (chip.getPossibility()) {
                return POSSIBLE;
            } else {
                return EMPTY;
            }
        }
    }
}
