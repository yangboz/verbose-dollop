package info.smartkit.verbose_dollop.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by yangboz on 1/6/16.
 *
 * @example: {"latitude": "52.986375", "user_id": 12, "name": "Christina McArdle", "longitude": "-6.043701"}
 * @more https://gist.github.com/brianw/19896c50afa89ad4dec3
 */
@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue
    protected Long id;
    protected String latitude;
    protected int user_id;
    protected String name;
    protected String longitude;

}
