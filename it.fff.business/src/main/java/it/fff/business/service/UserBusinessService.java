package it.fff.business.service;

import it.fff.business.common.bo.CreateResultBO;
import it.fff.business.common.bo.ProfileImageBO;
import it.fff.business.common.bo.SubscriptionBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.business.common.bo.UserBO;
import it.fff.persistence.facade.exception.PersistenceException;

public interface UserBusinessService extends BusinessService{

	public UserBO createUser(UserBO userBO) throws  PersistenceException;

	public ProfileImageBO updateProfileImage(ProfileImageBO imgBO) throws  PersistenceException;

	public CreateResultBO upgradeToPremium(int userIdInt, SubscriptionBO subscriptionBO) throws  PersistenceException;

	public UpdateResultBO updateUserData(UserBO userBO) throws  PersistenceException;

	public UserBO getUser(int userIdInt) throws  PersistenceException;


}
