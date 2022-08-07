package souvenirs;

import com.alibaba.fastjson.JSON;
import settings.Settings;
import workingWithFiles.WorkingWithFileStorage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 Working with souvenirs
 */
public class Souvenir implements Savable {
    private int id;
    private String title;
    private Producer producer;
    private int yearOfIssue;
    private double price;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public int getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(int yearOfIssue) {
        if(checkYearOfIssue(yearOfIssue)) {
            this.yearOfIssue = yearOfIssue;
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "souvenirs.Souvenir{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", producer=" + producer +
                ", yearOfIssue=" + yearOfIssue +
                ", price=" + price +
                '}';
    }

    /**
     Check that the year of issue is between 0 and 3999
     params: year of issue
     */
    private static boolean checkYearOfIssue(int yearOfIssue){
        if(yearOfIssue > 0 && yearOfIssue < 3999) {
            return true;
        }
        else {
            System.out.println("year of issue incorrect");
            return false;
        }
    }

    /**
     Gets the last souvenir id
     If the list of souvenirs is empty, returns 0
     */
    public static int getLastId(){
        TreeMap<Integer,Souvenir> producers= getTreeMapSouvenirs();
        if (producers.size() > 0) {
            return  producers.lastEntry().getKey();
        }
        else {
            return 0;
        }
    }

    /**
     Gets tree map souvenirs from file storage
     */
    public static TreeMap<Integer,Souvenir> getTreeMapSouvenirs()  {
        TreeMap<Integer,Souvenir> souvenirs= new TreeMap<>();

        String storagePathSouvenirs = Settings.getSetting("storagePathSouvenirs");
        List<File> files = WorkingWithFileStorage.getFilesFromFileStorage(storagePathSouvenirs);
        if(files != null){
            for (File file: files
            ) {
                String pathToFile = file.toString();
                String json = WorkingWithFileStorage.readFile(pathToFile);
                Souvenir souvenir = JSON.parseObject(json,Souvenir.class);

                souvenirs.put(souvenir.getId(),souvenir);
            }
        }
        return souvenirs;
    }

    /**
     Gets list souvenirs from file storage
     */
    public static List<Souvenir> getListSouvenirs()  {
        List<Souvenir> souvenirs= new ArrayList<>();

        String storagePathSouvenirs = Settings.getSetting("storagePathSouvenirs");
        List<File> files = WorkingWithFileStorage.getFilesFromFileStorage(storagePathSouvenirs);
        if(files != null){
            for (File file: files
            ) {
                String pathToFile = file.toString();
                String json = WorkingWithFileStorage.readFile(pathToFile);
                Souvenir souvenir = JSON.parseObject(json,Souvenir.class);

                souvenirs.add(souvenir);
            }
        }
        return souvenirs;
    }

    public static Souvenir.Builder builder() {
        return new Souvenir.Builder();
    }

    @Override
    public void save() {
        SaveSouvenir saveSouvenir = new SaveSouvenir(this);
        saveSouvenir.save();
    }

    /**
     Builder to create an object
     */
    public static final class Builder {
        private Souvenir souvenir;
        private Builder() {
            souvenir = new Souvenir();
        }

        public Souvenir.Builder setTitle(String title){
            souvenir.title = title;
            return this;
        }

        public Souvenir.Builder setProducer(Producer producer){
            souvenir.producer = producer;
            return this;
        }

        public Souvenir.Builder setYearOfIssue(int yearOfIssue){
            if(checkYearOfIssue(yearOfIssue)) {
                souvenir.yearOfIssue = yearOfIssue;
            }
            return this;
        }

        public Souvenir.Builder setPrice(double price){
            souvenir.price = price;
            return this;
        }

        public Souvenir.Builder setId(int id){
            souvenir.id = id;
            return this;
        }

        public Souvenir build(){
            return souvenir;
        }

    }

    /**
     To save the souvenir to the file storage
     */
    public static class SaveSouvenir{
        private final Souvenir souvenir;

        public SaveSouvenir(Souvenir souvenir) {
            this.souvenir = souvenir;
        }

        /**
         Saves souvenir to file storage in JSON format
         */
        public void save() {
            String storagePathSouvenirs = Settings.getSetting("storagePathSouvenirs");
            if(!storagePathSouvenirs.equals("")){
            try (FileWriter writer = new FileWriter(storagePathSouvenirs + souvenir.getId() + ".txt");
                 BufferedWriter bw = new BufferedWriter(writer)) {
                String json = JSON.toJSONString(souvenir);
                bw.write(json);
                System.out.println("souvenirs.Souvenir " + souvenir.toString() + " saved successfully");
            } catch (IOException e) {
                System.out.println("failed to write souvenir to file due to: " + e.getMessage());
            }
        }
        }
    }
}
