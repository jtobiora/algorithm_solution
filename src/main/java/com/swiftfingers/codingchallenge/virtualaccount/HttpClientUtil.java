package com.swiftfingers.codingchallenge.virtualaccount;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swiftfingers.codingchallenge.docstest.AES;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by Obiora on 14-Jul-2025 at 09:51
 */
@Slf4j
public class HttpClientUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String sendRequest(String urlString, String method, Object bodyObject, Map<String, String> headers, String decryptionKey) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod(method);
        connection.setDoInput(true);
        connection.setConnectTimeout(15000);
        connection.setReadTimeout(15000);

        // Add headers
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }

        if (bodyObject != null) {
            connection.setDoOutput(true);
            String jsonBody = mapper.writeValueAsString(bodyObject);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
        }

        int status = connection.getResponseCode();
        InputStream responseStream = (status < HttpURLConnection.HTTP_BAD_REQUEST)
                ? connection.getInputStream()
                : connection.getErrorStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(responseStream, "utf-8"));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line.trim());
        }

        connection.disconnect();

        // If decryption key is provided, attempt to decrypt the "data" field
        if (decryptionKey != null && !decryptionKey.isEmpty()) {
            try {
                JsonNode rootNode = mapper.readTree(response.toString());
                if (rootNode.has("data")) {
                    String encryptedData = rootNode.get("data").asText();
                    return AES.decrypt(encryptedData, decryptionKey);
                }
            } catch (Exception e) {
                log.error("Error making http request " + e);
            }
        }

        // If no decryption key or no "data" field — return raw response
        return response.toString();
    }
}
