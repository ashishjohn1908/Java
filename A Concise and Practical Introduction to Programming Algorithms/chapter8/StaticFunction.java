class ListObj {
    int container;
    ListObj next;

    ListObj(int element, ListObj tail) {
        this.container = element;
        this.next = tail;
    }

    ListObj insert(int s) {
        return new ListObj(s, this);
    }

    int head() {
        return this.container;
    }
}

class ListIn {
    int container;
    ListIn next;

    // Constructor  List(head, tail)
    ListIn(int element, ListIn tail) {
        this.container = element;
        this.next = tail;
    }
}

class ToolboxIn {


    static int head(ListIn list) {
        return list.container;
    }


    static ListIn insert(int s, ListIn list) {
        return new ListIn(s, list);
    }
}

class StaticFunction {
    public static void main(String[] args) {
        ListIn myList = new ListIn(3, null);
        myList = ToolboxIn.insert(6, ToolboxIn.insert(4, myList));
        System.out.println("Head:" + ToolboxIn.head(myList));


        ListObj list = new ListObj(7, null);
        list = list.insert(4);
        System.out.println("Head:" + list.head());

    }
}