package demo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

//要有两个generic parameter : location & long
//因为spring data 在做查询转化的时候要根据上述类型转化
public interface LocationRepository extends JpaRepository<Location,Long>{

    Page<Location> findByRunnerMovementType(@Param("movementType") Location.RunnerMovementType movementType, Pageable pageable);

    //想通过RunningID来找 但是runningID在unitinfo底下
    Page<Location> findByUnitInfoRunningId(@Param("runningId") String runningId, Pageable pageable);


}
