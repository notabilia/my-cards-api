package com.notabilia.mycards.controllers;

import com.notabilia.mycards.models.Ping;
import com.notabilia.mycards.services.PingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/1.0")
@Api(tags = {
        "Ping Controller"
})
class PingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PingController.class);

    private final PingService service;

    @Autowired
    PingController(final PingService service) {
        this.service = service;
    }

    @GetMapping(path = "/ping", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Perform health-check", response = Ping.class)
    @ApiResponse(code = 200, message = "[Successful] Request OK, system is healthy")
    ResponseEntity<Ping> getPing() {
        LOGGER.info("PingController getPing()");

        return new ResponseEntity<>(this.service.getPing(), HttpStatus.OK);
    }
}
