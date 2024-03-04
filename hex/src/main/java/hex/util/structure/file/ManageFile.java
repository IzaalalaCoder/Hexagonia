package hex.util.structure.file;

public interface ManageFile<E extends Comparable<E>> {

    // REQUESTS

    E getElementAtIndex(int index);

    int getSize();

    Boolean isEmpty();

    // COMMANDS

    E pop();

    void push(E element);
}
