package com.company;
import java.util.Scanner;

class Main {
	private static Scanner scanner = new Scanner(System.in,"cp1251");
	private static Node head = null;

	public static void main(String[] args) {
		System.out.println("Введите количество чисел =>");
		int count = TypeInt();
		System.out.println("\nВведите числа =>");

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
			System.out.println("\nЧисел, которые не делятся на 9, нет");
		}
		else {
			average = ((double)summ)/count;
			System.out.println("\nСумма: " + summ + "\nКоличество: " + count + "\nСреднее значение: " + average);
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
				if (min == null) { // Первая находка
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
			System.out.println("\nОтрицательных чисел, которые не делятся на 7, нет");
		}
		else if (min == max) { // Тут не имеет смысла менять местами, т.к. он только один (или следующее подходящие числа были такого-же значения, и ссылки указывают на один и тот же объект)
			System.out.println("\nТут только одно (или только одинаковые) отрицательное число, которое не делится на 7: " + min.GetValue());
			System.out.println("И оно находится на " + minIndex);
		}
		else {
			System.out.println("\nМинимальное отрицательное число, которое не делится на 7: " + min.GetValue());
			System.out.println("И оно находится на " + minIndex);
			System.out.println("\nМаксимальное отрицательное число, которое не делится на 7: " + max.GetValue());
			System.out.println("И оно находится на " + maxIndex);

			System.out.println("\nЗамена их местами...");
			if (preMin == null) { // Если min == head
				Node temp = head.GetNext();
				preMax.SetNext(head);
				min.SetNext(max.GetNext());
				max.SetNext(temp);
				head = max;
			}
			else if (preMax == null) { // Если max = head
				Node temp = head.GetNext();
				preMin.SetNext(head);
				max.SetNext(min.GetNext());
				min.SetNext(temp);
				head = min;
			}
			else { // Если min != head и max != head
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