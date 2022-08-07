package states;

import souvenirs.Country;

import java.util.TreeMap;

/**
 State display all countries
 */
public class StateShowAllCountries implements State {
    @Override
    public void run(StateContext sc) {
        TreeMap<Integer, Country> countries= Country.getTreeMapCountries();
        countries.entrySet().stream().forEach(System.out::println);

        sc.setCurrentState(new StateStart());
        sc.run();
    }
}
