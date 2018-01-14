package demo.service.impl;

import demo.model.CurrentPosition;
import demo.service.PositionService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//lombok log
@Slf4j

//标记成为一个service spring在启动的时候才能进行component scan
//才能 pick up defaultPositionService放到spring container里面
//这样别的spring bean 要用它做dependency injection的时候才能找到


@Service

//autowired注入最好注入一个Interface
//直接注入implementation class  就和implementation耦合在一起了  不好
//implementation class里面可能有些public Method  被你注入后可能这些method会被expose到bean里面

public class DefaultPositionService implements PositionService {

    //如果没有上面@Slf4j 就要写这一行

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultPositionService.class);

    //在spring里发送rest请求 要生成一个restTemplate
    private RestTemplate restTemplate = new RestTemplate();

    //POST to localhost:9006/api/locations body currentPosition
    //@Value("${com.xiaoyuz.running.location.distribution}")
    //dependency injection的value
    //在application.yml里定义
    //private String runningLocationDistribution;

    public DefaultPositionService() {
        super();
    }

    @Override
    public void processPositionInfo(long id, CurrentPosition currentPosition,
                                    boolean sendPositionsToDistributionService) {

        String runningLocationDistribution = "http://running-location-distribution";
        //发送之前先打log
        //接收同样应该有对应的log
        if (sendPositionsToDistributionService) {
            log.info(String
                    .format("Thread %d Simulator is callling distribution REST API", Thread.currentThread().getId()));
            this.restTemplate.postForLocation(runningLocationDistribution + "/api/locations", currentPosition);

        }

    }

    public void processPositionInfoFallback(long id, CurrentPosition currentPosition,
                                            boolean sendPositionsToDistributionService) {
        //如果没有上面@Slf4j 就要写这一行
        LOGGER.error("Hystrix Fallback Method. Unable to send message for distribution.");
        //log.error("Hystrix Fallback Method. Unable to send message for distribution.");
    }
}
