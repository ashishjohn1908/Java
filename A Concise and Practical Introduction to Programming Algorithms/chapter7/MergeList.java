class List {
    int container;
    List next;

    // Constructor  List(head, tail)
    List(int element, List tail) {
        this.container = element;
        this.next = tail;
    }

    List insert(int el) {
        return new List(el, this);
    }

    void Display() {
        List u = this;

        while (u != null) {
            System.out.print(u.container + "-->");
            u = u.next;
        }
        System.out.println("null");
    }

}

class MergeList {

    // Merge two ordered lists
    static List mergeRec(List u, List v) {
        if (u == null) return v;// terminal case
        if (v == null) return u;// terminal case

// Recursion
        if (u.container < v.container) {
            u.next = mergeRec(u.next, v);
            return u;
        } else {
            v.next = mergeRec(u, v.next);
            return v;
        }
    }

    public static void main(String[] args) {
        List u = new List(8, null);
        u = u.insert(6);
        u = u.insert(3);
        u.Display();

        List v = new List(9, null);
        v = v.insert(7);
        v = v.insert(5);
        v = v.insert(4);
        v = v.insert(2);
        v.Display();

        List w = mergeRec(u, v);
        w.Display();
    }

}