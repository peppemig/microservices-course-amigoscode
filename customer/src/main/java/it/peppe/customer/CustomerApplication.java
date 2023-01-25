package it.peppe.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "it.peppe.customer",
                "it.peppe.amqp"
        }
)
@EnableFeignClients(
        basePackages = "it.peppe.clients"
)
public class CustomerApplication {

    public static void main (String[] args){
        SpringApplication.run(CustomerApplication.class,args);
    }
}
