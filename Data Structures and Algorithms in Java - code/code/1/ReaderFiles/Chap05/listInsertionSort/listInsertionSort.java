// listInsertionSort.java
// demonstrates sorted list used for sorting
// to run this program: C>java ListInsertionSortApp
////////////////////////////////////////////////////////////////

class LinkS {
    public long dData;                  // data item
    public LinkS next;                   // next link in list

    // -------------------------------------------------------------
    public LinkS(long dd)                // constructor
    {
        dData = dd;
    }
// -------------------------------------------------------------
}  // end class Link

////////////////////////////////////////////////////////////////
class SortedListS {
    private LinkS first;            // ref to first item on list

    // -------------------------------------------------------------
    public SortedListS()            // constructor (no args)
    {
        first = null;
    }                    // initialize list

    // -------------------------------------------------------------
    public SortedListS(LinkS[] linkArr)  // constructor (array
    {                               // as argument)
        first = null;                        // initialize list
        for (int j = 0; j < linkArr.length; j++)  // copy array
            insert(linkArr[j]);             // to list
    }

    // -------------------------------------------------------------
    public void insert(LinkS k)     // insert (in order)
    {
        LinkS previous = null;            // start at first
        LinkS current = first;
        // until end of list,
        while (current != null && k.dData > current.dData) {                             // or key > current,
            previous = current;
            current = current.next;       // go to next item
        }
        if (previous == null)               // at beginning of list
            first = k;                    // first --> k
        else                             // not at beginning
            previous.next = k;            // old prev --> k
        k.next = current;                // k --> old currnt
    }  // end insert()

    // -------------------------------------------------------------
    public LinkS remove()           // return & delete first link
    {                           // (assumes non-empty list)
        LinkS temp = first;               // save first
        first = first.next;              // delete first
        return temp;                     // return value
    }
// -------------------------------------------------------------
}  // end class SortedList

////////////////////////////////////////////////////////////////
class ListInsertionSortApp {
    public static void main(String[] args) {
        int size = 10;
        // create array of links
        LinkS[] linkArray = new LinkS[size];

        for (int j = 0; j < size; j++)  // fill array with links
        {                            // random number
            int n = (int) (java.lang.Math.random() * 99);
            LinkS newLink = new LinkS(n);  // make link
            linkArray[j] = newLink;      // put in array
        }
        // display array contents
        System.out.print("Unsorted array: ");
        for (int j = 0; j < size; j++)
            System.out.print(linkArray[j].dData + " ");
        System.out.println("");

        // create new list
        SortedListS theSortedList = new SortedListS(linkArray);

        for (int j = 0; j < size; j++)  // links from list to array
            linkArray[j] = theSortedList.remove();

        // display array contents
        System.out.print("Sorted Array:   ");
        for (int j = 0; j < size; j++)
            System.out.print(linkArray[j].dData + " ");
        System.out.println("");
    }  // end main()
}  // end class ListInsertionSortApp
////////////////////////////////////////////////////////////////
