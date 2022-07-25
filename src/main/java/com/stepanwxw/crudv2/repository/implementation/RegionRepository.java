package com.stepanwxw.crudv2.repository.implementation;

import com.stepanwxw.crudv2.model.Region;

import java.util.List;

public interface RegionRepository extends GenericRepository<Region, Long> {

    @Override
    Region create(Region region);

    @Override
    List<Region> getAll();

    @Override
    Region getByID(Long id);

    @Override
    Region update(Region region);

    @Override
    void remove(Long id);
}
