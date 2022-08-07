package states;

/**
 Factory for creating states depending on the value entered by the user
 */
public class StateFactory {
    public static State createState(StateContext sc, int number){
        State currentState = sc.getCurrentState();

        if(currentState.getClass().equals(StateStart.class)){
            State state = handlerStateStart(number, currentState);
            return state;
        }
        
        return null;
    }

    /**
     Processes the selected value in the Start state
     */
    private static State handlerStateStart(int number, State currentState) {
        if(number == 1){
            return new StateCreateCountry();
        }
        else if(number == 2){
            return new StateCreateProducer();
        }

        else if(number == 3){
            return new StateCreateSouvenir();
        }

        else if(number == 4){
            return new StateShowAllCountries();
        }

        else if(number == 5){
            return new StateShowAllProducers();
        }

        else if(number == 6){
            return new StateShowAllSouvenirs();
        }

        else if(number == 7){
            return new StateShowInformationAboutSouvenirsOfAGivenProducer();
        }

        else if(number == 8){
            return new StateShowInformationAboutSouvenirsProducedInAGivenCountry();
        }

        else if(number == 9){
            return new StateShowInformationAboutProducersWhosePricesForSouvenirsLessThanSpecified();
        }

        else if(number == 10){
            return new StateShowInformationAboutProducersAndInformationAboutAllHisSouvenirs();
        }

        else if(number == 11){
            return new StateShowInformationAboutProducersSouvenirsOfWhichWereProducedInAGivenYear();
        }

        else if(number == 12){
            return new StateShowInformationAboutSouvenirsProducedInAThisYear();
        }

        else if(number == 13){
            return new StateDeleteProducerAndAllHisSouvenirs();
        }

        else if(number == 14){
            return new StateEditProducer();
        }

        else if(number == 15){
            return new StateEditSouvenir();
        }

        else if(number == 0){
            return new StateFinish();
        }

        else{
            return currentState;
        }
    }
}
