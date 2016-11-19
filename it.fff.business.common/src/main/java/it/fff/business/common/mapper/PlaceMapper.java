package it.fff.business.common.mapper;

import static org.hibernate.Hibernate.isInitialized;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressType;
import com.google.maps.model.GeocodingResult;

import it.fff.business.common.bo.CityBO;
import it.fff.business.common.bo.NationBO;
import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.eo.PlaceEO;
import it.fff.business.common.util.ConfigurationProvider;
import it.fff.business.common.util.DistanceCalculator;
import it.fff.clientserver.common.dto.PlaceDTO;
import it.fff.clientserver.common.enums.PlaceTypeEnum;
import it.fff.clientserver.common.util.Constants;

public class PlaceMapper implements Mapper<PlaceDTO,PlaceBO,PlaceEO>{
	
	private static final Logger logger = LogManager.getLogger(PlaceMapper.class);
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
			bo.setPlaceKey(dto.getPlaceKey());
			
			PlaceTypeMapper placeTypeMapper = PlaceTypeMapper.getInstance();
			bo.setPlaceType(placeTypeMapper.mapDTO2BO(dto.getPlaceType()));
			
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
			eo.setPlaceKeyIfNotEmpty(bo.getPlaceKey());
			
			if(eo.getDataCreazione()==null || "".equals(eo.getDataCreazione())){
				eo.setDataCreazione(bo.getDataCreazione());
			}
			eo.setDataAggiornamento(bo.getDataAggiornamento());
			
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
			bo.setId(eo.getId()==null?0:eo.getId()); //per evitare nullpointer in fase di cast
			bo.setPlaceKey(eo.getPlaceKey());
			bo.setNome(eo.getNome());
			bo.setAddressRoute(eo.getAddressRoute());
			bo.setCivico(eo.getCivico());
			bo.setGpsLat(eo.getGpsLat()==null?0:eo.getGpsLat()); //per evitare nullpointer in fase di cast
			bo.setGpsLong(eo.getGpsLong()==null?0:eo.getGpsLong()); //per evitare nullpointer in fase di cast
			bo.setCap(eo.getCap());
			
			Date dataCreazione = null;
			Date dataAggiornamento = null;
//			DateFormat df = new SimpleDateFormat();
			try {
				dataCreazione = Constants.DATE_FORMATTER.parse(eo.getDataCreazione());
				dataAggiornamento = Constants.DATE_FORMATTER.parse(eo.getDataAggiornamento());
			} catch (ParseException e) {
				logger.error("Errore durante il parsing delle date Luogo prese dal DB");
				e.printStackTrace();
			}
			
			bo.setDataCreazione(Constants.DATE_FORMATTER.format(dataCreazione));
			bo.setDataAggiornamento(Constants.DATE_FORMATTER.format(dataAggiornamento));
			
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

	public List<PlaceBO> mapGeocodingResults2BOs(GeocodingResult[] geoResults) {
		List<PlaceBO> placesBO = null;
		if(geoResults!=null){
			placesBO = new ArrayList<PlaceBO>();
			
			int maxResultsToLoad = Integer.valueOf(ConfigurationProvider.getInstance().getPlacesConfigProperty(Constants.PROP_GOOGLE_MAX_RESULTS_LOADED));
			
			int resultsLength = geoResults.length;
			if(resultsLength>maxResultsToLoad){
				resultsLength = maxResultsToLoad;
			}
			
			for (int i = 0; i < resultsLength; i++) {
				logger.debug("***************************************************************");
				GeocodingResult geoResult = geoResults[i];
				
				PlaceBO bo = this.mapGeocodingResult2BO(geoResult);
				
				//Add to list
				placesBO.add(bo);
				logger.debug("***************************************************************");
			}
		}
		return placesBO;
	}

