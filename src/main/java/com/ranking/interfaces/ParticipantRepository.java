package com.ranking.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ranking.model.Participant;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long>{

}
