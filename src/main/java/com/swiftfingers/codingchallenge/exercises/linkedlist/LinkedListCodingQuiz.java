package com.swiftfingers.codingchallenge.exercises.linkedlist;

public class LinkedListCodingQuiz {
     Node head;
     Node tail;
     int size;


    public LinkedListCodingQuiz () {
        this.size = 0;
    }

    public static void main(String[] args) {
       /* Use push() to construct below list
           1->12->1->4->1  */

        LinkedListCodingQuiz codingQuiz = new LinkedListCodingQuiz();
        codingQuiz.push(1);
        codingQuiz.push(4);
        codingQuiz.push(16);
        codingQuiz.push(12);
        codingQuiz.push(1);
        codingQuiz.push(7);
        codingQuiz.push(5);

        Node node = codingQuiz.head;

        codingQuiz.printList();
        System.out.println("The nth node value is: " +codingQuiz.getNth(node, 2));

        System.out.println("Count is: " +codingQuiz.getLength(node));

        codingQuiz.middleofLinkedList(node);

        System.out.println(1 + " appears " + codingQuiz.numberCount(node,1) + " times");

        codingQuiz.moveLasttoFront(node);
        codingQuiz.printList();

        codingQuiz.setMiddleHead(node);

        codingQuiz.printList();
    }

    public void push(int new_data) {

        /* 1. alloc the Node and put data*/
        Node new_Node = new Node(new_data);

        /* 2. Make next of new Node as head */
        new_Node.next = head;

        /* 3. Move the head to point to new Node */
        head = new_Node;
    }

    protected class Node {
        private int value;
        private Node next;

        public Node (int value) {
            this.value = value;
        }

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    //Given a singly linked list with N nodes and a number X. The task is to find the node at the given index
    // (X)(1 based index) of linked list.
    public int getNth(Node node, int x) {
        Node temp = node;
        int count = 0;

        while (temp != null ) {
            count++;
            if (count == x) {
                return temp.value;
            }
            temp = temp.next;
        }
        return -1;
    }

    //Given a singly linked list. The task is to find the length of the linked list, where length is defined
    // as the number of nodes in the linked list.
    public int getLength(Node head) {

        //Code here
        int count = 0;
        if (head == null) {
            System.out.println("Head is null and so length is empty");
            return 0;
        }

        while (head != null) {
            count++;
            head = head.next;
        }

        return count;


    }

    public void printList () {
        Node temp = head;

        while (temp != null) {
            System.out.print(temp.value + " -> ");
            temp = temp.next;
        }

        System.out.println("END");
    }

    //Given a singly linked list, find the middle of the linked list. For example, if the given linked list
    // is 1->2->3->4->5 then the output should be 3.
    //If there are even nodes, then there would be two middle nodes, we need to print the second
    // middle element.
    public Node middleofLinkedList (Node n) {
        Node temp = n;

        //get the length
        int length = getLength(n);
            int loopVal = (length/2) + 1;
            for (int i = 1; i < loopVal; i++) {
                temp = temp.next;
            }

        System.out.println("Middle element " + temp.value);
            return temp;
    }


    //Write a function that counts the number of times a given int occurs in a Linked List
    public int numberCount (Node node, int number) {
        Node temp = node;
        int count = 0;
        while (temp != null) {
            if (temp.value == number) {
                count++;
            }
            temp = temp.next;
        }

        return count;
    }

    //get a node at an index
    public Node get (int index) {
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        return node;
    }

    //Write a function that moves the last node to the front in a given Singly Linked List.
    public Node moveLasttoFront (Node node) {
        Node temp = node;

        if (temp == null) return null;

        //if only one item is found
        if (temp.next == null) return node;

        Node secondToLast = null;

        while (temp.next != null) {
            secondToLast = temp;
            temp = temp.next;
        }

        secondToLast.next = null;
        temp.next = head;
        head = temp;
        return head;
    }

    public void setMiddleHead (Node node) {
        //find the middle node
       Node middle =  middleofLinkedList(node);
       middle.next = head;
       head = middle;

    }

}


