package com.anyview.service;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "ANYVIEW-MICROSERVICE-STUDENT-LOGIN")
public interface StudentLoginClientService {

}
