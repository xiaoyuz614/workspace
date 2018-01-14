package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@SpringBootApplication
@EnableScheduling
//spring帮做的抽象
//DiscoveryClient:我的service/application现在是一个能被发现的client
//要去 server side 注册
@EnableDiscoveryClient
public class SimulationServiceApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SimulationServiceApplication.class, args);
    }

    @Bean
    //既是一个executor又是一个scheduler
    public AsyncTaskExecutor taskExecutor(){
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);
        return scheduler;
    }
}
