package io.samsorg.springbootstarter.dream;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DreamRepository extends CrudRepository<Dream, String> {
	
	
}
