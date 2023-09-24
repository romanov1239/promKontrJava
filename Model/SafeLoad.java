package Model;

import java.io.*;
import Model.Elements.ToyItem;
import Model.Pool;

public class SafeLoad implements Serializable {
    private String saveFileName;
    private String path = System.getProperty("user.dir");
    private String result;

    public SafeLoad(String saveName){
        saveFileName = path.concat("/" + saveName + ".data");
    }

    public SafeLoad(){
        saveFileName = path.concat("/save.data");
    }

    public void saveData(Object objectToSave){
        try (ObjectOutputStream oos =  new ObjectOutputStream((new FileOutputStream(saveFileName)))){
            oos.writeObject(objectToSave);
            result = "Данные успешно сохранены";
        } catch (Exception ex){
            result = "Ошибка: " + ex.getMessage();
        }
    }

    public String getResult(){
        return result;
    }

    public Object readData(){
        Object importObject = new Pool<ToyItem>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFileName))) {
            importObject = ois.readObject();
            result = "Данные из файла успешно загружены";
        } catch (Exception ex){
            result = "Ошибка считывания файла: "+ ex.getMessage();
        }
        return importObject;
    }
}
