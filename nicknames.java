import java.io.*;

class AVLVertex {
    AVLVertex(String v) { value = v; parent = left = right = null; height = 0; size = 1; bf = 0; }
    public AVLVertex parent, left, right;
    public String value;
    public int height; 
    public int size; 
    public int bf;

}

class AVL {
    public AVLVertex root;
    AVL() { root = null; }

    public String search(String string) {
        AVLVertex res = search(root, string);
        return res == null ? "Not Found" : res.value;
    }

    public AVLVertex search(AVLVertex vertex, String value) {
        if (vertex == null) {
            return null;
        } else if (value.equals(vertex.value)) {
            return vertex;
        } else if (value.compareTo(vertex.value) < 0) {
            return search(vertex.left, value);
        } else {
            return search(vertex.right, value);
        }
    }

    public void insert(String value) {
        root = insert(root, value);
    }

    public AVLVertex insert(AVLVertex vertex, String value) {
        if (vertex == null) {
            return new AVLVertex(value);
        } else if (value.compareTo(vertex.value) < 0) {
            vertex.left = insert(vertex.left, value);
            vertex.left.parent = vertex;
        } else {
            vertex.right = insert(vertex.right, value);
            vertex.right.parent = vertex; 
        }
        updateHeightAndSize(vertex);
        return balance(vertex);
    }    

    public int getHeight(AVLVertex vertex) {
        if (vertex == null) {
            return - 1;
        } else {
            return vertex.height;
        }
    }

    public int getBf(AVLVertex vertex) {
        if (vertex != null) {
            vertex.bf = getHeight(vertex.left) - getHeight(vertex.right);
            return vertex.bf;
        } else {
            return 0;
        }
    }

    public int getSize(AVLVertex vertex) {
        if (vertex == null) {
            return 0;
        } else {
            return vertex.size;
        }
    }

    public void updateHeightAndSize(AVLVertex vertex) {
        if (vertex == null) {
            return;
        }
        vertex.height = 1 + Math.max(getHeight(vertex.left), getHeight(vertex.right));
        vertex.size = 1 + getSize(vertex.left) + getSize(vertex.right);
    }

    public AVLVertex rotateRight(AVLVertex vertex) {
        AVLVertex other = vertex.left;
        other.parent = vertex.parent;
        vertex.parent = other;
        vertex.left = other.right;
        if (other.right != null) {
            other.right.parent = vertex;
        }
        other.right = vertex;       
        updateHeightAndSize(vertex);
        updateHeightAndSize(other);
        return other;
    }

    public AVLVertex rotateLeft(AVLVertex vertex) {
        AVLVertex other = vertex.right;
        other.parent = vertex.parent;
        vertex.parent = other;
        vertex.right = other.left;
        if (other.left != null) {
            other.left.parent = vertex;
        }
        other.left = vertex;        
        updateHeightAndSize(vertex);
        updateHeightAndSize(other);
        return other;
    }

    public AVLVertex balance(AVLVertex vertex) {
        int balanceFactor = getBf(vertex);
        int bfLeft = getBf(vertex.left);
        int bfRight = getBf(vertex.right);
        // Left heavy
        if (balanceFactor == 2) {
            if (0 <= bfLeft && bfLeft <= 1) {
                return rotateRight(vertex);
            } else if (bfLeft == - 1) {
                vertex.left = rotateLeft(vertex.left);
                return rotateRight(vertex);
            }
        } else if (balanceFactor == - 2) {
            if (- 1 <= bfRight && bfRight <= 0) {
                return rotateLeft(vertex);
            } else if (bfRight == 1) {
                vertex.right = rotateRight(vertex.right);
                return rotateLeft(vertex);
            }
        }
        return vertex;
    }

    public void inorder() { 
        inorder(root);
        System.out.println();
    }

    public void inorder(AVLVertex T) {
        if (T == null) return;
        inorder(T.left);                              
        System.out.printf(" %s", T.value);      
        inorder(T.right);
    }

    public int getRight(AVLVertex vertex, String string) {
        int length = string.length();
        if (vertex == null) {
            return 0;
        } else if (string.equals("")) {
            return getSize(root);
        } else {
            int cmp;
            if (vertex.value.length() < length) {
                cmp = string.compareTo(vertex.value);
            } else {
                cmp = string.compareTo(vertex.value.substring(0, length));
            }
            if (cmp < 0) {
                return getRight(vertex.left, string);
            } else {
                return 1 + getSize(vertex.left) + getRight(vertex.right, string);
            }
        }
    }

    public int getLeft(AVLVertex vertex, String string) {
        int length = string.length();
        if (vertex == null) {
            return 0;
        } else if (string.equals("")) {
            return 0;
        } else {
            int cmp;
            if (vertex.value.length() < length) {
                cmp = string.compareTo(vertex.value);
            } else {
                cmp = string.compareTo(vertex.value.substring(0, length));
            }
            if (cmp <= 0) {
                return getLeft(vertex.left, string);
            } else {
                return 1 + getSize(vertex.left) + getLeft(vertex.right, string);
            }
        }
    }

    public int getNickNames(String string) {
        return getRight(root, string) - getLeft(root, string);
    }
}


public class nicknames {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        AVL avlTree = new AVL();

        int names = Integer.parseInt(br.readLine());
        for (int i = 0; i < names; i++) {
            String name = br.readLine();
            avlTree.insert(name);
        }
        int nicknames = Integer.parseInt(br.readLine());
        for (int j = 0; j < nicknames; j++) {
            String nickname = br.readLine();
            pw.println(avlTree.getNickNames(nickname));
        }
        pw.close();
    }
}
