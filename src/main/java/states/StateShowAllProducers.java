package states;

import souvenirs.Producer;

import java.util.TreeMap;

/**
 State display all producers
 */
public class StateShowAllProducers implements State {
    @Override
    public void run(StateContext sc) {
        TreeMap<Integer, Producer> producers= Producer.getTreeMapProducers();
        producers.entrySet().stream().forEach(System.out::println);

        sc.setCurrentState(new StateStart());
        sc.run();
    }
}
