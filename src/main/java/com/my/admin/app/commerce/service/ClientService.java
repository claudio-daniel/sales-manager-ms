package com.my.admin.app.commerce.service;

import com.black.food.manager.dao.ClientDao;
import com.black.food.manager.exception.ApplicationException;
import com.black.food.manager.model.api.request.client.CreateClientRequest;
import com.black.food.manager.model.entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);

    private ClientDao clientDao;

    @Transactional
    public Client findByDniOrElseSave(final CreateClientRequest createClientRequest) {
        Optional<Client> clientOptional = findByEmail(createClientRequest.getEmail());

        return clientOptional.orElseGet(() -> {
            Client client = new Client();
            client.setName(createClientRequest.getName());
            client.setLastName(createClientRequest.getLastName());
            client.setPhone(createClientRequest.getPhone());
            client.setEmail(createClientRequest.getEmail());

            return save(client);
        });
    }

    private Optional<Client> findByEmail(final String email) {
        return clientDao.findByEmail(email);
    }

    @Transactional
    private Client save(final Client client) {
        Client clientSaved;
        try {
            clientSaved = clientDao.save(client);
        } catch (Exception constraintViolationException) {
            var message = "Ocurrio un error al almacenar el cliente, verifica los datos.";
            LOGGER.error(message);
            LOGGER.error(constraintViolationException.getLocalizedMessage());
            throw new ApplicationException(message, HttpStatus.CONFLICT);
        }
        return clientSaved;
    }

    @Autowired
    public void setClientDao(final ClientDao clientDao) {
        this.clientDao = clientDao;
    }
}
