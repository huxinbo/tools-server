/*
package com.painting.tools.common;

import lombok.Data;
import org.springframework.util.Assert;

public class PaginationUtils {
    @Data
    public static class Pagination {
        private Integer limit;
        private Integer offset;
    }

    public static Pagination toPagination(Integer pageIndex, Integer pageSize) {
        Assert.isTrue(pageIndex != null && pageIndex >= 1, "Invalid pageIndex");
        Assert.isTrue(pageSize != null && pageSize > 0 && pageSize < 1000, "Invalid pageSize");

        Pagination pagination = new Pagination();
        pagination.limit = pageSize;
        pagination.offset = (pageIndex - 1) * pageSize;
        return pagination;
    }
}*/
