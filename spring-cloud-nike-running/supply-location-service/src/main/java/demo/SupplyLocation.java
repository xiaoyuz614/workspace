package demo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonInclude(JsonInclude.Include.NON_EMPTY)

@RequiredArgsConstructor(onConstructor = @__(@PersistenceConstructor))
@Data
//说明supply location是一个document
@Document

public class SupplyLocation {

    @Id
    private String id;
    private String address1;
    private String address2;
    private String city;

    //关于JsonIgnore:假设有经纬度数据 但没有location点数据 需要加
    //longitude:32.222
    //latitude:23.0001
    //没有这个
    //location:{x: y:}
    @JsonIgnore private final
    @GeoSpatialIndexed
    Point location;

    private String state;
    private String zip;
    private String type;

    @SuppressWarnings("unused")
    public SupplyLocation(){
        this.location=new Point(0.0,0.0);
    }
    //跟上面那个@RequiredArgsConstructor(onConstructor = @__(@PersistenceConstructor)) 是一样的东西
/*    public SupplyLocation(Point location){
        this.location=location;
    }*/

    @JsonCreator
    public SupplyLocation(@JsonProperty("latitude") double latitude, @JsonProperty("longitude") double longitude){
        this.location=new Point(longitude,latitude);
    }


    @SuppressWarnings("unused")
    public double getLatitude(){
        return this.location.getY();
    }

    @SuppressWarnings("unused")
    private double getLongitude(){
        return this.location.getX();
    }

}
