package com.my.admin.app.commerce.handler;
import com.my.admin.app.commerce.model.document.ConsumedService;
import com.my.admin.app.commerce.model.document.Edifice;
import com.my.admin.app.commerce.model.response.EdificeResponse;
import com.my.admin.app.commerce.model.service.EdificeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
@Api(tags = "Edifices Handler", value = "For edifice manage")
public class EdificeHandler {

    private static final String BASE_URL = "my_administration/edifices";

    @Autowired
    private EdificeService edificeService;

    public Mono<ServerResponse> getAllEdifices(ServerRequest serverRequest){

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(edificeService.findAll(), EdificeResponse.class);
    }

    public Mono<ServerResponse> findEdificeById(ServerRequest serverRequest){

        String id = serverRequest.pathVariable("id");

        return edificeService.findById(id)
                .flatMap(e-> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(e)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> createEdifice(ServerRequest serverRequest){

        Mono<EdificeResponse> edificeMono = serverRequest.bodyToMono(EdificeResponse.class);

        return edificeMono
                .flatMap(edificeService::save)
                .flatMap(response -> ServerResponse
                        .created(URI.create(BASE_URL+response.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(response)));
    }

    public Mono<ServerResponse> updateEdifice(ServerRequest serverRequest){

        Mono<Edifice> edificeUpdateMono = serverRequest.bodyToMono(Edifice.class);
        String id = serverRequest.pathVariable("id");

        Mono<Edifice> edificeMonoOrigin = edificeService.findById(id);

        return edificeMonoOrigin
                .zipWith(edificeUpdateMono, (db, rq) -> {
                    db.setName(rq.getName());
                    db.setExpenses(rq.getExpenses());
                    db.setConsumedServices(rq.getConsumedServices());
                    db.setDepartments(rq.getDepartments());

                    return db;
                })
                .flatMap( edificeService::save)
                .flatMap(response -> ServerResponse
                        .created(URI.create(BASE_URL+response.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(response)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> deleteEdifice(ServerRequest serverRequest){

        String id = serverRequest.pathVariable("id");

        Mono<Edifice> edificeMono = edificeService.findById(id);

        return  edificeMono
                .flatMap( edifice -> edificeService.deleteById(edifice.getId())
                        .then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());

    }

    public Mono<ServerResponse> edificeAddConsumedService(ServerRequest serverRequest){

        Mono<ConsumedService> consumedServiceMono = serverRequest.bodyToMono(ConsumedService.class);
        String edificeId = serverRequest.pathVariable("edificeId");

        Mono<Edifice> edificeMono = edificeService.findById(edificeId);

        return consumedServiceMono
                .flatMap(edificeService::saveConsumedService)
                .zipWith(edificeMono, (consumedService, edifice) -> {
                    edifice.addConsumedServices(consumedService);
                    return edifice;
                })
                .flatMap( edificeService::save)
                .flatMap(response -> ServerResponse
                        .created(URI.create(BASE_URL+response.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(response)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
