package com.example.demo.dao;

import com.example.demo.model.Route;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RouteDao {

    int insertRoute(UUID id, Route route );

    default int insertRoute(Route route) {
        UUID id = UUID.randomUUID();
        return insertRoute(id, route);
    }

    List<Route> selectAllRoutes();

    Optional<Route> selectRouteById(UUID id);
    int deleteRouteById(UUID id);

    int updateRouteById(UUID id, Route route);
}
