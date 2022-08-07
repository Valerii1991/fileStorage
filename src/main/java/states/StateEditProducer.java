package states;

import converting.Converting;
import souvenirs.Country;
import souvenirs.Producer;

import java.util.Scanner;
import java.util.TreeMap;

/**
 State for editing producer
 */
public class StateEditProducer implements State {
    @Override
    public void run(StateContext sc) {
        Scanner scanner = sc.getScanner();
        TreeMap<Integer, Producer> producers= Producer.getTreeMapProducers();

        producers.entrySet().stream().forEach(System.out::println);

        System.out.println("Enter id producer for edit\n");
        int idProducer = Converting.convertFromStringToInt(scanner.next());

        System.out.println("Enter name \n");
        String name = scanner.next();

        TreeMap<Integer, Country> countries= Country.getTreeMapCountries();
        countries.entrySet().stream().forEach(System.out::println);

        System.out.println("Enter id country from the list of countries\n");
        int idCountry = Converting.convertFromStringToInt(scanner.next());

        Country country = countries.get(idCountry);

        Producer producer = producers.get(idProducer);
        if(producer != null){
            producer.setName(name);
            producer.setCountry(country);
            producer.save();
        }

        sc.setCurrentState(new StateStart());
        sc.run();
    }
}
