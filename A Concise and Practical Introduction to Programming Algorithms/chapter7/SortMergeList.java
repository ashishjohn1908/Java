//
// Sorting demo using linked lists as  data-structures
//

class ListCh7 {
    int container;
    ListCh7 next;

    // Constructor  List(head, tail)
    ListCh7(int element, ListCh7 tail) {
        this.container = element;
        this.next = tail;
    }

    ListCh7 insert(int el) {
        return new ListCh7(el, this);
    }

    int length() {
        int result = 0;
        ListCh7 u = this;
        while (u != null) {
            u = u.next;
            result++;
        }
        return result;
    }

    void Display() {
        ListCh7 u = this;

        while (u != null) {
            System.out.print(u.container + "-->");
            u = u.next;
        }
        System.out.println("null");
    }


    static void Display(List u) {
        while (u != null) {
            System.out.print(u.container + "-->");
            u = u.next;
        }
        System.out.println("null");
    }

}

class SortMergeList {

    // Merge two ordered lists
    static List mergeRec(List u, List v) {
        if (u == null) return v;
        if (v == null) return u;

        if (u.container < v.container) {
            u.next = mergeRec(u.next, v);
            return u;
        } else {
            v.next = mergeRec(u, v.next);
            return v;
        }

    }

    // Sorting procedure on linked list
    static int Length(List u) {
        int result = 0;
        while (u != null) {
            u = u.next;
            result++;
        }
        return result;
    }

    static List sortRec(List u) {
        int l = Length(u);

        if (l <= 1)
            return u;
        else {
            int i, lr;
            List u1, u2, split, prevSplit; // references to cells

            u1 = u;
            prevSplit = split = u;
            i = 0;
            lr = l / 2;

            while (i < lr) {
                i++;
                prevSplit = split;
                split = split.next;
            }

            u2 = split; // terminates with a null
            prevSplit.next = null; // ensure u1 terminates with a null

            return mergeRec(sortRec(u1), sortRec(u2));
        }
    }

    public static void main(String[] args) {

        List u = new List(3, null);
        u = u.insert(2);
        u = u.insert(9);
        u = u.insert(6);
        u = u.insert(1);
        u = u.insert(15);
        u = u.insert(17);
        u = u.insert(23);
        u = u.insert(21);
        u = u.insert(19);
        u = u.insert(20);

        u.Display();

        List sortu = sortRec(u);
        System.out.println("Sorted linked list:");
        sortu.Display();
    }

}