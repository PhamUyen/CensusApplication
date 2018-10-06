package com.uyenpham.censusapplication.utils;

import android.content.Context;

import com.uyenpham.censusapplication.models.locality.ReligionDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    private static List<String[]> getContentFromAsset(String fileName, Context context){
        List<String[]> csvLine = new ArrayList<>();
        String[] content = null;
        try {
            InputStream inputStream =  context.getAssets().open(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = br.readLine()) != null){
                content = line.split(",");
                csvLine.add(content);
            }
            br.close();
        } catch (IOException e) {
            Logger.e(e);
        }
        return csvLine;
    }

    public static List<ReligionDTO> getListReligion(Context context){
        ArrayList<ReligionDTO> listReligion = new ArrayList<>();
        List<String[]> csvLine = getContentFromAsset("DMTG.xlsx",context);
        for(int i =0; i< csvLine.size(); i++){
            String[] row = csvLine.get(i);
            ReligionDTO religionDTO = new ReligionDTO(Integer.parseInt(row[0]), row[1]);
            listReligion.add(religionDTO);
        }
        return listReligion;
    }
}
