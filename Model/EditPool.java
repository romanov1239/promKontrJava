package Model;


import Model.Elements.ToyItem;
import Presenter.Presenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;

public class EditPool implements Serializable {
    Pool<ToyItem> pool;
    Presenter presenter;
    DataLogger dataLogger;
    SafeLoad safeLoad;

    public EditPool(){
        this.pool = new Pool<>();
        safeLoad = new SafeLoad();
    }

    public void resetPool(){
        pool = new Pool<>();
        pool.getData().add(new ToyItem(1, "Игрушка №1", 30, 400));
        pool.getData().add(new ToyItem(2, "Игрушка №2", 100, 50));
        pool.getData().add(new ToyItem(3, "Игрушка №3", 50, 100));
        pool.getData().add(new ToyItem(4,"Игрушка №4", 100, 200));
        pool.getData().add(new ToyItem(5, "123", 50,100));
        saveData();
    }

    public void editToy(Integer toyId, String newTitle, String newAmount, String newWeight) {
        for (ToyItem toy: pool.getData()) {
            if (toy.getId().equals(toyId)){
                if (!newTitle.isBlank())
                    toy.setTitle(newTitle);
                if (!newAmount.replaceAll("[^0-9]","").isBlank()){
                    toy.setAmount(Integer.parseInt(newAmount.replaceAll("[^0-9]","")));
                }
                if (!newWeight.replaceAll("[^0-9]","").isBlank()){
                    toy.setWeight(Integer.parseInt(newWeight.replaceAll("[^0-9]","")));
                }
                return;
            }
        }
    }
//    public EditPool(Pool pool){
//        this.pool = pool;
//    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
        this.dataLogger = new DataLogger(presenter);
    }

//
//    public String showAll(){
//        StringBuilder outputString =  new StringBuilder();
//        String prefix = "";
//        for (ToyItem t: pool.getData()) {
//            outputString.append(prefix);
//            outputString.append(t);
//        }
//        return outputString.toString();
//    }
    public void addToy(ToyItem newToyItem){
        if (newToyItem == null)
            return;
        pool.getData().add(newToyItem);
    }
    public Integer checkIfToyExists(String newToyName) {
        for (ToyItem toy: pool.getData()) {
            if (toy.getTitle().compareToIgnoreCase(newToyName) == 0)
                return toy.getId();
        }
        return -1;
    }
    public void changeAmount(Integer id, Integer change) {
        for (ToyItem toy: pool.getData()) {
            if (toy.getId().equals(id)) {
                toy.setAmount(toy.getAmount() + change);
                if (toy.getAmount() < 1) {
                    deleteToy(toy.getId());
                    return;
                }
            }
        }
    }
    public void newToy(String newToyTitle, Integer newToyAmount, Integer newToyWeight) {
        addToy(new ToyItem(GetNewId(), newToyTitle, newToyAmount, newToyWeight));
    }



    public Integer tryRemoveToys(Integer id, Integer toRemove){
        for (ToyItem toy: pool.getData()) {
            if (toy.getId().equals(id)){
                if (toy.getAmount() >= toRemove){
                    changeAmount(id,toRemove * -1);
                    return 0; // Количество игрушек успешно уменьшено
                }
                else
                    return toy.getAmount();
            }
        }
        return -1; // неверный id
    }

    public int getTotalWeight(){
        int totalWeight = 0;
        for (ToyItem toy: pool.getData()) {
            totalWeight = totalWeight + (toy.getWeight() * toy.getAmount());
        }
        return totalWeight;
    }

    public int toysAvailable(){ // Количество игрушек, доступных для розыгрыша
        int count = 0;
        for (ToyItem toy: pool.getData()) {
            if (toy.getWeight() > 0){
                count = count + toy.getAmount();
            }
        }
        return count;
    }

    public ArrayList<String> raffleWeighted(Integer toysToRaffle){
        if (toysAvailable()<toysToRaffle)
            return null;
        ArrayList<String> winList = new ArrayList<>();
        ArrayList<String> winnersForLog = new ArrayList<>();
        int randomNumber; // Случайное число среди суммарного веса
        double chanceToWin;
        for (int i = 0; i < toysToRaffle; i++) {
            randomNumber = ThreadLocalRandom.current().nextInt(1,getTotalWeight() + 1);
            for (ToyItem toy: pool.getData()) {
                if (randomNumber <= toy.getWeight() * toy.getAmount()){
                    winList.add(toy.toString());
                    chanceToWin = (double) (toy.getWeight() * toy.getAmount()) / (double) (getTotalWeight());
                    winnersForLog.add(dataLogger.prepareToLog(
                            String.format("%s. Шанс выигрыша: %.2f%%", toy.toString(), chanceToWin * 100)));
                    changeAmount(toy.getId(),-1);
                    break;
                } else {
                    randomNumber = randomNumber - (toy.getWeight() * toy.getAmount());
                }
            }
        }
        logWinners(winnersForLog);
        return winList;
    }

    public String raffleOneWeighted(){
        return raffleWeighted(1).get(0);
    }

    public Integer GetNewId(){
        Integer id = -1;
        for (ToyItem toy: pool.getData()) {
            if (toy.getId() > id)
                id = toy.getId();
        }
        id = id + 1;
        return id;
    }

    public void logWinners(ArrayList<String> winnersList){
        dataLogger.saveData(winnersList);
    }

    public String getToyFullInfo(Integer toyId) {
        for (ToyItem toy: pool.getData()) {
            if (toy.getId().equals(toyId))
                return toy.getFullInfo();
        }
        return "Ошибка";
    }

    public boolean deleteToy(Integer toyId) {
        Predicate<ToyItem> condition = toyItem -> toyItem.getId().equals(toyId);
        pool.getData().removeIf(condition);
        return true;
    }

    public ArrayList<String> viewAll() {
        ArrayList<String> outputList = new ArrayList<>();
        if (pool.getData().size() < 1){
            outputList.add("<Список игрушек пуст>");
            return outputList;
        }
        for (ToyItem toy: pool.getData()) {
            outputList.add(toy.getFullInfo());
        }
        return outputList;
    }

    public void saveData() {
        safeLoad.saveData(pool);
    }

    public boolean loadData(){
        Object data = safeLoad.readData();
        if (data instanceof Pool){
            pool = (Pool<ToyItem>) data;
        }
        return true;
    }
}
