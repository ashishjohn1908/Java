public class WorkingSignals {

    public static Signal invert(Signal s) {
        return new Signal(!s.initialValue, s.transitions);
    }

    public static boolean valueAt(Signal s, int time) {
        boolean v = s.initialValue;
        Transition t = s.transitions;
        while (t != null) {
            if (time < t.time)
                return v;
            v = !v;
            t = t.next;
        }
        return v;
    }

    public static void print(Signal s) {
        boolean v = s.initialValue;
        Transition t = s.transitions;
        System.out.print("-inf -> ");
        while (t != null) {
            System.out.println(t.time + " : " + v);
            System.out.print(t.time + " -> ");
            v = !v;
            t = t.next;
        }
        System.out.println("+inf : " + v);
    }

    public static Transition shift(Transition t,
                                   int delta) {
        if (t == null)
            return null;
        else
            return new Transition(t.time + delta,
                    shift(t.next, delta));
    }

    public static Signal shift(Signal s, int delta) {
        return new Signal(s.initialValue,
                shift(s.transitions, delta));
    }

    public static boolean isWellFormed(Transition t) {
        if (t == null || t.next == null)
            return true;
        else
            return (t.time < t.next.time)
                    && isWellFormed(t.next);
    }

    static boolean isWellFormed(Signal s) {
        return isWellFormed(s.transitions);
    }

    public static Transition xorTransitions(Transition t1,
                                            Transition t2) {
        if (t1 == null)
            return t2;
        else if (t2 == null)
            return t1;
        else {
            int tt1 = t1.time;
            int tt2 = t2.time;
            if (tt1 < tt2)
                return new Transition(tt1,
                        xorTransitions(t1.next, t2));
            else if (tt2 < tt1)
                return new Transition(tt2,
                        xorTransitions(t1, t2.next));
            else
                return xorTransitions(t1.next, t2.next);
        }
    }

    public static Signal xorSignals(Signal s1, Signal s2) {
        return new Signal(
                (s1.initialValue) != (s2.initialValue),
                xorTransitions(s1.transitions, s2.transitions));
    }

    public static boolean recValueAt(boolean value, Transition t,
                                     int time) {
        if (t == null)
            return value;
        else if (time < t.time)
            return value;
        else
            return recValueAt(!value, t.next, time);
    }

    public static boolean recValueAt(Signal s, int instant) {
        return recValueAt(s.initialValue, s.transitions, instant);
    }

    public static Signal renverser(Signal s) {
        boolean v = s.initialValue;
        Transition t = s.transitions;
        Transition t2 = null;
        while (t != null) {
            t2 = new Transition(-t.time, t2);
            v = !v;
            t = t.next;
        }
        return new Signal(v, t2);
    }

    public static void testValues(Signal s) {
        int[] t = {-1, 0, 1, 2, 4, 6};
        for (int i = 0; i < t.length; ++i)
            System.out.println(t[i] + " : " + recValueAt(s, t[i]) + "  "
                    + valueAt(s, t[i]));
    }

    public static void test(Signal s) {
        print(s);
        testValues(s);
        System.out.println(isWellFormed(s));
        System.out.println();
    }

    public static void testAll(Signal s) {
        test(s);
        Signal is = invert(s);
        test(is);
        System.out.println("XOR");
        print(xorSignals(s, is));
        System.out.println();
        Signal it = shift(is, 1);
        test(it);
        System.out.println("XOR");
        print(xorSignals(it, is));
        System.out.println();
        Signal ir = renverser(it);
        test(ir);
    }

    public static void main(String[] args) {
        Signal s1 = new Signal(false, null);
        Signal s2 = new Signal(false, new Transition(10, new Transition(50, null)));
        Signal s3 = new Signal(true, new Transition(10, new Transition(15,
                new Transition(30, null))));
        Signal signal1 = new Signal(false,
                new Transition(1, new Transition(3, new Transition(4, null))));
        System.out.println("\ns1");
        testAll(s1);
        System.out.println("\ns2");
        testAll(s2);
        System.out.println("\ns3");
        testAll(s3);

        System.out.println("s2 ^ s3");
        print(xorSignals(s2, s3));

        System.out.println("\nsigna1");
        testAll(signal1);

    }

}
