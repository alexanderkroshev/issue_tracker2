package com.example.demo.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public  interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value ="SELECT * FROM comment WHERE issue_id =:id", nativeQuery = true)
    @Transactional(readOnly = true)
    List<Comment> findCommentsByIssueId(@Param("id") Long id);
}
