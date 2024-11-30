package com.swiftfingers.codingchallenge.exercises.linkedlist.singly_linked;

public class SIngleLinkedListRunner {
    public static void main(String[] args) {
        try {
            SInglyLinkedList sInglyLinkedList = new SInglyLinkedList();
            sInglyLinkedList.insertAtBeginning(10);
            sInglyLinkedList.insertAtBeginning(9);
            sInglyLinkedList.insertAtBeginning(2);
            sInglyLinkedList.insertAtBeginning(5);
            sInglyLinkedList.insertAtEnd(17);
            sInglyLinkedList.insertAtIndex(2, 100);
            sInglyLinkedList.printList();
            sInglyLinkedList.deleteFirst();
            sInglyLinkedList.printList();
            sInglyLinkedList.deleteLast();
            sInglyLinkedList.printList();
            sInglyLinkedList.deleteAtIndex(1);
            sInglyLinkedList.printList();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
