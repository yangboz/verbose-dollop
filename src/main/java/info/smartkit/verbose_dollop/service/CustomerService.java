package info.smartkit.verbose_dollop.service;

import com.vividsolutions.jts.io.ParseException;
import info.smartkit.verbose_dollop.domain.Customer;

import java.io.IOException;

/**
 * Created by yangboz on 1/7/16.
 */
public interface CustomerService {
    Iterable<Customer> populate() throws IOException, ParseException;

    Iterable<Customer> getNearby(double longitude, double latitude, long distance);
}
