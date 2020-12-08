package com.example.demo.dao;

import com.example.demo.model.Route;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// how we let spring know this is suppose to be instantiated as a bean
// the "fakeDao" part allows us to have multiple implementations
// fakeDao can be mongo instead, or postgres
@Repository("fakeDao")
public class FakeRouteDataAccessService implements RouteDao {

    private static List<Route> DB = new ArrayList<>();

    @Override
    public int insertRoute(UUID id, Route route) {
        DB.add(new Route(id, route.getName()));
        // return 1 so we know that it always works. returning 0 would mean that it doesn't work
        return 1;
    }

    @Override
    public List<Route> selectAllRoutes() {
        return DB;
    }

    @Override
    public Optional<Route> selectRouteById(UUID id) {
        return DB.stream().filter(route -> route.getId().equals(id)).findFirst();
    }

    @Override
    public int deleteRouteById(UUID id) {
        Optional<Route> routeMaybe = selectRouteById(id);
        if(routeMaybe.isEmpty()) {
            return 0;
        }
        DB.remove(routeMaybe.get());
        return 1;
    }

    @Override
    public int updateRouteById(UUID id, Route route) {
        return selectRouteById(id)
                .map(r -> {
                    int indexOfRouteToUpdate = DB.indexOf(r);
                    if (indexOfRouteToUpdate >= 0) {
                        DB.set(indexOfRouteToUpdate, new Route(id, route.getName()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
