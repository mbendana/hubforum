package com.mhalton.hubforum.domain.topic;

import com.mhalton.hubforum.domain.CustValidationException;
import com.mhalton.hubforum.domain.topic.rec.TopicData;
import com.mhalton.hubforum.domain.topic.rec.TopicDetail;
import com.mhalton.hubforum.domain.topic.repo.TopicRepository;
import com.mhalton.hubforum.domain.topic.validation.TopicValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService
{
    private final TopicRepository repository;
    private final List<TopicValidator> validators;

    @Autowired
    public TopicService(TopicRepository repository, List<TopicValidator> validators)
    {
        this.repository = repository;
        this.validators = validators;
    }

    public TopicDetail postTopic(@Valid TopicData tData)
    {
        validators.forEach(validator -> validator.validate(tData));

        Topic topic = repository.save(new Topic(tData));

        return new TopicDetail(topic);
    }

    public Page<TopicDetail> getTopics(Pageable pageable)
    {
        return repository.findAllByStatusTrue(pageable).map(TopicDetail::new);
    }

    public TopicDetail getTopic(long id)
    {
//        return repository.findById(id).map(TopicDetail::new).orElseThrow(EntityNotFoundException::new);
        return new TopicDetail(repository.getReferenceById(id));
    }

    public TopicDetail updateTopic(long id, @Valid TopicData tData)
    {
        Topic topic = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Topic with id " + id + " not found"));

        validators.forEach(validator -> validator.validate(tData));

        topic.update(tData);

        return new TopicDetail(topic);
    }

    public void deleteTopic(long id)
    {
        repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Topic with id " + id + " not found"));

        repository.deleteById(id);
    }
}
