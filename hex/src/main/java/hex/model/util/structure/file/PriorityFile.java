package hex.model.util.structure.file;

import java.util.ArrayList;
import java.util.List;

public class PriorityFile<E extends Comparable<E>> implements ManageFile<E> {

    // ATTRIBUTES

    private final List<E> fifo;

    // COMMANDS

    public PriorityFile() {
        this.fifo = new ArrayList<E>();
    }

    // REQUESTS

    @Override
    public E getElementAtIndex(int index) {
        if (index < 0 || index > this.getSize()) {
            throw new IllegalArgumentException("Error in index");
        }
        return this.fifo.get(index);
    }

    @Override
    public int getSize() {
        return this.fifo.size();
    }

    @Override
    public Boolean isEmpty() {
        return this.getSize() == 0;
    }

    // COMMANDS

    @Override
    public E pop() {
        E element = this.fifo.get(0);
        this.fifo.remove(0);
        return element;
    }

    @Override
    public void push(E element) {
        if (element == null) {
            throw new IllegalArgumentException("");
        }
        this.fifo.add(this.getFinalIndex(element), element);
    }

    // UTILS

    private int getFinalIndex(E element) {
        for (E e : this.fifo) {
            switch (element.compareTo(e)) {
                case 0 :
                case -1 :
                    return this.fifo.indexOf(e) + 1;
                case 1 :
                    break;
            }
        }
        return this.getSize();
    }

}
