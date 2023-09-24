package Model;

import Model.Elements.PoolElement;

import java.io.Serializable;
import java.util.ArrayList;


public class Pool<T extends PoolElement> implements Serializable {
    private ArrayList<T> toysPool;

    public Pool(ArrayList<T> toysPool){
        this.toysPool = toysPool;
    }

    public Pool(){
        this.toysPool = new ArrayList<>();
    }

    public ArrayList<T> getData(){
        return toysPool;
    }

}
