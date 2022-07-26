package com.stepanwxw.crudv2.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.stepanwxw.crudv2.model.Region;
import com.stepanwxw.crudv2.repository.implementation.RegionRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static java.io.File.separator;

public class RegionRepositoryImpl implements RegionRepository {
    final String fileRegions = "src" + separator + "main" + separator + "resources" + separator + "regions.json";
    @Override
    public Region create(Region region) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Region> regions = new ArrayList<>();
        Type itemsListType = new TypeToken<List<Region>>() {}.getType();
        try (FileReader fileReader = new FileReader(fileRegions)){
            List<Region> testNull;
            testNull = gson.fromJson(fileReader, itemsListType);
            if (testNull != null) {
                regions = testNull;
            }
        }catch (IOException e) {
            System.out.println(e);
        }
        try (FileWriter fileWriter = new FileWriter(fileRegions)){
            regions.add(region);
            gson.toJson(regions, fileWriter);
        } catch (IOException e) {
            System.out.println(e);
        }
        return region;
    }

    @Override
    public List<Region> getAll() {
        return null;
    }

    @Override
    public Region getByID(Long id) {
        return null;
    }

    @Override
    public Region update(Region region) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
