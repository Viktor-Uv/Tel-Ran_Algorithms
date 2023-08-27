package lesson16;

public class MyBinaryTree {
    static class Vortex {
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
}
