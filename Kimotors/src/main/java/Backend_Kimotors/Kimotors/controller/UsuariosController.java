package Backend_Kimotors.Kimotors.controller;

import Backend_Kimotors.Kimotors.model.usuarios.Usuarios;
import Backend_Kimotors.Kimotors.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/email/{email}")
    public ResponseEntity<Usuarios> getByEmail(@PathVariable String email) {
        System.out.println("Buscando el email: " + email);
        return service.getByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Usuarios> createUser(@RequestBody Usuarios usuario) {
        try {
            Usuarios nuevoUsuario = service.saveUser(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/password")
    public ResponseEntity<String> updatePassword(@RequestParam String email, @RequestParam String newPassword) {
        try {
            Usuarios usuarioActualizado = service.updatePasswordByEmail(email, newPassword);
            return ResponseEntity.ok("Contraseña actualizada correctamente para: " + usuarioActualizado.getEmail());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la contraseña");
        }
    }
}