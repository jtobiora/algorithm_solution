package com.swiftfingers.codingchallenge.exercises.linkedlist.singly_linked;

public class SInglyLinkedList {

    private Node head;
    private Node tail;
    private int size;


    public SInglyLinkedList () {
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

    public Node insertAtBeginning (int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;

        if (tail == null) {
            tail = head;
        }
        size += 1;
        return head;
    }

    public void insertAtEnd (int data) {
        //if list is empty
        if (tail == null) {
            insertAtBeginning(data);
            return;
        }
        Node newNode = new Node(data);
        tail.next = newNode;
        tail = newNode;
        size += 1;
    }

    public void insertAtIndex (int index, int data) throws Exception {
        Node newNode = new Node(data);
        Node temp = head;

        if (index < 0 || index > size) {
           throw new RuntimeException("Invalid argument");
        }

        if (index == 0) {
            insertAtBeginning(data);
            return;
        }

        if (index == size - 1) {
            insertAtEnd(data);
            return;
        }

        for (int x = 0; x < index - 1; x++) {
            temp = temp.next;
        }

        newNode.next = temp.next;
        temp.next = newNode;
        size++;
    }

    //Delete operations
    public Node deleteFirst () {
        System.out.println("Deleting first .....");
        if (head == null) return null;
        Node node = head;
        head = head.next;
        node.next = null;
        size--;
        return head;
    }

    public void deleteLast () {
        System.out.println("Deleting last ....");
        if (head == null) return;
        Node temp = head;
        Node secondToLast = null;

        while (temp.next != null) {
            secondToLast = temp;
            temp = temp.next;
        }

        secondToLast.next = null;
        size--;
    }

    public void deleteAtIndex (int index) {
        System.out.println("Deleting at index " + index);
        if (index < 0 || index >= size) {
            System.out.println("Argument passed is invalid....");
            return;
        }
        if (index == 0) {
            deleteFirst();
            return;
        }
        if (index == size - 1) {
            deleteLast();
            return;
        }

        Node prev = get(index - 1);

        prev.next = prev.next.next;
    }

    public void printList () {
        Node temp = head;

        while (temp != null) {
            System.out.print(temp.value + " -> ");
            temp = temp.next;
        }

        System.out.println("END");
    }

    public Node get (int index) {
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        return node;
    }


}
