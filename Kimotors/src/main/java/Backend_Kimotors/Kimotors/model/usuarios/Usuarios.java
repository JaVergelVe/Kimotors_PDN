package Backend_Kimotors.Kimotors.model.usuarios;

import Backend_Kimotors.Kimotors.model.Comentarios.Comentario;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
@Getter
@Setter
public class Usuarios {
    @Id
    private String id;

    private String username;
    private String email;
    private String password;
    private List<String> favoritos;
    private List<Comentario> comentarios;
}
