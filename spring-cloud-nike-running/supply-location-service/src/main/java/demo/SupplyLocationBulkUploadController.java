package demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SupplyLocationBulkUploadController {

    //constructor dependency injection
    //想注入repository
    private SupplyLocationRepository repository;

    @Autowired
    public SupplyLocationBulkUploadController(SupplyLocationRepository repository){
        this.repository=repository;
    }

    @RequestMapping(value = "/bulk/supplyLocations",method = RequestMethod.POST)

    @ResponseStatus(HttpStatus.CREATED)
    public  void upload(@RequestBody List<SupplyLocation> locations){
        this.repository.save(locations);
    }
    @RequestMapping(value ="/purge", method=RequestMethod.POST)
    public void purge(){
        this.repository.deleteAll();
    }
}
