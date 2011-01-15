package com.puresol;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * This class is a special implementation for a list for listeners. This is used
 * for keeping listener references. To avoid memory leaks, the list is
 * implemented with weak references.
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <T>
 */
public class ListenerSet<T> implements Set<T> {

	private final List<WeakReference<T>> listeners = new ArrayList<WeakReference<T>>();

	@Override
	public boolean add(T listener) {
		for (WeakReference<T> weakReference : listeners) {
			if (weakReference.get().equals(listener)) {
				/*
				 * Listener was found, so we can abort here...
				 */
				return false;
			}
		}
		listeners.add(new WeakReference<T>(listener));
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends T> listeners) {
		boolean result = false;
		for (T t : listeners) {
			if (add(t)) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public boolean remove(Object listener) {
		WeakReference<T> toBeRemoved = null;
		for (WeakReference<T> weakReference : listeners) {
			if (weakReference.get().equals(listener)) {
				toBeRemoved = weakReference;
				break;
			}
		}
		if (toBeRemoved == null) {
			return false;
		}
		listeners.remove(toBeRemoved);
		return true;
	}

	@Override
	public int size() {
		return getListeners().size();
	}

	private Set<T> getListeners() {
		Set<T> toBeRemoved = new HashSet<T>();
		Set<T> available = new HashSet<T>();
		for (WeakReference<T> weakReference : listeners) {
			if (weakReference.get() != null) {
				available.add(weakReference.get());
			} else {
				toBeRemoved.add(weakReference.get());
			}
		}
		listeners.removeAll(toBeRemoved);
		return available;
	}

	@Override
	public void clear() {
		listeners.clear();
	}

	@Override
	public boolean contains(Object listener) {
		for (WeakReference<T> weakReference : listeners) {
			T t = weakReference.get();
			if (t != null) {
				if (t.equals(listener)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> listeners) {
		for (Object listener : listeners) {
			if (!contains(listener)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		return listeners.isEmpty();
	}

	@Override
	public Iterator<T> iterator() {
		return getListeners().iterator();
	}

	@Override
	public boolean removeAll(Collection<?> listeners) {
		boolean result = false;
		for (Object listener : listeners) {
			if (!remove(listener)) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public boolean retainAll(Collection<?> collection) {
		boolean result = false;
		Set<T> listeners = getListeners();
		for (T listener : listeners) {
			if (!collection.contains(listener)) {
				remove(listener);
				result = true;
			}
		}
		return result;
	}

	@Override
	public Object[] toArray() {
		Set<T> listeners = getListeners();
		return listeners.toArray();
	}

	@Override
	public <S> S[] toArray(S[] s) {
		Set<T> listeners = getListeners();
		return listeners.toArray(s);
	}

}
