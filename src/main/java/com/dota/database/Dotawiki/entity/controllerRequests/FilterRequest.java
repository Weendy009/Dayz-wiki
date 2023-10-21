package com.dota.database.Dotawiki.entity.controllerRequests;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class FilterRequest {
    private Map<String, List<String>> weightFilters;
    private Map<String, List<String>> tierFilters;
    private Integer weaponCategory;
}
