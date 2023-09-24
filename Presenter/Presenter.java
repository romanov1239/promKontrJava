package Presenter;

import Model.EditPool;
import View.View;

import java.util.ArrayList;

public class Presenter {

    private View view;
    private EditPool service;

    public Presenter(View view, EditPool service){
        this.view = view;
        this.service = service;
        view.setPresenter(this);
        service.setPresenter(this);
    }

    public Integer checkIfToyExists(String newToyName){
        return service.checkIfToyExists(newToyName);
    }

    public void changeAmount(Integer id, Integer change) {
        service.changeAmount(id, change);
    }

//    public String getStringFromUser(String msg){
//        return view.read(msg);
//    }

    public void newToy(String newToyTitle, Integer newToyAmount, Integer newToyWeight) {
        service.newToy(newToyTitle,newToyAmount,newToyWeight);
    }

    public void sentMsg(String msg){
        view.print(msg);
    }

    public String getToyFullInfo(Integer toyId){
        return service.getToyFullInfo(toyId);
    }

    public void editToy(Integer toyId, String newTitle, String newAmount, String newWeight) {
        service.editToy(toyId, newTitle, newAmount, newWeight);
    }

    public ArrayList<String> raffleWeighted(Integer amountToRaffle) {
        return service.raffleWeighted(amountToRaffle);
    }

    public boolean deleteToy(Integer toyId) {
        return service.deleteToy(toyId);
    }

    public void loadPreload() {
        service.resetPool();
    }

    public ArrayList<String> viewAll() {
        return service.viewAll();
    }

    public void saveData() {
        service.saveData();
    }

    public void loadData() {
        service.loadData();
    }
}