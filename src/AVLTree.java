public class AVLTree implements TreeTAD<Integer> {

    public Node<Integer> root;

    public AVLTree() {
        root = null;
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
                } else
                    aux = aux.getRightSon();
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

        Node<Integer> minNode = null;
        Node<Integer> aux = root;

        while (aux != null) {
            minNode = aux;
            aux = aux.getLeftSon();
        }

        return minNode;
    }

    @Override
    public Node<Integer> min(Node<Integer> obj) {
//        obj = find(obj.getValue());
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

//        obj = find(obj.getValue());
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
            } else
                father.setValue(node.getValue());
        }
        verifyBalance(node);
    }

    @Override
    public void recursiveAdd(Integer obj) {

        Node<Integer> newNode = new Node<>(obj);
        Node<Integer> newRoot = root;

        if (root == null) {
            root = newNode;
        } else {
            recursiveAdd(newNode, newRoot);
        }
        verifyBalance(newNode);
    }

    private void recursiveAdd(Node<Integer> newNode, Node<Integer> newRoot) {

        if (newNode.getValue() > newRoot.getValue()) {
            if (newRoot.getRightSon() == null) {
                newRoot.setRightSon(newNode);
                newNode.setFather(newRoot);
            } else {
                newRoot = newRoot.getRightSon();
                recursiveAdd(newNode, newRoot);
            }
        } else if (newNode.getValue() < newRoot.getValue()) {
            if (newRoot.getLeftSon() == null) {
                newRoot.setLeftSon(newNode);
                newNode.setFather(newRoot);
            } else {
                newRoot = newRoot.getLeftSon();
                recursiveAdd(newNode, newRoot);
            }
        }
    }

    @Override
    public void remove(Integer obj) {

        Node<Integer> nodeToBeRemoved = find(obj);

        if (nodeToBeRemoved == null)
            return;
        else {

            /** Se o nó a ser removido for uma folha, isto é, não possuir filhos */
            if (nodeToBeRemoved.getLeftSon() == null && nodeToBeRemoved.getRightSon() == null) {

                /** Se o nó a ser deletado é um filho a esquerda de seu pai */
                if (nodeToBeRemoved.getFather().getLeftSon() == nodeToBeRemoved) {
                    nodeToBeRemoved.getFather().setLeftSon(null);
                    nodeToBeRemoved.setFather(null);
                } else { /** Se o nó a ser deletado é um filho a diretia de seu pai */
                    nodeToBeRemoved.getFather().setRightSon(null);
                    nodeToBeRemoved.setFather(null);
                }
            } else

            /** Se nó a ser removido possuir filho à esquerda */
            if (nodeToBeRemoved.getRightSon() == null) {

                nodeToBeRemoved.getLeftSon().setFather(nodeToBeRemoved.getFather());

                /** Se o nó a ser removido é filho à esquerda*/
                if (nodeToBeRemoved.getFather().getLeftSon() == nodeToBeRemoved)
                    nodeToBeRemoved.getFather().setLeftSon(nodeToBeRemoved.getLeftSon());

                /** Se o nó a ser removido é filho à direita */
                if (nodeToBeRemoved.getFather().getRightSon() == nodeToBeRemoved)
                    nodeToBeRemoved.getFather().setRightSon(nodeToBeRemoved.getLeftSon());

                nodeToBeRemoved.setFather(null);
                nodeToBeRemoved.setLeftSon(null);

            } else

            /** Se o nó a ser removido possuir um filho à direita */
            if (nodeToBeRemoved.getLeftSon() == null) {

                nodeToBeRemoved.getRightSon().setFather(nodeToBeRemoved.getFather());

                if (nodeToBeRemoved.getFather().getLeftSon() == nodeToBeRemoved)
                    nodeToBeRemoved.getFather().setLeftSon(nodeToBeRemoved.getRightSon());

                if (nodeToBeRemoved.getFather().getRightSon() == nodeToBeRemoved)
                    nodeToBeRemoved.getFather().setRightSon(nodeToBeRemoved.getRightSon());

                nodeToBeRemoved.setFather(null);
                nodeToBeRemoved.setRightSon(null);
            } else


            /** Se o nó a ser removido possuir dois filhos */
            if (nodeToBeRemoved.getLeftSon() != null && nodeToBeRemoved.getRightSon() != null) {
                Node<Integer> next = next(nodeToBeRemoved);

                nodeToBeRemoved.setValue(next.getValue());

                if (next.getFather().getLeftSon() == next)
                    next.getFather().setLeftSon(next.getRightSon());
                else
                    next.getFather().setRightSon(next.getRightSon());
                if (next.getRightSon() != null)
                    next.getRightSon().setFather(next.getFather());
            }
        }
        verifyBalance(root);
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

    private void verifyBalance(Node<Integer> node) {

        Node<Integer> aux = node;

        do {

            int bf = calculateBF(aux);
            aux.setBf(bf);

            if (Math.abs(bf) > 1) {

                switch (bf) {
                    case 2:
                        if (aux.getRightSon().getBf() == 0 || aux.getRightSon().getBf() == 1) {
                            leftRotate(aux);
                            break;
                        }
                        if (aux.getRightSon().getBf() == -1) {
                            rightRotate(aux.getRightSon());
                            leftRotate(aux);
                            break;
                        }
                    case -2:
                        if (aux.getLeftSon().getBf() == -1 || aux.getLeftSon().getBf() == 0) {
                            rightRotate(aux);
                            break;
                        }
                        if (aux.getLeftSon().getBf() == 1) {
                            leftRotate(aux.getLeftSon());
                            rightRotate(aux);
                        }
                }
            }
            aux = aux.getFather();
        } while (aux != null);
    }

    public int calculateBF(Node<Integer> node) {

        int heightLeft = 0;
        int heightRight = 0;

        if (node.getLeftSon() != null)
            heightLeft = 1 + subTreeHeight(node.getLeftSon());

        if (node.getRightSon() != null)
            heightRight = 1 + subTreeHeight(node.getRightSon());

        return heightRight - heightLeft;
    }

    private int subTreeHeight(Node<Integer> subTreeRoot) {

        int leftSubTreeHeight = 0;
        int rightSubTreeHeight = 0;

        if (subTreeRoot.getLeftSon() != null)
            leftSubTreeHeight = 1 + subTreeHeight(subTreeRoot.getLeftSon());

        if (subTreeRoot.getRightSon() != null)
            rightSubTreeHeight = 1 + subTreeHeight(subTreeRoot.getRightSon());

        if (leftSubTreeHeight > rightSubTreeHeight)
            return leftSubTreeHeight;
        else
            return rightSubTreeHeight;
    }

    public void rightRotate(Node<Integer> r) {

        Node<Integer> father = r.getFather();
        Node<Integer> t = r.getLeftSon();
        Node<Integer> m = t.getRightSon();

        t.setRightSon(r);
        r.setFather(t);
        r.setLeftSon(m);

        if (m != null)
            m.setFather(r);
        t.setFather(father);


        if (father != null) {
            if (father.getRightSon() == r)
                father.setRightSon(t);
            else
                father.setLeftSon(t);
        } else {
            root = t;
        }
    }

    public void leftRotate(Node<Integer> r) {

        Node<Integer> father = r.getFather();
        Node<Integer> t = r.getRightSon();
        Node<Integer> m = t.getLeftSon();

        t.setLeftSon(r);
        r.setFather(t);
        r.setRightSon(m);

        if (m != null)
            m.setFather(r);
        t.setFather(father);

        if (father != null) {
            if (father.getLeftSon() == r)
                father.setLeftSon(t);
            else
                father.setRightSon(t);
        } else {
            root = t;
        }
    }
}














