package Backend_Kimotors.Kimotors.model.Comentarios;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Comentario {
        private String moto;
        private String texto;
        private LocalDateTime fecha;

    }


