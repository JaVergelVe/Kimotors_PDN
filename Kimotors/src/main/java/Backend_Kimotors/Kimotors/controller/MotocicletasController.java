package Backend_Kimotors.Kimotors.controller;

import Backend_Kimotors.Kimotors.model.motos.Moto;
import Backend_Kimotors.Kimotors.model.motos.Motocicletas;
import Backend_Kimotors.Kimotors.repository.MotocicletasRepository;
import Backend_Kimotors.Kimotors.service.MotocicletasService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motocicletas")
@CrossOrigin(origins = "*")
public class MotocicletasController {

    @Autowired
    private MotocicletasService service;
    @Autowired
    private MotocicletasRepository motocicletasRepository;

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

    @GetMapping("/por-marca")
    public List<Document> contarMotosPorMarca() {
        return service.contarMotosPorMarca();
    }

    @GetMapping("/nombre-completo")
    public List<Document> nombreCompletoMotos() {
        return service.nombreCompletoMotos();
    }

    @GetMapping("/ordenadas-precio")
    public List<Document> motosPorPrecioDescendente() {
        return service.motosPorPrecioDescendente();
    }

    @GetMapping("/mayor-500cc")
    public List<Document> motosPorCilindrajeMayorA500() {
        return service.motosPorCilindrajeMayorA500();
    }

    @GetMapping("/mas-economicas/{limite}")
    public List<Document> motosMasEconomicas(@PathVariable int limite) {
        return service.motosMasEconomicas(limite);
    }

    @GetMapping("/paginadas/{skip}/{limit}")
    public List<Document> motosConPaginacion(@PathVariable int skip, @PathVariable int limit) {
        return service.motosConPaginacion(skip, limit);
    }

    @GetMapping({"/motor-unwind", "/motor-unwind/{modelo}"})
    public List<Document> descomponerDatosMotor(@PathVariable(required = false) String modelo) {
        return service.descomponerDatosMotor(modelo);
    }

    @GetMapping("/buscar/{texto}")
    public ResponseEntity<List<Document>> buscarMotosPorTexto(@PathVariable String texto) {
        List<Document> resultados = service.buscarPorTexto(texto);
        return ResponseEntity.ok(resultados);
    }





}