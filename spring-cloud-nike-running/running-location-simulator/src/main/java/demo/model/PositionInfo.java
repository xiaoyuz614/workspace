package demo.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

//速度
//当前起始点的距离
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class PositionInfo {

    private String runningId;
    private Point position;
    private RunnerStatus runnerStatus = RunnerStatus.NONE;

    private Leg leg;

    private Double distanceFromStart;

    /**
     * The speed in m/s
     */
    private Double speed;

}
