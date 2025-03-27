package Backend_Kimotors.Kimotors.model.usuarios;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Getter
@Setter
public class Usuarios {
    @Id
    private String id;

    private String username;
    private String email;
    private String password;
}
