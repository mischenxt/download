package cn.tedu.down;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ParseJsonDemo {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/file2"));
            StringBuilder sb = new StringBuilder();
            String str = null;
            while ((str = br.readLine()) != null) {
                if (!str.contains("tts")) {
                    continue;
                }
                sb.append(str);
                sb.append("\r\n");
            }
            br.close();
            str = sb.toString();


        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }


}
