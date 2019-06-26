package io.code.lms.Repositories;

import io.code.lms.Entities.BookIdScholarIdMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookIdScholarIdMappingDao extends JpaRepository<BookIdScholarIdMapping,Integer> {
}
