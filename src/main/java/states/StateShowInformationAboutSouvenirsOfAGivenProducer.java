package states;

import converting.Converting;
import souvenirs.Producer;
import souvenirs.Souvenir;

import java.util.Scanner;
import java.util.TreeMap;

/**
 State show information about souvenirs of a given producer
 */
public class StateShowInformationAboutSouvenirsOfAGivenProducer implements State {
    @Override
    public void run(StateContext sc) {
        Scanner scanner = sc.getScanner();

        TreeMap<Integer, Producer> producers= Producer.getTreeMapProducers();
        producers.entrySet().stream().forEach(System.out::println);

        System.out.println("Enter id producer from the list of producers for display information about his souvenirs\n");
        int idProducer = Converting.convertFromStringToInt(scanner.next());

        TreeMap<Integer, Souvenir> souvenirs= Souvenir.getTreeMapSouvenirs();

        souvenirs.entrySet().stream()
                .filter(s->s.getValue().getProducer() != null)
                .filter(s->s.getValue().getProducer().getId() == idProducer)
                .forEach(System.out::println);

        sc.setCurrentState(new StateStart());
        sc.run();
    }
}
