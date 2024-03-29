package reverci.model;

public class Chip {
    /**
     * Свойство существования на доске.
     * {@code true} - фишка есть на доске.
     * {@code false} - фишки нет на доске.
     */
    private boolean existence = false;

    /**
     * Координата фишки по горизонатли.
     */
    private final int X;
    /**
     * Координата фишки по вертикали.
     */
    private final int Y;
    /**
     * Свойство вероятности на доске.
     * {@code true} - фишка может быть поставлена в текущем ходе.
     * {@code false} - фишка НЕ может быть поставлена в текущем ходе.
     */
    private boolean possibility = false;
    /**
     * Свойство цвета.
     * {@code true} - белый цвет.
     * {@code false} - черный цвет.
     */
    private boolean color;

    /**
     * Создает несуществующую фишку.
     * Цвет - автоматически черный.
     * Существование - автоматически нет.
     *
     * @param x координата новой фишки по горизонтали.
     * @param y координата новой фишки по вертикали.
     * @see Chip#Chip(int, int, boolean)
     */
    public Chip(int x, int y) {
        X = x;
        Y = y;
    }

    /**
     * Создает новую существующую фишку.
     *
     * @param x координата новой фишки по горизонтали.
     * @param y координата новой фишки по вертикали.
     * @param c цвет фишки.
     * @see Chip#Chip(int, int)
     */
    public Chip(int x, int y, boolean c) {
        X = x;
        Y = y;
        color = c;
        existence = true;
    }

    /**
     * Проерка, что фишка стоит на угловой клетке
     *
     * @return {@code true} - фишка угловая, {@code false} - фишка НЕ угловая.
     */
    public boolean isAngular() {

        if (X == Y && (X == 0 || X == 7)) {
            return true;
        }
        if (X == 0 && Y == 7 || X == 7 && Y == 0) {
            return true;
        }
        return false;
    }

    /**
     * Проерка, что фишка стоит на кромочной клетке.
     *
     * @return {@code true} - фишка кромочная, {@code false} - фишка НЕ кромочная.
     */
    public boolean isEdging() {
        if (X == 0 || X == 7) {
            return true;
        }
        if (Y == 0 || Y == 7) {
            return true;
        }
        return false;
    }

    /**
     * Получает значение свойства color.
     *
     * @return Значение свойства color.
     */
    public boolean getColor() {
        return color;
    }

    /**
     * Получает значение свойства exist.
     *
     * @return Значение свойства exist.
     */
    public boolean getExistence() {
        return existence;
    }

    /**
     * Получает значение свойства possibility, которое можно задать с помощью метода {@link #setPossibility(boolean)}
     *
     * @return Значение свойства possibility.
     */
    public boolean getPossibility() {
        return possibility;
    }


    /**
     * Получает координату по горизонтали.
     *
     * @return Координата по горизонтали.
     */
    public int getX() {
        return X;
    }

    /**
     * Получает координату по вертикали.
     *
     * @return Координата по вертикали.
     */
    public int getY() {
        return Y;
    }

    /**
     * Меняет цвет фигшки на противоположный.
     */
    public void changeColor() {
        color = !color;
    }

    /**
     * Задает значение свойства possibility, которое можно получить при помощи метода {@link #getPossibility()}
     *
     * @param value Новое значение свойства possibility.
     */
    public void setPossibility(boolean value) {
        possibility = value;
    }


}
