package com.puresoltechnologies.purifinity.server.common.job;

public class PersistentStepUserData implements StepProgress, StepInformation {

	private static final long serialVersionUID = 8222672295996609864L;

	private long currentItem;
	private final String name;
	private final String description;
	private final long totalItems;

	public PersistentStepUserData(String name, String description,
			long totalItems) {
		super();
		this.name = name;
		this.description = description;
		this.totalItems = totalItems;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public long getTotalItems() {
		return totalItems;
	}

	@Override
	public long getCurrentItem() {
		return currentItem;
	}

	public void setCurrentItem(long currentItem) {
		this.currentItem = currentItem;
	}

	public synchronized void increaseCurrentItem(int count) {
		currentItem += count;
	}

}
