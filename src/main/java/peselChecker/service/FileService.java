package peselChecker.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import peselChecker.model.Pesel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileService {
    public List<String> convertFileToStringList(String pathString) {
        Path path = Paths.get(pathString);
        List<String> lines = Collections.emptyList();

        try {
            lines = Files.readAllLines(path,StandardCharsets.UTF_8);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
    public void printList(List<String> list){
        for (String s : list) {
            System.out.println(s);
        }
    }
    public void writeJSONToFile(String path, List<Pesel> pesels){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(path), pesels);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
