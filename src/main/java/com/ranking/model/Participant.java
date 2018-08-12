package com.ranking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Participant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true, nullable=false)
	private String name; //unique player nickname or team name
	
	private Integer winCount;
	private Integer matchCount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getWinCount() {
		return winCount;
	}

	public void setWinCount(Integer winCount) {
		this.winCount = winCount;
	}

	public Integer getMatchCount() {
		return matchCount;
	}

	public void setMatchCount(Integer matchCount) {
		this.matchCount = matchCount;
	}

}
