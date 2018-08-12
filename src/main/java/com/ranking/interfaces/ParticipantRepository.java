package com.ranking.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ranking.model.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long>{
	List<Participant> findAllByWinCountNotNullAndMatchCountNotNullOrderByWinCountDescMatchCountAsc();
}
