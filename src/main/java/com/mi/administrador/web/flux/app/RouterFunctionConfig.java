package com.mi.administrador.web.flux.app;

import com.mi.administrador.web.flux.app.handler.EdificeHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class RouterFunctionConfig {

    private static final String BASE_URL = "my_administration/edifices";

    @Bean
    public RouterFunction<ServerResponse> routes(EdificeHandler edificeHandler) {
        return route(GET(BASE_URL) , edificeHandler::getAllEdifices)
                .andRoute(GET(BASE_URL+"/{id}"), edificeHandler::findEdificeById)
                .andRoute(POST(BASE_URL), edificeHandler::createEdifice)
                .andRoute(PUT(BASE_URL+"/{id}"), edificeHandler::updateEdifice)
                .andRoute(DELETE(BASE_URL+"/{id}"), edificeHandler::deleteEdifice)
                .andRoute(POST(BASE_URL+"/{edificeId}/consumedServices"), edificeHandler::edificeAddConsumedService);
    }
}
