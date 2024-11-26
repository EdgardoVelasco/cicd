package com.bancolombia.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bancolombia.app.entities.Client;

public interface IClientDAO extends CrudRepository<Client, Long> {
	
	@Query("SELECT u FROM Client u WHERE u.email=:email")
	Optional<Client> searchByEmail(@Param("email") String email);
	

}
