package Model.Elements;

import java.io.Serializable;

public class ToyItem implements Serializable, PoolElement {
    private Integer id;
    private String title;
    private Integer amount;
    private Integer weight;

    public ToyItem(Integer id, String title, Integer weight){
        this.id = id;
        this.title = title;
        this.weight = weight;
        this.amount = 1;
    }

    public ToyItem(Integer id, String title, Integer amount, Integer weight){
        this.id = id;
        this.title = title;
        this.weight = weight;
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle){
        this.title = newTitle;
    }
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString(){
        return title;
    }

    public String getFullInfo(){
        return "Название: " + title + "\nКоличество: " + amount + "\nШанс выпадения: " + weight + "\n";
    }

//    @Override
    public int compareTo(ToyItem toCompare){

        return this.getId().compareTo(toCompare.getId());
    }
    
}
