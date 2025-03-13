package Backend_Kimotors.Kimotors.repository;
import Backend_Kimotors.Kimotors.model.Motocicletas;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MotocicletasRepository extends MongoRepository<Motocicletas, String> {

}