package com.bk.reactive.app.commerce.admin.my.dao

import com.bk.reactive.app.commerce.admin.my.model.document.Client
import com.bk.reactive.app.commerce.admin.my.repository.ClientRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class ClientDAO(
    private val clientRepository: ClientRepository
) {
    fun findByEmail(email: String): Mono<Client> = clientRepository.findByEmail(email)

    fun findById(id: String): Mono<Client> = clientRepository.findById(id)

    fun save(client: Client): Mono<Client> = clientRepository.save(client)
}