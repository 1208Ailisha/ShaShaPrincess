package com.ailisha.cmsweb.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hspcadmin on 2017/12/4.
 */
@Component
public class SchedulerTask {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //@Scheduled(cron="*/6 * * * * ?")
    //@Scheduled(cron="0 0/1 * * * ?")
    @Scheduled(cron="0 0 0/1 * * ?")//0 0 0/1 * * ?
    private void process(){
        System.out.println("现在时间：" + dateFormat.format(new Date()));
    }
}
