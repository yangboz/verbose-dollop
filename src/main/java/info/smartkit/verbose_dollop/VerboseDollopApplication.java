package info.smartkit.verbose_dollop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;

import java.io.IOException;

@SpringBootApplication
public class VerboseDollopApplication {


    public static void main(String[] args) throws IOException {
        SpringApplication.run(VerboseDollopApplication.class, args);
    }

    // @see:
    // http://stackoverflow.com/questions/26425067/resolvedspring-boot-access-to-entitymanager
    @Bean
    public PersistenceAnnotationBeanPostProcessor persistenceBeanPostProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }

}
