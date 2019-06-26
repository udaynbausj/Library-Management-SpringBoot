package io.code.lms.Repositories;

import io.code.lms.Entities.BookIdScholarIdMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookIdScholarIdMappingDao extends JpaRepository<BookIdScholarIdMapping,Integer> {
    List<BookIdScholarIdMapping> findByBookId(Integer bookId);
    List<BookIdScholarIdMapping> findByScholarId(Integer scholarId);
    BookIdScholarIdMapping findByBookIdAndScholarId(Integer bookId,Integer scholarId);
}
