package reverci;

import reverci.model.Chip;
import reverci.model.Field;

import java.util.HashSet;
import java.util.Set;

public abstract class Gamer {
    /**
     * Вес кромовой фишки.
     */
    private final int weightEdging = 2;
    /**
     * Вес НЕ кромовой фишки.
     */
    private final int weightOther = 1;

    /**
     * Приоритетный вес угловой фишки.
     */
    private final double priorityWeightAngular = 0.8;
    /**
     * Приоритетный вес кромовой фишки.
     */
    private final double priorityWeightEdging = 0.4;
    /**
     * Приортитеный вес НЕ кромовых клеток.
     */
    private final double priorityWeightOther = 0;

    protected Field field;

    protected boolean color;

    public boolean getColor() {
        return color;
    }

    public Field getField() {
        return field;
    }

    Gamer(Field f, boolean c) {
        field = f;
        color = c;
    }

    /**
     * Узнать, хочет ли игрок сделать ход назад.
     *
     * @return {@code true} - хочет, {@code false} - НЕ хочет.
     */
    public abstract boolean wantReturn();

    /**
     * Обновить поле.
     *
     * @param f поле, на котрое нужно обновить текущее.
     */
    public void updateField(Field f) {
        field = f;
    }

    /**
     * Получить фишку, которую хочет поставить игрок в текущем ходу.
     *
     * @return фишка, которую выбрал игрок.
     */
    public abstract Chip makeMove();

    /**
     * Показать возможные фишки.
     */
    public void showPossibleChips() {
        Set<Chip> possibleChips = findPossibleChips(color);
        changePossibility(possibleChips);
    }

