package reverci;

import reverci.model.Chip;
import reverci.model.Field;

import java.util.Set;

public class ComputerGamerEasy extends ComputerGamer {
    public ComputerGamerEasy(Field f, boolean c) {
        super(f, c);
    }

    @Override
    public Chip makeMove() {
        double maxEasyProfit = maxEasyProfit(field, color);
        return maxEasyProfitChip(field, color, maxEasyProfit);
    }

    /**
     * Получить фишку с максимальной выгодой на лекгой мложности.
     *
     * @param field     поле, на котором будет считаться максимальная выгода.
     * @param color     цвет игрока, для которого ищется.
     * @param maxProfit максимальная выгода, возможная в данном ходе.
     * @return фишка с максимально возможной выгодой.
     */
    private Chip maxEasyProfitChip(Field field, boolean color, double maxProfit) {
        Chip bestCh = new Chip(0, 0);
        Set<Chip> possibleChips = findPossibleChips(color);
        int i = 0;
        for (Chip chip : possibleChips) {
            if (calculateChipProfit(field, chip, color) == maxProfit) {
                bestCh = new Chip(chip.getX(), chip.getY(), color);
            }
            i++;
        }
        return bestCh;
    }

}
