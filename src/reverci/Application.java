package reverci;

import reverci.view.GameProcessDecorator;
import reverci.view.Messages;

import java.util.NoSuchElementException;

public class Application {

    private InputConsole input;

    Application() {
        input = new InputConsole();
    }

    public void start() {
        boolean flag = true;
        Messages.printHello();
        try {
            while (flag) {
                GameProcessDecorator.printMenu();
                Messages.printMenu();
                Messages.printTerminationConditions();
                int var = input.getVar();
                if (var == 1) {
                    Game game = new Game(input);
                    game.start(1);
                } else if (var == 2) {
                    Game game = new Game(input);
                    game.start(2);
                } else if (var == 3) {
                    Game game = new Game(input);
                    game.start(3);
                } else if (var == 4) {
                    flag = false;
                }
            }
        } catch (NoSuchElementException e) {
            Messages.printEarlyTermination();
        }

    }
}
