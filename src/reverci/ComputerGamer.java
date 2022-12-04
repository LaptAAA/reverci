package reverci;

import reverci.model.Chip;
import reverci.model.Field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public abstract class ComputerGamer extends Gamer {

    ComputerGamer(Field f, boolean c) {
        super(f, c);
    }
    @Override
    public boolean wantReturn() {
        return false;
    }

    /**
     * Посчитать максимальную выгоду на легкой сложности.
     * @param field поле, на котором будет считаться максимальная выгода.
     * @param color цвет игрока, для которого считается максимальная выгода.
     * @return максимально возможная выгода.
     */
    protected Double maxEasyProfit(Field field, boolean color) {
        Set<Chip> possibleChips = findPossibleChips(color);
        List<Double> rs = collectEasyProfits(field, possibleChips);
        double maxProfit = Collections.max(rs);
        return maxProfit;
    }




    /**
     * Собрать выгоды от всех возможных ходов.
     * @param field поле
     * @param possibleChips Set - возможные фишки.
     * @return список, состоящий из выгод от возможных фишек.
     */
    private List<Double> collectEasyProfits(Field field, Set<Chip> possibleChips) {
        List<Double> rs = new ArrayList<>();
        for (Chip possibleChip : possibleChips) {
            rs.add(calculateChipProfit(field, possibleChip, color));
        }
        return rs;
    }
}
