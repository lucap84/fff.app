package it.fff.business.common.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FeedbackDTO extends DataTransferObject {

	private static final long serialVersionUID = 8179075372815645124L;
	private boolean isPositiveFeedback;

	@XmlElement public boolean isPositiveFeedback() {
		return isPositiveFeedback;
	}

	public void setPositiveFeedback(boolean isPositiveFeedback) {
		this.isPositiveFeedback = isPositiveFeedback;
	}
	
	
}
