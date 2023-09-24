package View.Commands;

import View.Console;

public class LoadPreLoad extends Command{

        private final String description = "Загрузить демонстративный набор игрушек";
        Console console;
        public LoadPreLoad(Console console){
            super(console);
            this.console = console;
        }
        @Override
        public String getDescription() {
            return description;
        }

        @Override
        public void execute() {
            console.loadPreLoad();
            console.autoSaveSwitch(false);
        }

    }


