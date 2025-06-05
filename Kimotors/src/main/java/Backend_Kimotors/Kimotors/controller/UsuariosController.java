package Backend_Kimotors.Kimotors.controller;

import Backend_Kimotors.Kimotors.model.Comentarios.Comentario;
import Backend_Kimotors.Kimotors.model.usuarios.LoginRequest;
import Backend_Kimotors.Kimotors.model.usuarios.Usuarios;
import Backend_Kimotors.Kimotors.service.UsuariosService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("/login")
    public ResponseEntity<Usuarios> login(@RequestBody LoginRequest request) {
        Optional<Usuarios> userOptional = service.getByEmail(request.getEmail());

        if (userOptional.isPresent()) {
            Usuarios user = userOptional.get();
            if (user.getPassword().equals(request.getPassword())) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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

    @DeleteMapping("/email/{email}")
    public ResponseEntity<Void> deleteUserByEmail(@PathVariable String email) {
        try {
            System.out.println("Intentando eliminar el usuario con email: " + email);
            service.deleteUserByEmail(email);
            System.out.println("Usuario eliminado con email: " + email);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/favoritos/{email}")
    public ResponseEntity<List<Document>> obtenerFavoritos(@PathVariable String email) {
        List<Document> motosFavoritas = service.obtenerMotosFavoritasDelUsuario(email);
        return ResponseEntity.ok(motosFavoritas);
    }

    @PostMapping("/favoritos/agregar/{email}/{modelo}")
    public ResponseEntity<String> agregarFavorito(@PathVariable String email, @PathVariable String modelo) {
        service.agregarMotoAFavoritos(email, modelo);
        return ResponseEntity.ok("Moto agregada a favoritos.");
    }

    @DeleteMapping("/favoritos/eliminar/{email}/{modelo}")
    public ResponseEntity<String> eliminarFavorito(@PathVariable String email, @PathVariable String modelo) {
        service.eliminarMotoDeFavoritos(email, modelo);
        return ResponseEntity.ok("Moto eliminada de favoritos.");
    }

}