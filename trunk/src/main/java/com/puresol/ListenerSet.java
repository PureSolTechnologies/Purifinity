package com.puresol;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
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
public class ListenerSet<T> {

	private final List<WeakReference<T>> listeners = new ArrayList<WeakReference<T>>();

	public void addListener(T listener) {
		for (WeakReference<T> weakReference : listeners) {
			if (weakReference.get().equals(listener)) {
				/*
				 * Listener was found, so we can abort here...
				 */
				return;
			}
		}
		listeners.add(new WeakReference<T>(listener));
	}

	public void removeListener(T listener) {
		WeakReference<T> toBeRemoved = null;
		for (WeakReference<T> weakReference : listeners) {
			if (weakReference.get().equals(listener)) {
				toBeRemoved = weakReference;
				break;
			}
		}
		if (toBeRemoved != null) {
			listeners.remove(toBeRemoved);
		}
	}

	public int size() {
		return listeners.size();
	}

	public Set<T> getListeners() {
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

}
