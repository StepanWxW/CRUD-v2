package com.stepanwxw.crudv2.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.stepanwxw.crudv2.model.Region;
import com.stepanwxw.crudv2.repository.implementation.RegionRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

import static java.io.File.separator;

public class RegionRepositoryImpl implements RegionRepository {
    final String fileRegions = "src" + separator + "main" + separator + "resources" + separator + "regions.json";
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    List<Region> regions = new ArrayList<>();
    Region region;
     public Long generateId() {
         long id = 1L;
        Optional<Region> optGenerateId = regions.stream().max(Comparator.comparing(Region::getId));
        optGenerateId.ifPresent(value -> region = value);
        if (region != null) {
            id = region.getId()+ 1L;
        }
        return id;
    }
    private Region returnId(Long id) {
       Optional<Region> optReturnId = regions.stream().filter((s) -> Objects.equals(s.getId(), id)).findFirst();
        optReturnId.ifPresent(value -> region = value);
       return region;
    }
    private void openFile() {
        Type itemsListType = new TypeToken<List<Region>>() {}.getType();
        try (FileReader fileReader = new FileReader(fileRegions)){
            List<Region> testNull;
            testNull = gson.fromJson(fileReader, itemsListType);
            if (testNull != null) {
                regions = testNull;
            }
        }catch (IOException e) {
            System.out.println("Problem with fileReader" + e);
        }
    }
    private void writeFile() {
        try (FileWriter fileWriter = new FileWriter(fileRegions)){
            gson.toJson(regions, fileWriter);
        } catch (IOException e) {
            System.out.println("Problem with fileWriter" + e);
        }
    }
    @Override
    public Region create(Region region) {
        openFile();
        regions.add(new Region(generateId(), region.getName()));
        writeFile();
        return region;
    }

    @Override
    public List<Region> getAll() {
        openFile();
        return regions;
    }

    @Override
    public Region getByID(Long id) {
        openFile();
        return returnId(id);
    }

    @Override
    public Region update(Region region) {
        openFile();
        Region regionNew = returnId(region.getId());
        if (regionNew == null){
            regions.add(region);
        }
        else {
            regions.remove(regionNew);
            regions.add(region);;
            regions = regions.stream().sorted(((o2, o1) -> -o1.getId().compareTo(o2.getId()))).collect(Collectors.toList());
        }
        writeFile();
        return region;
    }

    @Override
    public void remove(Long id) {
        openFile();
        regions.remove(returnId(id));
        writeFile();
    }
}
