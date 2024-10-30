package conexion;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;
import org.bson.codecs.Codec;

/**
 * Codec personalizado para manejar la conversión de fechas
 *
 * @author karim
 */
public class DateTimeCodec implements Codec<Date> {

    /**
     * Codifica un objeto de tipo Date en formato BSON.
     *
     * Si el objeto Date es nulo, se escribe un valor nulo en el flujo de
     * escritura.
     *
     * @param writer Flujo de escritura de BSON.
     * @param value Objeto Date a codificar.
     * @param encoderContext Contexto de codificación.
     */
    @Override
    public void encode(org.bson.BsonWriter writer, Date value, org.bson.codecs.EncoderContext encoderContext) {
        if (value != null) {
            ZonedDateTime zonedDateTime = value.toInstant()
                    .atZone(TimeZone.getDefault().toZoneId());
            writer.writeDateTime(value.getTime());
        } else {
            writer.writeNull();
        }
    }

    /**
     * Decodifica un valor BSON en un objeto de tipo Date.
     *
     * @param reader Flujo de lectura de BSON.
     * @param decoderContext Contexto de decodificación.
     * @return Objeto Date decodificado.
     */
    @Override
    public Date decode(org.bson.BsonReader reader, org.bson.codecs.DecoderContext decoderContext) {
        return new Date(reader.readDateTime());
    }

    /**
     * Retorna la clase de objetos que este codec puede codificar.
     *
     * @return Clase de objetos Date.
     */
    @Override
    public Class<Date> getEncoderClass() {
        return Date.class;
    }
}
