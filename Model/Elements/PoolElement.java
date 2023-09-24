package Model.Elements;

public interface PoolElement {
    String getTitle();
    Integer getId();

    Integer getAmount();

    void setAmount(Integer newAmount);
}
