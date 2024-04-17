package org.eclipse.main;

import java.util.List;

public class MyClass {

	private List list;

	public MyClass(List list) {
		super();
		this.list = list;
	}

	public List getList() {
		return list;
	}

	public int add(int a, int b) {
		return (a > b ? a + b : b + a);
	}

}
