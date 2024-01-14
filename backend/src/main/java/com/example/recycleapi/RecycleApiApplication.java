package com.example.recycleapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RecycleApiApplication {

	@GetMapping("/long_response_api/{time}")
	String longResponseApi(@PathVariable int time) {
		System.out.println("기다림 시작" + time);
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) { }
		System.out.println("기다림 끝");

		return "you waited for " + time + "seconds";
	}
	public static void main(String[] args) {
		SpringApplication.run(RecycleApiApplication.class, args);
	}

}
