package br.com.springbatchredis.config;

import br.com.springbatchredis.model.ReaderCSVModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.logging.Level;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final Logger logger = LoggerFactory.getLogger(BatchConfig.class);
    @Autowired private DataSource dataSource;
    @Autowired private JobLauncher jobLauncher;
    @Autowired private JobRepository jobRepository;
    @Autowired private PlatformTransactionManager transactionManager;
    @Autowired private JobBuilderFactory jobBuilderFactory;
    @Autowired private StepBuilderFactory stepBuilderFactory;

//    @Scheduled(fixedRate = 5000) //Execute job every 5 seconds
    public void launchJob () throws Exception {
        logger.info("Batch processing starts at: {}", LocalDateTime.now());
        jobLauncher.run(job(jobRepository, transactionManager),
                new JobParametersBuilder().addDate("launchJob", new Date())
                        .toJobParameters());
        logger.info("Batch processing ends at: {}", LocalDateTime.now());
    }

    @Bean
    public Job job(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return jobBuilderFactory
                .get("job")
                .start(step(jobRepository, transactionManager))
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step step (JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return stepBuilderFactory
            .get("step")
            .<ReaderCSVModel, ReaderCSVModel>chunk(5)
            .reader(reader())
            .writer(writer())
            .build();
    }

    @Bean
    public ItemReader<ReaderCSVModel> reader() {
        return new FlatFileItemReaderBuilder<ReaderCSVModel>()
            .name("reader")
            .resource(new FileSystemResource("src/main/resources/dataset/100-random-datas.csv"))
            .delimited()
            .delimiter(";")
            .names("id", "name", "dateOfBirthday", "currentJob")
            .targetType(ReaderCSVModel.class)
            .build();

    }

    @Bean
    public ItemWriter<ReaderCSVModel> writer() {
        return new JdbcBatchItemWriterBuilder<ReaderCSVModel>()
            .dataSource(dataSource)
            .sql("INSERT INTO CSV_DATA (ID, NAME, DATE_OF_BIRTHDAY, CURRENT_JOB) " +
                    " VALUES (:id, :name, :dateOfBirthday, :currentJob)")
            .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
            .build();
    }

}
