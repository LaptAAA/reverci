package reverci.view;

import reverci.model.Chip;

/**
 * Класс, отвечающий за декоративное оформление фишек в консоли.
 */
public class ChipDecorator {
    /**
     * Основной цвет для печати.
     */
    public static final String defaultColor = "\u001B[0m";
    /**
     * Цвет для возможных фишек.
     */
    public static final String  possibleColor = "\u001B[32m";
    /**
     * Обозначение белой фишки.
     */
    public static final char white = '●';
    /**
     * Обозначение чёрной фишки.
     */
    public static final char black = '◯';
    /**
     * Обозначение возможной фишки.
     */
    public static final char possible = '◎';
    /**
     * Обозначение не существующей фишки.
     */
    public static final char empty = ' ';
    /**
     * Получить обозначение возможной фишки в нужном цвете.
     * @return обозначение возможной фишки в нужном цвете.
     */

    public static String stringPossible() {
        return possibleColor + possible + defaultColor;
    }

    /**
     * Получить обозначение фишки.
     * @param chip фишка
     * @return чаровое обозначение фишки.
     */
    public static char getChipDesign(Chip chip) {
        if (chip.getExistence()) {
            if (chip.getColor()) {
                return white;
            } else {
                return black;
            }
        } else {
            if (chip.getPossibility()) {
                return possible;
            } else {
                return empty;
            }
        }
    }
}
