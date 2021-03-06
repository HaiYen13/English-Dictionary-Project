/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import main.Dictionary;

/**
 *
 * @author This PC
 */
public class Viet_Anh extends Dictionary{
    
    //Đọc databas từ tệp E_V.zip
    public Viet_Anh(){
        readFile();
    }
    
    
    String path = "Database/V_E.zip";
    /**
     * 
     */
    public void readFile() {
        FileInputStream file = null;
        ZipInputStream zipStream = null;
        ZipEntry entry = null;
        BufferedReader reader = null;

        try {
            file = new FileInputStream(path);
            zipStream = new ZipInputStream(file);
            entry = zipStream.getNextEntry();

            reader = new BufferedReader(new InputStreamReader(zipStream));

            String line, word, def;
            int wordsNum = 0;
            while ((line = reader.readLine()) != null) {
                //System.out.printf("%s\n----------------------\n", line);
                int index = line.indexOf("<html>");
                int index2 = line.indexOf("<ul>");

                if (index2 != -1 && index > index2) {
                    index = index2;
                }

                if (index != -1) {
                    word = line.substring(0, index);

                    word = word.trim();
                    keys2.add(word);

                    //word = word.toLowerCase();
                    def = line.substring(index);
                    //def = "<html>" + def + "</html>";

                    Word2.put(word, def);

                    wordsNum++;
                }
            }
            reader.close();

            System.out.println(wordsNum + " words");


        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }

    }
    
    
    //Viết lại file txt sau khi thêm, xóa hoặc sửa.
    public void reloadFile(){
        FileOutputStream file = null;
        ZipOutputStream zipStream = null;
        BufferedWriter writer = null;

        try {
            file = new FileOutputStream(path);
            zipStream = new ZipOutputStream(file);
            writer = new BufferedWriter(new OutputStreamWriter(zipStream));
            zipStream.putNextEntry(new ZipEntry(path.replace("./data/", "").replace("zip", "txt")));

            for (String key : keys2) {
                writer.write(key);
                String def = Word2.get(key);
                if (def != null) {
                    writer.write(Word2.get(key));
                }

                writer.newLine();
            }

            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
