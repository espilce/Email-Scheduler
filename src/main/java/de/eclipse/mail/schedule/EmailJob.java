package de.eclipse.mail.schedule;

import de.eclipse.mail.EmailService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class EmailJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailJob.class);

    @Value("${email.period}")
    private Long emailPeriod;

    @Autowired
    private EmailService mailMessage;

    private ApplicationContext applicationContext;

    private static final AtomicInteger count = new AtomicInteger();


    @PostConstruct
    public void init() {
        LOGGER.info("Starting scheduler...");
    }

    @Autowired
    public EmailJob(EmailService message, ApplicationContext applicationContext) {
        this.mailMessage = message;
        this.applicationContext = applicationContext;
    }

    public EmailJob() {
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        LOGGER.info("Email Job has begun...");
        mailMessage.sendEmail();
        count.incrementAndGet();
        LOGGER.info("Job has finished: {} {}", getNumberOfJobsDone(), "times");
    }

    @Bean
    public JobDetailFactoryBean jobDetail() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(EmailJob.class);
        jobDetailFactory.setDescription("Invoke E-Mail service...");
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }

    @Bean
    public SimpleTriggerFactoryBean trigger(JobDetail job) {
        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setJobDetail(job);
        trigger.setRepeatInterval(emailPeriod);
        trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        return trigger;
    }

    @Bean
    public SchedulerFactoryBean scheduler(Trigger trigger, JobDetail job, SpringBeanJobFactory factory) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();

        schedulerFactory.setJobFactory(factory);
        schedulerFactory.setJobDetails(job);
        schedulerFactory.setTriggers(trigger);
        return schedulerFactory;
    }

    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    private int getNumberOfJobsDone() {
        return count.get();
    }
}
