package com.example.server;

import com.example.server.dto.AirplaneDto;
import com.example.server.entity.Ware.*;
import com.example.server.service.Ware.Director;
import com.example.server.service.Ware.TransportAirplaneBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);

		Director director = new Director();
		director.setBuilder(new TransportAirplaneBuilder());
		Airplane airplane = director.buildAirplane();
		System.out.println(airplane);
		//
		Ware helicopter = new CombatHelicopter(new Helicopter());
		System.out.println(helicopter.showDescription());
		//

		//
		WareTemplate hangGlider = new HangGlider();
		hangGlider.showWareInfo();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:3000")
						.allowedMethods("*");
			}
		};
	}
}
