package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Session;

import it.fff.business.common.bo.KeywordBO;
import it.fff.business.common.eo.KeywordEO;
import it.fff.business.common.eo.LanguageEO;
import it.fff.business.common.eo.PlaceEO;

public class KeywordMapper implements Mapper<String,String,KeywordEO>{

	private static KeywordMapper mapper;
	
	private KeywordMapper(){
		
	}
	
	public static KeywordMapper getInstance(){
		if(mapper==null){
			mapper= new  KeywordMapper();
		}
		return mapper;
	}

	@Override
	public List<String> mapDTOs2BOs(List<String> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String mapDTO2BO(String dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KeywordEO> mergeBOs2EOs(List<String> bos, List<KeywordEO> eos, Session session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KeywordEO mergeBO2EO(String bo, KeywordEO eo, Session session) {
		if(bo!=null){
			eo = new KeywordEO();
			eo.setToken(bo);
			eo.setRelatedPlaces(new HashSet<PlaceEO>());
		}
		return eo;
	}

	@Override
	public List<String> mapEOs2BOs(List<KeywordEO> eos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String mapEO2BO(KeywordEO eo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> mapBOs2DTOs(List<String> bos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String mapBO2DTO(String bo) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
