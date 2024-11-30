package com.swiftfingers.codingchallenge.exercises.linkedlist.doubly_linked;


public class DoublyLinkedList {
    private Node head;
    private int size;


    public DoublyLinkedList () {
        this.size = 0;
    }

    public Node setNode (int val) {
        Node node = new Node(val);
        return node;
    }

    protected class Node {
        private Node next;
        private Node prev;
        private int value;

        public Node(Node next, Node prev, int value) {
            this.next = next;
            this.prev = prev;
            this.value = value;
        }

        public Node(int value) {
            this.value = value;
        }


    }


    public void insertAtBeginning (int value) {
        Node newNode = new Node(value); //create a new node
        newNode.next = head; //make the next of the newNode point to head
        newNode.prev = null; //make the prev of the newNode point to null
        if (head != null) {
            head.prev = newNode; //make the prev of the head point to the newNode
        }

        head = newNode;
    }

    public void insertAtEnd (int value) {
        Node newNode = new Node(value); //create a new node
        Node temp = head;
         if (head == null) {
             newNode.prev = null;
             head = newNode;
             return;
         }

         while (temp.next != null) {
             temp = temp.next;
         }

         newNode.prev = temp;
         temp.next = newNode;
         newNode.next = null;
    }


    //given a node as prev_node insert a new node after the given node
    public void insertAfterANode (Node prevNode, int value) {
        //create the new node
        Node newNode = new Node(value);
        if (prevNode == null) {
            System.out.println("Previuos node cannot be empty. Inserting at the beginning");
            insertAtBeginning(value);
            return;
        }

        System.out.println("---" + prevNode);
        prevNode.next = newNode;
        newNode.prev = prevNode;
        newNode.next = prevNode.next;
        if (newNode.next != null) {
            newNode.next.prev = newNode;
        }


    }

    //given a node as next_node insert a new node before the given node
    public void insertBeforeANode (Node nextNode, int value) {
        //create the new node
        Node createdNode = new Node(value);
        createdNode.next = nextNode;
        nextNode.prev = createdNode;
        createdNode.prev = nextNode.prev;
        if (createdNode.prev != null) {
            createdNode.prev.next = createdNode;
        } else {
            head = createdNode;
        }


    }


    public void printList () {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.value + " -> ");
            temp = temp.next;
        }

        System.out.println("END");
    }
}
