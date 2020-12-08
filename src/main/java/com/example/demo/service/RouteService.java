package com.example.demo.service;

import com.example.demo.dao.RouteDao;
import com.example.demo.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RouteService {

    // this is not instantiated ... we use annotations to instantiate beans
    // we can have multiple implementations of this interface
    // so we must have a way to distinguish between them
    // so we use @ Qualifier
    private final RouteDao routeDao;

    // this is a constructor
    // we are trying to use dependency injection
    // @ autowired is the way we inject (into the RouteDao interface)
    @Autowired
    public RouteService(@Qualifier("fakeDao") RouteDao routeDao) {
        this.routeDao = routeDao;
    }

    public int addRoute(Route route) {
        return routeDao.insertRoute(route);
    }

    public List<Route> getAllRoutes() {
        return routeDao.selectAllRoutes();
    }

    public Optional<Route> getRouteById(UUID id) {
        return routeDao.selectRouteById(id);
    }

    public int deleteRoute(UUID id) {
        return routeDao.deleteRouteById(id);
    }

    public int updateRoute(UUID id, Route newRoute) {
        return routeDao.updateRouteById(id, newRoute);
    }
}
