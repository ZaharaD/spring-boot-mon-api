package com.ordexa.service;

import com.ordexa.model.Client;
import com.ordexa.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAll() { return clientRepository.findAll(); }
    public Page<Client> findAll(Pageable pageable) { return clientRepository.findAll(pageable); }
    public Optional<Client> findById(Long id) { return clientRepository.findById(id); }
    public Client save(Client client) { return clientRepository.save(client); }
    public void delete(Long id) { clientRepository.deleteById(id); }
} 