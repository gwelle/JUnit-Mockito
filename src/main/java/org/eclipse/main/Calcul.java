package org.eclipse.main;

public class Calcul {

	public int add(int a, int b) {
		return a + b;
	}

	public int multiply(int a, int b) {
		return a * b;
	}

	public double add(double a, double b) {
		return a + b;
	}

	public double multiply(double a, double b) {
		return a * b;
	}

	public int divide(int x, int y) {
		if (y == 0)
			throw new ArithmeticException();
		return x / y;
	}
}