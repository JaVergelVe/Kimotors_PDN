package Backend_Kimotors.Kimotors.controller;

import Backend_Kimotors.Kimotors.model.usuarios.Usuarios;
import Backend_Kimotors.Kimotors.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
    @Autowired
    private UsuariosService service;

    @GetMapping
    public List<Usuarios> getAll() {
        return service.getAll();
    }
}
