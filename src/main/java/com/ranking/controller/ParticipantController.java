package com.ranking.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ranking.interfaces.ParticipantRepository;
import com.ranking.model.Participant;

@RestController
@RequestMapping("participant")
public class ParticipantController {
	private ParticipantRepository participantRepository;
	
	public ParticipantController(ParticipantRepository participantRepository){
		this.participantRepository = participantRepository;
	}
	
	@PostMapping("create")
	public Participant createParticipant(@Valid @RequestBody Participant participant) {
		return participantRepository.save(participant);
	}
}
