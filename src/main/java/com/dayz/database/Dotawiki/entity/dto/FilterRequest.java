package com.dayz.database.Dotawiki.entity.dto;

import lombok.Data;

import java.util.Set;

@Data
public class FilterRequest {
    private Set<String> selectedFilters;
    private Integer itemCategory;
}
