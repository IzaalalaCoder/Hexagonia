package hex.util.structure.stack;

public interface ManageStack<E> {

    // Requetes

    public int getSize();

    public E getElementAtIndex(int index);

    // Commandes

    public void addElement(E element);

    public E removeLastElement();

}
