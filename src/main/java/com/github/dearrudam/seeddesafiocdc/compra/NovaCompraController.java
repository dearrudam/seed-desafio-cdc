package com.github.dearrudam.seeddesafiocdc.compra;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@RestController
public class NovaCompraController {

    @PostMapping(path = "/api/novacompra")
    @ResponseBody
    public  Object novaCompra
            (@RequestBody @Valid
                     String input) {
        throw new UnsupportedOperationException("must be implemented!");
    }
    


}
