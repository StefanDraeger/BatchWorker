package de.draegerit.batchworker;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import de.draegerit.batchworker.entity.SensorEntity;
import de.draegerit.batchworker.util.CSVSensorFieldSetMapper;
import de.draegerit.batchworker.util.CSVSensorTokenizer;
import de.draegerit.batchworker.util.CustomItemProcessor;
import de.draegerit.batchworker.writer.CSVWriter;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	private static final int CHUNK_SIZE = 0;
	private static final String INPUT_FILE = "./csv/data.csv";
	private static final String STEP1 = "step1";
	private static final String IMPORT_JOB = "convertCSVtoPdfJob";

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	private long timestamp = 0L;

	@Bean
	public FlatFileItemReader<SensorEntity> reader() {
		timestamp = System.currentTimeMillis();

		FlatFileItemReader<SensorEntity> reader = new FlatFileItemReader<SensorEntity>();
		reader.setResource(new FileSystemResource(INPUT_FILE));
		reader.setLineMapper(new DefaultLineMapper<SensorEntity>() {
			{
				setLineTokenizer(new CSVSensorTokenizer());
				setFieldSetMapper(new CSVSensorFieldSetMapper());
			}
		});
		return reader;
	}

	@Bean
	public CustomItemProcessor processor() {
		return new CustomItemProcessor();
	}

	@Bean
	public CSVWriter writer() {
		return new CSVWriter(timestamp);
	}

	@Bean
	public Job convertCSVtoPdfJob(@Qualifier("step1") Step step1) {
		return jobBuilderFactory.get(IMPORT_JOB).start(step1).build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get(STEP1).<SensorEntity, SensorEntity>chunk(CHUNK_SIZE).reader(reader())
				.writer(writer()).build();
	}

}