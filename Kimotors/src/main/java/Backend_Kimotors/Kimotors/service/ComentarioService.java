package Backend_Kimotors.Kimotors.service;

import Backend_Kimotors.Kimotors.model.Comentarios.Comentario;
import Backend_Kimotors.Kimotors.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    public Comentario agregarComentario(Comentario comentario) {
        comentario.setFecha(LocalDateTime.now());
        return comentarioRepository.save(comentario);
    }

    public List<Comentario> obtenerComentariosDeMoto(String marca, String modelo) {
        return comentarioRepository.findByMotoMarcaAndMotoModelo(marca, modelo);
    }

    public List<Comentario> obtenerComentariosDeUsuario(String userEmail) {
        return comentarioRepository.findByUserEmail(userEmail);
    }

    public void eliminarComentario(String id) {
        comentarioRepository.deleteById(id);
    }
}
