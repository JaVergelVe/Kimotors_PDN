package Backend_Kimotors.Kimotors.repository;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.aggregation.ConvertOperators;


import java.util.ArrayList;
import java.util.List;

@Repository
public class MotocicletasRepositoryImpl implements MotocicletasRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Document> contarMotosPorMarca() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project().and("motocicletas").as("motocicletas"),
                Aggregation.project().andExpression("objectToArray(motocicletas)").as("motosPorMarca"),
                Aggregation.unwind("motosPorMarca"),
                Aggregation.unwind("motosPorMarca.v"),
                Aggregation.group("motosPorMarca.k").count().as("total"),
                Aggregation.project("total").and("_id").as("marca")
        );
        return mongoTemplate.aggregate(aggregation, "motorbikes", Document.class).getMappedResults();
    }

    @Override
    public List<Document> nombreCompletoMotos() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project().and("motocicletas").as("motocicletas"),
                Aggregation.project().andExpression("objectToArray(motocicletas)").as("motosPorMarca"),
                Aggregation.unwind("motosPorMarca"),
                Aggregation.unwind("motosPorMarca.v"),
                Aggregation.project()
                        .and("motosPorMarca.k").as("marca")
                        .and("motosPorMarca.v.modelo").as("modelo")
                        .andExpression("concat(motosPorMarca.k, ' ', motosPorMarca.v.modelo)").as("fullName")
        );
        return mongoTemplate.aggregate(aggregation, "motorbikes", Document.class).getMappedResults();
    }

    @Override
    public List<Document> motosPorPrecioDescendente() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project().and("motocicletas").as("motocicletas"),
                Aggregation.project().andExpression("objectToArray(motocicletas)").as("motosPorMarca"),
                Aggregation.unwind("motosPorMarca"),
                Aggregation.unwind("motosPorMarca.v"),
                Aggregation.project()
                        .and("motosPorMarca.v").as("moto")

                        .andExpression(
                                "toDouble(replaceAll(replaceAll(motosPorMarca.v.precio_aprox, ',', ''), ' €', ''))"
                        ).as("precioNumerico"),
                Aggregation.sort(Sort.by(Sort.Direction.DESC, "precioNumerico"))
        );

        return mongoTemplate.aggregate(aggregation, "motorbikes", Document.class).getMappedResults();
    }


    @Override
    public List<Document> motosPorCilindrajeMayorA500() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project().and("motocicletas").as("motocicletas"),
                Aggregation.project().andExpression("objectToArray(motocicletas)").as("motosPorMarca"),
                Aggregation.unwind("motosPorMarca"),
                Aggregation.unwind("motosPorMarca.v"),
                Aggregation.match(Criteria.where("motosPorMarca.v.datos_motor.cilindraje").gt(900))
        );
        return mongoTemplate.aggregate(aggregation, "motorbikes", Document.class).getMappedResults();
    }

    @Override
    public List<Document> motosMasEconomicas(int precioMaximo) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project().and("motocicletas").as("motocicletas"),
                Aggregation.project().andExpression("objectToArray(motocicletas)").as("motosPorMarca"),
                Aggregation.unwind("motosPorMarca"),
                Aggregation.unwind("motosPorMarca.v"),
                Aggregation.project()
                        .and("motosPorMarca.v.modelo").as("modelo")
                        .and("motosPorMarca.v.marca").as("marca")
                        .and("motosPorMarca.v.precio_aprox").as("precio_aprox")
                        .andExpression(
                                "toDouble(replaceAll(replaceAll(motosPorMarca.v.precio_aprox, ',', ''), ' €', ''))"
                        ).as("precioNumerico"),
                Aggregation.match(Criteria.where("precioNumerico").lte(precioMaximo)),
                Aggregation.sort(Sort.by(Sort.Direction.ASC, "precioNumerico"))
        );

        return mongoTemplate.aggregate(aggregation, "motorbikes", Document.class).getMappedResults();
    }


    @Override
    public List<Document> motosConPaginacion(int skip, int limit) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project().and("motocicletas").as("motocicletas"),
                Aggregation.project().andExpression("objectToArray(motocicletas)").as("motosPorMarca"),
                Aggregation.unwind("motosPorMarca"),
                Aggregation.unwind("motosPorMarca.v"),
                Aggregation.skip((long) skip),
                Aggregation.limit(limit)
        );
        return mongoTemplate.aggregate(aggregation, "motorbikes", Document.class).getMappedResults();
    }

    public List<Document> descomponerDatosMotor(String modelo) {
        List<AggregationOperation> operations = new ArrayList<>();

        operations.add(Aggregation.project().and("motocicletas").as("motocicletas"));
        operations.add(Aggregation.project().andExpression("objectToArray(motocicletas)").as("motosPorMarca"));
        operations.add(Aggregation.unwind("motosPorMarca"));
        operations.add(Aggregation.unwind("motosPorMarca.v"));

        if (modelo != null && !modelo.isEmpty()) {
            operations.add(Aggregation.match(Criteria.where("motosPorMarca.v.modelo").regex("^" + modelo + "$", "i")));
        }

        operations.add(
                Aggregation.project()
                        .and("motosPorMarca.v.datos_motor").as("datos_motor")
                        .and("motosPorMarca.v.modelo").as("modelo")
        );

        Aggregation aggregation = Aggregation.newAggregation(operations);
        return mongoTemplate.aggregate(aggregation, "motorbikes", Document.class).getMappedResults();
    }



    @Override
    public List<Document> motosFavoritasDeUsuario(List<String> favoritos) {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.project().and("motocicletas").as("motocicletas"),
                Aggregation.project().andExpression("objectToArray(motocicletas)").as("motosPorMarca"),
                Aggregation.unwind("motosPorMarca"),
                Aggregation.unwind("motosPorMarca.v"),
                Aggregation.match(Criteria.where("motosPorMarca.v.modelo").in(favoritos)),
                Aggregation.project().and("motosPorMarca.v").as("motocicleta")
        );

        return mongoTemplate.aggregate(agg, "motorbikes", Document.class).getMappedResults();
    }

    @Override
    public List<Document> buscarPorTexto(String texto) {
        Aggregation aggregation = Aggregation.newAggregation(
                context -> new Document("$project",
                        new Document("marcas", new Document("$objectToArray", "$motocicletas"))
                ),
                Aggregation.unwind("marcas"),
                Aggregation.unwind("marcas.v"),
                Aggregation.match(Criteria.where("marcas.v.modelo").regex("^" + texto, "i")),
                context -> new Document("$project",
                        new Document("motocicleta", "$marcas.v")
                )
        );
        AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, "motorbikes", Document.class);
        return results.getMappedResults();
    }





}
