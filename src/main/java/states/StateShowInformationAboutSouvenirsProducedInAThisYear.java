package states;

import souvenirs.Souvenir;

import java.util.List;
import java.util.stream.Collectors;

/**
 State show information about souvenirs produced this year
 */
public class StateShowInformationAboutSouvenirsProducedInAThisYear implements State {
    @Override
    public void run(StateContext sc) {
        List<Souvenir> souvenirs= Souvenir.getListSouvenirs();
        souvenirs.stream()
                .collect(Collectors.groupingBy(Souvenir::getYearOfIssue)).entrySet().stream()
                .forEach(m-> System.out.println("Year of issue: " + m.getKey()+ " souvenirs produced in a this year: " + m.getValue()));

        sc.setCurrentState(new StateStart());
        sc.run();
    }
}
