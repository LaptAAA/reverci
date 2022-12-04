package reverci.model;

public class Field {
    /**
     * Свойство поле с фишками.
     */
    private final Chip[][] field = new Chip[8][8];

    /**
     * Конструктор глубокого копирования.
     * @param f копируемое поле.
     * @see Field#Field()
     */
    public Field(Field f) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Chip oldChip = f.field[i][j];
                if (oldChip.getExistence()) {
                    field[i][j] = new Chip(oldChip.getX(), oldChip.getY(), oldChip.getColor());
                } else {
                    field[i][j] = new Chip(oldChip.getX(), oldChip.getY());
                }
            }
        }
    }

    /**
     * Конструктор без параметров.
     * @see Field#Field(Field)
     */
    public Field() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 3 && j == 3 || i == 4 && j == 4) {
                    field[i][j] = new Chip(j, i, true);
                } else if (i == 4 && j == 3 || i == 3 && j == 4) {
                    field[i][j] = new Chip(j, i, false);
                } else {
                    field[i][j] = new Chip(j, i);
                }

            }
        }
    }

    /**
     * Получить поле.
     * @return поле.
     */
    public Chip[][] getField() {
        return field;
    }

    /**
     * Добавить новую фишку на поле.
     * @param newChip новая фишка.
     */
    public void putChip(Chip newChip) {
        field[newChip.getY()][newChip.getX()] = newChip;
    }

    /**
     * Сделать все фишки невозможными для следующего хода.
     */
    public void nullifyProbabilities() {
        for (Chip[] chips : field) {
            for (Chip chip : chips) {
                chip.setPossibility(false);
            }
        }
    }

    /**
     * Перекрасить поле относительно поставленной фишки.
     * @param chip поставленная фишка.
     */
    public void recolorCapturedChips(Chip chip) {
        recolorHorizontalLeft(chip);
        recolorHorizontalRight(chip);

        recolorVerticalUp(chip);
        recolorVerticalDown(chip);

        recolorDiagonalRightUp(chip);
        recolorDiagonalRightDown(chip);
        recolorDiagonalLeftDown(chip);
        recolorDiagonalLeftUp(chip);

    }

    /**
     * Перекрасить фишки, находящиеся слева по горизонтали от поставленной фишки.
     * @param chip поставленная фишка, относительно которой идет перекрашивание.
     */
    void recolorHorizontalLeft(Chip chip) {
        boolean color = chip.getColor();
        int x = chip.getX();
        int y = chip.getY();

        boolean flag = false;

        int count = 0;
        for (int i = x - 1; i >= 0; i--) {
            if (!field[y][i].getExistence()) {
                break;
            }

            if (field[y][i].getColor() == color) {
                flag = true;
                break;
            }
            count++;
        }
        if (flag) {
            for (int i = 1; i <= count; i++) {

                field[y][x - i].changeColor();
            }
        }
    }


    /**
     * Перекрасить фишки, находящиеся справа по горизонтали от поставленной фишки.
     * @param chip поставленная фишка, относительно которой идет перекрашивание.
     */
    void recolorHorizontalRight(Chip chip) {
        boolean color = chip.getColor();
        int x = chip.getX();
        int y = chip.getY();

        boolean flag = false;

        int count = 0;
        for (int i = x + 1; i < 8; i++) {
            if (!field[y][i].getExistence()) {
                break;
            }

            if (field[y][i].getColor() == color) {
                flag = true;
                break;
            }
            count++;
        }

        if (flag) {
            for (int i = 1; i <= count; i++) {
                field[y][x + i].changeColor();
            }
        }
    }

    /**
     * Перекрасить фишки, находящиеся сверху по вертикали от поставленной фишки.
     * @param chip поставленная фишка, относительно которой идет перекрашивание.
     */
    void recolorVerticalUp(Chip chip) {
        boolean color = chip.getColor();
        int x = chip.getX();
        int y = chip.getY();

        boolean flag = false;

        int count = 0;
        for (int j = y - 1; j >= 0; j--) {
            if (!field[j][x].getExistence()) {
                break;
            }

            if (field[j][x].getColor() == color) {
                flag = true;
                break;
            }
            count++;
        }
        if (flag) {
            for (int i = 1; i <= count; i++) {
                field[y - i][x].changeColor();
            }
        }
    }

    /**
     * Перекрасить фишки, находящиеся снизу по вертикали от поставленной фишки.
     * @param chip поставленная фишка, относительно которой идет перекрашивание.
     */
    void recolorVerticalDown(Chip chip) {
        boolean color = chip.getColor();
        int x = chip.getX();
        int y = chip.getY();

        boolean flag = false;

        int count = 0;
        for (int j = 1; y + j < 8; j++) {
            if (!field[y + j][x].getExistence()) {
                break;
            }

            if (field[y + j][x].getColor() == color) {
                flag = true;
                break;
            }
            count++;
        }
        if (flag) {
            for (int i = 1; i <= count; i++) {
                field[y + i][x].changeColor();
            }
        }
    }

    /**
     * Перекрасить фишки, находящиеся сверху справа по диагонали от поставленной фишки.
     * @param chip поставленная фишка, относительно которой идет перекрашивание.
     */
    void recolorDiagonalRightUp(Chip chip) {
        boolean color = chip.getColor();
        int x = chip.getX();
        int y = chip.getY();

        boolean flag = false;

        int count = 0;
        for (int i = 1; x + i < 8 && y - i >= 0; i++) {
            if (!field[y - i][x + i].getExistence()) {
                break;
            }

            if (field[y - i][x + i].getColor() == color) {
                flag = true;
                break;
            }
            count++;
        }
        if (flag) {
            for (int i = 1; i <= count; i++) {
                field[y - i][x + i].changeColor();
            }
        }
    }

    /**
     * Перекрасить фишки, находящиеся снизу справа по диагонали от поставленной фишки.
     * @param chip поставленная фишка, относительно которой идет перекрашивание.
     */
    void recolorDiagonalRightDown(Chip chip) {
        boolean color = chip.getColor();
        int x = chip.getX();
        int y = chip.getY();

        boolean flag = false;

        int count = 0;
        for (int i = 1; x + i < 8 && y + i < 8; i++) {
            if (!field[y + i][x + i].getExistence()) {
                break;
            }

            if (field[y + i][x + i].getColor() == color) {
                flag = true;
                break;
            }
            count++;
        }
        if (flag) {
            for (int i = 1; i <= count; i++) {
                field[y + i][x + i].changeColor();
            }
        }
    }

    /**
     * Перекрасить фишки, находящиеся снизу слева по диагонали от поставленной фишки.
     * @param chip поставленная фишка, относительно которой идет перекрашивание.
     */
    void recolorDiagonalLeftDown(Chip chip) {
        boolean color = chip.getColor();
        int x = chip.getX();
        int y = chip.getY();

        boolean flag = false;

        int count = 0;
        for (int i = 1; x - i >= 0 && y + i < 8; i++) {
            if (!field[y + i][x - i].getExistence()) {
                break;
            }

            if (field[y + i][x - i].getColor() == color) {
                flag = true;
                break;
            }
            count++;
        }
        if (flag) {
            for (int i = 1; i <= count; i++) {
                field[y + i][x - i].changeColor();
            }
        }
    }

    /**
     * Перекрасить фишки, находящиеся сверху слева по диагонали от поставленной фишки.
     * @param chip поставленная фишка, относительно которой идет перекрашивание.
     */
    void recolorDiagonalLeftUp(Chip chip) {
        boolean color = chip.getColor();
        int x = chip.getX();
        int y = chip.getY();

        boolean flag = false;

        int count = 0;
        for (int i = 1; x - i >= 0 && y - i >= 0; i++) {
            if (!field[y - i][x - i].getExistence()) {
                break;
            }

            if (field[y - i][x - i].getColor() == color) {
                flag = true;
                break;
            }
            count++;
        }
        if (flag) {
            for (int i = 1; i <= count; i++) {
                field[y - i][x - i].changeColor();
            }
        }
    }

}
