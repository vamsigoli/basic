package com.vamsi.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class TestClass<T> implements TestInterface<T>  {


	public Collection<T> testCollectionReturn() {
		
		List<T> alist = new ArrayList<T>();
		return alist;
	}

}
