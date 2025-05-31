package Backend_Kimotors.Kimotors.repository;

import Backend_Kimotors.Kimotors.model.Comentarios.Comentario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ComentarioRepository extends MongoRepository<Comentario, String> {
    List<Comentario> findByMotoMarcaAndMotoModelo(String marca, String modelo);
    List<Comentario> findByUserEmail(String userEmail);
}