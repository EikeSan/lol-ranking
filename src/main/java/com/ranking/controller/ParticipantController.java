package com.ranking.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("participants")
public class ParticipantController {
	private ParticipantRepository participantRepository;
	
	public ParticipantController(ParticipantRepository participantRepository){
		this.participantRepository = participantRepository;
	}
	
	@PostMapping("/")
	public ResponseEntity<?> createParticipant(@Valid @RequestBody Participant participant) {
		try {
			if (participant.getWinCount() != null &&  participant.getMatchCount() == null) {
				CustomError customError = new CustomError("Bad Request", "You Must Specify Number of Matches Greater or Equal than Wins");
				return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
			}
			
			if (participant.getWinCount() != null && participant.getWinCount() > participant.getMatchCount()) {
				CustomError customError = new CustomError("Bad Request", "Wins Can't be Greater than Matches");
				return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Participant>(participantRepository.save(participant), HttpStatus.CREATED);

		} catch (Exception e) {
			CustomError customError = new CustomError("Bad Request", e.getMessage());
			return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable(value = "id") Long participantId, @RequestBody Participant updateParticipant){
		Participant participant; 
		try {
			participant = participantRepository.findById(participantId).get();
				
		} catch (Exception e) {
			CustomError customError = new CustomError("Bad Request", e.getMessage());
			return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
		}
		
		try {
			
			if (updateParticipant.getWinCount() != null &&  updateParticipant.getMatchCount() == null) {
				CustomError customError = new CustomError("Bad Request", "You Must Specify Number of Matches Greater or Equal than Wins");
				return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
			}
			
			if (updateParticipant.getWinCount() != null && updateParticipant.getWinCount() > updateParticipant.getMatchCount()) {
				CustomError customError = new CustomError("Bad Request", "Wins Can't be Greater than Matches");
				return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
			}
			
			participant.setName(updateParticipant.getName());
			participant.setWinCount(updateParticipant.getWinCount());
			participant.setMatchCount(updateParticipant.getMatchCount());
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
			int win = ((participant.getWinCount() != null) ? participant.getWinCount() : 0) 
					+ ((updateParticipant.getWinCount() != null) ? updateParticipant.getWinCount() : 0);
			
			int match = ((participant.getMatchCount()!= null) ? participant.getMatchCount() : 0) 
					+ ((updateParticipant.getMatchCount()!= null) ? updateParticipant.getMatchCount() : 0);
			
			if(updateParticipant.getMatchCount() == null) {
				CustomError customError = new CustomError("Bad Request", "Matches can't be null");
				return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
			}
			
			if (updateParticipant.getWinCount() != null && updateParticipant.getWinCount() > updateParticipant.getMatchCount()) {
				CustomError customError = new CustomError("Bad Request", "Wins Can't be Greater than Matches");
				return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
			}
			
			participant.setWinCount(win);
			participant.setMatchCount(match);
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
			int win = ((participant.getWinCount() != null) ? participant.getWinCount() : 0) 
					+ ((updateParticipant.getWinCount() != null) ? updateParticipant.getWinCount() : 0);
			
			if(updateParticipant.getWinCount() == null) {
				CustomError customError = new CustomError("Bad Request", "Wins can't be null");
				return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
			}
			
			if (win > updateParticipant.getMatchCount()) {
				CustomError customError = new CustomError("Bad Request", "Wins Can't be Greater than Matches");
				return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
			}
			
			participant.setWinCount(win);
			return new ResponseEntity<Participant>(participantRepository.save(participant), HttpStatus.OK);
		} catch (Exception e) {
			CustomError customError = new CustomError("Bad Request", e.getMessage());
			return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long participantId){
		Participant participant; 
		try {
			participant = participantRepository.findById(participantId).get();
				
		} catch (Exception e) {
			CustomError customError = new CustomError("Bad Request", e.getMessage());
			return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
		}
		
		try {
			participantRepository.delete(participant);
			return new ResponseEntity<Participant>(HttpStatus.OK);
		} catch (Exception e) {
			CustomError customError = new CustomError("Bad Request", e.getMessage());
			return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/ranking")
	public ResponseEntity<?> ranking(){
		return new ResponseEntity<>(participantRepository.findAllByWinCountNotNullOrderByWinCountDescMatchCountAsc(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findParticipantById(@PathVariable(value = "id") Long participantId){
		Participant participant; 
		try {
			participant = participantRepository.findById(participantId).get();
			return new ResponseEntity<Participant>(participant,HttpStatus.OK);
		} catch (Exception e) {
			CustomError customError = new CustomError("Bad Request", e.getMessage());
			return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
		}
	}
}
