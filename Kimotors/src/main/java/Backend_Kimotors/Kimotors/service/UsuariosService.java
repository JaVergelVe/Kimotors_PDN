package Backend_Kimotors.Kimotors.service;

import Backend_Kimotors.Kimotors.model.usuarios.Usuarios;
import Backend_Kimotors.Kimotors.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosService {

    private final UsuariosRepository usuariosRepository;

    @Autowired
    public UsuariosService(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    public List<Usuarios> getAll() {
        return usuariosRepository.findAll();
    }
}
