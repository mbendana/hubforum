package com.mhalton.hubforum.domain.topic.validation;

import com.mhalton.hubforum.domain.topic.rec.TopicData;

public interface TopicValidator
{
    void validate(TopicData tData);
}
