package info.smartkit.verbose_dollop.domain;


import com.vividsolutions.jts.io.ParseException;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by yangboz on 1/6/16.
 *
 * @example: {"latitude": "52.986375", "user_id": 12, "name": "Christina McArdle", "longitude": "-6.043701"}
 * @more https://gist.github.com/brianw/19896c50afa89ad4dec3
 */

@Entity
//@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer {

    @Setter
    @Getter
    protected String latitude;
    @Setter
    @Getter
    protected int user_id;
    @Setter
    @Getter
    protected String name;
    @Setter
    @Getter
    protected String longitude;
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(generator = "increment")
//    @GenericGenerator(name = "increment", strategy = "increment")
    @GeneratedValue
    protected Long id;

    //
//    @Setter
//    @Getter
//    @JsonIgnore
//    @Column(columnDefinition = "Geometry", nullable = true)
//    @Type(type = "org.hibernate.spatial.GeometryType")
//    protected Geometry geometry;
//    protected Point geometry;

    @Getter
    protected String geoString;

    public void setGeoString(String geoString) throws ParseException {
        this.geoString = geoString;
        //@see: https://thespatialperspective.wordpress.com/2015/06/20/spring-boot-jpa-and-hibernate-spatial/#comment-18
//        WKTReader wktReader = new WKTReader();
//        Geometry geometry = wktReader.read(geoString);//'POINT(-105 40)','POLYGON((-107 39, -102 39, -102 41, -107 41, -107 39))'
//        this.setGeometry(geometry);
    }
}
