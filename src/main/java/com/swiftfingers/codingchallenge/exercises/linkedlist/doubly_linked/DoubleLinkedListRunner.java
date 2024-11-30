package com.swiftfingers.codingchallenge.exercises.linkedlist.doubly_linked;

public class DoubleLinkedListRunner {
    public static void main(String[] args) {
        DoublyLinkedList runner = new DoublyLinkedList();
        runner.insertAtBeginning(4);
        runner.insertAtBeginning(12);
        runner.insertAtBeginning(6);
        runner.insertAtEnd(21);


        DoublyLinkedList.Node node = runner.setNode(6);
        runner.insertAfterANode(node, 33);
        runner.printList();
    }
}
