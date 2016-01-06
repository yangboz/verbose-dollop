package info.smartkit.verbose_dollop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.javac.util.List;
import info.smartkit.verbose_dollop.domain.Customer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class VerboseDollopApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(VerboseDollopApplication.class, args);
        //2.
        //3.Load JSON file and convert to Java POJO
        ObjectMapper objectMapper = new ObjectMapper();
        List<Customer> customerList = objectMapper.readValue(new File("classpath://customer.json"), List.class);
        System.out.println("customerList:" + customerList);
    }
}
