// sortedList.java
// demonstrates sorted list
// to run this program: C>java SortedListApp
////////////////////////////////////////////////////////////////

class LinkSl {
    public long dData;                  // data item
    public LinkSl next;                   // next link in list

    // -------------------------------------------------------------
    public LinkSl(long dd)                // constructor
    {
        dData = dd;
    }

    // -------------------------------------------------------------
    public void displayLink()           // display this link
    {
        System.out.print(dData + " ");
    }
}  // end class Link

////////////////////////////////////////////////////////////////
class SortedList {
    private LinkSl first;                 // ref to first item

    // -------------------------------------------------------------
    public SortedList()                 // constructor
    {
        first = null;
    }

    // -------------------------------------------------------------
    public boolean isEmpty()            // true if no links
    {
        return (first == null);
    }

    // -------------------------------------------------------------
    public void insert(long key)        // insert, in order
    {
        LinkSl newLink = new LinkSl(key);    // make new link
        LinkSl previous = null;            // start at first
        LinkSl current = first;
        // until end of list,
        while (current != null && key > current.dData) {                             // or key > current,
            previous = current;
            current = current.next;       // go to next item
        }
        if (previous == null)               // at beginning of list
            first = newLink;              // first --> newLink
        else                             // not at beginning
            previous.next = newLink;      // old prev --> newLink
        newLink.next = current;          // newLink --> old currnt
    }  // end insert()

    // -------------------------------------------------------------
    public LinkSl remove()           // return & delete first link
    {                           // (assumes non-empty list)
        LinkSl temp = first;               // save first
        first = first.next;              // delete first
        return temp;                     // return value
    }

    // -------------------------------------------------------------
    public void displayList() {
        System.out.print("List (first-->last): ");
        LinkSl current = first;       // start at beginning of list
        while (current != null)      // until end of list,
        {
            current.displayLink();   // print data
            current = current.next;  // move to next link
        }
        System.out.println("");
    }
}  // end class SortedList

////////////////////////////////////////////////////////////////
class SortedListApp {
    public static void main(String[] args) {                            // create new list
        SortedList theSortedList = new SortedList();
        theSortedList.insert(20);    // insert 2 items
        theSortedList.insert(40);

        theSortedList.displayList(); // display list

        theSortedList.insert(10);    // insert 3 more items
        theSortedList.insert(30);
        theSortedList.insert(50);

        theSortedList.displayList(); // display list

        theSortedList.remove();      // remove an item

        theSortedList.displayList(); // display list
    }  // end main()
}  // end class SortedListApp
////////////////////////////////////////////////////////////////
