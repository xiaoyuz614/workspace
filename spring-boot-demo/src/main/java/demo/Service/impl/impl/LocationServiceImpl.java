package demo.Service.impl.impl;

import demo.Service.impl.LocationService;
import demo.domain.Location;
import demo.domain.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Dependency Injection： bean 和 bean之间的依赖
 * eg: locationServiceImpl要依赖于LocationRepository的实例
 *
 * 我们需要这个实例的时候，不需要关心怎么创建的
 * spring container会帮我们注入进来我们可以直接使用
 * 注意必须是spring container管理的bean
 * 要加 @Service这个annotation 不加注入不成功
 *
 * If dependency injection is used, then the class B is given to class A via
 * field injection
 * the constructor of the class A - this is then called construction injection
 * a setter - this is then called setter injection :
 */

@Service
public class LocationServiceImpl implements LocationService {

    //看上去是一个field 实际注入的是一个instance， 可以直接调用locationRepository
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<Location> saveRunningLocations(List<Location> runningLocations) {
        return locationRepository.save(runningLocations);
    }

    @Override
    public void deleteAll() {
        locationRepository.deleteAll();
    }

    @Override
    public Page<Location> findByRunnerMovementType(String movementType, Pageable pageable) {
        //要通过valueOf转化成enum
        return locationRepository.findByRunnerMovementType(Location.RunnerMovementType.valueOf(movementType),pageable);
    }

    @Override
    public Page<Location> findByRunningId(String runningId, Pageable pageable) {
        //可以加一些validation
        return locationRepository.findByUnitInfoRunningId(runningId, pageable);
    }
}







