package com.example.toolsserver.category;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;
import java.time.Instant;

//@Document(indexName = "tools", type = "category", shards = 1, replicas = 0)
@Entity
@Table(name = "category")
public class Category {
    @Id
    private Long id = 0L;
    @Version
    private Integer version = 0;
    private Instant createAt = Instant.EPOCH;
    private Instant updateAt = Instant.EPOCH;
    private String code = "";
    private String name = "";
    private Integer level = 0;
    private Integer parentId = 0;
    private Integer rootId = 0;
    private Boolean disabled = false;

}
