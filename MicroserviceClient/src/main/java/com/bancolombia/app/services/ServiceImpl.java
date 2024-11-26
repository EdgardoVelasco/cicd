package com.bancolombia.app.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancolombia.app.dao.IClientDAO;
import com.bancolombia.app.entities.Client;
import com.bancolombia.app.exceptions.ClientNotStruct;

@Service
public class ServiceImpl implements IService {
	private final Logger LOGGER=LoggerFactory.getLogger(ServiceImpl.class);
	
	@Autowired
	private IClientDAO dao;

	@Override
	public boolean insert(Client client) {
		if(dao.save(client)!=null) {
		   return true;
		}
		
		return false;
	}

	@Override
	public boolean deleteById(long id) {
		if(dao.existsById(id)) {
		    dao.deleteById(id);
		    return true;	
		}
		return false;
	}

	@Override
	public boolean update(Client client) {
	    if(dao.existsById(client.getId())) {
	    	dao.save(client);
	    	return true;
	    }
		return false;
	}

	@Override
	public Client findById(long id) {
		try {
		    Optional<Client> search=dao.findById(id);
		    if(search.isPresent()) {
		    	if(search.get().getAddress()!=null) {
		    		return search.orElseThrow();
		    	}else {
		    		throw new ClientNotStruct("MUA123445 Client no cumple"+search.get());
		    	}
		    	
		    }
		    throw new NoSuchElementException();
		    
		}catch(ClientNotStruct ex) {
			LOGGER.warn(ex.getMessage());
			throw new RuntimeException();
		}
	}

	@Override
	public List<Client> getAll() {
		return (List<Client>)dao.findAll();
	}

}
