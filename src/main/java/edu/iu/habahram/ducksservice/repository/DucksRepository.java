package edu.iu.habahram.ducksservice.repository;

import edu.iu.habahram.ducksservice.model.DuckData;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class DucksRepository {

    private final DucksJpaRepository ducksJpaRepository;
    private String IMAGES_FOLDER_PATH = "ducks/images/";
    private String AUDIO_FOLDER_PATH = "ducks/audio/";

    public DucksRepository(DucksJpaRepository ducksJpaRepository) {
        this.ducksJpaRepository = ducksJpaRepository;
        File ducksImagesDirectory = new File("ducks/images");
        if (!ducksImagesDirectory.exists()) {
            ducksImagesDirectory.mkdirs();
        }
        File ducksAudioDirectory = new File("ducks/audio");
        if (!ducksAudioDirectory.exists()) {
            ducksAudioDirectory.mkdirs();
        }
    }

    public int add(DuckData duckData) {
        DuckData saved = ducksJpaRepository.save(duckData);
        return saved.id();
    }

    public boolean updateImage(int id, MultipartFile file) throws IOException {
        String fileExtension = ".png";
        Path path = Paths.get(IMAGES_FOLDER_PATH + id + fileExtension);
        file.transferTo(path);
        return true;
    }

    public boolean updateAudio(int id, MultipartFile file) throws IOException {
        String fileExtension = ".mp3";
        Path path = Paths.get(AUDIO_FOLDER_PATH + id + fileExtension);
        file.transferTo(path);
        return true;
    }

    public byte[] getImage(int id) throws IOException {
        Path path = Paths.get(IMAGES_FOLDER_PATH + id + ".png");
        return Files.readAllBytes(path);
    }

    public byte[] getAudio(int id) throws IOException {
        Path path = Paths.get(AUDIO_FOLDER_PATH + id + ".mp3");
        return Files.readAllBytes(path);
    }

    public List<DuckData> findAll() {
        return (List<DuckData>) ducksJpaRepository.findAll();
    }

    public DuckData find(int id) {
        return ducksJpaRepository.findById(id).orElse(null);
    }

    public List<DuckData> search(String type) {
        if (type != null) {
            return ducksJpaRepository.findByType(type);
        }
        return findAll();
    }
}