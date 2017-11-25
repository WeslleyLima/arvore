public class BinarySearchTree implements TreeTAD<Integer> {

    private Node<Integer> root;

    public BinarySearchTree() {
        this.root = null;
    }

    @Override
    public Node<Integer> find(Integer obj) {
        if (root == null) {
            return null;
        } else {
            Node<Integer> aux = root;
            while (aux != null && aux.getValue() != obj) {

                if (obj < aux.getValue()) {
                    aux = aux.getLeftSon();
                } else {
                    aux = aux.getRightSon();
                }
            }
            return aux;
        }
    }

    @Override
    public Node<Integer> recursiveFind(Integer obj) {
        return this.recursiveFind(root, obj);
    }

    private Node<Integer> recursiveFind(Node<Integer> tree, Integer obj) {
        if (tree == null)
            return null;
        if (obj == tree.getValue())
            return tree;
        if (obj > tree.getValue()) {
            return recursiveFind(tree.getRightSon(), obj);
        } else {
            return recursiveFind(tree.getLeftSon(), obj);
        }
    }

    @Override
    public Node<Integer> min() {

        Node<Integer> minNode = root;
        Node<Integer> aux = root;

        while (aux != null) {
            minNode = aux;
            aux = aux.getLeftSon();
        }

        return minNode;
    }

    @Override
    public Node<Integer> min(Node<Integer> obj) {
        obj = find(obj.getValue());
        if (obj == null)
            return null;
        while (obj.getLeftSon() != null)
            obj = obj.getLeftSon();
        return obj;
    }

    @Override
    public Node<Integer> recursiveMin(Node<Integer> obj) {
        Node<Integer> aux = find(obj.getValue());
        return recursiveMinimum(aux);
    }

    private Node<Integer> recursiveMinimum(Node<Integer> node) {
        if (node.getLeftSon() != null)
            node = recursiveMinimum(node.getLeftSon());
        return node;
    }

    @Override
    public Node<Integer> next(Node<Integer> obj) {
        obj = find(obj.getValue());
        if (obj == null) {
            return null;
        } else if (obj.getRightSon() != null) {
            return min(obj.getRightSon());
        } else {
            Node<Integer> p = obj.getFather();
            while (p != null && obj == p.getRightSon()) {
                obj = p;
                p = p.getFather();
            }
            return p;
        }
    }

    @Override
    public void add(Integer obj) {

        Node<Integer> node = new Node<>(obj);

        if (root == null) {
            root = node;
        } else {

            Node<Integer> aux = root;
            Node<Integer> father = null;

            while (aux != null && aux.getValue() != node.getValue()) {

                father = aux;

                if (aux.getValue() > node.getValue()) {
                    aux = aux.getLeftSon();
                } else {
                    aux = aux.getRightSon();
                }

            }

            node.setFather(father);
            if (father.getValue() > node.getValue()) {
                father.setLeftSon(node);
            } else if (father.getValue() < node.getValue()) {
                father.setRightSon(node);
            } else {
                father.setValue(node.getValue()); // se os valores forem igual ele não add
            }
        }
    }

    @Override
    public void recursiveAdd(Integer obj) {

        Node<Integer> node = new Node<>(obj);

        if (root == null) {
            root = node;
        } else {
            this.recursiveAdd(root, node);
        }

    }

    private void recursiveAdd(Node<Integer> subTree, Node<Integer> node) {

        if (subTree.getValue() < node.getValue()) {

            if (subTree.getRightSon() == null) {
                subTree.setRightSon(node);
                node.setFather(subTree);
            } else {
                this.recursiveAdd(subTree.getRightSon(), node);
            }

        } else if (subTree.getValue() > node.getValue()) {

            if (subTree.getLeftSon() == null) {
                subTree.setLeftSon(node);
                node.setFather(subTree);
            } else {
                this.recursiveAdd(subTree.getLeftSon(), node);
            }

        } else {
            subTree.setValue(node.getValue());
        }

    }

    @Override
    public void remove(Integer obj) {
        this.remove(root, obj);
    }

    private Node<Integer> remove(Node<Integer> node, Integer obj) {

        if (node == null)
            return null;
        else if (obj < node.getValue())
            node.setLeftSon(remove(node.getLeftSon(), obj));
        else if (obj > node.getValue())
            node.setRightSon(remove(node.getRightSon(), obj));
        else{
            if (node.getLeftSon() == null && node.getRightSon() == null) {
                node.setValue(null);
                node = null;
            } else if (node.getLeftSon() == null) {
                Node<Integer> aux = node;
                node = node.getRightSon();
                node.setFather(aux.getFather());
                aux = null;
            } else if (node.getRightSon() == null) {
                Node<Integer> aux = node;
                node = node.getLeftSon();
                node.setFather(aux.getFather());
                aux = null;
            } else {
                Node<Integer> sucessor = node.getRightSon();
                while (sucessor.getLeftSon() != null) {
                    sucessor = sucessor.getLeftSon();
                }
                node.setValue(sucessor.getValue());
                sucessor.setValue(obj);

                if (sucessor.getFather().getLeftSon() == sucessor)
                    sucessor.getFather().setLeftSon(sucessor.getRightSon());
                else
                    sucessor.getFather().setRightSon(sucessor.getRightSon());
                if (sucessor.getRightSon() != null)
                    sucessor.getRightSon().setFather(sucessor.getFather());

                sucessor = null;
            }
        }
        return node;
    }


    @Override
    public String preOrderPrint() {
        preOrderPrint(root);
        return "";
    }

    private void preOrderPrint(Node<Integer> root) {
        if (root == null)
            return;
        System.out.printf("%d ", root.getValue());
        preOrderPrint(root.getLeftSon());
        preOrderPrint(root.getRightSon());
    }

    @Override
    public String posOrderPrint() {
        posOrderPrint(root);
        return "";
    }

    private void posOrderPrint(Node<Integer> root) {
        if (root == null)
            return;
        posOrderPrint(root.getLeftSon());
        posOrderPrint(root.getRightSon());
        System.out.printf("%d ", root.getValue());
    }

    @Override
    public String inOrderPrint() {
        inOrderPrint(root);
        return "";
    }

    private void inOrderPrint(Node<Integer> root) {
        if (root == null)
            return;
        this.inOrderPrint(root.getLeftSon());
        System.out.printf("%d ", root.getValue());
        this.inOrderPrint(root.getRightSon());
    }
}













