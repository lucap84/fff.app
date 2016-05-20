package it.fff.business.common.mapper;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import it.fff.business.common.bo.AccountBO;
import it.fff.business.common.bo.SessionBO;
import it.fff.business.common.bo.UserBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.clientserver.common.dto.AuthDataResponseDTO;
import it.fff.clientserver.common.dto.LoginInputDTO;
import it.fff.clientserver.common.enums.UserSexEnum;

public class CustomMapper {
	
	private static final Logger logger = LogManager.getLogger(CustomMapper.class);
	private static CustomMapper mapper;
	
	private CustomMapper(){
		
	}
	
	public static CustomMapper getInstance(){
		if(mapper==null){
			mapper= new  CustomMapper();
		}
		return mapper;
	}

	public AuthDataResponseDTO mapWriteResult2AuthData(WriteResultBO bo) {
		AuthDataResponseDTO dto = new AuthDataResponseDTO();
		if(!bo.isSuccess()){
			for (String errCode : bo.getErrorCodes()) {
				dto.putErrorInMap(errCode, errCode);
			}
		}
		dto.setUserId(bo.getWrittenKey());
		return dto;
	}

	public SessionBO mapLoginInput2Session(LoginInputDTO loginInfo) {
		AccountBO accBO = new AccountBO();
		accBO.setEmail(loginInfo.getEmail());
		accBO.setPassword(loginInfo.getPassword());
		
		SessionBO sessBO = new SessionBO();
		sessBO.setDeviceId(loginInfo.getDeviceId());
		sessBO.setAccount(accBO);
		return sessBO;
	}
	
	public UserBO mapFacebookData2BO(String jsonUser){
		UserBO user = null;
		
		String facebookId = null;
        String firstName = null;
        String middleName = null;
        String lastName = null;
        String email = null;
        String userBirthday = null;
        UserSexEnum gender = UserSexEnum.UNKNOWN;
        try {
            JSONObject json = new JSONObject(jsonUser);
            String[] names = JSONObject.getNames(json);json.getString("updated_time");
            
            for (int i = 0; i < names.length; i++) {
				switch(names[i]){
					case "id": facebookId = json.getString("id"); break;
					case "first_name": firstName = json.getString("first_name"); break;
					case "middle_name": middleName = json.getString("middle_name"); break;
					case "last_name": lastName = json.getString("last_name"); break;
					case "email": email = json.getString("email"); break;
					case "gender": {
						String g = json.getString("gender");
		                if (g.equalsIgnoreCase("female"))
		                    gender = UserSexEnum.F;
		                else if (g.equalsIgnoreCase("male"))
		                    gender = UserSexEnum.M;
		                else
		                    gender = UserSexEnum.UNKNOWN;
		                break;
					}
					case "birthday": userBirthday = json.getString("birthday"); break;
				}
			}
        } catch (JSONException e) {
            logger.error("invalid JSON structure");
            return user;
        }        
        
        user = new UserBO();
        
        if(middleName!=null && !"".equals(middleName)){
        	firstName += " "+middleName;
        }
        user.setNome(firstName);
        user.setCognome(lastName);
        user.setSesso(gender);
        user.setDataNascita(userBirthday);
        
        AccountBO account = new AccountBO();
        account.setFacebookId(Long.valueOf(facebookId));
        account.setEmail(email);
        account.setFlgValidita(true);
        account.setSessions(new ArrayList<SessionBO>());
        
        user.setAccount(account);

        return user;
	}	
	
	
}
