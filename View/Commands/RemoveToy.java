package View.Commands;

import View.Console;

public class RemoveToy extends Command{
    private final String description = "Удалить игрушку из списка";
    Console console;

    public RemoveToy(Console console){
        super(console);
        this.console = console;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void execute() {
        String toyTitle = console.read("Введите название игрушки для удаления: ");
        Integer toyId = console.CheckIfToyExists(toyTitle);
        if (toyId > -1) {
            console.deleteToy(toyId);
        } else {
            console.print("Игрушка с указанным названием не найдена.");
        }
    }
}
