package com.example.demo.api;

import com.example.demo.model.Route;
import com.example.demo.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/route")
@RestController
public class RouteController {

    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping
    public void addRoute(@Valid @NonNull @RequestBody Route route) {
        routeService.addRoute(route);
    }

    @GetMapping
    public List<Route> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    @GetMapping(path = "{id}")
    public Route getRouteById(@PathVariable("id") UUID id) {
        return routeService.getRouteById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteRouteById(@PathVariable("id") UUID id) {
        routeService.deleteRoute(id);
    }

    // We want use @Valid annotation to fail as soon as possible
    @PutMapping(path = "{id}")
    public void updateRoute(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Route routeToUpdate) {
        routeService.updateRoute(id, routeToUpdate);
    }
}
