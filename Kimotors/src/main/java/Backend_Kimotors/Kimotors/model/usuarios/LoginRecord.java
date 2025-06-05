package Backend_Kimotors.Kimotors.model.usuarios;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "login_records")
@Getter
@Setter
public class LoginRecord {
    @Id
    private String id;
    private String username;
    private String email;
    private String provider;
    private String activityType;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime loginTimestamp;
}
