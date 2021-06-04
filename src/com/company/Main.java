package com.company;
import java.util.Scanner;

class Main {
	private static Scanner scanner = new Scanner(System.in,"cp1251");
	private static Node head = null;

	public static void main(String[] args) {
		System.out.println("������� ���������� ����� =>");
		int count = TypeInt();
		System.out.println("\n������� ����� =>");

		{
			Node current = null;
			for (int i = 0; i < count; i++) {
				if (head == null)
					current = head = new Node(TypeInt());
				else
					current = current.SetNext(new Node(TypeInt())).GetNext();
			}
		}
		a();
		b();
	}

	private static void a() {
		int summ = 0;
		int count = 0;
		double average = 0;
		Node current = head;
		while (current != null) {
			int currentValue = current.GetValue();
			if ((currentValue % 9) != 0) {
				summ += currentValue;
				count++;
			}
			current = current.GetNext();
		}
		if (count == 0) {
			System.out.println("\n�����, ������� �� ������� �� 9, ���");
		}
		else {
			average = ((double)summ)/count;
			System.out.println("\n�����: " + summ + "\n����������: " + count + "\n������� ��������: " + average);
		}
	}

	private static void b() {
		Node min = null;
		Node max = null;
		Node preMin = null;
		Node preMax = null;
		Node previous = null;
		Node current = head;
		int minIndex = 0;
		int maxIndex = 0;
		int index = 0;
		while (current != null) {
			int currentValue = current.GetValue();
			if ((currentValue < 0) && ((currentValue % 7) != 0)) {
				if (min == null) { // ������ �������
					min = max = current;
					minIndex = maxIndex = index;
					preMin = preMax = previous;
				}
				else {
					if (currentValue < min.GetValue()) {
						min = current;
						preMin = previous;
						minIndex = index;
					}
					if (currentValue > max.GetValue()) {
						max = current;
						preMax = previous;
						maxIndex = index;
					}
				}
			}
			previous = current;
			current = current.GetNext();
			index++;
		}
		if (min == null) {
			System.out.println("\n������������� �����, ������� �� ������� �� 7, ���");
		}
		else if (min == max) { // ��� �� ����� ������ ������ �������, �.�. �� ������ ���� (��� ��������� ���������� ����� ���� ������-�� ��������, � ������ ��������� �� ���� � ��� �� ������)
			System.out.println("\n��� ������ ���� (��� ������ ����������) ������������� �����, ������� �� ������� �� 7: " + min.GetValue());
			System.out.println("� ��� ��������� �� " + minIndex);
		}
		else {
			System.out.println("\n����������� ������������� �����, ������� �� ������� �� 7: " + min.GetValue());
			System.out.println("� ��� ��������� �� " + minIndex);
			System.out.println("\n������������ ������������� �����, ������� �� ������� �� 7: " + max.GetValue());
			System.out.println("� ��� ��������� �� " + maxIndex);

			System.out.println("\n������ �� �������...");
			if (preMin == null) { // ���� min == head
				Node temp = head.GetNext();
				preMax.SetNext(head);
				min.SetNext(max.GetNext());
				max.SetNext(temp);
				head = max;
			}
			else if (preMax == null) { // ���� max = head
				Node temp = head.GetNext();
				preMin.SetNext(head);
				max.SetNext(min.GetNext());
				min.SetNext(temp);
				head = min;
			}
			else { // ���� min != head � max != head
				preMax.SetNext(min);
				preMin.SetNext(max);
				Node temp = min.GetNext();
				min.SetNext(max.GetNext());
				max.SetNext(temp);
			}

			System.out.print(head.GetValue());
			current = head.GetNext();
			while (current != null) {
				System.out.print(" " + current.GetValue());
				current = current.GetNext();
			}
			System.out.println();
		}
	}

	private static int TypeInt() {
		int value = scanner.nextInt();
		scanner.nextLine();
		return value;
	}
}