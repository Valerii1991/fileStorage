package souvenirs;

import com.alibaba.fastjson.JSON;
import settings.Settings;
import workingWithFiles.WorkingWithFileStorage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

/**
 Working with countries
 */
public class Country implements Savable {
    private int id;
    private String name;

    public Country(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    /**
     Gets the last country id
     If the list of countries is empty, returns 0
     */
    public static int getLastId(){
        TreeMap<Integer,Country> countries= getTreeMapCountries();
        if (countries.size() > 0) {
            return  countries.lastEntry().getKey();
        }
        else {
            return 0;
        }
    }

    /**
     Gets tree map countries from file storage
     */
    public static TreeMap<Integer,Country> getTreeMapCountries()  {
        TreeMap<Integer,Country> countries= new TreeMap<>();

        String storagePathCountries = Settings.getSetting("storagePathCountries");
        List<File> files = WorkingWithFileStorage.getFilesFromFileStorage(storagePathCountries);
        if(files != null){
            for (File file: files
            ) {
                String pathToFile = file.toString();
                String json = WorkingWithFileStorage.readFile(pathToFile);
                Country country = JSON.parseObject(json,Country.class);

                countries.put(country.getId(),country);
            }
        }
        return countries;
    }

    @Override
    public String toString() {
        return "souvenirs.Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public void save() {
        SaveCountry saveCountry = new SaveCountry(this);
        saveCountry.save();
    }

    /**
     To save the country to the file storage
     */
    public static class SaveCountry{
        private final Country country;

        public SaveCountry(Country country) {
            this.country = country;
        }

        /**
         Saves country to file storage in JSON format
         */
        public void save() {
            String storagePathCountries = Settings.getSetting("storagePathCountries");
            if(!storagePathCountries.equals("")){
                try (FileWriter writer = new FileWriter(storagePathCountries + country.getId() + ".txt");
                     BufferedWriter bw = new BufferedWriter(writer)) {
                    String json = JSON.toJSONString(country);
                    bw.write(json);
                    System.out.println("souvenirs.Country " + country.toString() + " saved successfully");
                } catch (IOException e) {
                    System.out.println("failed to write country to file due to: " + e.getMessage());
                }
            }
        }
    }
}