    /**
     * Найти возможные фишки.
     *
     * @param color цвет игрока.
     * @return список возможных фишек без повторений.
     */
    public Set<Chip> findPossibleChips(boolean color) {
        Set<Chip> possibleChips = new HashSet<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Chip chip = field.getField()[i][j];
                if (!chip.getExistence()) {
                    double profit = calculateChipProfit(field, chip, color);
                    if (profit >= 1) {
                        possibleChips.add(chip);
                    }
                }
            }
        }
        return possibleChips;
    }

    /**
     * Подсчитать выгод от возможной фишки.
     *
     * @param field поле, на котором счиатется выгода.
     * @param chip  возможная фишка, для которой счиитается выгода.
     * @param color цвет игрока, для которого считается выгода.
     * @return выгода от возможной фишки.
     */
    protected double calculateChipProfit(Field field, Chip chip, boolean color) {

        double sum = 0;

        sum += profitHorizontalLeft(field, chip, color);
        sum += profitHorizontalRight(field, chip, color);

        sum += profitVerticalUp(field, chip, color);
        sum += profitVerticalDown(field, chip, color);

        sum += profitDiagonalRightUp(field, chip, color);
        sum += profitDiagonalRightDown(field, chip, color);
        sum += profitDiagonalLeftDown(field, chip, color);
        sum += profitDiagonalLeftUp(field, chip, color);


        if (chip.isAngular()) {
            sum += priorityWeightAngular;
        } else if (chip.isEdging()) {
            sum += priorityWeightEdging;
        } else {
            sum += priorityWeightOther;
        }

        return sum;
    }

    /**
     * Выгода от фишки, получаемая по диагонали право вниз от данной фишки.
     *
     * @param field поле, на котором счиатется выгода.
     * @param chip  возможная фишка, для которой счиитается выгода.
     * @param color цвет игрока, для которого считается выгода.
     * @return выгода от возможной фишки по диагонали право вниз.
     */
    private double profitDiagonalRightDown(Field field, Chip chip, boolean color) {
        double sum = 0;

        int x = chip.getX();
        int y = chip.getY();

        boolean flag = false;

        int count = 0;
        for (int i = 1; x + i < 8 && y + i < 8; i++) {
            if (!field.getField()[y + i][x + i].getExistence()) {
                break;
            }
            if (field.getField()[y + i][x + i].getColor() == color) {
                flag = true;
                break;
            }
            count++;
        }
        if (flag) {
            for (int i = 1; i <= count; i++) {
                if (field.getField()[y + i][x + i].isEdging()) {
                    sum += weightEdging;
                } else {
                    sum += weightOther;
                }

            }
        }
        return sum;
    }

    /**
     * Выгода от фишки, получаемая по вертикали вниз от данной фишки.
     *
     * @param field поле, на котором счиатется выгода.
     * @param chip  возможная фишка, для которой счиитается выгода.
     * @param color цвет игрока, для которого считается выгода.
     * @return выгода от возможной фишки по вертикали вниз.
     */
    private double profitVerticalDown(Field field, Chip chip, boolean color) {
        double sum = 0;

        int x = chip.getX();
        int y = chip.getY();

        boolean flag = false;

        int count = 0;
        for (int j = 1; y + j < 8; j++) {
            if (!field.getField()[y + j][x].getExistence()) {
                break;
            }

            if (field.getField()[y + j][x].getColor() == color) {
                flag = true;
                break;
            }
            count++;
        }
        if (flag) {
            for (int i = 1; i <= count; i++) {
                if (field.getField()[y + i][x].isEdging()) {
                    sum += weightEdging;
                } else {
                    sum += weightOther;
                }
            }
        }
        return sum;
    }

    /**
     * Выгода от фишки, получаемая по диагонали влево вниз от данной фишки.
     *
     * @param field поле, на котором счиатется выгода.
     * @param chip  возможная фишка, для которой счиитается выгода.
     * @param color цвет игрока, для которого считается выгода.
     * @return выгода от возможной фишки по диагонали влево вниз.
     */
    private double profitDiagonalLeftDown(Field field, Chip chip, boolean color) {
        double sum = 0;

        int x = chip.getX();
        int y = chip.getY();

        boolean flag = false;


        int count = 0;
        for (int i = 1; x - i >= 0 && y + i < 8; i++) {
            if (!field.getField()[y + i][x - i].getExistence()) {
                break;
            }

            if (field.getField()[y + i][x - i].getColor() == color) {
                flag = true;
                break;
            }
            count++;
        }
        if (flag) {
            for (int i = 1; i <= count; i++) {
                if (field.getField()[y + i][x - i].isEdging()) {
                    sum += weightEdging;
                } else {
                    sum += weightOther;
                }
            }
        }
        return sum;
    }

    /**
     * Выгода от фишки, получаемая по горизонтали вправо от данной фишки.
     *
     * @param field поле, на котором счиатется выгода.
     * @param chip  возможная фишка, для которой счиитается выгода.
     * @param color цвет игрока, для которого считается выгода.
     * @return выгода от возможной фишки по горизонтали вправо.
     */
    private double profitHorizontalRight(Field field, Chip chip, boolean color) {
        double sum = 0;

        int x = chip.getX();
        int y = chip.getY();

        boolean flag = false;


        int count = 0;
        for (int i = x + 1; i < 8; i++) {
            if (!field.getField()[y][i].getExistence()) {
                break;
            }

            if (field.getField()[y][i].getColor() == color) {
                flag = true;
                break;
            }
            count++;
        }

        if (flag) {
            for (int i = 1; i <= count; i++) {
                if (field.getField()[y][x + i].isEdging()) {
                    sum += weightEdging;
                } else {
                    sum += weightOther;
                }
            }
        }
        return sum;
    }

    /**
     * Выгода от фишки, получаемая по горизонтали влево от данной фишки.
     *
     * @param field поле, на котором счиатется выгода.
     * @param chip  возможная фишка, для которой счиитается выгода.
     * @param color цвет игрока, для которого считается выгода.
     * @return выгода от возможной фишки по горизонтали влево.
     */
    private double profitHorizontalLeft(Field field, Chip chip, boolean color) {
        double sum = 0;

        int x = chip.getX();
        int y = chip.getY();

        boolean flag = false;


        int count = 0;
        for (int i = x - 1; i >= 0; i--) {
            if (!field.getField()[y][i].getExistence()) {
                break;
            }

            if (field.getField()[y][i].getColor() == color) {
                flag = true;
                break;
            }
            count++;
        }
        if (flag) {
            for (int i = 1; i <= count; i++) {

                if (field.getField()[y][x - i].isEdging()) {
                    sum += weightEdging;
                } else {
                    sum += weightOther;
                }
            }
        }
        return sum;
    }

    /**
     * Выгода от фишки, получаемая по диагонали вправо вверх от данной фишки.
     *
     * @param field поле, на котором счиатется выгода.
     * @param chip  возможная фишка, для которой счиитается выгода.
     * @param color цвет игрока, для которого считается выгода.
     * @return выгода от возможной фишки по диагонали вправо вверх.
     */
    private double profitDiagonalRightUp(Field field, Chip chip, boolean color) {
        double sum = 0;

        int x = chip.getX();
        int y = chip.getY();

        boolean flag = false;


        int count = 0;
        for (int i = x - 1; i >= 0; i--) {
            if (!field.getField()[y][i].getExistence()) {
                break;
            }

            if (field.getField()[y][i].getColor() == color) {
                flag = true;
                break;
            }
            count++;
        }
        if (flag) {
            for (int i = 1; i <= count; i++) {

                if (field.getField()[y][x - i].isEdging()) {
                    sum += weightEdging;
                } else {
                    sum += weightOther;
                }
            }
        }
        return sum;
    }

    /**
     * Выгода от фишки, получаемая по вертикали вверх от данной фишки.
     *
     * @param field поле, на котором счиатется выгода.
     * @param chip  возможная фишка, для которой счиитается выгода.
     * @param color цвет игрока, для которого считается выгода.
     * @return выгода от возможной фишки по вертикали вверх.
     */
    private double profitVerticalUp(Field field, Chip chip, boolean color) {
        double sum = 0;

        int x = chip.getX();
        int y = chip.getY();

        boolean flag = false;


        int count = 0;
        for (int j = y - 1; j >= 0; j--) {
            if (!field.getField()[j][x].getExistence()) {
                break;
            }

            if (field.getField()[j][x].getColor() == color) {
                flag = true;
                break;
            }
            count++;
        }
        if (flag) {
            for (int i = 1; i <= count; i++) {
                if (field.getField()[y - i][x].isEdging()) {
                    sum += weightEdging;
                } else {
                    sum += weightOther;
                }
            }
        }
        return sum;
    }

    /**
     * Выгода от фишки, получаемая по вдиагонали влево вверх от данной фишки.
     *
     * @param field поле, на котором счиатется выгода.
     * @param chip  возможная фишка, для которой счиитается выгода.
     * @param color цвет игрока, для которого считается выгода.
     * @return выгода от возможной фишки по вдиагонали влево вверх.
     */
    private double profitDiagonalLeftUp(Field field, Chip chip, boolean color) {
        double sum = 0;

        int x = chip.getX();
        int y = chip.getY();

        boolean flag = false;


        int count = 0;
        for (int i = -1; x + i >= 0 && y + i >= 0; i--) {
            if (!field.getField()[y + i][x + i].getExistence()) {
                break;
            }

            if (field.getField()[y + i][x + i].getColor() == color) {
                flag = true;
                break;
            }
            count++;
        }
        if (flag) {
            for (int i = -1; i >= -count; i--) {
                if (field.getField()[y + i][x + i].isEdging()) {
                    sum += weightEdging;
                } else {
                    sum += weightOther;
                }
            }
        }
        return sum;
    }


    /**
     * Обозначить возможные фишки возможными.
     *
     * @param chips set возможных фишек.
     */
    private void changePossibility(Set<Chip> chips) {
        for (Chip chip : chips) {
            field.getField()[chip.getY()][chip.getX()].setPossibility(true);
        }
    }
}
