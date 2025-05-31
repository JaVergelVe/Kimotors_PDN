package Backend_Kimotors.Kimotors.controller;

import Backend_Kimotors.Kimotors.model.usuarios.LoginRecord;
import Backend_Kimotors.Kimotors.repository.LoginRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/login-records")
public class LoginRecordController {

    @Autowired
    private LoginRecordRepository loginRecordRepository;

    @PostMapping
    public ResponseEntity<LoginRecord> registerLoginActivity(@RequestBody LoginRecord loginRecord) {
        LoginRecord savedRecord = loginRecordRepository.save(loginRecord);
        return ResponseEntity.ok(savedRecord);
    }

    @GetMapping
    public ResponseEntity<List<LoginRecord>> getAllLoginRecords() {
        List<LoginRecord> records = loginRecordRepository.findAll();
        return ResponseEntity.ok(records);
    }
}