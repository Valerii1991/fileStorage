package states;

import converting.Converting;
import souvenirs.Souvenir;

import java.util.List;
import java.util.Scanner;

/**
 State Show information about producers souvenirs of which were produced in a given year
 */
public class StateShowInformationAboutProducersSouvenirsOfWhichWereProducedInAGivenYear implements State {
    @Override
    public void run(StateContext sc) {
        Scanner scanner = sc.getScanner();

        System.out.println("Enter title souvenirs for display information about producers whose prices for souvenirs less than specified\n");
        String title = scanner.next();

        System.out.println("Enter year of issue for display information about producers whose prices for souvenirs less than specified\n");
        int year = Converting.convertFromStringToInt(scanner.next());

        List<Souvenir> souvenirs= Souvenir.getListSouvenirs();
        souvenirs.stream()
                .filter(s->s.getProducer()!=null)
                .filter(s->s.getTitle()!=null)
                .filter(s->s.getTitle().equals(title))
                .filter(s->s.getYearOfIssue()==year)
                .forEach(s-> System.out.println(s.getProducer()));

        sc.setCurrentState(new StateStart());
        sc.run();
    }
}
