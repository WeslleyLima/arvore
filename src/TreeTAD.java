public interface TreeTAD<T> {

    Node<T> find(T obj);

    Node<T> recursiveFind(T obj);

    Node<T> min();

    Node<T> min(Node<T> obj);

    Node<T> recursiveMin(Node<T> obj);

    Node<T> next(Node<T> obj);

    void add(T obj);

    void recursiveAdd(T obj);

    void remove(T obj);

    String preOrderPrint();

    String posOrderPrint();

    public String inOrderPrint();
}
