package helper;

import algorithm.models.outputs.GAOutput;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;

public class FileHelper {

    public static void WriteGeneration(GAOutput output){
        // Java objects to File
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(".\\generation_"+output.generationInfo.generationCount+".json")) {
            gson.toJson(output, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
