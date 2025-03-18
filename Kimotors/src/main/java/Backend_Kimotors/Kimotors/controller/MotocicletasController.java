package Backend_Kimotors.Kimotors.controller;


import Backend_Kimotors.Kimotors.model.motos.Moto;
import Backend_Kimotors.Kimotors.model.motos.Motocicletas;
import Backend_Kimotors.Kimotors.service.MotocicletasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motocicletas")
public class MotocicletasController {

    @Autowired
    private MotocicletasService service;

    @GetMapping
    public List<Motocicletas> getAll() {
        return service.getAll();
    }

    @GetMapping("/{marca}")
    public List<Moto> getByMarca(@PathVariable String marca) {
        return service.getByMarca(marca);
    }

    @GetMapping("/modelo/{modelo}")
    public Moto getByModelo(@PathVariable String modelo) {
        return service.getByModelo(modelo);
    }

    @PostMapping
    public void addMoto(@RequestBody Moto moto) {
        service.addMoto(moto);
    }


}