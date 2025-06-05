package Backend_Kimotors.Kimotors.repository;

import Backend_Kimotors.Kimotors.model.usuarios.LoginRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoginRecordRepository extends MongoRepository<LoginRecord, String> {

}
