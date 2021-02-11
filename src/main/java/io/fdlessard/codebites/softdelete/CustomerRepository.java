package io.fdlessard.codebites.softdelete;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "customers")
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

  List<Customer> findByLastName(@Param("lastName") String lastName);

  List<Customer> findByFirstName(@Param("firstName") String firstName);

  @Query(value = "select * from customer", nativeQuery = true)
  List<Customer> findAllIncludingDeleted();
}
