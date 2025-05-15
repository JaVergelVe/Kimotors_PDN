package Backend_Kimotors.Kimotors.repository;
import org.bson.Document;
import java.util.List;
public interface MotocicletasRepositoryCustom {
    List<Document> contarMotosPorMarca();
    List<Document> nombreCompletoMotos();
    List<Document> motosPorPrecioDescendente();
    List<Document> motosPorCilindrajeMayorA500();
    List<Document> motosMasEconomicas(int limite);
    List<Document> motosConPaginacion(int skip, int limit);
    List<Document> descomponerDatosMotor(String modelo);
    List<Document> motosFavoritasDeUsuario(List<String> favoritos);
    List<Document> buscarPorTexto(String texto);

}
