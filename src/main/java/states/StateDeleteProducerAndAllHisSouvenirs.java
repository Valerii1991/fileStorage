package states;

import converting.Converting;
import souvenirs.Producer;
import settings.Settings;
import souvenirs.Souvenir;
import workingWithFiles.WorkingWithFileStorage;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

/**
 State to remove the producer and all his souvenirs
 */
public class StateDeleteProducerAndAllHisSouvenirs implements State {
    @Override
    public void run(StateContext sc) {
        Scanner scanner = sc.getScanner();
        TreeMap<Integer, Producer> producers= Producer.getTreeMapProducers();

        producers.entrySet().stream().forEach(System.out::println);

        System.out.println("Enter id producer from the list of countries for delete producer and all his souvenirs\n");
        int idProducer = Converting.convertFromStringToInt(scanner.next());

        List<Souvenir> souvenirs= Souvenir.getListSouvenirs();
        List<Integer> listIdSouvenirs = souvenirs.stream().filter(s->s.getProducer()!=null)
                .filter(s->s.getProducer().getId()==idProducer).map(s->s.getId()).toList();

        String storagePathProducers = Settings.getSetting("storagePathProducers");
        String catalogProducers = storagePathProducers.replace('/','\\');
        List<File> filesProducers = WorkingWithFileStorage.getFilesFromFileStorage(storagePathProducers);
        if(filesProducers != null){
            for (File file: filesProducers
            ) {
                String pathToFile = file.toString();
                storagePathProducers.replace('/','\\');
                if((catalogProducers + idProducer + ".txt").equals(pathToFile)) {
                    WorkingWithFileStorage.deleteFile(file);
                }
            }
        }

        String storagePathSouvenirs = Settings.getSetting("storagePathSouvenirs");
        String catalogSouvenirs = storagePathSouvenirs.replace('/','\\');
        List<File> filesSouvenirs = WorkingWithFileStorage.getFilesFromFileStorage(storagePathSouvenirs);
        if(filesProducers != null){
            for (File file: filesSouvenirs
            ) {
                for (int id:listIdSouvenirs
                     ) {
                    String pathToFile = file.toString();
                    if((catalogSouvenirs + id + ".txt").equals(pathToFile)) {
                        WorkingWithFileStorage.deleteFile(file);
                    }
                }
            }
        }

        sc.setCurrentState(new StateStart());
        sc.run();
    }
}
