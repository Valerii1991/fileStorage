package states;

import converting.Converting;
import souvenirs.Country;
import souvenirs.Producer;

import java.util.*;

/**
 State for creating a new producer
 */
public class StateCreateProducer implements State {
    @Override
    public void run(StateContext sc) {
        Scanner scanner = sc.getScanner();

        System.out.println("Enter producer \n");
        String name = scanner.next();

        TreeMap<Integer,Country> countries= Country.getTreeMapCountries();

        countries.entrySet().stream().forEach(System.out::println);

        System.out.println("Enter id country from the list of countries\n");

        int idCountry = Converting.convertFromStringToInt(scanner.next());

        Country country = countries.get(idCountry);

        int lastId = Producer.getLastId();
        Producer producer = new Producer(lastId+1, name, country);
        producer.save();
        sc.setCurrentState(new StateStart());

        sc.run();
    }
}
