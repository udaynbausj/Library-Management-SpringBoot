package io.code.lms.Repositories;


import io.code.lms.Entities.Scholar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScholarCrudDao extends JpaRepository<Scholar,Integer> {

}
