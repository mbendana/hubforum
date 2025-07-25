package com.mhalton.hubforum.domain.topic.rec;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record TopicData(
        @JsonProperty("user_id")
        @Positive
        long userId,
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotBlank
        @JsonProperty("course_name")
        String courseName
)
{
}
