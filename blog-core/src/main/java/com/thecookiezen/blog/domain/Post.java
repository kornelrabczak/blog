package com.thecookiezen.blog.domain;

import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Post {

    @Id
    private String id;

    private String title;

    private String lead;

    private String body;

    private DateTime created;

    private DateTime modified;
}
