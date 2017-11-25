public class Node<T> {

    private Node<T> father;
    private Node<T> leftSon;
    private Node<T> rightSon;
    private T value;
    private int height;
    private int bf;

    public Node() {

    }

    public Node(T value) {
        this(null, null, null, value);
    }

    public Node(Node<T> father, Node<T> leftSon, Node<T> rightSon, T value) {
        this.father = father;
        this.leftSon = leftSon;
        this.rightSon = rightSon;
        this.value = value;
    }

    public Node<T> getFather() {
        return father;
    }

    public void setFather(Node<T> father) {
        this.father = father;
    }

    public Node<T> getLeftSon() {
        return leftSon;
    }

    public void setLeftSon(Node<T> leftSon) {
        this.leftSon = leftSon;
    }

    public Node<T> getRightSon() {
        return rightSon;
    }

    public void setRightSon(Node<T> rightSon) {
        this.rightSon = rightSon;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node: " + value.toString();
    }

    public int getBf() {
        return bf;
    }

    public void setBf(int bf) {
        this.bf = bf;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
