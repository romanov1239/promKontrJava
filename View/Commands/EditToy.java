package View.Commands;

import View.Console;

public class EditToy extends Command{
    private final String description = "Редактировать игрушку";
    Console console;

    public EditToy(Console console){
        super(console);
        this.console = console;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void execute() {
        String toyTitle = console.read("Введите название игрушки для редактирования: ");
        Integer toyId = console.CheckIfToyExists(toyTitle);
        if (toyId > -1){
            console.print(console.getToyFullInfo(toyId));
            console.print("Введите новые данные или оставьте поле пустым, чтобы не вносить в него изменения: ");
            String newTitle = console.readAcceptNothing("Введите новое название: ");
            String newAmount = console.readAcceptNothing("Введите новое количество: ");
            String newWeight = console.readAcceptNothing("Введите новый шанс на выигрыш: ");
            console.editToy(toyId, newTitle, newAmount, newWeight);
            console.doAutoSave();
        } else {
            console.print("Игрушка с введенным названием не найдена");
        }
    }
}
