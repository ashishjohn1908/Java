class ListCn8 {
    int element;
    ListCn8 next;

    ListCn8(int el, ListCn8 l) {
        this.element = el;
        this.next = l;
    }

    static ListCn8 EmptyList() {
        return new ListCn8(0, null);
    }

    boolean isEmpty() {
        return (this.next == null);
    }

    int length() {
        ListCn8 u = this;
        int res = 0;
        while (u != null) {
            res++;
            u = u.next;
        }
        return res - 1;
    }

    boolean belongsTo(int el) {
        ListCn8 u = this.next;
        while (u != null) {
            if (el == u.element) return true;
            u = u.next;
        }

        return false;
    }

    void add(int el) {
        ListCn8 u = this.next;
        this.next = new ListCn8(el, u);
    }

    void delete(int el) {
        ListCn8 v = this;
        ListCn8 w = this.next;

        while (w != null && w.element != el) {
            v = w;
            w = w.next;
        }
        if (w != null) v.next = w.next;
    }

    void display() {
        ListCn8 u = this.next;
        while (u != null) {
            System.out.print(u.element + "->");
            u = u.next;
        }
        System.out.println("null");
    }

    static ListCn8 FromArray(int[] array) {
        ListCn8 u = EmptyList();
        for (int i = array.length - 1; i >= 0; i--)
            u.add(array[i]);
        return u;
    }

    public static void main(String[] args) {
        int[] array = {2, 3, 5, 7, 11, 13, 17, 19, 23};

        ListCn8 u = FromArray(array);

        u.add(1);

        u.display();

        u.delete(5);
        u.display();

        System.out.println(u.belongsTo(17));
        System.out.println(u.belongsTo(24));
    }

}