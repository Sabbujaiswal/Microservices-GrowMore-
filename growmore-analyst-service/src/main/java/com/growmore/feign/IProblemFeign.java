package com.growmore.feign;


import com.growmore.model.Farmer;
import com.growmore.model.Problem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "PROBLEM-SERVICE",url = "http://localhost:8082/problem-api")
public interface IProblemFeign {



    @GetMapping("/problems/id/{id}")
    Problem getAnalystById(@PathVariable("id") int id);

    @GetMapping("/problems")
    List<Problem> getAllProblems();

    @GetMapping("/problems/intensity/{intensity}")
    List<Problem> getProWithIntensity(@PathVariable("intensity") String intensity);

}
