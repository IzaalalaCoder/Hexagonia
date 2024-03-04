package hex.util.structure.tree;

import java.util.List;

public interface Tree<E> {

    // COMMANDES

    void addChildren(E element);

    void removeChildren(E element);

    // REQUETES

    Boolean isRoot();

    Boolean isLeaf();

    E getValueAtNode();

    E getFatherElement();

    List<E> getAllChildren();
}
