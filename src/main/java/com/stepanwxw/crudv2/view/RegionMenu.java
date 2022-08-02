package com.stepanwxw.crudv2.view;

import com.stepanwxw.crudv2.directory.MenuDirectory;
import com.stepanwxw.crudv2.model.Region;
import com.stepanwxw.crudv2.repository.NoEntityException;
import com.stepanwxw.crudv2.repository.RegionRepositoryImpl;

import java.util.Scanner;

public class RegionMenu {
    RegionRepositoryImpl regionRepository = new RegionRepositoryImpl();
    private String scanLine(){
       return new Scanner(System.in).nextLine();
    }
    public void menu() {
        System.out.println(MenuDirectory.MAIN_MENU);
        switch (scanLine()) {
            case ("1"): create(); break;
            case ("2"): readAll(); break;
            case ("3"): readId(); break;
            case ("4"): deleteId(); break;
            case ("5"): updateId(); break;
        }
    }
    private void create() {
        System.out.println("Enter Region please:");
        regionRepository.create(new Region(scanLine()));
        System.out.println("Congratulation: create is complete.");
    }
    private void readAll() {
        String[] words = regionRepository.getAll().toString().split("},");
        for (String word : words) {
            System.out.println(word);
        }
    }
    private void readId() {
        System.out.println("Enter id region please:");
        try {
            Long readId = Long.valueOf(scanLine());
            System.out.println(regionRepository.getByID(readId));
        } catch (NumberFormatException e) {
            System.out.println("Input number please");
        }
            }
    private void deleteId() {
        System.out.println("Enter id to delete region please:");
        try {
            Long readId = Long.valueOf(scanLine());
                regionRepository.remove(readId);
        } catch (NumberFormatException e) {
            System.out.println("Input number please");
        }
    }
    private void updateId() {
        System.out.println("Enter id to update region please:");
        try {
            Long readId = Long.valueOf(scanLine());
            if(regionRepository.getByID(readId) != null) {
                System.out.println("Enter please new region for id: " +readId);
                regionRepository.update(new Region(readId, scanLine()));
            }
        } catch (NumberFormatException e) {
            System.out.println("Input number please");
        }
    }
}
