package states;

/**
 User Selected Action State Handler
 */
public interface State {
    /**
     Current state handler
     */
    public void run(StateContext sc);
}
