package states;

import java.util.Scanner;

/**
 To work with handler state
 */
public class StateContext {
    private Scanner scanner;
    private State currentState;

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public StateContext() {
        this.currentState = new StateStart();
        this.scanner = new Scanner(System.in);
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    /**
     Current state handler
     */
    public void run(){
        this.currentState.run(this);
    }
}
