package com.puresoltechnologies.commons.misc;

/**
 * This interface is used for all classes which are interested in the progress
 * of an object.
 * 
 * @param <Observable>
 *            is the type of class which is to be observed. This type needs to
 *            implement {@link ProgressObservable} interface.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface ProgressObserver<Observable> {

	/**
	 * This method is implemented by the observer. This method gets called when
	 * the work is started.
	 * 
	 * @param observable
	 *            is the observable sending the event.
	 * @param message
	 *            is a message which might be get displayed by the observer.
	 * @param total
	 *            is the total amount of work to be performed.
	 */
	public void started(Observable observable, String message, long total);

	/**
	 * This method is implemented by the observer. This method gets called when
	 * the work is started.
	 * 
	 * @param observable
	 *            is the observable sending the event.
	 * @param message
	 *            is a message which might be get displayed by the observer.
	 * @param successful
	 *            is a flag which shows whether or not the work was successful.
	 *            True means the work was successfully finished. False means
	 *            otherwise.
	 */
	public void done(Observable observable, String message, boolean successful);

	/**
	 * This method is to be implemented by any observer. This method is called
	 * if progression changed.
	 * 
	 * @param observable
	 *            is the object which is currently observed.
	 * @param message
	 *            is a message which might be shown .be the observer
	 * @param total
	 *            is a quantity for the total amount of work to be done. The
	 *            unit of this number is arbitrary.
	 * @param finished
	 *            is the quantity of work already finished in the same unit as
	 *            total.
	 */
	public void updateWork(Observable observable, String message, long finished);

}