	public PlaceBO mapGeocodingResult2BO(GeocodingResult geoResult) {
		PlaceBO bo = null;
		
		if(geoResult!=null){
			bo = new PlaceBO();
			String gPlaceId = geoResult.placeId;
			boolean gPartialMatch = geoResult.partialMatch;
			String gNome = geoResult.formattedAddress;
			AddressType gAddressType = geoResult.types[0];
			String gRoute = null;
			String gStreetAddress = null;
			String gCivico = null;
			String gLocality = null;
			String gRegione = null;
			String gComuneLongName = null;
			String gComuneShortName = null;
			String gAdminArea3 = null;
			String gAdminArea4 = null;
			String gAdminArea5 = null;
			String gNazioneLongName = null;
			String gNazioneShortName = null;
			String gCAP = null;
			double gLat = geoResult.geometry.location.lat;
			double gLong = geoResult.geometry.location.lng;
			
			for (int j = 0; j < geoResult.addressComponents.length; j++) {
				AddressComponent addressComponent = geoResult.addressComponents[j];
				
				switch(addressComponent.types[0]){
					case ROUTE: gRoute = addressComponent.longName; break;	
					case STREET_ADDRESS: gStreetAddress = addressComponent.longName; break;
					case STREET_NUMBER: gCivico = addressComponent.longName; break;
					case LOCALITY: gLocality = addressComponent.longName; break;
					case ADMINISTRATIVE_AREA_LEVEL_1: gRegione = addressComponent.longName; break;
					case ADMINISTRATIVE_AREA_LEVEL_2: {
						gComuneLongName = addressComponent.longName;
						gComuneShortName = addressComponent.shortName;
						break;
					}
					case ADMINISTRATIVE_AREA_LEVEL_3: gAdminArea3 = addressComponent.longName; break;
					case ADMINISTRATIVE_AREA_LEVEL_4: gAdminArea4 = addressComponent.longName; break;
					case ADMINISTRATIVE_AREA_LEVEL_5: gAdminArea5 = addressComponent.longName; break;
					case COUNTRY: {
						gNazioneLongName  =	addressComponent.longName;
						gNazioneShortName = addressComponent.shortName;
						break;
					}
					case POSTAL_CODE: gCAP = addressComponent.longName; break;
				default:
					break;
				}
			}
	
			logger.debug("placeId: "+gPlaceId);
			logger.debug("partialMatch: "+gPartialMatch);
			logger.debug("formattedAddress: "+gNome);
			logger.debug("gRoute: "+gRoute);
			logger.debug("gStreetAddress: "+gStreetAddress);
			logger.debug("gCivico: "+gCivico);
			logger.debug("latitudine: "+gLat);
			logger.debug("longitudine: "+gLong);
			logger.debug("postal code: "+gCAP);
			logger.debug("gAddressType: "+gAddressType);
			logger.debug("gLocality: "+gLocality);
			logger.debug("gRegione: "+gRegione);
			logger.debug("gComuneLongName: "+gComuneLongName);
			logger.debug("gComuneShortName: "+gComuneShortName);
			logger.debug("gNazioneLongName: "+gNazioneLongName);
			logger.debug("gNazioneShortName: "+gNazioneShortName);
			
			bo.setPlaceKey(gPlaceId);
			bo.setNome(gNome);
			
			if(gStreetAddress!=null && gStreetAddress.length()>0){
				bo.setAddressRoute(gStreetAddress);
			} else{
				if(gRoute!=null && gRoute.length()>0){
					bo.setAddressRoute(gRoute);
				} else{
					if(gLocality!=null && gLocality.length()>0){
						bo.setAddressRoute(gLocality);
					} else{
						if(gComuneLongName!=null && gComuneLongName.length()>0){
							bo.setAddressRoute(gComuneLongName);
						} else{
							bo.setAddressRoute(Constants.UNDEFINED_DATA);
						}
					}
				}
			}
			bo.setCivico(gCivico);
			bo.setCap(gCAP);
			
			int decimalPrecision = Integer.valueOf(ConfigurationProvider.getInstance().getPlacesConfigProperty(Constants.PROP_PLACE_GPS_DECIMALPREC_CACHE));
			
			double roundedLat = DistanceCalculator.round(gLat, decimalPrecision);
			double roundedLong = DistanceCalculator.round(gLong, decimalPrecision);
			bo.setGpsLat(roundedLat);
			bo.setGpsLong(roundedLong);
			
			
//			Date currentDate = new Date();
//			String currentDateStr = Constants.DATE_FORMATTER.format(currentDate);
//			bo.setDataCreazione(currentDateStr);
//			bo.setDataAggiornamento(currentDateStr);
			
			CityBO cittaBO = new CityBO();
			//Se la citta (LOCALITY) non e' presente nella risposta uso le administrative area, dalla piu' specifica alla meno specifica (il comune), fino ad usare la nazione stessa
			if(gLocality!=null && !"".equals(gLocality)){
				cittaBO.setNome(gLocality);
			} else{
				if(gAdminArea5!=null && !"".equals(gAdminArea5)){
					cittaBO.setNome(gAdminArea5);	
				} else{
					if(gAdminArea4!=null && !"".equals(gAdminArea4)){
						cittaBO.setNome(gAdminArea4);	
					} else{
						if(gAdminArea3!=null && !"".equals(gAdminArea3)){
							cittaBO.setNome(gAdminArea3);	
						} else{
							if(gComuneLongName!=null && !"".equals(gComuneLongName)){
								cittaBO.setNome(gComuneLongName);	
							} else{
								cittaBO.setNome(Constants.UNDEFINED_DATA);
							}
						}
					}
				}
			}
			
			NationBO nazioneBO = new NationBO();
			nazioneBO.setNome(gNazioneLongName);
			if(gNazioneShortName!=null && gNazioneShortName.length()==2){
				nazioneBO.setInternationalCodeAplha2(gNazioneShortName);
			}
			if(gNazioneShortName!=null && gNazioneShortName.length()==3){
				nazioneBO.setInternationalCodeAplha3(gNazioneShortName);
			}
			
	
			cittaBO.setNazione(nazioneBO);
			bo.setCity(cittaBO);
			
			PlaceTypeEnum placeTypeBO = null;
			try{
				placeTypeBO = PlaceTypeEnum.valueOf(gAddressType.name());
			}
			catch(IllegalArgumentException e){
				logger.error("State not Recognized! :"+gAddressType.name());
			}
			bo.setPlaceType(placeTypeBO);
		}
		
		return bo;

	}
	
}
