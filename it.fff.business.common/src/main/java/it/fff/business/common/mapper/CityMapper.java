package it.fff.business.common.mapper;

import java.util.List;

import it.fff.business.common.bo.AttendanceStateBO;
import it.fff.business.common.bo.CityBO;
import it.fff.business.common.eo.AttendanceStateEO;
import it.fff.business.common.eo.CityEO;
import it.fff.clientserver.common.dto.AttendanceStateDTO;
import it.fff.clientserver.common.dto.CityDTO;

public class CityMapper implements Mapper<CityDTO,CityBO,CityEO>{

	private static CityMapper mapper;
	
	private CityMapper(){
		
	}
	
	public static CityMapper getInstance(){
		if(mapper==null){
			mapper= new  CityMapper();
		}
		return mapper;
	}

	@Override
	public List<CityBO> mapDTOs2BOs(List<CityDTO> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CityBO mapDTO2BO(CityDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CityEO> mergeBOs2EOs(List<CityBO> bos, List<CityEO> eso) {
		return null;
	}

	@Override
	public CityEO mergeBO2EO(CityBO bo, CityEO eo) {
		return null;
	}

	@Override
	public List<CityBO> mapEOs2BOs(List<CityEO> eos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CityBO mapEO2BO(CityEO eo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CityDTO> mapBOs2DTOs(List<CityBO> bos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CityDTO mapBO2DTO(CityBO bo) {
		CityDTO dto = new CityDTO();
		if(bo!=null){
			if(bo.getId()>0){
				dto.setId(String.valueOf(bo.getId()));
			}
			dto.setNome(bo.getNome());
			
			NationMapper nationMapper = NationMapper.getInstance();
			dto.setNazione(nationMapper.mapBO2DTO(bo.getNazione()));
		}
		return dto;
	}

}
