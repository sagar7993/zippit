package com.zippit.zippitbackend.repositories;

import com.zippit.zippitbackend.beans.CommentBean;
import com.zippit.zippitbackend.entities.Comment;
import com.zippit.zippitbackend.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by akash.mercer on 04-Jun-17.
 */

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment, String> {

    @Query(value = "select new com.zippit.zippitbackend.beans.CommentBean(c.id, c.commentText, c.createdAt, c.user.id, c.user.name, c.user.profilePicUrl) from Comment c where c.post = :post")
    Page<CommentBean> findByPost(@Param("post") Post post, Pageable pageable);
}
