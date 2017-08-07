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
    private int width;

    public QuadTree(int width) {
        this.width = width;
        this.root = new TreeNode<T>(this.width / 2, this.width / 2, this.width, null);
    }

    public void insert(float x, float y, T data) {
        this.root = insert(x, y, width, width / 2, width / 2, root, data);
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

    private T get(float x, float y, TreeNode<T> root) {
        if (x < 0 || y < 0) {
            return null;
        }
        if (x > this.root.width || y > this.root.width) {
            return null;
        }
        if (root.width <= .5) {
            return root.data;
        }
        if (root.x == x && root.y == y) {
            root.width /= 2;
            return get(x, y, root);
        }


        if (greaterThan(x, root.x) && greaterThan(y, root.y)) {
            if (root.NE == null) {
                return null;
            } else {
                return get(x, y, root.NE);
            }
        } else if (!greaterThan(x, root.x) && greaterThan(y, root.y)) {
            if (root.NW == null) {
                return null;
            } else {
                return get(x, y, root.NW);
            }
        } else if (!greaterThan(x, root.x) && !greaterThan(y, root.y)) {
            if (root.SW == null) {
                return null;
            } else {
                return get(x, y, root.SW);
            }
        } else if (greaterThan(x, root.x) && !greaterThan(y, root.y)) {
            if (root.SE == null) {
                return null;
            } else {
                return get(x, y, root.SE);
            }
        }
        return null;
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    private boolean greaterThan(float num1, float num2) {
        return num1 > num2;
    }
}
