package com.my.admin.app.commerce.model.transformer;

import com.my.admin.app.commerce.model.document.Edifice;
import com.my.admin.app.commerce.model.api.response.EdificeResponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class EdificeTransformer implements Function<Edifice, EdificeResponse> {

    @Override
    public EdificeResponse apply(Edifice edifice) {
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
