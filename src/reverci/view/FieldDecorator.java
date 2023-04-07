package reverci.view;

import reverci.model.Field;

/**
 * Класс, отвечающий за декоративное оформление игрового поля в консоли.
 */
public class FieldDecorator {

    static final private char[] TOP_LINE = "  ╔═══╦═══╦═══╦═══╦═══╦═══╦═══╦═══╗".toCharArray();

    static final private String MIDDLE_BORDER_LINE = " ║   ║   ║   ║   ║   ║   ║   ║   ║";

    static final private char[] MIDDLE_NODAL_LINE = "  ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣".toCharArray();

    static final private char[] BOTTOM_LINE = "  ╚═══╩═══╩═══╩═══╩═══╩═══╩═══╩═══╝".toCharArray();

    static final private char[] HORIZONTAL_COORDINATES_LINE = "    A   B   C   D   E   F   G   H  ".toCharArray();
    /**
     * Обозначение игрового поля с границами со столбцом координат по вертикали и со строкой координат по горизонтали.
     */
    static final private char[][] decoratedField = {
            TOP_LINE,
            ("8" + MIDDLE_BORDER_LINE).toCharArray(),
            MIDDLE_NODAL_LINE,
            ("7" + MIDDLE_BORDER_LINE).toCharArray(),
            MIDDLE_NODAL_LINE,
            ("6" + MIDDLE_BORDER_LINE).toCharArray(),
            MIDDLE_NODAL_LINE,
            ("5" + MIDDLE_BORDER_LINE).toCharArray(),
            MIDDLE_NODAL_LINE,
            ("4" + MIDDLE_BORDER_LINE).toCharArray(),
            MIDDLE_NODAL_LINE,
            ("3" + MIDDLE_BORDER_LINE).toCharArray(),
            MIDDLE_NODAL_LINE,
            ("2" + MIDDLE_BORDER_LINE).toCharArray(),
            MIDDLE_NODAL_LINE,
            ("1" + MIDDLE_BORDER_LINE).toCharArray(),
            BOTTOM_LINE,
            HORIZONTAL_COORDINATES_LINE};

    /**
     * Получить декорированное поля
     *
     * @param miniField поле с фишками, котрое нужнот декорировать.
     */
    static private StringBuilder[] getDecoratedField(Field miniField) {
        char[][] field = decoratedField;
        for (int i = 0; i < miniField.getField().length; i++) {
            for (int j = 0; j < miniField.getField()[i].length; j++) {
                field[2 * i + 1][4 * j + 4] = ChipDecorator.getChipDesign(miniField.getField()[i][j]);
            }
        }
        StringBuilder[] finalField = new StringBuilder[field.length];
        int i = 0;
        for (char[] chars : field) {
            StringBuilder line = new StringBuilder();
            for (char aChar : chars) {
                if (aChar == ChipDecorator.POSSIBLE) {
                    line.append(ChipDecorator.stringPossible());
                } else {
                    line.append(aChar);
                }
            }
            finalField[i] = line;
            i++;
        }
        return finalField;
    }


    /**
     * Печать декорированного поля в консоль.
     *
     * @param field поле, которое необходимо напечатать.
     */
    static public void printField(Field field) {
        StringBuilder[] decoratedField = FieldDecorator.getDecoratedField(field);
        for (StringBuilder line : decoratedField) {
            System.out.println(line);
        }
    }
}
