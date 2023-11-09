package com.dota.database.Dotawiki.entity.controllerRequests;

import lombok.Data;

import java.util.Set;

@Data
public class FilterRequest {
    private Set<String> selectedFilters;
    private Integer itemCategory;
}
