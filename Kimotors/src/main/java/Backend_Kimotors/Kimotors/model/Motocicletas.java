package Backend_Kimotors.Kimotors.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document(collection = "kimotors")
@Getter
@Setter
public class Motocicletas {

    @Id
    private String id;
    private Map<String, List<Moto>> motocicletas;


}
