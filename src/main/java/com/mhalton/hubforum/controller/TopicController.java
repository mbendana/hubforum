package com.mhalton.hubforum.controller;

import com.mhalton.hubforum.domain.topic.TopicService;
import com.mhalton.hubforum.domain.topic.rec.TopicData;
import com.mhalton.hubforum.domain.topic.rec.TopicDetail;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topics")
public class TopicController
{
    //    private final TopicRepository topicRepository;
    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService)
    {
        this.topicService = topicService;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<TopicDetail> postTopic(
            @RequestBody @Valid
            TopicData tData,
            UriComponentsBuilder uriBuilder)
    {
//        Topic topic = topicRepository.save(new Topic(tData));

        TopicDetail tDetail = topicService.postTopic(tData);

        URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(tDetail.id()).toUri();

        return ResponseEntity.created(uri).body(tDetail);
    }

    @GetMapping
    public ResponseEntity<Page<TopicDetail>> getTopics(@PageableDefault(sort = {"id"}) Pageable pageable)
    {
        Page<TopicDetail> topics = topicService.getTopics(pageable);

        return ResponseEntity.ok(topics);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDetail> getTopic(@PathVariable long id)
    {
        return ResponseEntity.ok(topicService.getTopic(id));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<TopicDetail> updateTopic(
            @PathVariable long id,
            @Valid @RequestBody TopicData tData)
    {
        return ResponseEntity.ok().body(topicService.updateTopic(id, tData));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deleteTopic(@PathVariable long id)
    {
        topicService.deleteTopic(id);
    }
}
