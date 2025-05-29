package Backend_Kimotors.Kimotors.service;

import Backend_Kimotors.Kimotors.model.usuarios.Usuarios;
import Backend_Kimotors.Kimotors.repository.MotocicletasRepositoryCustom;
import Backend_Kimotors.Kimotors.repository.UsuariosRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuariosService {

    private final UsuariosRepository usuariosRepository;

    @Autowired
    private MotocicletasRepositoryCustom motocicletasRepository;

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
            System.out.println("Eliminando usuario: " + usuario.get());
            usuariosRepository.delete(usuario.get());
        } else {
            System.err.println("Usuario no encontrado para eliminar con email: " + email);
            throw new IllegalArgumentException("Usuario no encontrado");
        }
    }

    public List<Document> obtenerMotosFavoritasDelUsuario(String email) {
        Usuarios usuario = usuariosRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<String> favoritos = usuario.getFavoritos();

        return motocicletasRepository.motosFavoritasDeUsuario(favoritos);
    }

    public void agregarMotoAFavoritos(String email, String modeloMoto) {
        Usuarios usuario = usuariosRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<String> favoritos = usuario.getFavoritos();
        if (favoritos == null) {
            favoritos = new ArrayList<>();
        }

        if (!favoritos.contains(modeloMoto)) {
            favoritos.add(modeloMoto);
            usuario.setFavoritos(favoritos);
            usuariosRepository.save(usuario);
        }
    }

    public void eliminarMotoDeFavoritos(String email, String modeloMoto) {
        Usuarios usuario = usuariosRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<String> favoritos = usuario.getFavoritos();
        if (favoritos != null && favoritos.contains(modeloMoto)) {
            favoritos.remove(modeloMoto);
            usuario.setFavoritos(favoritos);
            usuariosRepository.save(usuario);
        }
    }

}