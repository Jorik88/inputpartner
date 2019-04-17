package com.example.inputpartner.service;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class SendReportRequestService {

    private String filePath = "templates/reportTemplate.xml";
    private static final String KEI_ID_PLACEHOLDER = "KEI_ID";
    private static final String ACCOUNT_PLACEHOLDER = "ACCOUNT";
    private static final String DATE_FROM_PLACEHOLDER = "DATE_FROM";
    private final static String DATE_TILL_PLACEHOLDER = "DATE_TILL";
    private static final String REQUEST_URL = "https://localhost:9001/itwGateWS/exec/FISPut";
    private static final String UTF_ENCODING = "UTF-8";

    public String getBankReport(String keiId, String account, String dateFrom, String dateTill) {
        try {
            InputStream resourceStream = SendReportRequestService.class.getClassLoader().getResourceAsStream(filePath);
            StringWriter writer = new StringWriter();
            IOUtils.copy(resourceStream, writer, UTF_ENCODING);
            String xmlRequest = writer.toString()
                    .replace(KEI_ID_PLACEHOLDER, keiId)
                    .replace(ACCOUNT_PLACEHOLDER, account)
                    .replace(DATE_FROM_PLACEHOLDER, dateFrom)
                    .replace(DATE_TILL_PLACEHOLDER, dateTill);
            return sendRequest(xmlRequest);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    private String sendRequest(String data) throws IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build())
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build()) {
            HttpPost httpPost = new HttpPost(REQUEST_URL);
            httpPost.setEntity(new StringEntity(data, UTF_ENCODING));
            httpPost.setHeader("Content-Type", "text/xml");

            try (CloseableHttpResponse httpResponse = httpClient.execute(httpPost)) {
                return EntityUtils.toString(httpResponse.getEntity());
            }
        }
    }
}
