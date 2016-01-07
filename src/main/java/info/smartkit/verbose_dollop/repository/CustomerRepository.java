package info.smartkit.verbose_dollop.repository;

import info.smartkit.verbose_dollop.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yangboz on 1/7/16.
 */
@Repository
//public interface CustomerRepository extends JpaRepository<Customer, Long> {
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
