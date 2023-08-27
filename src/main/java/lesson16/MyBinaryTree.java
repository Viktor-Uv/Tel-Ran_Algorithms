package lesson16;

public class MyBinaryTree {
    static class Vortex {

        // глублина дерева с текущей вершины
        private int depth() {
            int leftCount = 1 + (left == null ? 0 : left.depth());
            int rightCount = 1 + (right == null ? 0 : right.depth());
            return Math.max(leftCount, rightCount);
        }

        // количество вершин начиная с текущей
        private int countVertices() {
            return 1 +
                    (left == null ? 0 : left.countVertices()) +
                    (right == null ? 0 : right.countVertices());
        }

        int value; // значение вершины
        Vortex left;
        Vortex right;

        public Vortex(int value) {
            this.value = value;
        }

        public Vortex(int value, Vortex left, Vortex right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        /*
         *  {
         *      "left":{},
         *      "value": 3,
         *      "right": {
         *          "left":{},
         *          "value": 5,
         *          "right": {}
         *      }
         *  }
         */
        @Override
        public String toString() {
            String r = "{";
            r += "\"left\":";
            r += left == null ? "{}" : left.toString();
            r += ",  \"value\":  ";
            r += value;
            r += ", ";
            r += "\"right\":";
            r += right == null ? "{}" : right.toString();
            r += "}";
            return r;
        }
    }

    private Vortex root; // корень - вершина дерева

    public MyBinaryTree(Vortex root) {
        this.root = root;
    }

    @Override
    public String toString() {
        return root.toString();
    }

    // Количество вершин
    public int countVertices() {
        return root == null ? 0 : root.countVertices();
    }

    public int depth() {
        return root == null ? 0 : root.depth();
    }

    // присутствует ли значение в дереве
    public boolean contains(int value) {
        return containsRecursive(root, value);
    }

    private boolean containsRecursive(Vortex current, int value) {
        if (current == null) {
            return false;
        } else if (current.value == value) {
            return true;
        } else if (value < current.value) {
            return containsRecursive(current.left, value);
        } else {
            return containsRecursive(current.right, value);
        }
    }

    public void add(int value) {
        root = addRecursive(root, value);
    }

    private Vortex addRecursive(Vortex current, int value) {
        if (current == null) {
            return new Vortex(value);
        } else if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        }
        return current;
    }
}
