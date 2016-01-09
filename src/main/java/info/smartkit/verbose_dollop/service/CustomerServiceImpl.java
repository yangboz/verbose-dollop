package info.smartkit.verbose_dollop.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.util.GeometricShapeFactory;
import info.smartkit.verbose_dollop.domain.Customer;
import info.smartkit.verbose_dollop.dto.CustomerDto;
import info.smartkit.verbose_dollop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yangboz on 1/7/16.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private static final String fileName = "customers.json";

    @Autowired
    private CustomerRepository customerRepository;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Iterable<Customer> populate() throws IOException, ParseException {

        //Get file from resources folder
//        ClassLoader classLoader = getClass().getClassLoader();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
//        System.out.println("CustomerDto file:" + file.toString());
        ObjectMapper objectMapper = new ObjectMapper();
        List<CustomerDto> customerDtoList = objectMapper.readValue(file, new TypeReference<List<CustomerDto>>() {
        });
        //
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        System.out.println("customerDtoList:" + customerDtoList);
        List<Customer> customerList = new ArrayList<Customer>();
        //
        for (CustomerDto customerDto : customerDtoList) {
            Customer customer = new Customer();
            customer.setName(customerDto.getName());
            customer.setLatitude(Double.parseDouble(customerDto.getLatitude()));
            customer.setLongitude(Double.parseDouble(customerDto.getLongitude()));
            customer.setUser_id(customerDto.getUser_id());
            customer.setGeoString("POINT(" + customerDto.getLatitude() + " " + customerDto.getLongitude() + ")");
            //
            customerList.add(customer);
        }
//        System.out.println("customerList:" + customerList);
        //
        //JPA saving...
//        System.out.println("customerRepository:" + customerRepository.toString());
        return customerRepository.save(customerList);
    }

    @Override
    public List<Customer> getNearby(double longitude, double latitude, double distance) {
        Iterable<Customer> customerList = customerRepository.findAll();
        Iterator<Customer> iterator = customerList.iterator();
        int sizeOfCustomer = 0;
        while (iterator.hasNext()) {
            iterator.next();
            sizeOfCustomer++;
        }
//        System.out.println("findAll(),customerList:" + customerList + ",size:" + sizeOfCustomer);
//create Geometry shape
        GeometricShapeFactory shapeFactory = new GeometricShapeFactory();
        shapeFactory.setNumPoints(sizeOfCustomer);
        shapeFactory.setCentre(new Coordinate(longitude, latitude));
        shapeFactory.setSize(distance);
        Geometry aroundDublinOfficeShape = shapeFactory.createCircle();
//findWithIn
        List<Customer> findWithIns =
                this.entityManager.createQuery(
                        "FROM Customer WHERE within(geometry, :shape) = true ORDER BY user_id",//sorted by user id (ascending).
                        Customer.class)
                        .setParameter("shape", aroundDublinOfficeShape)
                        .getResultList();
        System.out.println("!!!findWithIns(nearby):" + findWithIns.toString() + ",size:" + findWithIns.size());
        return findWithIns;
    }
}
