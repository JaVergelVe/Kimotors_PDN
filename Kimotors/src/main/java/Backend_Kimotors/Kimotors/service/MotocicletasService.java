package Backend_Kimotors.Kimotors.service;


import Backend_Kimotors.Kimotors.model.Moto;
import Backend_Kimotors.Kimotors.model.Motocicletas;
import Backend_Kimotors.Kimotors.repository.MotocicletasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MotocicletasService {

    @Autowired
    private MotocicletasRepository repository;

    // Obtener todas las motos
    public List<Motocicletas> getAll() {
        return repository.findAll();
    }


    // Obtener por marca
    public List<Moto> getByMarca(String marca) {
        Optional<Motocicletas> data = repository.findAll().stream().findFirst();
        if (data.isPresent()) {
            Motocicletas motosExistente = data.get();
            for (String key : motosExistente.getMotocicletas().keySet()) {
                if (key.equalsIgnoreCase(marca)) {
                    return motosExistente.getMotocicletas().get(key);
                }
            }
        }

        return Collections.emptyList();
    }


    // Obtener por modelo específico
    public Moto getByModelo(String modelo) {
        Optional<Motocicletas> data = repository.findAll().stream().findFirst();
        if (data.isPresent()) {
            Motocicletas motosExistente = data.get();
            for (List<Moto> listaMotos : motosExistente.getMotocicletas().values()) {
                for (Moto moto : listaMotos) {
                    if (moto.getModelo().equalsIgnoreCase(modelo)) {
                        return moto;
                    }
                }
            }
        }

        return null;
    }


    // Agregar nueva moto
    public void addMoto(Moto moto) {
        Optional<Motocicletas> data = repository.findAll().stream().findFirst();
        Motocicletas motosExistente;

        if (data.isPresent()) {
            motosExistente = data.get();
        } else {

            motosExistente = new Motocicletas();
            motosExistente.setMotocicletas(new HashMap<>());
        }
        String marcaKey = motosExistente.getMotocicletas().keySet()
                .stream()
                .filter(key -> key.equalsIgnoreCase(moto.getMarca()))
                .findFirst()
                .orElse(moto.getMarca());

        motosExistente.getMotocicletas()
                .computeIfAbsent(marcaKey, k -> new ArrayList<>())
                .add(moto);


        repository.save(motosExistente);
    }

}