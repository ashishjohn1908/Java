public class Signal {
    boolean initialValue;
    Transition transitions;

    Signal(boolean value, Transition transitions) {
        this.initialValue = value;
        this.transitions = transitions;
    }
}
