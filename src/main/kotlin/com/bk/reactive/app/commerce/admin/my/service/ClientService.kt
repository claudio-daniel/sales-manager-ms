package com.bk.reactive.app.commerce.admin.my.service

import com.bk.reactive.app.commerce.admin.my.dao.ClientDAO
import com.bk.reactive.app.commerce.admin.my.exception.api.ApplicationException
import com.bk.reactive.app.commerce.admin.my.model.api.request.client.CreateClientRequest
import com.bk.reactive.app.commerce.admin.my.model.document.Client
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ClientService(
    private val clientDAO: ClientDAO
) {
    private val logger = KotlinLogging.logger { }

    fun findByDniOrElseSave(createClientRequest: CreateClientRequest): Mono<Client> {
        return findByEmail(createClientRequest.email)
            .switchIfEmpty(
                save(
                    Client(
                        id = null,
                        name = createClientRequest.name,
                        lastName = createClientRequest.lastName,
                        phone = createClientRequest.phone,
                        email = createClientRequest.email,
                        dni = createClientRequest.dni
                    )
                )
            )
    }

    private fun save(client: Client): Mono<Client> {
        return clientDAO.save(client)
            .onErrorResume {
                val message = "Ocurrio un error al almacenar el cliente, verifica los datos."
                logger.error { message }
                logger.error { it.localizedMessage }
                Mono.error {
                    ApplicationException(
                        message = message,
                        code = HttpStatus.CONFLICT, cause = Throwable(message)
                    )
                }
            }
    }

    private fun findByEmail(email: String): Mono<Client> = clientDAO.findByEmail(email)
}