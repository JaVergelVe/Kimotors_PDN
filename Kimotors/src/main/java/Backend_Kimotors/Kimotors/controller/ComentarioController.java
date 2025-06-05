package Backend_Kimotors.Kimotors.controller;

import Backend_Kimotors.Kimotors.model.Comentarios.Comentario;
import Backend_Kimotors.Kimotors.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @PostMapping
    public ResponseEntity<Comentario> agregarComentario(@RequestBody Comentario comentario) {
        return ResponseEntity.ok(comentarioService.agregarComentario(comentario));
    }

    @GetMapping("/moto/{marca}/{modelo}")
    public ResponseEntity<List<Comentario>> obtenerPorMoto(@PathVariable String marca, @PathVariable String modelo) {
        return ResponseEntity.ok(comentarioService.obtenerComentariosDeMoto(marca, modelo));
    }

    @GetMapping("/usuario/{userEmail}")
    public ResponseEntity<List<Comentario>> obtenerPorUsuario(@PathVariable String userEmail) {
        return ResponseEntity.ok(comentarioService.obtenerComentariosDeUsuario(userEmail));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        comentarioService.eliminarComentario(id);
        return ResponseEntity.noContent().build();
    }
}
