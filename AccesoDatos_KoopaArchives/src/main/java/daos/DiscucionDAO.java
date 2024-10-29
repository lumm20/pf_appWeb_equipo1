/*
 * DiscucionDAO.java
 */
package daos;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lte;
import static com.mongodb.client.model.Filters.regex;
import conexion.Conexion;
import conexion.IConexion;
import entidades.Discusion;
import entidades.FiltroNoticia;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import org.bson.conversions.Bson;

/**
 *
 * @author Juventino López García - 00000248547 - 28/10/2024
 */
public class DiscucionDAO implements IDiscusionDAO {

    private IConexion conexion;
    private MongoCollection<Discusion> discusiones;

    public DiscucionDAO() {
        this.conexion = Conexion.getInstance();
        MongoDatabase baseDatos = this.conexion.obtenerBaseDatos();
        discusiones = baseDatos.getCollection("discusiones", Discusion.class);
    }

    @Override
    public void publicarNuevaDiscusion(Discusion discusion) throws PersistenciaException {
        try {
            discusiones.insertOne(discusion);
        } catch (MongoException me) {
            throw new PersistenciaException("No se pudo guardar la noticia.");
        }
    }

    @Override
    public Discusion buscarDiscusion(Discusion discusion) throws PersistenciaException {
        try {
            return discusiones.find(eq("_id", discusion.getIdNoticia())).first();
        } catch (MongoException me) {
            throw new PersistenciaException("No se pudo guardar la noticia.");
        }
    }

    @Override
    public List<Discusion> buscarDiscusiones(FiltroNoticia filtros) throws PersistenciaException {
        List<Bson> condiciones = new ArrayList<>();

        // Filtro por descripción usando regex
        if (filtros.getTitulo() != null) {
            condiciones.add(
                    regex("contenido.descripcion",
                            ".*" + Pattern.quote(filtros.getTitulo()) + ".*",
                            "i")
            );
        }

        if (filtros.getCategoria() != null) {
            condiciones.add(match(eq("categoria", filtros.getCategoria())));
        }

        // Filtro por rango de fechas
        if (filtros.getFechaDesde() != null && filtros.getFechaHasta() != null) {
            Date fechaDesde = ajustarFechaInicio(filtros.getFechaDesde());
            Date fechaHasta = ajustarFechaFin(filtros.getFechaHasta());

            condiciones.add(
                    and(
                            gte("fecha_publicacion", fechaDesde),
                            lte("fecha_publicacion", fechaHasta)
                    )
            );
        }

        return discusiones.find(and(condiciones)).into(new ArrayList<>());
    }

    // Método para ajustar la fecha de inicio a las 00:00:00
    private Date ajustarFechaInicio(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    // Método para ajustar la fecha de fin a las 23:59:59
    private Date ajustarFechaFin(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }
}
