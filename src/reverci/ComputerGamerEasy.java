package reverci;

import reverci.model.Chip;
import reverci.model.Field;

/**
 * Класс компьютерного игрока на легкой сложности.
 */
public class ComputerGamerEasy extends ComputerGamer {
    ComputerGamerEasy(Field f, boolean c) {
        super(f, c);
    }
    @Override
    Chip makeMove() {
        double maxEasyProfit = maxEasyProfit(field, color);
        return maxEasyProfitChip(field, color, maxEasyProfit);
    }

}
