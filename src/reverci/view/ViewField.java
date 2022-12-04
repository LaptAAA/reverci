package reverci.view;

import reverci.model.Field;

/**
 * Класс, отвечающий за декоративное оформление игрового поля в консоли.
 */
public class ViewField {
    /**
     * Обозначение верхней границы поля.
     */
    static final private char[] topLine = "  ╔═══╦═══╦═══╦═══╦═══╦═══╦═══╦═══╗".toCharArray();
    /**
     * Обозначение средней линии поля с границами.
     */
    static final private String middleBorderLine = " ║   ║   ║   ║   ║   ║   ║   ║   ║";
    /**
     * Обозначение средней линии поля с узлами.
     */
    static final private char[] middleNodalLine = "  ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣".toCharArray();
    /**
     * Обозначение нижней границы поля.
     */
    static final private char[] bottomLine = "  ╚═══╩═══╩═══╩═══╩═══╩═══╩═══╩═══╝".toCharArray();
    /**
     * Обозначение линии с координатами по горизонтали.
     */
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
                field[2 * i + 1][4 * j + 4] = ViewChip.getChipDesign(miniField.getField()[i][j]);
            }
        }
        StringBuilder[] finalField = new StringBuilder[field.length];
        int i=0;
        for (char[] chars : field) {
            StringBuilder line = new StringBuilder();
            for (char aChar : chars) {
                if (aChar == ViewChip.possible) {
                    line.append(ViewChip.stringPossible());
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
        StringBuilder[] decoratedField = ViewField.getDecoratedField(field);
        for (StringBuilder line : decoratedField) {
            System.out.println(line);
        }
    }
}
