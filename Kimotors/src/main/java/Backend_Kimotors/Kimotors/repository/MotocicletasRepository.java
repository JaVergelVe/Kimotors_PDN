package Backend_Kimotors.Kimotors.repository;
import Backend_Kimotors.Kimotors.model.Motocicletas;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MotocicletasRepository extends MongoRepository<Motocicletas, String>, MotocicletasRepositoryCustom {
}