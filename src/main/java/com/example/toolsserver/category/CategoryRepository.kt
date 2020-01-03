package com.example.toolsserver.category

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface CategoryRepository : ElasticsearchRepository<Category, Long>