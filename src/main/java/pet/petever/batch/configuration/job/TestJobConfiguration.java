package pet.petever.batch.configuration.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class TestJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job testJob() {
        return jobBuilderFactory.get("testJob")
                .incrementer((i) -> {
                    Map<String, JobParameter> parameters = new HashMap<>();
                    if (i != null) {
                        parameters.putAll(i.getParameters());
                    }
                    parameters.put("createTime", new JobParameter(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
                    return new JobParameters(parameters);
                })
                .start(testStep())
                .build();
    }

    @Bean
    public Step testStep() {
        return stepBuilderFactory.get("testStep")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("test");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
