package com.mhalton.hubforum.domain.topic.validation;

import com.mhalton.hubforum.domain.CustValidationException;
import com.mhalton.hubforum.domain.topic.Topic;
import com.mhalton.hubforum.domain.topic.rec.TopicData;
import com.mhalton.hubforum.domain.topic.repo.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DuplicateTopicValidator implements TopicValidator
{
    private final TopicRepository topicRepository;

    @Autowired
    public DuplicateTopicValidator(TopicRepository topicRepository)
    {
        this.topicRepository = topicRepository;
    }

    @Override
    public void validate(TopicData tData)
    {
        Topic topic = topicRepository.findByTitleAndMessage(
                tData.title(), tData.message());

        if (topic != null)
        {
            throw new CustValidationException("Topic with title '" +
                                              topic.getTitle() +
                                              "' and message '" +
                                              topic.getMessage() +
                                              "' already exists!");
        }
    }
}
