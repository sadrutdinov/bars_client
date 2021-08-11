package client.dataloader;

import client.model.Contract;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.Arrays;


public class DataLoader {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();


    public ObservableList<Contract> getData() throws Exception {

        HttpGet request = new HttpGet("http://localhost:8080/rest/contract/all");

        request.addHeader(HttpHeaders.USER_AGENT, "BarsClient");

        String result = null;
        ObservableList<Contract> contractsData = FXCollections.observableArrayList();

        try (CloseableHttpResponse response = httpClient.execute(request)) {


            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();

            result = EntityUtils.toString(entity);

        } catch (HttpHostConnectException e) {
            return contractsData;
        }

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module =
                new SimpleModule("CustomContractDeserializer", new Version(1, 0, 0,
                        null, null, null));
        module.addDeserializer(Contract.class, new CustomContractDeserializer());
        mapper.registerModule(module);
        Contract[] contracts = mapper.readValue(result, Contract[].class);

        contractsData.addAll(Arrays.asList(contracts));

        return contractsData;

    }

}
