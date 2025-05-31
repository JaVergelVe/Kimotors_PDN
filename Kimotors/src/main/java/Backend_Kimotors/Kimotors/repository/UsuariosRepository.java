package Backend_Kimotors.Kimotors.repository;

import Backend_Kimotors.Kimotors.model.usuarios.Usuarios;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuariosRepository extends MongoRepository<Usuarios, String> {
    Optional<Usuarios> findByEmail(String email);

    Optional<Usuarios> findByUsername(String username);
}