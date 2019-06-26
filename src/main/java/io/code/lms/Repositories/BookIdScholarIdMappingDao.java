package io.code.lms.Repositories;

import io.code.lms.Entities.BookIdScholarIdMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookIdScholarIdMappingDao extends JpaRepository<BookIdScholarIdMapping,Integer> {
    BookIdScholarIdMapping findByBookId(Integer bookId);
    BookIdScholarIdMapping findByScholarId(Integer scholarId);
}
