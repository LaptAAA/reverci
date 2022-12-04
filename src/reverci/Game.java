package reverci;

import reverci.model.Chip;
import reverci.model.Field;
import reverci.view.GameProcessDecorator;
import reverci.view.FieldDecorator;
import reverci.view.Messages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Game {
    /**
     * Список из полей, которые были в предыдущих ходах в игре.
     */
    List<Field> history;
    /**
     * Луйчший счет черных в игре.
     */
    private static int maxScoreBlack = 0;
    /**
     * Лучший счет белых в игре.
     */
    private static int maxScoreWithe = 0;
    /**
     * Поле, на котором идет игра.
     */
    Field field;
    /**
     * Ввод из консоли.
     */
    InputConsole input;
    /**
     * Игрок за белых.
     */
    Gamer gamerWithe;
    /**
     * Игрок за черных.
     */
    Gamer gamerBlack;

    Game(InputConsole i) {
        field = new Field();
        input = i;
        history = new ArrayList<>();
        history.add(new Field(field));
    }

    /**
     * Старт игры.
     * @param var вариант игры.
     */
    void start(int var) {
        if (var == 1) {
            gamerBlack = new RealGamer(field, false, input);
            gamerWithe = new ComputerGamerEasy(field, true);
        } else if (var == 2) {
            gamerBlack = new RealGamer(field, false, input);
            gamerWithe = new ComputerGamerHard(field, true);
        } else if (var == 3) {
            gamerBlack = new RealGamer(field, false, input);
            gamerWithe = new RealGamer(field, true, input);
        } else {
            return;
        }
        body();
    }

    /**
     * Тело игры.
     */
    void body() {
        while (!isEnd()) {
            move(gamerBlack);
            if (!isEnd()) {
                move(gamerWithe);
            }
            history.add(new Field(gamerBlack.getField()));
        }
        end();
    }

    /**
     * Конец игры.
     */
    void end() {
        GameProcessDecorator.printEnd();
        FieldDecorator.printField(field);
        int scoreBlack = countResult(false);
        int scoreWithe = countResult(true);
        changeMaxScore(scoreBlack, scoreWithe);
        Messages.printResults(scoreBlack, scoreWithe);
        Messages.printBestScore(maxScoreBlack, maxScoreWithe);
    }

    /**
     * Ход игрока.
     * @param gamer игрок, который ходит.
     */
    void move(Gamer gamer) {
        printField(gamer);
        Messages.printPrompt();
        GameProcessDecorator.printMove(gamer.getColor());
        if (!skipping(gamer)) {
            if (gamerWithe instanceof ComputerGamer && history.size() >= 2 && gamer.wantReturn()) {
                returnMove();
                move(gamer);
                return;
            }
            if (gamer instanceof RealGamer) {
                Messages.printVariants(gamer.findPossibleChips(gamer.getColor()));
            }
            Chip newChip = gamer.makeMove();
            field.putChip(newChip);
            field.recolorCapturedChips(newChip);
            Messages.printMove(gamer.getColor(), newChip.getX(), newChip.getY());

        } else {
            Messages.printSkipping(gamer.getColor());
        }
    }

    /**
     * Уловия пропуска хода
     * @param gamer игрок, для которого проверяется пропуск хода.
     * @return {@code true} - игрок пропускает ход, {@code false}  - игрок НЕ пропускает ход.
     */
    boolean skipping(Gamer gamer) {
        Set<Chip> possibleChip = gamer.findPossibleChips(gamer.getColor());
        if (possibleChip.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Вернуться на предыдущий ход.
     */
    void returnMove() {
        history.remove(history.size() - 1);
        field = new Field(history.get(history.size() - 1));

        gamerWithe.updateField(field);
        gamerBlack.updateField(field);

        GameProcessDecorator.printPreviousMove();
    }

    /**
     * Условие окончания игры.
     * @return {@code true} - игра закончена, {@code false} - игра продолжается.
     */
    boolean isEnd() {

        int count = 0;
        for (int i = 0; i < field.getField().length; i++) {
            for (int j = 0; j < field.getField()[i].length; j++) {
                if (field.getField()[i][j].getExistence()) {
                    count++;
                }
            }
        }
        if (count == 64) {
            return true;
        }
        if (skipping(gamerBlack) && skipping(gamerWithe)) {
            return true;
        }
        return false;
    }

    /**
     * Изменить лучший счет игроков.
     * @param resultBlack счет черных в текущей игре.
     * @param resultWithe счет белых в текущей игре.
     */
    void changeMaxScore(int resultBlack, int resultWithe) {
        if (resultBlack > maxScoreBlack) {
            maxScoreBlack = resultBlack;
        }
        if (resultWithe > maxScoreWithe) {
            maxScoreWithe = resultWithe;
        }
    }

    /**
     * Распечатать поле с возможными ходами для игрока.
     * @param gamer игрок, для которого выбираются возможные ходы.
     */
    public void printField(Gamer gamer) {
        if (gamer instanceof RealGamer) {
            gamer.showPossibleChips();
        }
        FieldDecorator.printField(gamer.getField());
        field.nullifyProbabilities();
    }

    /**
     * Подсчет результата для игрока.
     * @param color цвет игрока.
     * @return результат игрока.
     */
    public int countResult(boolean color) {
        int score = 0;
        for (int i = 0; i < field.getField().length; i++) {
            for (int j = 0; j < field.getField()[i].length; j++) {
                Chip ch = field.getField()[i][j];
                if (ch.getExistence()) {
                    if (ch.getColor() == color) {
                        score++;
                    }
                }
            }
        }
        return score;
    }
}
