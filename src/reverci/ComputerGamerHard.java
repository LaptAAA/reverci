package reverci;

import reverci.model.Chip;
import reverci.model.Field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;


public class ComputerGamerHard extends ComputerGamer {
    public ComputerGamerHard(Field f, boolean c) {
        super(f, c);
    }

    @Override
    public Chip makeMove() {
        double maxHardProfit = maxHardProfit();
        return maxHardProfitChip(maxHardProfit);
    }

    /**
     * Посчитать максимальную выгоду на сложном режиме.
     *
     * @return количство очков.
     */
    private Double maxHardProfit() {
        Set<Chip> possibleChips = findPossibleChips(color);
        List<Double> hardProfits = collectHardProfits(possibleChips);
        return Collections.max(hardProfits);
    }

    /**
     * Получить из списка возможных фишек список возможных выгод.
     *
     * @param possibleChips Set<Chip> - список возможных фишек.
     * @return ArrayList<Double> - список возможных выгод.
     */
    private List<Double> collectHardProfits(Set<Chip> possibleChips) {
        List<Double> hardProfits = new ArrayList<>();
        for (Chip chip : possibleChips) {
            hardProfits.add(calculateChipHardProfit(chip));
        }
        return hardProfits;
    }

    /**
     * Посчитать выгоду на сложном режиме от возможной фишки.
     *
     * @param chip возможная фишка
     * @return выгода.
     */
    private double calculateChipHardProfit(Chip chip) {
        double maxEasyProfit = calculateChipProfit(field, chip, color);
        double maxEasyProfitEnemy = maxProfitEnemy(chip);
        return maxEasyProfit - maxEasyProfitEnemy;

    }

    /**
     * Посчитать максимальную выгоду противника относительно поставленной фишки.
     *
     * @param chip поставленная фишка.
     * @return максимальная выгода.
     */
    private Double maxProfitEnemy(Chip chip) {
        Field newField = new Field(field);
        Chip newChip = new Chip(chip.getX(), chip.getY(), color);
        newField.putChip(newChip);
        return maxEasyProfit(newField, !color);
    }

    /**
     * Получить фишку с максимальной выгодой на сложном режиме.
     *
     * @param maxProfit максимальная выгода, возможная в данном ходе.
     * @return Chip - самая выгодная фишка.
     */
    private Chip maxHardProfitChip(double maxProfit) {
        Chip bestCh = new Chip(0, 0);
        Set<Chip> possibleChips = findPossibleChips(color);
        for (Chip chip : possibleChips) {
            if (calculateChipHardProfit(chip) == maxProfit) {
                bestCh = new Chip(chip.getX(), chip.getY(), color);
            }
        }
        return bestCh;
    }
}
