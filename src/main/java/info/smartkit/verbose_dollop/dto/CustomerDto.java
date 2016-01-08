package info.smartkit.verbose_dollop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by yangboz on 1/8/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDto {
    protected String latitude;
    protected int user_id;
    protected String name;
    protected String longitude;
}
