package com.swiftfingers.codingchallenge.exercises;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Created by Obiora on 07-Aug-2025 at 02:10
 */
public class ApiTest {

    public static void connect () throws IOException, InterruptedException {
        //create HttpClient
        HttpClient httpClient = HttpClient.newHttpClient();

        //create a request
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("")).build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
}
