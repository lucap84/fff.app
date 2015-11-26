package it.fff.client.test.uc;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

import it.fff.client.stub.SecurityServiceStub;
import it.fff.client.stub.UserServiceStub;
import it.fff.clientserver.common.dto.LinguaDTO;
import it.fff.clientserver.common.dto.NazioneDTO;
import it.fff.clientserver.common.dto.UserDTO;
import it.fff.clientserver.common.dto.WriteResultDTO;

public class UC08Test {
	
	@Test
	public void testUC8(){//Modifica i dati utente
		/*
		 * Preconditions:  UC9 Login (oppure UC1 Registrazione)
		 */
		
		UC09Test loginTest = new UC09Test();
		loginTest.testUC9();
		
		/*
		 * UC3:
		 * Modifica la propria immagine
		 * Modifica i propri dati
		 */		
		UserServiceStub userService = new UserServiceStub();
		
		//Carica una nuova immagine profilo
		String imageLocation ="C:\\Users\\lpelosi\\imagetest.jpg";
		String userId = userService.getSecureConfiguration().getUserId();
		
		WriteResultDTO updateProfileImageResult = userService.updateProfileImage(userId, imageLocation, MediaType.APPLICATION_JSON);
		assertNotNull(updateProfileImageResult);
		assertTrue(updateProfileImageResult.isOk());
		assertTrue(updateProfileImageResult.getAffectedRecords()>0);
		assertNotNull(updateProfileImageResult.getIdentifier());
		assertFalse(updateProfileImageResult.getIdentifier().isEmpty());
		
		//Modifica i propri dati
		UserDTO  user = new UserDTO();
		user.setId(userId);
		user.setEmail("nuovamail@mail.it");
		user.setSesso("F");
		user.setDataNascita("1900-01-01");
		user.setNome("Nome mod");
		user.setCognome("cognome mod");
		user.setDescrizione("Descrizione mod");
		
		NazioneDTO nazionalita = new NazioneDTO();
		nazionalita.setId("1");
		user.setNazionalita(nazionalita);
		
		LinguaDTO l1 = new LinguaDTO();
		l1.setId("1");
		LinguaDTO l2 = new LinguaDTO();
		l1.setId("2");
		List<LinguaDTO> lingue = new ArrayList<LinguaDTO>();
		lingue.add(l1);
		lingue.add(l2);
		user.setLingue(lingue);
		
		//TODO info impostate in modo silente dal sistema
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
		user.setLastPositionDate(df.format(new Date()));
		user.setLastPositionLat("1.234");
		user.setLastPositionLong("2.345");
		
		WriteResultDTO modifyUserDataResult = userService.modifyUserData(user, MediaType.APPLICATION_JSON);
		assertNotNull(modifyUserDataResult);
		assertTrue(modifyUserDataResult.isOk());
		assertTrue(modifyUserDataResult.getAffectedRecords()>0);
		assertNotNull(modifyUserDataResult.getIdentifier());
		assertFalse(modifyUserDataResult.getIdentifier().isEmpty());
		
		
		/*
		 * Postconditions:  i dati utente sono cambiati
		 */
		
		UserDTO userCheck = userService.getUser(userId, MediaType.APPLICATION_JSON);
		assertEquals(user.getEmail(),userCheck.getEmail());
		assertEquals(user.getSesso(),userCheck.getSesso());
		assertEquals(user.getDataNascita(),userCheck.getDataNascita());
		assertEquals(user.getNome(),userCheck.getNome());
		assertEquals(user.getCognome(),userCheck.getCognome());
		assertEquals(user.getDescrizione(),userCheck.getDescrizione());

	}
}