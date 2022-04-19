package com.credibanco.ms.controller;


import com.credibanco.ms.dto.TarjetaDto;
import com.credibanco.ms.dto.TransaccionDto;
import com.credibanco.ms.entity.TarjetaEntity;
import com.credibanco.ms.service.TarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;

@RestController
@RequestMapping("/credibanco/tarjeta")
@CrossOrigin
public class TarjetaController {

    @Autowired
    private TarjetaService tarjetaService;

    @PostMapping("/crearTarjeta")
    public TarjetaDto crearTarjeta(@RequestBody TarjetaEntity tarjeta) {

        return tarjetaService.crearTarjeta(tarjeta);

    }

    @PostMapping("/enrolarTarjeta")
    public TarjetaDto enrolarTarjeta(@RequestBody TarjetaDto infoTarjeta) {

        return tarjetaService.enrolarTarjeta(infoTarjeta);

    }

    @GetMapping("/consultaTarjeta")
    public TarjetaDto consultarTarjeta(@QueryParam("idSistema") String idSistema)  {

        return tarjetaService.consultarTarjeta(idSistema);

    }

    @PostMapping("/eliminarTarjeta")
    public TarjetaDto eliminarTarjeta(@RequestBody TarjetaEntity infoTarjeta) {

        return tarjetaService.eliminarTarjeta(infoTarjeta);

    }

}
