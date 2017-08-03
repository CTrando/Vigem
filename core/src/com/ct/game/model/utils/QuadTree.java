package com.ct.game.model.utils;

class TreeNode<T> {
    public int x;
    public int y;
    public int width;
    public T data;

    public TreeNode NW;
    public TreeNode NE;
    public TreeNode SW;
    public TreeNode SE;

    public TreeNode(int x, int y, int width, T data) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.data = data;
    }
}

public class QuadTree<T> {
    TreeNode root;
    int width;

    public QuadTree(int width) {
        this.width = width;
        this.root = new TreeNode<T>(width / 2, width / 2, width, null);
    }

    public void insert(int x, int y, T data) {
        this.root = insert(x, y, width, width / 2, width / 2, root, data);
        System.out.println("Complete");
    }

    private TreeNode insert(int x, int y, int width, int centerX, int centerY, TreeNode root, T data) {
        int newWidth = width / 2;
        if(root == null){
            root = new TreeNode<T>(centerX+1, centerY+1, newWidth, null);
            if(newWidth <= 1) {
                root.data = data;
                return root;
            }
        }

        if (greaterThan(x, root.x) && greaterThan(y, root.y)) {
            root.NE = insert(x, y, newWidth,
                   centerX + newWidth / 2 ,
                   centerY + newWidth / 2,
                   root.NE, data);
        } else if (!greaterThan(x, root.x) && greaterThan(y, root.y)) {
            root.NW = insert(x, y, newWidth,
                   centerX - newWidth/2,
                   centerY + newWidth/2,
                   root.NW, data);
        } else if (!greaterThan(x, root.x) && !greaterThan(y, root.y)) {
            root.SW = insert(x, y, newWidth,
                   centerX - newWidth/2,
                   centerY - newWidth/2,
                   root.SW, data);
        } else if (greaterThan(x, root.x) && !greaterThan(y, root.y)) {
            root.SE = insert(x, y, newWidth,
                   centerX + newWidth/2,
                   centerY - newWidth/2,
                   root.SE, data);
        }
        return root;
    }

    private boolean greaterThan(int num1, int num2) {
        return num1 > num2;
    }
}
