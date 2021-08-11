package client.dataloader;

import client.model.Contract;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CustomContractDeserializer extends StdDeserializer<Contract> {

    public CustomContractDeserializer() {
        this(null);
    }

    public CustomContractDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Contract deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException, JsonProcessingException {

        Contract contract = new Contract();

        ObjectCodec codec = parser.getCodec();
        JsonNode node = codec.readTree(parser);


        JsonNode numberNode = node.get("number");
        Integer number = Integer.parseInt(numberNode.asText());
        contract.setNumber(number);

        JsonNode createdDateNode = node.get("createdDate");
        String createdDateStr = createdDateNode.asText().substring(0, 10);

        JsonNode lastUpdatedDateNode = node.get("lastUpdatedDate");
        String lastUpdatedDateStr = lastUpdatedDateNode.asText().substring(0, 10);

        contract.setCreatedDate(convertStringToCalendar(createdDateStr));
        contract.setLastUpdatedDate(convertStringToCalendar(lastUpdatedDateStr));

        return contract;
    }

    private Calendar convertStringToCalendar(String dateStr) {
        Calendar dateCalendar = new GregorianCalendar();
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
            dateCalendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return dateCalendar;
    }
}
