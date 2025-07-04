package edu.bk.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.bk.core.model.PingRequest;
import edu.bk.core.model.dto.ChatRequest;
import edu.bk.core.model.dto.ChatResponse;
import edu.bk.core.service.CoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "/home")
public class CoreController {

    @Autowired(required = false)
    private CoreService coreService;

    @PostMapping(path = "/ping-server")
    public String ping(@RequestBody PingRequest request){
        return coreService.pong(request);
    }

    @PostMapping(path = "/get-mission")
    public ChatResponse getMission(@RequestBody ChatRequest request) throws JsonProcessingException {
        return coreService.getChallenge(request);
    }

    @PostMapping(path = "/get-challenges")
    public List<ChatResponse> getMission(){
        return coreService.getAllChallenge();
    }
}