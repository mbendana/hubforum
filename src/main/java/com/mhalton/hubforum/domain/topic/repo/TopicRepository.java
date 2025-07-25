package com.mhalton.hubforum.domain.topic.repo;

import com.mhalton.hubforum.domain.topic.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long>
{
    Page<Topic> findAllByStatusTrue(Pageable pageable);

    Topic findByTitleAndMessage(String title, String message);
}
