public class LinkedQueue<E> implements Queue<E> {
    private Nodo<E> first;
    private Nodo<E> last;

    public LinkedQueue() {
        this.first = null;
        this.last = null;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public void add(E item) {
        Nodo<E> n = new Nodo<>(item);
        if (isEmpty()) {
            last = n;
            first = n;
        } else {
            last.setNext(n);
            last = n;
        }
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        } else {
            return first.getData();
        }
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        } else {
            E item = first.getData();
            first = first.getNext();
            if (first == null) {
                last = null;
            }
            return item;
        }
    }
}
