package states;

import converting.Converting;
import souvenirs.Souvenir;

import java.util.Scanner;
import java.util.TreeMap;

/**
 State show information about producers whose prices for souvenirs less than specified
 */
public class StateShowInformationAboutProducersWhosePricesForSouvenirsLessThanSpecified implements State {
    @Override
    public void run(StateContext sc) {
        Scanner scanner = sc.getScanner();

        System.out.println("Enter price for display information about producers whose prices for souvenirs less than specified\n");
        Double price = Converting.convertFromStringToDouble(scanner.next());

        TreeMap<Integer, Souvenir> souvenirs= Souvenir.getTreeMapSouvenirs();

        souvenirs.entrySet().stream()
                .filter(s->s.getValue().getPrice() < price)
                .forEach(s-> System.out.println(s.getValue().getProducer()));

        sc.setCurrentState(new StateStart());
        sc.run();
    }
}
