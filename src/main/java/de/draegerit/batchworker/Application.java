package de.draegerit.batchworker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class Application {

	private static final String JOB_LAUNCHER = "jobLauncher";

	private static final String CONVERT_CSV_TO_PDF = "convertCSVtoPDF";

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	private static String[] springConfig = { "spring/batch/config/context.xml",
			"spring/batch/jobs/job-convertCSVtoPDF.xml" };

	private Application() {

	}

	/**
	 * Startmethode der Java Anwendung
	 *
	 * @param args
	 *            - Parameter von der Konsole
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// Laden der Konfiguration
		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);
		JobLauncher jobLauncher = (JobLauncher) context.getBean(JOB_LAUNCHER);
		Job job = (Job) context.getBean(CONVERT_CSV_TO_PDF);

		try {
			//Starten des Jobs
			JobExecution execution = jobLauncher.run(job, new JobParameters());
			log.info("Exit Status : " + execution.getStatus());

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		log.info("----Done----");
	}
}