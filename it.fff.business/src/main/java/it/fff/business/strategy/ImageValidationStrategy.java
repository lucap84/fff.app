package it.fff.business.strategy;

import it.fff.business.common.bo.ProfileImageBO;

public interface ImageValidationStrategy {

	public boolean isValid(ProfileImageBO image);
}
