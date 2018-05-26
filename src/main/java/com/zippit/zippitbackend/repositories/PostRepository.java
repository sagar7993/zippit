package com.zippit.zippitbackend.repositories;

import com.zippit.zippitbackend.beans.PostBean;
import com.zippit.zippitbackend.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by akash.mercer on 04-Jun-17.
 */

@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, String> {

    @Transactional
    @Query(value = "select new com.zippit.zippitbackend.beans.PostBean(p.id , p.postText, p.postImageUrl, p.commentCount, p.upvoteCount, p.downvoteCount, p.createdAt, p.user.id, p.user.name, p.user.profilePicUrl) from Post p")
    Page<PostBean> fetchAll(Pageable pageable);
    
}
