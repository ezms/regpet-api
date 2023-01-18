package com.regpet.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringJoiner;

public class SqlUtils {
    public static String readSql(String sqlFilePath) {
        StringJoiner sql = new StringJoiner("");

        try {
            File myObj = new File(sqlFilePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                sql.add(data);
            }
            myReader.close();
            return sql.toString();
        } catch (FileNotFoundException e) {
            System.err.println("Cannot read this file.");
            e.printStackTrace();
        }
        return "";
    }
}
