package com.credibanco.ms.controller;

import com.credibanco.ms.dto.TransaccionDto;
import com.credibanco.ms.entity.TransaccionEntity;
import com.credibanco.ms.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credibanco/transacciones")
@CrossOrigin
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @PostMapping("/crearTransaccion")
    public TransaccionDto crearTransaccion(@RequestBody TransaccionDto infoTransaccion) {

        return transaccionService.crearTransaccion(infoTransaccion);

    }

    @PostMapping("/anularTransaccion")
    public TransaccionDto anularTransaccion(@RequestBody TransaccionDto infoTransaccion) {

        return transaccionService.anularTransaccion(infoTransaccion);

    }

}
