package reverci;

import reverci.model.Chip;
import reverci.model.Field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Абстрактный класс, обозначающий компьютерного игрока.
 */
public abstract class ComputerGamer extends Gamer {

    ComputerGamer(Field f, boolean c) {
        super(f, c);
    }
    @Override
    boolean wantReturn() {
        return false;
    }

    /**
     * Посчитать максимальную выгоду на легкой сложности.
     * @param field поле, на котором будет считаться максимальная выгода.
     * @param color цвет игрока, для которого считается максимальная выгода.
     * @return максимально возможная выгода.
     */
    Double maxEasyProfit(Field field, boolean color) {
        Set<Chip> possibleChips = findPossibleChips(color);
        List<Double> rs = collectEasyProfits(field, possibleChips);
        double maxProfit = Collections.max(rs);
        return maxProfit;
    }


    /**
     * Получить фишку с максимальной выгодой на лекгой мложности.
     * @param field поле, на котором будет считаться максимальная выгода.
     * @param color цвет игрока, для которого ищется.
     * @param maxProfit максимальная выгода, возможная в данном ходе.
     * @return фишка с максимально возможной выгодой.
     */
    Chip maxEasyProfitChip(Field field, boolean color, double maxProfit) {
        Chip bestCh = new Chip(0,0);
        Set<Chip> possibleChips = findPossibleChips(color);
        int i =0;
        for (Chip chip : possibleChips) {
            if (calculateChipProfit(field, chip, color) == maxProfit) {
                bestCh =  new Chip(chip.getX(), chip.getY(), color);
            }
            i++;
        }
        return bestCh;
    }

    /**
     * Собрать выгоды от всех возможных ходов.
     * @param field поле
     * @param possibleChips Set - возможные фишки.
     * @return список, состоящий из выгод от возможных фишек.
     */
    List<Double> collectEasyProfits(Field field, Set<Chip> possibleChips) {
        List<Double> rs = new ArrayList<>();
        for (Chip possibleChip : possibleChips) {
            rs.add(calculateChipProfit(field, possibleChip, color));
        }
        return rs;
    }
}
