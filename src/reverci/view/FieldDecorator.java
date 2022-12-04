package reverci.view;

import reverci.model.Field;

/**
 * Класс, отвечающий за декоративное оформление игрового поля в консоли.
 */
public class FieldDecorator {

    static final private char[] topLine = "  ╔═══╦═══╦═══╦═══╦═══╦═══╦═══╦═══╗".toCharArray();

    static final private String middleBorderLine = " ║   ║   ║   ║   ║   ║   ║   ║   ║";

    static final private char[] middleNodalLine = "  ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣".toCharArray();

    static final private char[] bottomLine = "  ╚═══╩═══╩═══╩═══╩═══╩═══╩═══╩═══╝".toCharArray();

    static final private char[] horizontalCoordinatesLine = "    A   B   C   D   E   F   G   H  ".toCharArray();
    /**
     * Обозначение игрового поля с границами со столбцом координат по вертикали и со строкой координат по горизонтали.
     */
    static final private char[][] decoratedField = {
            topLine,
            ("8" + middleBorderLine).toCharArray(),
            middleNodalLine,
            ("7" + middleBorderLine).toCharArray(),
            middleNodalLine,
            ("6" + middleBorderLine).toCharArray(),
            middleNodalLine,
            ("5" + middleBorderLine).toCharArray(),
            middleNodalLine,
            ("4" + middleBorderLine).toCharArray(),
            middleNodalLine,
            ("3" + middleBorderLine).toCharArray(),
            middleNodalLine,
            ("2" + middleBorderLine).toCharArray(),
            middleNodalLine,
            ("1" + middleBorderLine).toCharArray(),
            bottomLine,
            horizontalCoordinatesLine};

    /**
     * Получить декорированное поля
     * @param miniField поле с фишками, котрое нужнот декорировать.
     */
    static private StringBuilder[] getDecoratedField(Field miniField) {
        char[][] field = decoratedField;
        for (int i=0; i<miniField.getField().length; i++) {
            for (int j=0; j<miniField.getField()[i].length; j++) {
                field[2 * i + 1][4 * j + 4] = ChipDecorator.getChipDesign(miniField.getField()[i][j]);
            }
        }
        StringBuilder[] finalField = new StringBuilder[field.length];
        int i=0;
        for (char[] chars : field) {
            StringBuilder line = new StringBuilder();
            for (char aChar : chars) {
                if (aChar == ChipDecorator.possible) {
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
     * @param field поле, которое необходимо напечатать.
     */
    static public void printField(Field field) {
        StringBuilder[] decoratedField = FieldDecorator.getDecoratedField(field);
        for (StringBuilder line : decoratedField) {
            System.out.println(line);
        }
    }
}
