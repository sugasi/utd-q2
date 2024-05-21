package com.utd.q2.service.reload.scheduler;


import com.utd.q2.service.reload.ReloadService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@AllArgsConstructor
public class ReloadScheduler {

    private final ReloadService reloadService;

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.HOURS)
    public void reload() {
        log.info("#######################Reload Scheduler started#######################");
        reloadService.reload();
        log.info("#######################Reload Scheduler finished#######################");
    }
}
