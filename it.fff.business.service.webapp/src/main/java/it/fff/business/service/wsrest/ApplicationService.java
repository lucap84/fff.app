package it.fff.business.service.wsrest;

import java.util.Locale;
import java.util.ResourceBundle;

import it.fff.business.common.util.LogUtils;
import it.fff.clientserver.common.dto.DataTransferObject;
import it.fff.business.facade.exception.BusinessException;
import it.fff.business.service.util.ResourceBundleProvider;

public class ApplicationService {
	
	public void manageErrors(BusinessException e, DataTransferObject dto, Locale locale){
		if(dto==null){
			return;
		}
		//put original error information WARNING: the original error is send to client only for debug reasons (to remove in production)
		dto.putErrorInMap("STACKTRACE", LogUtils.stackTrace2String(e));

		//populate additional business errors as list (error code -> error message) in a specific language
		ResourceBundle resourceBundle = ResourceBundleProvider.getInstance().getResourceBundle(locale);
		for (String c : e.getErrorCodes()) {
			dto.putErrorInMap(c, resourceBundle.getString(c));
		}		
	}

}
