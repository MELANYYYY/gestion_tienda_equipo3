public class LinkedStack<E> implements IStack<E> {
    private Nodo<E> top;

    public LinkedStack() {
        top = null;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public void push(E item) {
        Nodo<E> n = new Nodo<>(item);
        if (isEmpty()) {
            top = n;
        } else {
            n.setNext(top);
            top = n;
        }
    }

    @Override
    public E top() {
        if (isEmpty()) {
            return null;
        } else {
            return top.getData();
        }
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        } else {
            E aux = top.getData();
            top = top.getNext();
            return aux;
        }
    }
}