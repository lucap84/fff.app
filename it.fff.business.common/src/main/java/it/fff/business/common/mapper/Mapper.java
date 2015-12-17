package it.fff.business.common.mapper;

import java.util.List;

import org.hibernate.Session;

public interface Mapper<DTO,BO,EO> {
	
	//DTO ----> BO
	
	public List<BO> mapDTOs2BOs(List<DTO> dtos);
	
	public BO mapDTO2BO(DTO dto);
	
	//BO ----> EO
	
	public List<EO> mergeBOs2EOs(List<BO> bos, List<EO> eos, Session session);
	
	public EO mergeBO2EO(BO bo,EO eo, Session session);
	
	//BO <---- EO
	
	public List<BO> mapEOs2BOs(List<EO> eos);
	
	public BO mapEO2BO(EO eo);

	//DTO <---- BO
	
	public List<DTO> mapBOs2DTOs(List<BO> bos);
	
	public DTO mapBO2DTO(BO bo);
}
