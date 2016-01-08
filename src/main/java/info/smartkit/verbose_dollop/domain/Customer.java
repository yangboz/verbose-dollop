package info.smartkit.verbose_dollop.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by yangboz on 1/6/16.
 *
 * @example: {"latitude": "52.986375", "user_id": 12, "name": "Christina McArdle", "longitude": "-6.043701"}
 * @more https://gist.github.com/brianw/19896c50afa89ad4dec3
 */

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    protected String latitude;
    protected int user_id;
    protected String name;
    protected String longitude;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
}
