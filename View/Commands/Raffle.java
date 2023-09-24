package View.Commands;

import View.Console;

import java.util.ArrayList;

public class Raffle extends Command {
    private final String description = "Запустить розыгрыш";
    Console console;

    public Raffle(Console console){
        super(console);
        this.console = console;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void execute() {
        ArrayList<String> winList = console.raffleWeighted(
                console.readInt("Введите количество разыгрываемых игрушек: "));
        if (winList == null) {
            console.print("Недостаточно игрушек для проведения розыгрыша");
            return;
        }
        if (winList.size()>0){
            console.doAutoSave();
            console.print("Список выигравших игрушек в порядке выигрыша:");
            for (int i = 0; i < winList.size(); i++) {
                console.print(i+1 + ") " + winList.get(i));
            }
            console.print("===Конец списка===");

        }
    }
}
