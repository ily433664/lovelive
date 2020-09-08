package com.lovelive.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TxtFileTest {

    public static void main(String[] args) {
        try {
            Map<String, Object> map = new LinkedHashMap<>();

            List<String[]> dataList = getDataList("E:\\test\\NatureClassAndDormitory.txt");

            //Specialty specialty = null;
            int i = 1;
            for (String[] ss : dataList) {

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static List<String[]> getDataList(String filePath) {
        BufferedReader br = null;
        List<String[]> resultList = new ArrayList<String[]>();
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
            String s = br.readLine();
            while (s != null) {
                String[] ss = s.split("\t");
                resultList.add(ss);
                s = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

}
