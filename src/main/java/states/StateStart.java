package states;

import converting.Converting;

import java.util.Scanner;

/**
 Application start state
 */
public class StateStart implements State {
    @Override
    public void run(StateContext sc) {
        Scanner scanner = sc.getScanner();
        System.out.println("1 -- create a country \n" +
                "2 -- create a producer \n" +
                "3 -- create a souvenir \n" +
                "4 -- show all countries \n" +
                "5 -- show all producers \n" +
                "6 -- show all souvenirs \n" +
                "7 -- show information about souvenirs of a given producer\n" +
                "8 -- show information about souvenirs produced in a given country\n" +
                "9 -- show information about producers whose prices for souvenirs less than specified\n" +
                "10 -- show information about producers and information about all his souvenirs \n" +
                "11 -- show information about producers souvenirs of which were produced in a given year\n" +
                "12 -- show information about souvenirs produced in a this year\n" +
                "13 -- delete producer and all his souvenirs\n" +
                "14 -- edit producer\n" +
                "15 -- edit souvenir\n" +
                "0 -- close the application \n");

        int number = Converting.convertFromStringToInt(scanner.next());

        State state = StateFactory.createState(sc, number);
        sc.setCurrentState(state);
        sc.run();
    }
}
