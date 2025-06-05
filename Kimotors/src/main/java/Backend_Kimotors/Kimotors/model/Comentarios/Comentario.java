package Backend_Kimotors.Kimotors.model.Comentarios;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comentarios")
@Getter
@Setter
public class Comentario {
    @Id
    private String id;
    private String texto;
    private String userEmail;
    private String motoMarca;
    private String motoModelo;
    private LocalDateTime fecha;
}


