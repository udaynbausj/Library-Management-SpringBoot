package io.code.lms.Repositories;

import io.code.lms.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCrudDao extends JpaRepository<Book,Integer> {
}
