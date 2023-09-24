package View.Commands;

import View.Console;

public class AutoSave extends Command{
    private final String description = "ключить автосохранение";
    Console console;
    boolean currentSaveState = true;

    public AutoSave(Console console) {
        super(console);
        this.console = console;
    }

    @Override
    public String getDescription() {
        if (currentSaveState)
            return "Вы" + description;
        return "В" + description;
    }

    @Override
    public void execute() {
        if (currentSaveState) {
            currentSaveState = false;
            console.autoSaveSwitch(false);
        }
        else {
            currentSaveState = true;
            console.autoSaveSwitch(true);
        }
    }
}