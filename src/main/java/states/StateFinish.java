package states;

/**
 State shutting down the application
 */
public class StateFinish implements State{
    @Override
    public void run(StateContext sc) {
        System.out.println("application is closed");
        System.exit(0);
    }
}
