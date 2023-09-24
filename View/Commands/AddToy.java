package View.Commands;

import View.Console;

public class AddToy extends Command {
    private final String description = "Добавить игрушку";
    Console console;

    public AddToy(Console console) {
        super(console);
        this.console = console;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void execute() {
        String newToyTitle = console.read("Введите название игрушки.");
        Integer toyExists = console.CheckIfToyExists(newToyTitle);
        if (toyExists > -1) {
            if (console.readBool("Игрушка с указанным названием уже существует." +
                    " Хотите увеличить количество игрушек c таким названием (y) или вернуться в меню(n)?")) {
                console.changeAmount(toyExists, console.readInt("Введите количество игрушек (" +
                        newToyTitle + ") для добавления: "));
                console.doAutoSave();
            }
            return;
        }
        Integer newToyAmount = console.readInt("Введите количество новых игрушек: ");
        Integer newToyWeight = console.readInt("Введите вероятность выигрыша для новой игрушки (целое число): ");
        console.newToy(newToyTitle, newToyAmount, newToyWeight);
        console.doAutoSave();
    }
}
