package com.mhalton.hubforum.domain.topic.rec;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mhalton.hubforum.domain.topic.Topic;

import java.time.LocalDateTime;

public record TopicDetail(
        long id,
        String title,
        String message,
        @JsonProperty("creation_date")
        LocalDateTime creationDate
)
{
    public TopicDetail(Topic topic)
    {
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getCreationDate());
    }
}
