package Backend_Kimotors.Kimotors.repository;

import Backend_Kimotors.Kimotors.model.usuarios.Usuarios;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuariosRepository extends MongoRepository<Usuarios, String> {
}