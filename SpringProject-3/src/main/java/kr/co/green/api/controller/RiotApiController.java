package kr.co.green.api.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.green.api.service.RiotApiService;

@RestController
@RequestMapping("/riot/api")
public class RiotApiController {

    private final RiotApiService riotApiService;

    public RiotApiController(RiotApiService riotApiService) {
        this.riotApiService = riotApiService;
    }

    @GetMapping("/league/entries/{summonerId}")
    public ResponseEntity<String> getLeagueEntries(@PathVariable("summonerId") String summonerId) {
        String response = riotApiService.getLeagueEntriesBySummonerId(summonerId);
        return ResponseEntity.ok(response);
    }
}
