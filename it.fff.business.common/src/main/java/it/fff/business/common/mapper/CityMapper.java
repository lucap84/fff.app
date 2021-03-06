package it.fff.business.common.mapper;

import static org.hibernate.Hibernate.isInitialized;

import java.util.List;

import org.hibernate.Session;

import it.fff.business.common.bo.CityBO;
import it.fff.business.common.eo.AchievementTypeEO;
import it.fff.business.common.eo.AttendanceEO;
import it.fff.business.common.eo.CityEO;
import it.fff.business.common.eo.EventEO;
import it.fff.business.common.eo.NationEO;
import it.fff.business.common.eo.UserEO;
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
		CityBO bo = null;
		if(dto!=null){
			bo = new CityBO();
			if(dto.getId()>0){
				bo.setId(Integer.valueOf(dto.getId()));
			}
			bo.setNome(dto.getNome());

			NationMapper nationMapper = NationMapper.getInstance();
			bo.setNazione(nationMapper.mapDTO2BO(dto.getNazione()));
		}
		return bo;
	}

	@Override
	public List<CityEO> mergeBOs2EOs(List<CityBO> bos, List<CityEO> eos, Session session) {
		return null;
	}

	@Override
	public CityEO mergeBO2EO(CityBO bo, CityEO eo, Session session) {
		if(bo!=null){
			if(bo.getId()>0){
				//L'entita' gia esiste, quindi la carico per eventualmente modificarla
				eo = (CityEO)session.load(CityEO.class, bo.getId());
			}
			if(eo==null){//se l'entita' non e' stata caricata da DB e non era presente nel parametro, sono in creazione
				eo = new CityEO();
			}
			//Da qui in poi o sto creando nuovi valori o sto facendo l'update
			eo.setNomeIfNotEmpty(bo.getNome()); //Se non ci sono valori nel BO allora mantengo i vecchi
			
			NationEO nationEO = NationMapper.getInstance().mergeBO2EO(bo.getNazione(), eo.getNazione(), session);
			eo.setNazione(nationEO);

		}
		return eo;
		
//		if(bo!=null){
//			if(bo.getId()>0){
//				eo = (CityEO)session.load(CityEO.class, bo.getId());
//			}
//			if(eo==null){
//				eo = new CityEO();
//			}
//			eo.setIdIfNotEmpty(bo.getId());
//			eo.setNomeIfNotEmpty(bo.getNome());
//			
//			NationMapper nationMapper = NationMapper.getInstance();
//			eo.setNazione(nationMapper.mergeBO2EO(bo.getNazione(), eo.getNazione(), session));
//			
//		}
//		return eo;
	}

	@Override
	public List<CityBO> mapEOs2BOs(List<CityEO> eos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CityBO mapEO2BO(CityEO eo) {
		CityBO bo = null;
		if(eo!=null && isInitialized(eo)){
			bo = new CityBO();
			bo.setId(eo.getId()==null?0:eo.getId()); //per evitare nullpointer in fase di cast
			bo.setNome(eo.getNome());
			
			NationMapper nationMapper = NationMapper.getInstance();
			bo.setNazione(nationMapper.mapEO2BO(eo.getNazione()));
		}
		return bo;
	}

	@Override
	public List<CityDTO> mapBOs2DTOs(List<CityBO> bos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CityDTO mapBO2DTO(CityBO bo) {
		CityDTO dto = null;
		if(bo!=null){
			dto = new CityDTO();
			if(bo.getId()>0){
				dto.setId(bo.getId());
			}
			dto.setNome(bo.getNome());
			
			NationMapper nationMapper = NationMapper.getInstance();
			dto.setNazione(nationMapper.mapBO2DTO(bo.getNazione()));
		}
		return dto;
	}

}
