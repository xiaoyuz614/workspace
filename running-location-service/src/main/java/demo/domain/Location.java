package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@JsonInclude
@Data
@Entity
@Table(name="LOCATION")

//Json String-->Java Object--> （JPA ORM）Relational DB
//db table: 一个table是一个class
//一个数据是其中一行

public class Location {
    enum GpsStatus {
        EXCELLENT, OK, UNRELIABLE, BAD, NOFIX, UNKNOWN;
    }

    public enum RunnerMovementType {
        STOPPED, IN_MOTION;

        public boolean isMoving() {
            return this != STOPPED;
        }
    }

    @Id
    @GeneratedValue
    private long id;
    //默认field是column  也可以自己标记改 eg. @column：lat

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "bfr", column = @Column(name = "medical_bfr")),
            @AttributeOverride(name = "fmi", column = @Column(name = "medical_fmi"))
    })

    private MedicalInfo medicalInfo;

    @Embedded
    @AttributeOverride(name = "bandMake", column = @Column(name = "unit_band_make"))

    private UnitInfo unitInfo;

    private double latitude;
    private double longitude;
    private String heading;
    private double gpsSpeed;
    private GpsStatus gpsStatus;

    private double odometer;
    private double totalRunningTime;
    private double totalIdleTime;
    private double totalCalorieBurnt;
    private String address;
    private Date timestamp = new Date();
    private RunnerMovementType runnerMovementType = RunnerMovementType.STOPPED;
    private String serviceType;

    //ORM: relational database 里面的table 要和Java里面的object对应
    //constructor
    public Location() {

    }

    @JsonCreator
    //通过哪个constructor来调用
    //property： 哪个property和runningI的相对应
    public Location(@JsonProperty("runningId") String runningId) {
        this.unitInfo=new UnitInfo(runningId);
    }

    public Location(UnitInfo unitInfo) {
        this.unitInfo=unitInfo;
    }

    //overwrite lombok自己生成的getter and setter
    //处理null 以防NullPointerException
    //自动调用这个

    public String getRunningId(){
        return  this.unitInfo==null? null : this.getRunningId();
    }


}
