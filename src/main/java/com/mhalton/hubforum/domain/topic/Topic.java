package com.mhalton.hubforum.domain.topic;

import com.mhalton.hubforum.domain.topic.rec.TopicData;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "topics")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String message;
    private LocalDateTime creationDate;
    private boolean status = true;
    private long authorId;
    private String courseName;

    public Topic(@Valid TopicData dTopic)
    {
        // JPA is supposed to handle the id field
        title = dTopic.title();
        message = dTopic.message();
        creationDate = LocalDateTime.now();
        // status value is handled when declaring the field
        authorId = dTopic.userId();
        courseName = dTopic.courseName();
    }

    public void delete()
    {
        status = false;
    }

    public void update(@Valid TopicData tData)
    {
        title = tData.title();
        message = tData.message();
        courseName = tData.courseName();
    }
}
