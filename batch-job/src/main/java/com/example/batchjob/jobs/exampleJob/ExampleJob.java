package com.example.batchjob.jobs.exampleJob;

import org.springframework.batch.core.ItemProcessListener;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.batchjob.listener.GenericItemListener;
import com.example.batchjob.listener.JobCompletionListener;

@Configuration
public class ExampleJob {

	@Value("${spring.batch.chunkSize}")
	private int chunkSize;
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Bean
	public JobReader quoteReader() {
		return new JobReader();
	};
	
	@Bean
	public JobProcessor quoteProcessor() {
		return new JobProcessor();
	};
	
	@Bean
	public JobWriter quoteWriter() {
		return new JobWriter();
	};
	
	@Bean
	public GenericItemListener<String, String> genericListener() {
	  return new GenericItemListener<String, String>();
	}
	
	@Bean(name = "exampleJobObj")
	public Job initJob() {
		Step step1 = stepBuilderFactory.get("exampleJobStep").<String, String>chunk(chunkSize)
				.reader(quoteReader()).listener((ItemReadListener<String>) genericListener())
				.processor(quoteProcessor()).listener((ItemProcessListener<String, String>) genericListener())
				.writer(quoteWriter()).listener((ItemWriteListener<String>) genericListener()).build();
		Job job = jobBuilderFactory.get("exampleJob").listener(new JobCompletionListener()).flow(step1).end().build();
		return job;
	}
	
	public void launchJob() {
		try {
			JobParameters params = new JobParametersBuilder()
					.addString("JobID", String.valueOf(System.currentTimeMillis()))
					.addString("Wish", "Pray to run successfully")
					.addString("JobName", initJob().getName())
					.toJobParameters();
			jobLauncher.run(initJob(), params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Scheduled(cron = "${spring.batch.cronSimpleJob}")
//	public void schedule() {
//		batchService.launchJob();
//	}
}
