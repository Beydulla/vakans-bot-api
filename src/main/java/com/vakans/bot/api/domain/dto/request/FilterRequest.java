package com.vakans.bot.api.domain.dto.request;

import lombok.Data;

@Data
public class FilterRequest {

    private long id;
    private String tags;
    private int minimumSalary;
    private int maximumSalary;
    private String employer;
    private long userId;

}
