package com.puresol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.lang.ref.WeakReference;

import org.junit.Test;

public class ListenerSetTest {

    private class TestClass {

	private final int i;

	public TestClass(int i) {
	    super();
	    this.i = i;
	}

	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + getOuterType().hashCode();
	    result = prime * result + i;
	    return result;
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
		return true;
	    if (obj == null)
		return false;
	    if (getClass() != obj.getClass())
		return false;
	    TestClass other = (TestClass) obj;
	    if (!getOuterType().equals(other.getOuterType()))
		return false;
	    if (i != other.i)
		return false;
	    return true;
	}

	private ListenerSetTest getOuterType() {
	    return ListenerSetTest.this;
	}

    }

    @Test
    public void testWeakReference() {
	WeakReference<TestClass> ref1;
	WeakReference<TestClass> ref2;
	{
	    TestClass class1 = new TestClass(1);
	    ref1 = new WeakReference<TestClass>(class1);
	    ref2 = new WeakReference<TestClass>(class1);
	    assertNotSame(ref1, ref2);
	    assertSame(ref1.get(), ref2.get());
	    assertEquals(ref1.get(), ref2.get());
	    assertFalse(ref1.equals(ref2));
	    class1 = null;
	    System.gc();
	}
	assertNull(ref1.get());
	assertNull(ref2.get());
    }

    @Test
    public void testInstance() {
	assertNotNull(new WeakReferenceSet<String>());
    }

    @Test
    public void testInitValues() {
	WeakReferenceSet<String> set = new WeakReferenceSet<String>();
	assertEquals(0, set.size());
    }

    @Test
    public void testAddingAndRemoveing() {
	Integer s1 = 1;
	Integer s2 = 2;
	WeakReferenceSet<Integer> set = new WeakReferenceSet<Integer>();
	assertEquals(0, set.size());
	set.add(s1);
	assertEquals(1, set.size());
	set.add(s1);
	assertEquals(1, set.size());
	set.add(s2);
	assertEquals(2, set.size());
	{
	    Integer s3 = new Integer(3);
	    set.add(s3);
	    assertEquals(3, set.size());
	    set.remove(s2);
	    assertEquals(2, set.size());
	    s3 = null;
	    System.gc();
	}
	assertEquals(1, set.size());
    }
}
