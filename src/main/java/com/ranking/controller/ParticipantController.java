package com.ranking.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ranking.interfaces.ParticipantRepository;
import com.ranking.model.CustomError;
import com.ranking.model.Participant;

@RestController
@RequestMapping("participant")
public class ParticipantController {
	private ParticipantRepository participantRepository;
	
	public ParticipantController(ParticipantRepository participantRepository){
		this.participantRepository = participantRepository;
	}
	
	@PostMapping("create")
	public ResponseEntity<?> createParticipant(@Valid @RequestBody Participant participant) {
		try {
			if (participant.getWin() != null &&  participant.getMatch() == null) {
				CustomError customError = new CustomError("Bad Request", "You Must Specify Number of Matches Greater or Equal than Wins");
				return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
			}
			
			if (participant.getWin() != null && participant.getWin() > participant.getMatch()) {
				CustomError customError = new CustomError("Bad Request", "Wins Can't be Greater than Matches");
				return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Participant>(participantRepository.save(participant), HttpStatus.OK);

		} catch (Exception e) {
			CustomError customError = new CustomError("Bad Request", e.getMessage());
			return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable(value = "id") Long participantId, @RequestBody Participant updateParticipant){
		Participant participant; 
		try {
			participant = participantRepository.findById(participantId).get();
				
		} catch (Exception e) {
			CustomError customError = new CustomError("Bad Request", e.getMessage());
			return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
		}
		
		try {
			
			if (updateParticipant.getWin() != null &&  updateParticipant.getMatch() == null) {
				CustomError customError = new CustomError("Bad Request", "You Must Specify Number of Matches Greater or Equal than Wins");
				return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
			}
			
			if (updateParticipant.getWin() != null && updateParticipant.getWin() > updateParticipant.getMatch()) {
				CustomError customError = new CustomError("Bad Request", "Wins Can't be Greater than Matches");
				return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
			}
			
			participant.setName(updateParticipant.getName());
			participant.setWin(updateParticipant.getWin());
			participant.setMatch(updateParticipant.getMatch());
			return new ResponseEntity<Participant>(participantRepository.save(participant), HttpStatus.OK);
		} catch (Exception e) {
			CustomError customError = new CustomError("Bad Request", e.getMessage());
			return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/updateStatus/{id}")
	public ResponseEntity<?> updateStatus(@PathVariable(value = "id") Long participantId, @RequestBody Participant updateParticipant){
		Participant participant; 
		try {
			participant = participantRepository.findById(participantId).get();
				
		} catch (Exception e) {
			CustomError customError = new CustomError("Bad Request", e.getMessage());
			return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
		}
		
		try {
			int win = ((participant.getWin() != null) ? participant.getWin() : 0) 
					+ ((updateParticipant.getWin() != null) ? updateParticipant.getWin() : 0);
			
			int match = ((participant.getMatch()!= null) ? participant.getMatch() : 0) 
					+ ((updateParticipant.getMatch()!= null) ? updateParticipant.getMatch() : 0);
			
			if(updateParticipant.getMatch() == null) {
				CustomError customError = new CustomError("Bad Request", "Matches can't be null");
				return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
			}
			
			if (updateParticipant.getWin() != null && updateParticipant.getWin() > updateParticipant.getMatch()) {
				CustomError customError = new CustomError("Bad Request", "Wins Can't be Greater than Matches");
				return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
			}
			
			participant.setWin(win);
			participant.setMatch(match);
			return new ResponseEntity<Participant>(participantRepository.save(participant), HttpStatus.OK);
		} catch (Exception e) {
			CustomError customError = new CustomError("Bad Request", e.getMessage());
			return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/updateWins/{id}")
	public ResponseEntity<?> updateWins(@PathVariable(value = "id") Long participantId, @RequestBody Participant updateParticipant){
		Participant participant; 
		try {
			participant = participantRepository.findById(participantId).get();
				
		} catch (Exception e) {
			CustomError customError = new CustomError("Bad Request", e.getMessage());
			return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
		}
		
		try {
			int win = ((participant.getWin() != null) ? participant.getWin() : 0) 
					+ ((updateParticipant.getWin() != null) ? updateParticipant.getWin() : 0);
			
			if(updateParticipant.getWin() == null) {
				CustomError customError = new CustomError("Bad Request", "Wins can't be null");
				return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
			}
			
			if (win > updateParticipant.getMatch()) {
				CustomError customError = new CustomError("Bad Request", "Wins Can't be Greater than Matches");
				return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
			}
			
			participant.setWin(win);
			return new ResponseEntity<Participant>(participantRepository.save(participant), HttpStatus.OK);
		} catch (Exception e) {
			CustomError customError = new CustomError("Bad Request", e.getMessage());
			return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
		}
	}
}
