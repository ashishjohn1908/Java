class DoublyLinkedList {
    int container;
    DoublyLinkedList prev, next;
}

/* 
 *public class List
{
int container;
List next;

// Constructor
List(int element, List tail)
	{
	this.container=element; 
	this.next=tail;
	}

static boolean isEmpty(List list)
	{// in compact form return (list==null);
	if (list==null) return true; 
		else return false;
	}
	
static int head(List list)
	{if (list!=null) 
			return list.container; 
				else 
					return -1; }

static List tail(List list)
	{return list.next;}	
	
static boolean belongTo(int element, List list)
{
while (list!=null)
	{
		if (element==list.container) return true;
		list=list.next;
	}
	return false;
}
	
}
*/


class ListString {
    String name;
    ListString next;

    // Constructor
    ListString(String name, ListString tail) {
        this.name = new String(name);
        this.next = tail;
    }

    static boolean isEmpty(ListString list) {
        return (list == null);
    }

    static String head(ListString list) {
        return list.name;
    }

    static ListString tail(ListString list) {
        return list.next;
    }

    static boolean belongTo(String s, ListString list) {
        while (list != null) {
            if (s.equals(list.name))
                return true;
            list = list.next;
        }
        return false;
    }


    static boolean belongToRec(String s, ListString list) {
        if (list == null) return false;
        else {
            if (s.equals(list.name))
                return true;
            else
                return belongToRec(s, list.next);
        }
    }

    static int length(ListString list) {
        int l = 0;
        while (list != null) {
            l++;
            list = list.next;
        }
        return l;
    }

    static int lengthRec(ListString list) {
        if (list == null)
            return 0;
        else
            return 1 + lengthRec(list.next);
    }


    static ListString Insert(String s, ListString list) {
        return new ListString(s, list);
    }

    static void Display(ListString list) {
        while (list != null) {
            System.out.print(list.name + "-->");
            list = list.next;
        }
        System.out.println("null");
    }


    static void DisplayRec(ListString list) {
        if (list == null)
            System.out.println("null");
        else {
            System.out.print(list.name + "-->");
            DisplayRec(list.next);
        }
    }

    static void DisplayRecRev(ListString list) {
        if (list == null)
            System.out.print("null");
        else {
            DisplayRecRev(list.next);
            System.out.print("<--" + list.name);
        }
    }

    // Delete an element of the string
    static ListString Delete(String s, ListString list) {
// if list is empty
        if (list == null)
            return null;

// If element is at the head
        if (list.name.equals(s))
            return list.next;

// Otherwise
        ListString v = list;
        ListString w = list.next; //tail

        while (w != null && !((w.name).equals(s))) {
            v = w;
            w = v.next;
        }

        if (w != null)
            v.next = w.next;

        return list;
    }


    static ListString copy(ListString l) {

        ListString result = null;

        while (l != null) {
            result = new ListString(l.name, result);
            l = l.next;
        }
        return result;

    }


    static ListString copyRec(ListString l) {
        if (l == null)
            return null;
        else
            return new ListString(l.name, copyRec(l.next));
    }

    static ListString Build(String[] array) {
        ListString result = null;

// To ensure that head is the first array element, decrement
        for (int i = array.length - 1; i >= 0; i--)
            result = new ListString(array[i], result);

        return result;
    }

}

class ListJava {

    public static void main(String[] args) {
        /*	List u=new List(6,null);
          u=new List(16,u);
          u=new List(32,u);
          u=new List(25,u);

          System.out.println(List.belongTo(6,u));
          System.out.println(List.belongTo(17,u));
          */


        ListString l = new ListString("Paris", null);
        l = new ListString("Tokyo", l);
        l = new ListString("Berlin", l);
        l = new ListString("Porto", l);
        l = new ListString("Cambridge", l);
        l = new ListString("Roma", l);

        ListString.Display(l);
        ListString.DisplayRec(l);
        ListString.DisplayRecRev(l);
        System.out.println("");

        //	System.out.println(ListString.belongTo("Marc",l));
        //	System.out.println(ListString.belongTo("Sarah",l));

        //	System.out.println(ListString.length(l));
        //		System.out.println(ListString.length(l));

        /*
                  l=ListString.Insert("Philippe", l);

                      ListString.Display(l);
                      l=ListString.Delete("Steve",l);
                      ListString.Display(l);
                      l=ListString.Delete("Frank",l);
                      ListString.Display(l);
                      l=ListString.Delete("Philippe",l);
                      ListString.Display(l);
                      l=ListString.Delete("Michel",l);
                      ListString.Display(l);

                      ListString.Display(l);
                      System.out.println(ListString.lengthRec(l));


                  System.out.println(ListString.belongToRec("Marc",l));

                          ListString.DisplayRec(l);
                          ListString lcopy=ListString.copy(l);
                          ListString.Display(lcopy);
                          ListString lcopyrec=ListString.copyRec(l);
                          ListString.Display(lcopyrec);


  String [] colors={"green", "red", "blue", "purple", "orange", "yellow"};
  ListString lColors=ListString.Build(colors);
  ListString.Display(lColors);

          */
    }
}