package com.ranking.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ranking.model.UserApplication;

public interface UserRepository  extends JpaRepository<UserApplication, Long>{
	UserApplication findByUsername(String username);

}
