package View.Commands;

import View.Console;

public class Quit extends Command{
    private final String description = "Выход";
    Console console;

    public Quit(Console console){
        super(console);
        this.console = console;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void execute() {
        console.quit();
    }
}
