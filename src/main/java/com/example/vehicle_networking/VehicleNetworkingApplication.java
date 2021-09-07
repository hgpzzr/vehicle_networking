package com.example.vehicle_networking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import springfox.documentation.swagger2.mappers.LicenseMapper;

@SpringBootApplication
@MapperScan("com.example.vehicle_networking.mapper")
public class VehicleNetworkingApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleNetworkingApplication.class, args);
	}

}
