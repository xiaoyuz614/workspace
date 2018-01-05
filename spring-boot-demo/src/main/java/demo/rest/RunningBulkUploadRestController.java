package demo.rest;


import demo.Service.impl.LocationService;
import demo.domain.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RunningBulkUploadRestController {

    @Autowired
    private LocationService locationService;
}
