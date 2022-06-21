package com.cscloi.card_db.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/games")
@OpenAPIDefinition(info = @Info(title = "Games Service"), servers = {@Server(url = "http://localhost:8080", description = "Local server.")})
public interface GameController {
}
