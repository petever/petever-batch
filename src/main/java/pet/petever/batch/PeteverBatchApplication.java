package pet.petever.batch;


import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
@EnableBatchProcessing
public class PeteverBatchApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(PeteverBatchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Job Start");
        Arrays.stream(args).forEach(System.out::println);
    }
}
