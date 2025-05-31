package Backend_Kimotors.Kimotors.service;

import Backend_Kimotors.Kimotors.model.Comentarios.Comentario;
import Backend_Kimotors.Kimotors.model.usuarios.Usuarios;
import Backend_Kimotors.Kimotors.repository.MotocicletasRepositoryCustom;
import Backend_Kimotors.Kimotors.repository.UsuariosRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuariosService {

    private final UsuariosRepository usuariosRepository;

    @Autowired
    private MotocicletasRepositoryCustom motocicletasRepository;
    @Autowired
    private UsuariosRepository usuarioRepository;

    @Autowired
    public UsuariosService(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    public Optional<Usuarios> agregarComentario(String username, Comentario comentario) {
        Optional<Usuarios> usuarioOpt = usuarioRepository.findByUsername(username);
        if (usuarioOpt.isPresent()) {
            Usuarios usuario = usuarioOpt.get();

            if (comentario.getFecha() == null) {
                comentario.setFecha(LocalDateTime.now());
            }

            usuario.getComentarios().add(comentario);
            usuarioRepository.save(usuario);
            return Optional.of(usuario);
        }
        return Optional.empty();
    }
    public Optional<List<Comentario>> obtenerComentarios(String username) {
        Optional<Usuarios> usuarioOpt = usuarioRepository.findByUsername(username);
        return usuarioOpt.map(Usuarios::getComentarios);
    }

    public Optional<Usuarios> eliminarComentario(String username, int index) {
        Optional<Usuarios> usuarioOpt = usuarioRepository.findByUsername(username);
        if (usuarioOpt.isPresent()) {
            Usuarios usuario = usuarioOpt.get();
            List<Comentario> comentarios = usuario.getComentarios();
            if (index >= 0 && index < comentarios.size()) {
                comentarios.remove(index);
                usuarioRepository.save(usuario);
                return Optional.of(usuario);
            }
        }
        return Optional.empty();
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