package it.fff.business.service.wsrest;

import java.util.Locale;
import java.util.ResourceBundle;

import it.fff.clientserver.common.dto.ResultDTO;
import it.fff.business.facade.exception.BusinessException;
import it.fff.business.service.util.ResourceBundleProvider;

public class ApplicationService {
	
	public void manageErrors(BusinessException e, ResultDTO dto, Locale locale){
		ResourceBundle resourceBundle = ResourceBundleProvider.getInstance().getResourceBundle(locale);
		dto.setOk(false);
		for (String code : e.getErrorCodes()) {
			dto.putErrorInMap(code, resourceBundle.getString(code));
		}		
	}

}
