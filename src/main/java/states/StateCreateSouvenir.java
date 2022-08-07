package states;

import converting.Converting;
import souvenirs.Producer;
import souvenirs.Souvenir;

import java.util.Scanner;
import java.util.TreeMap;

/**
 State for creating a new souvenir
 */
public class StateCreateSouvenir implements State {
    @Override
    public void run(StateContext sc) {
        Scanner scanner = sc.getScanner();

        System.out.println("Enter title \n");
        String title = scanner.next();

        TreeMap<Integer, Producer> producers= Producer.getTreeMapProducers();

        producers.entrySet().stream().forEach(System.out::println);

        System.out.println("Enter id producer from the list of countries\n");
        int idProducer = Converting.convertFromStringToInt(scanner.next());

        System.out.println("Enter year of issue\n");
        int yearOfIssue = Converting.convertFromStringToInt(scanner.next());

        System.out.println("Enter price\n");
        Double price = Converting.convertFromStringToDouble(scanner.next());

        Producer producer = producers.get(idProducer);

        int lastId = Souvenir.getLastId();
        Souvenir souvenir = souvenirs.Souvenir.builder()
                .setTitle(title)
                .setProducer(producer)
                .setYearOfIssue(yearOfIssue)
                .setPrice(price)
                .setId(lastId+1)
                .build();
        souvenir.save();
        sc.setCurrentState(new StateStart());

        sc.run();
    }
}
