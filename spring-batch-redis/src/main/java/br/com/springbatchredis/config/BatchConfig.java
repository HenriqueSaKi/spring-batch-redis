package br.com.springbatchredis.config;

import br.com.springbatchredis.model.ReaderCSVModel;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job(Step step) {
        return jobBuilderFactory
                .get("job")
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step step (ItemReader<ReaderCSVModel> reader, ItemWriter<ReaderCSVModel> writer) {
        return stepBuilderFactory
            .get("step")
            .<ReaderCSVModel, ReaderCSVModel>chunk(5)
            .reader(reader)
            .writer(writer)
            .build();
    }

    @Bean
    public ItemReader<ReaderCSVModel> readCSVFile() {
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
    public ItemWriter<ReaderCSVModel> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<ReaderCSVModel>()
            .dataSource(dataSource)
            .sql("INSERT INTO CSV_DATA (ID, NAME, DATE_OF_BIRTHDAY, CURRENT_JOB) " +
                    " VALUES (:id, :name, :dateOfBirthday, :currentJob)")
            .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
            .build();
    }

}
