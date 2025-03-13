package Backend_Kimotors.Kimotors.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Moto {

    private String marca;
    private String modelo;
    private int año;
    private DatosMotor datos_motor;
    private Transmision transmision;
    private Suspension suspension;
    private Frenos frenos;
    private Ruedas ruedas;
    private Dimensiones dimensiones;
    private String consumo;
    private String electronica;
    private String precio_aprox;
    private List<String> imagenes;


}
