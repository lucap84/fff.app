package it.fff.business.common.mapper;

import static org.hibernate.Hibernate.isInitialized;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.eo.PlaceEO;
import it.fff.business.common.eo.PlaceTypeEO;
import it.fff.clientserver.common.dto.PlaceDTO;

public class PlaceMapper implements Mapper<PlaceDTO,PlaceBO,PlaceEO>{
	
	private static PlaceMapper mapper;
	
	private PlaceMapper(){
		
	}
	
	public static PlaceMapper getInstance(){
		if(mapper==null){
			mapper= new  PlaceMapper();
		}
		return mapper;
	}

	@Override
	public List<PlaceDTO> mapBOs2DTOs(List<PlaceBO> bos) {
		List<PlaceDTO> dtos = null;
		if(bos!=null){
			dtos = new ArrayList<PlaceDTO>();
			PlaceMapper placeMapper = PlaceMapper.getInstance();
			for (PlaceBO bo : bos) {
				dtos.add(placeMapper.mapBO2DTO(bo));
			}
		}
		return dtos;
	}

	@Override
	public PlaceDTO mapBO2DTO(PlaceBO bo) {
		PlaceDTO dto = null;
		if(bo!=null){
			dto = new PlaceDTO();
			if(bo.getId()>0){
				dto.setId(bo.getId());
			}
			dto.setNome(bo.getNome());
			dto.setGpsLat(bo.getGpsLat());
			dto.setGpsLong(bo.getGpsLong());
			dto.setCivico(bo.getCivico());
			dto.setRoute(bo.getAddressRoute());
			dto.setCap(bo.getCap());
			
			PlaceTypeMapper placeTypeMapper = PlaceTypeMapper.getInstance();
			dto.setPlaceType(placeTypeMapper.mapBO2DTO(bo.getPlaceType()));
			
			CityMapper cityMapper = CityMapper.getInstance();
			dto.setCity(cityMapper.mapBO2DTO(bo.getCity()));
		}
		return dto;
	}

	@Override
	public PlaceBO mapDTO2BO(PlaceDTO dto) {
		PlaceBO bo = null;
		if(dto!=null){
			bo = new PlaceBO();
			if(dto.getId()>0){
				bo.setId(Integer.valueOf(dto.getId()));
			}
			bo.setNome(dto.getNome());
			bo.setAddressRoute(dto.getRoute());
			bo.setCivico(dto.getCap());
			bo.setGpsLat(Double.valueOf(dto.getGpsLat()));
			bo.setGpsLong(Double.valueOf(dto.getGpsLong()));
			bo.setCap(dto.getCap());
			
			CityMapper cityMapper = CityMapper.getInstance();
			bo.setCity(cityMapper.mapDTO2BO(dto.getCity()));
			
		}
		return bo;
	}

	@Override
	public PlaceEO mergeBO2EO(PlaceBO bo, PlaceEO eo, Session session) {
		if(bo!=null){
			if(bo.getId()>0){
				eo = (PlaceEO)session.load(PlaceEO.class, bo.getId());
			}
			if(eo==null){
				eo = new PlaceEO();
			}
			eo.setIdIfNotEmpty(bo.getId());
			eo.setNomeIfNotEmpty(bo.getNome());
			eo.setAddressRouteIfNotEmpty(bo.getAddressRoute());
			eo.setCivicoIfNotEmpty(bo.getCivico());
			eo.setGpsLatIfNotEmpty(bo.getGpsLat());
			eo.setGpsLongIfNotEmpty(bo.getGpsLong());
			eo.setCapIfNotEmpty(bo.getCap());
			eo.setPlaceKey(bo.getPlaceKey());
			
			eo.setPlaceType(PlaceTypeMapper.getInstance().mergeBO2EO(bo.getPlaceType(), null, session));
			
			CityMapper cityMapper = CityMapper.getInstance();
			eo.setCity(cityMapper.mergeBO2EO(bo.getCity(), eo.getCity(), session));
			
		}
		return eo;
	}

	@Override
	public List<PlaceBO> mapEOs2BOs(List<PlaceEO> eos) {
		List<PlaceBO> bos = null;
		if(eos!=null && isInitialized(eos)){
			bos = new ArrayList<PlaceBO>();
			PlaceMapper placeMapper = PlaceMapper.getInstance();
			for (PlaceEO eo : eos) {
				bos.add(placeMapper.mapEO2BO(eo));
			}
		}
		return bos;
	}

	@Override
	public PlaceBO mapEO2BO(PlaceEO eo) {
		PlaceBO bo = null;
		if(eo!=null && isInitialized(eo)){
			bo = new PlaceBO();
			if(eo.getId()!=null && eo.getId()>0){
				bo.setId(eo.getId());
			}
			bo.setPlaceKey(eo.getPlaceKey());
			bo.setNome(eo.getNome());
			bo.setAddressRoute(eo.getAddressRoute());
			bo.setCivico(eo.getCivico());
			bo.setGpsLat(eo.getGpsLat());
			bo.setGpsLong(eo.getGpsLong());
			bo.setCap(eo.getCap());
			
			bo.setDataAggiornamento(eo.getDataAggiornamento());
			bo.setDataCreazione(eo.getDataCreazione());
			
			PlaceTypeMapper placeTypeMapper = PlaceTypeMapper.getInstance();
			bo.setPlaceType(placeTypeMapper.mapEO2BO(eo.getPlaceType()));
			
			CityMapper cityMapper = CityMapper.getInstance();
			bo.setCity(cityMapper.mapEO2BO(eo.getCity()));			
		}
		return bo;
	}

	@Override
	public List<PlaceBO> mapDTOs2BOs(List<PlaceDTO> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlaceEO> mergeBOs2EOs(List<PlaceBO> bos, List<PlaceEO> eos, Session session) {
		// TODO Auto-generated method stub
		return null;
	}

}
