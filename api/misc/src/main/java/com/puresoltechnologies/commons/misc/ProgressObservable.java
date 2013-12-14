package com.puresoltechnologies.commons.misc;

/**
 * This is an interface which is implemented by progress observables.
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <Observable>
 *            is the class of the observable.
 */
public interface ProgressObservable<Observable> {

	/**
	 * This method is used to add a new observer.
	 * 
	 * @param observer
	 *            is the observer do be added.
	 */
	public void addObserver(ProgressObserver<Observable> observer);

	/**
	 * This method is used to remove an already added observer. If the observer
	 * is not present, no action takes place.
	 * 
	 * @param observer
	 *            is the observer do be added.
	 */
	public void removeObservable(ProgressObserver<Observable> observer);

}
