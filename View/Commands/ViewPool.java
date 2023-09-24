package View.Commands;

import View.Console;

import java.util.ArrayList;

public class ViewPool extends Command{

    private final String description = "Список игрушек";
    Console console;

    public ViewPool(Console console){
        super(console);
        this.console = console;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void execute() {
        ArrayList<String> viewToys = console.viewAll();
        for (String s: viewToys) {
            console.print(s);
        }
    }

}
