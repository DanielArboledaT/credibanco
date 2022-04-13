package com.credibanco.ms.controller;

import com.credibanco.ms.dto.TransaccionDto;
import com.credibanco.ms.entity.TransaccionEntity;
import com.credibanco.ms.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credibanco/transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @PostMapping("/crearTransaccion")
    public TransaccionDto crearTransaccion(@RequestBody TransaccionDto infoTransaccion) {

        return transaccionService.crearTransaccion(infoTransaccion);

    }

    @PostMapping("AnularTransaccion")
    public TransaccionDto anularTransaccion(@RequestBody TransaccionDto infoTransaccion) {

        return transaccionService.anularTransaccion(infoTransaccion);

    }

}
