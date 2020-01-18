package it.world.service.timing.taskRun;


import it.world.service.timing.Schedule.CronSchedulerJob;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 定时执行
 * 两种方式二选一
 */
//@Configuration
public class SchedulerListener {
    @Autowired
    public CronSchedulerJob scheduleJobs;

    @Scheduled(cron="0/5 * * * * ?")
    @Async
    public void schedule() throws SchedulerException {
        System.out.println(">>>>>>>>>>>>>>>定时任务开始执行<<<<<<<<<<<<<");
        System.out.println(System.currentTimeMillis()+":1111111111111111");
        //scheduleJobs.scheduleJobs();
        //readCsv.readCSV();
    }

    @Scheduled(cron="0/10 * * * * ?")
    @Async
    public void schedule2() throws SchedulerException {
        System.out.println(">>>>>>>>>>>>>>>定时任务开始执行<<<<<<<<<<<<<");
        System.out.println(System.currentTimeMillis()+":2222222222222222");
        //scheduleJobs.scheduleJobs();
        //readCsv.readCSV();
    }
}
/**
 *   @Async  开启多线程
 *  任务1和任务2一起处理，但是线程1卡死了，任务2是可以正常执行的。
 *  且下个周期，任务1还是会正常执行，不会因为上一次卡死了，影响任务1。
 *  但是任务1中的卡死线程越来越多，会导致50个线程池占满，还是会影响到定时任务
*/

/**cron常用规则
 "30 * * * * ?" 每半分钟触发任务
 "30 10 * * * ?" 每小时的10分30秒触发任务
 "30 10 1 * * ?" 每天1点10分30秒触发任务
 "30 10 1 20 * ?" 每月20号1点10分30秒触发任务
 "30 10 1 20 10 ? *" 每年10月20号1点10分30秒触发任务
 "30 10 1 20 10 ? 2011" 2011年10月20号1点10分30秒触发任务
 "30 10 1 ? 10 * 2011" 2011年10月每天1点10分30秒触发任务
 "30 10 1 ? 10 SUN 2011" 2011年10月每周日1点10分30秒触发任务
 "15,30,45 * * * * ?" 每15秒，30秒，45秒时触发任务
 "15-45 * * * * ?" 15到45秒内，每秒都触发任务
 "0/10 * * * * ?"  每10秒触发一次
 "15/5 * * * * ?" 每分钟的每15秒开始触发，每隔5秒触发一次
 "15-30/5 * * * * ?" 每分钟的15秒到30秒之间开始触发，每隔5秒触发一次
 "0 0/3 * * * ?" 每小时的第0分0秒开始，每三分钟触发一次
 "0 15 10 ? * MON-FRI" 星期一到星期五的10点15分0秒触发任务
 "0 15 10 L * ?" 每个月最后一天的10点15分0秒触发任务
 "0 15 10 LW * ?" 每个月最后一个工作日的10点15分0秒触发任务
 "0 15 10 ? * 5L" 每个月最后一个星期四的10点15分0秒触发任务
 "0 15 10 ? * 5#3" 每个月第三周的星期四的10点15分0秒触发任务
 */
