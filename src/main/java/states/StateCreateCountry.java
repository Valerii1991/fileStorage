package states;

import souvenirs.Country;

import java.util.Scanner;

/**
 State for creating a new country
 */
public class StateCreateCountry implements State {
    @Override
    public void run(StateContext sc) {
        Scanner scanner = sc.getScanner();

        System.out.println("Enter country \n");
        String name = scanner.next();

        int lastId = Country.getLastId();
        Country country = new Country(lastId+1, name);
        country.save();
        sc.setCurrentState(new StateStart());

        sc.run();
    }
}
