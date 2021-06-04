package com.company;

class Node {
	private int value;
	private Node next;

	public Node(int value, Node next) {
		this.value = value;
		this.next = next;
	}
	public Node(int value) {
		this(value, null);
	}

	public Node SetNext(Node next) {
		this.next = next;
		return this;
	}
	public Node GetNext() {
		return this.next;
	}
	public Node SetValue(int value) {
		this.value = value;
		return this;
	}
	public int GetValue() {
		return this.value;
	}
}