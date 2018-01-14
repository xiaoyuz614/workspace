package demo.rest;


import demo.service.LocationService;
import demo.domain.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//rest controller 返回的对象会被spring解析成json对象去返回
//如果只是@controller 那么返回一个String eg 返回"loginSuccess" 会被spring的viewResolver resolve to corresponding view
//比如变成 loginSuccess.html
//eg Spring 支持 Thymeleaf.html Apache tiles JSP JSF
//这个string相当于抽象  只要选择返回哪种view 形式 它自动帮你实现并返回

//restController的话 不会经过resolver 直接


public class RunningBulkUploadRestController {

    @Autowired
    private LocationService locationService;

    @RequestMapping(value="/running", method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)

    //加这个@requestBody才能使用json传数据
    public void upload(@RequestBody List<Location> locations){
        locationService.saveRunningLocations(locations);
    }

    @RequestMapping(value="/purge", method= RequestMethod.DELETE)

    public void purge(){
        this.locationService.deleteAll();
    }

    @RequestMapping(value="/running/{movementType}",method = RequestMethod.GET)
    public Page<Location> findByMovementType(@PathVariable String movementType, @RequestParam(name="page") int page, @RequestParam(name="size") int size){
          return this.locationService.findByRunnerMovementType(movementType,new PageRequest(page,size));
    }

    @RequestMapping(value = "/running/runningId/{runningId}",method = RequestMethod.GET)
    public Page<Location> findByRunningId(@PathVariable String runningId,@RequestParam(name="page") int page, @RequestParam(name="size") int size){
        return this.locationService.findByRunningId(runningId,new PageRequest(page,size));
    }

}
