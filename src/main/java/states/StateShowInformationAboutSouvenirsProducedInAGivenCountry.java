package states;

import converting.Converting;
import souvenirs.Country;
import souvenirs.Souvenir;

import java.util.Scanner;
import java.util.TreeMap;

/**
 State show information about souvenirs produced in a given country
 */
public class StateShowInformationAboutSouvenirsProducedInAGivenCountry implements State {
    @Override
    public void run(StateContext sc) {
        Scanner scanner = sc.getScanner();

        TreeMap<Integer, Country> countries = Country.getTreeMapCountries();
        countries.entrySet().stream().forEach(System.out::println);

        System.out.println("Enter id country from the list of countries for display information about souvenirs produced in a given country\n");
        int idCountry = Converting.convertFromStringToInt(scanner.next());

        TreeMap<Integer, Souvenir> souvenirs= Souvenir.getTreeMapSouvenirs();

        souvenirs.entrySet().stream()
                .filter(s->s.getValue().getProducer() != null)
                .filter(s->s.getValue().getProducer().getCountry() != null)
                .filter(s->s.getValue().getProducer().getCountry().getId() == idCountry)
                .forEach(System.out::println);

        sc.setCurrentState(new StateStart());
        sc.run();
    }
}
