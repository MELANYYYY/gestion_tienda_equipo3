public interface Queue<E> {
    void add(E item);
    E peek();
    E poll();
    boolean isEmpty();
}
