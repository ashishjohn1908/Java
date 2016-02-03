class EventObject {
    int year, month, day;

    EventObject(int y, int m, int d) {
        year = y;
        month = m;
        day = d;
    }

    static void Display(EventObject obj) {
        System.out.println(obj.year + "/" + obj.month + "/" + obj.day);
    }
}

class SelectionSortEvent {

    static boolean GreaterThan(EventObject a, EventObject b) {
        return ((a.year > b.year) ||
                ((a.year == b.year) && (a.month > b.month)) ||
                ((a.year == b.year) && (a.month == b.month) && (a.day > b.day)));
    }

    static void swap(EventObject[] array, int i, int j) {
        EventObject tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    static void SelectionSort(EventObject[] array) {
        int n = array.length;

        for (int i = 0; i < n - 1; i++)
            for (int j = i + 1; j < n; j++)
                if (GreaterThan(array[i], array[j]))
                    swap(array, i, j);
    }

    public static void main(String[] args) {
        EventObject[] array = new EventObject[5];

        array[0] = new EventObject(2008, 06, 01);
        array[1] = new EventObject(2005, 04, 03);
        array[2] = new EventObject(2005, 05, 27);
        array[3] = new EventObject(2005, 04, 01);
        array[4] = new EventObject(2005, 04, 15);

        SelectionSort(array);
        for (int i = 0; i < array.length; i++)
            EventObject.Display(array[i]);
        System.out.println("");
    }


}