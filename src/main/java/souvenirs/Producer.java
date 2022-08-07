package souvenirs;

import com.alibaba.fastjson.JSON;
import settings.Settings;
import workingWithFiles.WorkingWithFileStorage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;

/**
 Working with producers
 */
public class Producer implements Savable {
    private int id;
    private String name;
    private Country country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producer producer = (Producer) o;
        return id == producer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Producer(int id, String name, Country country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     Gets the last producer id
     If the list of producers is empty, returns 0
     */
    public static int getLastId(){
        TreeMap<Integer,Producer> producers= getTreeMapProducers();
        if (producers.size() > 0) {
            return  producers.lastEntry().getKey();
        }
        else {
            return 0;
        }
    }

    /**
     Gets tree map producers from file storage
     */
    public static TreeMap<Integer,Producer> getTreeMapProducers()  {
        TreeMap<Integer,Producer> producers= new TreeMap<>();

        String storagePathProducers = Settings.getSetting("storagePathProducers");
        List<File> files = WorkingWithFileStorage.getFilesFromFileStorage(storagePathProducers);
        if(files != null){
            for (File file: files
            ) {
                String pathToFile = file.toString();
                String json = WorkingWithFileStorage.readFile(pathToFile);
                Producer producer = JSON.parseObject(json,Producer.class);

                producers.put(producer.getId(),producer);
            }
        }
        return producers;
    }

    @Override
    public String toString() {
        return "souvenirs.Producer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country=" + country +
                '}';
    }

    @Override
    public void save() {
        SaveProducer saveProducer = new SaveProducer(this);
        saveProducer.save();
    }

    /**
     To save the producer to the file storage
     */
    public static class SaveProducer{
        private final Producer producer;

        public SaveProducer(Producer producer) {
            this.producer = producer;
        }

        /**
         Saves producer to file storage in JSON format
         */
        public void save() {
            String storagePathProducers = Settings.getSetting("storagePathProducers");
            if(!storagePathProducers.equals("")){
                try (FileWriter writer = new FileWriter(storagePathProducers + producer.getId() + ".txt");
                     BufferedWriter bw = new BufferedWriter(writer)) {
                    String json = JSON.toJSONString(producer);
                    bw.write(json);
                    System.out.println("souvenirs.Producer " + producer.toString() + " saved successfully");
                } catch (IOException e) {
                    System.out.println("failed to write producer to file due to: " + e.getMessage());
                }
            }
        }
    }
}
