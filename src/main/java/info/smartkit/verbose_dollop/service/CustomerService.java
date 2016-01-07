package info.smartkit.verbose_dollop.service;

import info.smartkit.verbose_dollop.domain.Customer;

import java.io.IOException;
import java.util.List;

/**
 * Created by yangboz on 1/7/16.
 */
public interface CustomerService {
    Iterable<Customer> populate() throws IOException;

    List<Customer> getNearby(long distance);
}
