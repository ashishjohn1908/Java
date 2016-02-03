/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 08/03/11
 * Time: 21:16
 * To change this template use File | Settings | File Templates.
 */
public abstract class Node<E> {
    private Node<E> prev, next;    // References to the nodes before and after
    private E element;    // Element stored in this position

    /**
     * Constructor
     */
    public Node(Node<E> newPrev, Node<E> newNext, E elem) {
        prev = newPrev;
        next = newNext;
        element = elem;
    }


    // Accessor methods

    public abstract Node<E> getNext();

    public abstract Node<E> getPrev();

    // Update methods

    public abstract void setNext(Node<E> newNext);

    public abstract void setPrev(Node<E> newPrev);

    public abstract void setElement(E newElement);
}

