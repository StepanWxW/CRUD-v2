package com.stepanwxw.crudv2.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.stepanwxw.crudv2.directory.JsonDirectory;
import com.stepanwxw.crudv2.exception.NoEntityException;
import com.stepanwxw.crudv2.model.Region;
import com.stepanwxw.crudv2.repository.implementation.RegionRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class RegionRepositoryImpl implements RegionRepository {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Region create(Region region) {
        List<Region> regionsCreate = getAll();
        Region regionCreate = regionsCreate.stream().max(Comparator.comparing(Region::getId))
                .orElse(new Region(0L));
        long id = regionCreate.getId() + 1L;
        regionsCreate.add(new Region(id, region.getName()));
        writeFile(regionsCreate);
        return region;
    }

    @Override
    public List<Region> getAll() {
        List<Region> regions = new ArrayList<>();
        Type itemsListType = new TypeToken<List<Region>>() {}.getType();
        try (FileReader fileReader = new FileReader(JsonDirectory.REGION)) {
            List<Region> testNull = gson.fromJson(fileReader, itemsListType);
            if (testNull != null) {
                regions = testNull;
            }
        } catch (IOException e) {
            System.out.println("Problem with fileReader" + e);
        }
        return regions;
    }

    @Override
    public Region getByID(Long id) {
        try {
            return getAll().stream()
                    .filter(r -> id.equals(r.getId()))
                    .findFirst().orElseThrow(() ->new NoEntityException(id));
        } catch (NoEntityException e) {
            System.out.println("This object is missing");
        }
        return null;
    }

    @Override
    public Region update(Region region) {
        List<Region> regionsListUpdate = getAll();
        regionsListUpdate = regionsListUpdate.stream()
                .filter((p) -> !Objects.equals(p.getId(), region.getId()))
                .collect(Collectors.toList());
        regionsListUpdate.add(region);
        regionsListUpdate = regionsListUpdate.stream().
                sorted(((o2, o1) -> -o1.getId().compareTo(o2.getId()))).
                collect(Collectors.toList());
        writeFile(regionsListUpdate);
        return region;
    }

    @Override
    public void remove(Long id) {
        List<Region> regionsRemove = getAll().stream()
                .filter((p) -> !Objects.equals(p.getId(), id))
                .collect(Collectors.toList());
        writeFile(regionsRemove);
    }

    private void writeFile(List<Region> regionList) {
        try (FileWriter fileWriter = new FileWriter(JsonDirectory.REGION)) {
            gson.toJson(regionList, fileWriter);
        } catch (IOException e) {
            System.out.println("Problem with fileWriter" + e);
        }
    }
}

