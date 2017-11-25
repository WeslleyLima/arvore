public class App {

    public static void main(String[] args) {

        AVLTree avlTree = new AVLTree();

//        avlTree.add(15);
//        avlTree.add(27);
//        avlTree.add(49);
//        avlTree.add(10);
//        avlTree.add(9);
//        avlTree.add(67);
//        avlTree.add(59);
//        avlTree.add(8);
//        avlTree.add(13);
//        avlTree.add(20);
//        avlTree.add(14);
//        avlTree.add(7);

        avlTree.add(30);
        avlTree.add(50);
        avlTree.add(80);
        avlTree.add(20);
        avlTree.add(10);
        avlTree.add(25);
        avlTree.add(70);

//        avlTree.recursiveAdd(30);
//        avlTree.recursiveAdd(50);
//        avlTree.recursiveAdd(80);
//        avlTree.recursiveAdd(20);
//        avlTree.recursiveAdd(10);
//        avlTree.recursiveAdd(25);
//        avlTree.recursiveAdd(70);


//        avlTree.recursiveAdd(15);
//        avlTree.recursiveAdd(27);
//        avlTree.recursiveAdd(49);
//        avlTree.recursiveAdd(10);
//        avlTree.recursiveAdd(9);
//        avlTree.recursiveAdd(67);
//        avlTree.recursiveAdd(59);
//        avlTree.recursiveAdd(8);
//        avlTree.recursiveAdd(13);
//        avlTree.recursiveAdd(20);
//        avlTree.recursiveAdd(14);
//        avlTree.recursiveAdd(7);

        System.out.println();
        avlTree.remove(30);
        System.out.println();
        avlTree.remove(20);
        System.out.println();
        avlTree.remove(10);
        System.out.println();
        avlTree.remove(25);
        System.out.println();
        avlTree.add(75);
        System.out.println();
        avlTree.remove(50);
        System.out.println();
//        System.out.println(avlTree.recursiveMin(new Node<>(59)));

    }
}
