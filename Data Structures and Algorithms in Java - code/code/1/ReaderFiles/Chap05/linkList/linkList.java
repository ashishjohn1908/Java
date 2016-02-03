// linkList.java
// demonstrates linked list
// to run this program: C>java LinkListApp
////////////////////////////////////////////////////////////////

class Link2 {
    public int iData;              // data item
    public double dData;           // data item
    public Link2 next;              // next link in list

    // -------------------------------------------------------------
    public Link2(int id, double dd) // constructor
    {
        iData = id;                 // initialize data
        dData = dd;                 // ('next' is automatically
    }                           //  set to null)

    // -------------------------------------------------------------
    public void displayLink()      // display ourself
    {
        System.out.print("{" + iData + ", " + dData + "} ");
    }
}  // end class Link

////////////////////////////////////////////////////////////////
class LinkList2 {
    private Link2 first;            // ref to first link on list

    // -------------------------------------------------------------
    public LinkList2()              // constructor
    {
        first = null;               // no links on list yet
    }

    // -------------------------------------------------------------
    public boolean isEmpty()       // true if list is empty
    {
        return (first == null);
    }

    // -------------------------------------------------------------
    // insert at start of list
    public void insertFirst(int id, double dd) {                           // make new link
        Link2 newLink = new Link2(id, dd);
        newLink.next = first;       // newLink --> old first
        first = newLink;            // first --> newLink
    }

    // -------------------------------------------------------------
    public Link2 deleteFirst()      // delete first item
    {                           // (assumes list not empty)
        Link2 temp = first;          // save reference to link
        first = first.next;         // delete it: first-->old next
        return temp;                // return deleted link
    }

    // -------------------------------------------------------------
    public void displayList() {
        System.out.print("List (first-->last): ");
        Link2 current = first;       // start at beginning of list
        while (current != null)      // until end of list,
        {
            current.displayLink();   // print data
            current = current.next;  // move to next link
        }
        System.out.println("");
    }
// -------------------------------------------------------------
}  // end class LinkList

////////////////////////////////////////////////////////////////
class LinkListApp {
    public static void main(String[] args) {
        LinkList2 theList = new LinkList2();  // make new list

        theList.insertFirst(22, 2.99);      // insert four items
        theList.insertFirst(44, 4.99);
        theList.insertFirst(66, 6.99);
        theList.insertFirst(88, 8.99);

        theList.displayList();              // display list

        while (!theList.isEmpty())         // until it's empty,
        {
            Link2 aLink = theList.deleteFirst();   // delete link
            System.out.print("Deleted ");         // display it
            aLink.displayLink();
            System.out.println("");
        }
        theList.displayList();              // display list
    }  // end main()
}  // end class LinkListApp
////////////////////////////////////////////////////////////////
