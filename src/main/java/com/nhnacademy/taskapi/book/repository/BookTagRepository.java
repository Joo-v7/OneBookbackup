package com.nhnacademy.taskapi.book.repository;

import com.nhnacademy.taskapi.book.domain.BookTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookTagRepository extends JpaRepository<BookTag, Long> {
}
