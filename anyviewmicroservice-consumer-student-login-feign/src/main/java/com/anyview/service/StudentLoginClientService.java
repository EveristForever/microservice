package com.anyview.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "ANYVIEW-MICROSERVICE-STUDENT-LOGIN")
public interface StudentLoginClientService {

}
