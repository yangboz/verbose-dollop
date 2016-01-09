package info.smartkit.verbose_dollop.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.vividsolutions.jts.io.ParseException;
import info.smartkit.verbose_dollop.domain.Customer;
import info.smartkit.verbose_dollop.dto.CustomerDto;
import info.smartkit.verbose_dollop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangboz on 1/7/16.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private static final String fileName = "customers.json";

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Iterable<Customer> populate() throws IOException, ParseException {

        //Get file from resources folder
//        ClassLoader classLoader = getClass().getClassLoader();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        System.out.println("CustomerDto file:" + file.toString());
        ObjectMapper objectMapper = new ObjectMapper();
        List<CustomerDto> customerDtoList = objectMapper.readValue(file, new TypeReference<List<CustomerDto>>() {
        });
        //
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        System.out.println("customerDtoList:" + customerDtoList);
        List<Customer> customerList = new ArrayList<Customer>();
        //
        for (CustomerDto customerDto : customerDtoList) {
            Customer customer = new Customer();
            customer.setName(customerDto.getName());
            customer.setLatitude(customerDto.getLatitude());
            customer.setLongitude(customerDto.getLongitude());
            customer.setUser_id(customerDto.getUser_id());
            customer.setGeoString("POINT(" + customerDto.getLatitude() + " " + customerDto.getLongitude() + ")");
            //
            customerList.add(customer);
        }
        System.out.println("customerList:" + customerList);
        //
        //JPA saving...
//        System.out.println("customerRepository:" + customerRepository.toString());
        return customerRepository.save(customerList);
    }

    @Override
    public List<Customer> getNearby(long distance) {
        return null;
    }
}
