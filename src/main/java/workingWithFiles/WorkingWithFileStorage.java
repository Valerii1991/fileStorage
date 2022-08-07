package workingWithFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 Working with files. Reading, deleting, getting a list of files from a directory
 */
public class WorkingWithFileStorage {

    /**
     Reads information from a file
     At the output, if the file is successfully read - the text of the file
     If you fail to read the file, the output is an empty string
     Params: pathToFile - path to the file to be read
     */
    public static String readFile(String pathToFile){
        try (FileReader reader = new FileReader(pathToFile);
             BufferedReader br = new BufferedReader(reader)) {
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            return stringBuilder.toString();

        } catch (IOException e) {
            System.out.println("failed " + pathToFile + " to read file due to: " + e.getMessage());
            return "";
        }
    }

    /**
     Deletes the input file
     Params: file - file to be deleted
     */
    public static void deleteFile(File file){
        file.delete();
        System.out.println("file: " + file.toString() + " deleted" );
    }

    /**
     * Gets a list of files that are in the given directory
     * Params: storagePathSouvenirs - the directory path
     * The output is a list of files
     *If an exception occurs, returns null
     */
    public static List<File> getFilesFromFileStorage(String storagePathSouvenirs){
        if(!storagePathSouvenirs.equals("")){
            try {
                List<File> files =  Files.walk(Paths.get(storagePathSouvenirs))
                        .filter(Files::isRegularFile)
                        .map(Path::toFile)
                        .collect(Collectors.toList());
                return files;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

}
