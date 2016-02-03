public class Transition {
    int time;
    Transition next;

    Transition(int time, Transition next) {
        this.time = time;
        this.next = next;
    }
}
