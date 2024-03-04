package hex.util.structure.stack;

import java.util.ArrayList;
import java.util.List;

public class Stack<E> implements ManageStack<E> {

    // Attributs

    private final List<E> list;

    // Constructeurs

    public Stack() {
        this.list = new ArrayList<E>();
    }

    // Requêtes

    @Override
    public int getSize() {
        return this.list.size();
    }

    @Override
    public E getElementAtIndex(int index) {
        if (index < 0 || index >= this.list.size()) {
            throw new IllegalArgumentException("Error in index");
        }
        return this.list.get(index);
    }

    // Commandes

    @Override
    public void addElement(E element) {

    }

    @Override
    public E removeLastElement() {
        return null;
    }

    // Outils
}
