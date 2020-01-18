package it.world.service.timing.taskRun;


import it.world.service.timing.Schedule.CronSchedulerJob;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

/**
 * 项目启动时执行
 * 两种方式二选一
 */
//@Configuration
public class MyStartupRunner implements CommandLineRunner {

    @Autowired
    private CronSchedulerJob scheduleJobs;



    public void run(String... args) throws SchedulerException {
        System.out.println(">>>>>>>>>>>>>>>定时任务开始执行<<<<<<<<<<<<<");
        scheduleJobs.scheduleJobs();

    }
}
