package it.fff.business.common.bo;

public class FeedbackBO implements BusinessObject {

	private boolean isPositiveFeedback;

	public boolean isPositiveFeedback() {
		return isPositiveFeedback;
	}

	public void setPositiveFeedback(boolean isPositiveFeedback) {
		this.isPositiveFeedback = isPositiveFeedback;
	}
}
