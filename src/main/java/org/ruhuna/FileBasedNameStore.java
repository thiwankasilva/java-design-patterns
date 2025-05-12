package org.ruhuna;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileBasedNameStore implements NameStore{

    private String filePath = "D:/Java basics/DesignPatterns/DesignPatterns/src/names.txt";

    @Override
    public void save(String name) {
        File nameFile = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(nameFile,true);
            fileWriter.append(name+"\n");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(fileWriter !=null)
                {
                    fileWriter.close();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public List<String> getNames() {
        try {
            return Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
