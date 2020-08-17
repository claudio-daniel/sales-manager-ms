package com.mi.administrador.web.flux.app.model.transformer;

import com.mi.administrador.web.flux.app.model.document.Edifice;
import com.mi.administrador.web.flux.app.model.response.EdificeResponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class EdificeTransformer implements Function<Edifice, EdificeResponse> {

    @Override
    public EdificeResponse apply(Edifice edifice){
        EdificeResponse edificeResponse = new EdificeResponse();

        edificeResponse.setId(edifice.getId());
        edificeResponse.setName(edifice.getName());

        return edificeResponse;
    }

    public Edifice transformEdificeResponseToEdifice(EdificeResponse edificeResponse) {
        Edifice edifice = new Edifice();

        edifice.setId(edificeResponse.getId());
        edifice.setName(edificeResponse.getName());

        return edifice;
    }
}
