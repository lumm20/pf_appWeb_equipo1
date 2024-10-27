package conexion;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;
import org.bson.codecs.Codec;

/**
 *  * Codec personalizado para manejar la conversión de fechas

 * @author karim
 */
public class DateTimeCodec implements Codec<Date> {

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

    @Override
    public Date decode(org.bson.BsonReader reader, org.bson.codecs.DecoderContext decoderContext) {
        return new Date(reader.readDateTime());
    }

    @Override
    public Class<Date> getEncoderClass() {
        return Date.class;
    }
}
