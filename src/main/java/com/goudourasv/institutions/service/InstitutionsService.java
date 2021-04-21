package com.goudourasv.institutions.service;

import com.goudourasv.institutions.domain.Institution;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InstitutionsService {
    Map<UUID,String> institutionsMap = new HashMap<>();

    public InstitutionsService(){
        Institution Stanford = new Institution(UUID.randomUUID(),"stanford");
        Institution AUTH = new Institution(UUID.randomUUID(),"auth");
        Institution Buddha = new Institution(UUID.randomUUID(),"budhha");
        institutionsMap.put(Stanford.getId(),"stanford");
        institutionsMap.put(AUTH.getId(),"auth");
        institutionsMap.put(Buddha.getId(),"Buddha");


    }
}
