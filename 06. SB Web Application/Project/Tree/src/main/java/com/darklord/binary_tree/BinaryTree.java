package com.darklord.binary_tree;

import java.util.Scanner;

public class BinaryTree {
    private Node root;

    public BinaryTree(){
        this.root = null;
    }
    private static class Node{
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public void populate(Scanner sc){
        System.out.println("Enter the root node value : ");
        int value = sc.nextInt();
        this.root = new Node(value);
        populate(sc, root);
    }

    public void populate(Scanner sc, Node node){
        System.out.println("Do you want to enter left of " + node.value);
        boolean left = sc.nextBoolean();
        if(left){
            System.out.println("Enter the value of the left of "+ node.value);
            int value = sc.nextInt();
            populate(sc,  new Node(value));
        }
        System.out.println("Do you want to enter right of " + node.value);
        boolean right = sc.nextBoolean();
        if(right){
            System.out.println("Enter the value of the right of "+ node.value);
            int value = sc.nextInt();
            populate(sc,  new Node(value));
        }
    }


}
