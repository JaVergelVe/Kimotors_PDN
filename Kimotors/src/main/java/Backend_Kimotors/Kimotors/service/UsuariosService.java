package Backend_Kimotors.Kimotors.service;

import Backend_Kimotors.Kimotors.model.usuarios.Usuarios;
import Backend_Kimotors.Kimotors.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Usuarios> getByEmail(String email) {
        return usuariosRepository.findByEmail(email);
    }

    public Usuarios saveUser(Usuarios usuario) {
        return usuariosRepository.save(usuario);
    }

    public Usuarios updatePasswordByEmail(String email, String newPassword) {
        Usuarios usuario = usuariosRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con el email: " + email));
        usuario.setPassword(newPassword);
        return usuariosRepository.save(usuario);
    }

    public void deleteUserByEmail(String email) {
        Optional<Usuarios> usuario = usuariosRepository.findByEmail(email);
        if (usuario.isPresent()) {
            usuariosRepository.deleteByEmail(email);
        } else {
            throw new IllegalArgumentException("Usuario no encontrado con el email: " + email);
        }
    }
}