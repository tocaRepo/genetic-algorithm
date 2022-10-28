package helper;

import algorithm.models.outputs.GAOutput;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHelper {

    public static void WriteGeneration(GAOutput output){
        // Java objects to File
        Gson gson = new Gson();
        File file = new File(".\\simulation\\generation_"+output.generationInfo.generationCount+".json");
        file.getParentFile().mkdirs();
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(output, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
