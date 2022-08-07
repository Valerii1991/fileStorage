package states;

import souvenirs.Souvenir;

import java.util.TreeMap;

/**
 State display all souvenirs
 */
public class StateShowAllSouvenirs implements State {
    @Override
    public void run(StateContext sc) {
        TreeMap<Integer, Souvenir> souvenirs= Souvenir.getTreeMapSouvenirs();
        souvenirs.entrySet().stream().forEach(System.out::println);

        sc.setCurrentState(new StateStart());
        sc.run();
    }
}
