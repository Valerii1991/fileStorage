package states;

import souvenirs.Souvenir;

import java.util.List;

import java.util.stream.Collectors;

/**
 State show information about producers and information about all his souvenirs
 */
public class StateShowInformationAboutProducersAndInformationAboutAllHisSouvenirs implements State {
    @Override
    public void run(StateContext sc) {
        List<Souvenir> souvenirs= Souvenir.getListSouvenirs();
        souvenirs.stream()
                .filter(s->s.getProducer()!=null)
                .collect(Collectors.groupingBy(Souvenir::getProducer)).entrySet().stream()
                .forEach(m-> System.out.println("Producer: " + m.getKey()+ " his souvenirs: " + m.getValue()));

        sc.setCurrentState(new StateStart());
        sc.run();
    }
}
