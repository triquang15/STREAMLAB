package com.aptech.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.aptech.common.entity"})
public class SpringAppAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAppAdminApplication.class, args);
	}

}
