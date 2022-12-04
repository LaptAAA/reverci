package reverci;

import reverci.model.Chip;
import reverci.model.Field;
import reverci.view.Messages;

import java.util.ArrayList;
import java.util.List;

public class RealGamer extends Gamer {
    InputConsole input;
    RealGamer(Field f, boolean c, InputConsole i) {
        super(f,c);
        input = i;
    }

    @Override
    public boolean wantReturn() {
        boolean answer = input.getBoolean();
        return answer;
    }

    @Override
    public Chip makeMove() {
        boolean flag = true;
        List<Chip> possibleChips = new ArrayList<>(findPossibleChips(color));
        int[] cage = input.getCoordinats();
        while(flag) {
            for (Chip possibleChip : possibleChips) {
                if (possibleChip.getX() == cage[0] && possibleChip.getY() == cage[1]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                Messages.printErrorMove();
                cage = input.getCoordinats();
            }
        }
        return new Chip(cage[0], cage[1], color);
    }
}
