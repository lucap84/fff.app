package it.fff.business.common.mapper;

import static org.hibernate.Hibernate.isInitialized;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import it.fff.business.common.bo.NationBO;
import it.fff.business.common.eo.NationEO;
import it.fff.clientserver.common.dto.NationDTO;

public class NationMapper implements Mapper<NationDTO,NationBO,NationEO>{
	
	private static final Logger logger = LogManager.getLogger(NationMapper.class);
	private static NationMapper mapper;
	
	private NationMapper(){
		
	}
	
	public static NationMapper getInstance(){
		if(mapper==null){
			mapper= new  NationMapper();
		}
		return mapper;
	}
	
	@Override
	public NationBO mapDTO2BO(NationDTO dto) {
		NationBO bo = null;
		if(dto!=null){
			bo = new NationBO();
			if(dto.getId()>0){
				bo.setId(Integer.valueOf(dto.getId()));
			}
			bo.setNome(dto.getNome());
			bo.setInternationalKey(dto.getInternationalKey());
		}
		return bo;
	}
	
	@Override
	public NationEO mergeBO2EO(NationBO bo, NationEO eo, Session session) {
		if(bo!=null){
			if(bo.getId()>0){
				//L'entita' gia esiste, quindi la carico per eventualmente modificarla
				eo = (NationEO)session.load(NationEO.class, bo.getId());
			}
			if(eo==null){//se l'entita' non e' stata caricata da DB e non era presente nel parametro, sono in creazione
				eo= new NationEO();
			}
			eo.setNomeIfNotEmpty(bo.getNome());
			eo.setInternationalKeyIfNotEmpty(bo.getInternationalKey());
			eo.setInternationalCodeAlpha2IfNotEmpty(bo.getInternationalCodeAplha2());
			eo.setInternationalCodeAlpha3IfNotEmpty(bo.getInternationalCodeAplha3());
		}
		return eo;
	}
	
	@Override
	public NationBO mapEO2BO(NationEO eo) {
		NationBO bo = null;
		if(eo!=null && isInitialized(eo)){
			bo = new NationBO();
			bo.setId(eo.getId());
			bo.setNome(eo.getNome());
			bo.setInternationalKey(eo.getInternationalKey());
			bo.setInternationalCodeAplha2(eo.getInternationalCodeAplha2());
			bo.setInternationalCodeAplha3(eo.getInternationalCodeAplha3());
		}
		return bo;
	}

	@Override
	public NationDTO mapBO2DTO(NationBO bo) {
		NationDTO dto = null;
		if(bo!=null){
			dto = new NationDTO();
			if(bo.getId()>0){
				dto.setId(bo.getId());
			}
			dto.setInternationalKey(bo.getInternationalKey());
			dto.setNome(bo.getNome());
		}
		return dto;
	}

	@Override
	public List<NationBO> mapDTOs2BOs(List<NationDTO> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NationEO> mergeBOs2EOs(List<NationBO> bos, List<NationEO> eos, Session session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NationBO> mapEOs2BOs(List<NationEO> eos) {
		List<NationBO> bos = null;
		if(eos!=null && isInitialized(eos)){
			bos = new ArrayList<NationBO>();
			NationMapper nationMapper = NationMapper.getInstance();
			for (NationEO eo : eos) {
				bos.add(nationMapper.mapEO2BO(eo));
			}
		}
		return bos;
	}

	@Override
	public List<NationDTO> mapBOs2DTOs(List<NationBO> bos) {
		List<NationDTO> dtos = null;
		if(bos!=null){
			dtos = new ArrayList<NationDTO>();
			NationMapper nationMapper = NationMapper.getInstance();
			for (NationBO bo : bos) {
				dtos.add(nationMapper.mapBO2DTO(bo));
			}
		}
		return dtos;
	}	

}
