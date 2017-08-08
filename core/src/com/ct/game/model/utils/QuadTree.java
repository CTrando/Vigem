package com.ct.game.model.utils;

class TreeNode<T> {
    float x;
    float y;
    float width;
    T data;

    TreeNode<T> NW;
    TreeNode<T> NE;
    TreeNode<T> SW;
    TreeNode<T> SE;

    TreeNode(float x, float y, float width, T data) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.data = data;
    }
}

public class QuadTree<T> {
    private TreeNode<T> root;
    private float width;

    public QuadTree(float width) {
        this.width = width;
        this.root = new TreeNode<T>(0, 0, this.width, null);
    }

    public void insert(float x, float y, T data) {
        this.root = insert(x, y, root.width, root.x, root.y, root, data);
        System.out.println("Complete");
    }

    private TreeNode<T> insert(float x, float y, float width, float centerX, float centerY, TreeNode<T> root, T data) {
        float newWidth = width / 2;
        if (root == null) {
            root = new TreeNode<T>(centerX, centerY, newWidth, null);
        }

        if (newWidth <= .5) {
            root.data = data;
            return root;
        }

        if (greaterThan(x, root.x) && greaterThan(y, root.y)) {
            root.NE = insert(x, y, newWidth,
                             centerX + newWidth / 2,
                             centerY + newWidth / 2,
                             root.NE, data);
        } else if (!greaterThan(x, root.x) && greaterThan(y, root.y)) {
            root.NW = insert(x, y, newWidth,
                             centerX - newWidth / 2,
                             centerY + newWidth / 2,
                             root.NW, data);
        } else if (!greaterThan(x, root.x) && !greaterThan(y, root.y)) {
            root.SW = insert(x, y, newWidth,
                             centerX - newWidth / 2,
                             centerY - newWidth / 2,
                             root.SW, data);
        } else if (greaterThan(x, root.x) && !greaterThan(y, root.y)) {
            root.SE = insert(x, y, newWidth,
                             centerX + newWidth / 2,
                             centerY - newWidth / 2,
                             root.SE, data);
        }
        return root;
    }

    public T get(float x, float y) {
        return get(x, y, root);
    }

    private T get(float x, float y, TreeNode<T> node) {
        if (x < -this.root.width/2 || y < -this.root.width/2) {
            return null;
        }
        if (x > this.root.width/2 || y > this.root.width/2) {
            return null;
        }
        if (node.width <= .5) {
            return node.data;
        }
        if (node.x == x && node.y == y) {
            node.width /= 2;
            return get(x, y, node);
        }


        if (greaterThan(x, node.x) && greaterThan(y, node.y)) {
            if (node.NE == null) {
                return null;
            } else {
                return get(x, y, node.NE);
            }
        } else if (!greaterThan(x, node.x) && greaterThan(y, node.y)) {
            if (node.NW == null) {
                return null;
            } else {
                return get(x, y, node.NW);
            }
        } else if (!greaterThan(x, node.x) && !greaterThan(y, node.y)) {
            if (node.SW == null) {
                return null;
            } else {
                return get(x, y, node.SW);
            }
        } else if (greaterThan(x, node.x) && !greaterThan(y, node.y)) {
            if (node.SE == null) {
                return null;
            } else {
                return get(x, y, node.SE);
            }
        }
        return null;
    }

    public void setRoot(TreeNode<T> newRoot) {
        this.root = newRoot;
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    private boolean greaterThan(float num1, float num2) {
        return num1 > num2;
    }
}
