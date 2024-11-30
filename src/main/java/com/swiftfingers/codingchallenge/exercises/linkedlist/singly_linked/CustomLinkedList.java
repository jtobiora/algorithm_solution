package com.swiftfingers.codingchallenge.exercises.linkedlist.singly_linked;


public class CustomLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public CustomLinkedList() {
        this.size = 0;
    }

    protected class Node {
        private int value;
        private Node next;

        public Node (int data) {
            this.value = data;
        }

        public Node(int data, Node next) {
            this.value = data;
            this.next = next;
        }
    }
}
