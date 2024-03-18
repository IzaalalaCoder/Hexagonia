package hex.util.structure.stack;

import java.util.ArrayList;
import java.util.List;

public class Stack<E> implements ManageStack<E> {
// ATTRIBUTS

    private final List<E> elements;
    private int position;

    // CONSTRUCTEUR

    public Stack() {
        this.elements = new ArrayList<E>();
        this.position = -1;
    }

    // REQUETES

    @Override
    public List<E> getAllElements() {
        return this.elements;
    }

    @Override
    public E getElementCurrent() {
        return this.elements.get(position);
    }

    @Override
    public E getElementAtIndex(int pos) {
        assert !this.elements.isEmpty();
        assert pos < this.elements.size()
                && pos >= 0;

        return this.elements.get(pos);
    }

    @Override
    public int getSize() {
        return this.elements.size();
    }

    // COMMANDES

    @Override
    public void clear() {
        this.elements.clear();
        this.position = -1;
    }

    @Override
    public void push(E element) {
        if (element == null) {
            throw new AssertionError();
        }

        if (this.elements.size() - 1 != this.position) {
            for (int i = this.position + 1; i < this.elements.size(); i++) {
                this.elements.remove(this.getElementAtIndex(i));
            }
        }

        this.elements.add(element);
        this.position += 1;
    }

    @Override
    public void pop() {
        if (this.elements.isEmpty()) {
            throw new AssertionError();
        }

        if (this.position == this.elements.size() - 1) {
            this.position -= 1;
        }
        int index = this.elements.size() - 1;
        this.elements.remove(index);
    }

    @Override
    public void redo() {
        if (!this.canRedo()) {
            throw new AssertionError();
        }
        this.position += 1;
    }

    @Override
    public void undo() {
        if (!this.canUndo()) {
            // System.out.println(this.position);
            throw new AssertionError();
        }
        this.position = this.position - 1;
    }

    @Override
    public boolean canUndo() {
        return !this.elements.isEmpty() && this.position > -1;
    }

    @Override
    public boolean canRedo() {
        return this.position >= -1 && this.getSize() - 1 > this.position;
    }
}
