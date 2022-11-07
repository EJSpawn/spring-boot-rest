package com.example.demo;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.handler.JsonBodyHandler;
import com.example.demo.model.Model;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		getAPI();
	}


	public static void getAPI() {
		Model model = new Model();
		try {
			HttpRequest request = HttpRequest.newBuilder(new URI("https://jsonplaceholder.typicode.com/todos/1"))
            .header("Accept", "application/json")
            .build();

			model = HttpClient.newHttpClient()
					.send(request, new JsonBodyHandler<>(Model.class))
					.body()
					.get();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(model);
	}
}
