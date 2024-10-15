package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ClasificacionJsonDAO implements Dao<Clasificacion, String> {
    public static final Path DEFAULT_PATH = Paths.get("src\\main\\resources");
    public static ClasificacionJsonDAO instance;
    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ClasificacionJsonDAO() {
    }

    public static ClasificacionJsonDAO getInstance() {
        if (instance == null)
            instance = new ClasificacionJsonDAO();
        return instance;
    }

    @Override
    public Clasificacion get(String id) {
        return null;
    }

    @Override
    public List<Clasificacion> getAll() {
        return List.of();
    }

    @Override
    public boolean save(Clasificacion obxecto) {
        Path completePath = Paths.get(String.valueOf(DEFAULT_PATH) + "\\" + obxecto.getCompeticion().toLowerCase().replace(' ','_') + ".json");

        if (Files.exists(completePath)) {
            try {
                Files.createFile(completePath);
            } catch (IOException e) {
                System.err.println("No se pudo crear archivo");
            }
        }

        try (var fw = new FileWriter(String.valueOf(completePath))) {
            gson.toJson(obxecto,fw);
        } catch (IOException e) {
            System.err.println("ERROR: save");
            return false;
        }

        return true;
    }

    @Override
    public boolean delete(Clasificacion obx) {
        try {
            Files.list(DEFAULT_PATH).forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }

    @Override
    public void update(Clasificacion obx) {

    }
}
