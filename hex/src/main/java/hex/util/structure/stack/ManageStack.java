package hex.util.structure.stack;

import java.util.List;

public interface ManageStack<E> {

    // REQUETES

    /**
     * Retourne la liste des éléments qui sont dans la pile.
     */
    List<E> getAllElements();

    /**
     * Retourne l'élément courant de la pile.
     */
    E getElementCurrent();

    /**
     * Retourne l'élément à la position index de la pile.
     */
    E getElementAtIndex(int index);

    /**
     * Retourne la taille de la pile.
     */
    int getSize();

    /**
     * Retourne si l'on peut reculer d'un cran la position
     * courante dans notre pile.
     */
    boolean canUndo();

    /**
     * Retourne si l'on peut avancer d'un cran la position
     * courante dans notre pile.
     */
    boolean canRedo();

    // COMMANDES

    /**
     * Vide la pile.
     */
    void clear();

    /**
     * Ajoute un élement dans la pile.
     */
    void push(E element);

    /**
     * Enlève le sommet de notre pile.
     */
    void pop();

    /**
     * Avance notre position courante.
     */
    void redo();

    /**
     * Recule notre position courante.
     */
    void undo();
}
