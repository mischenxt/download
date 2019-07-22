package cn.tedu.down;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ApplicationStartDemo {

    public static String readLine(String path) throws IOException {
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        StringBuilder sb = new StringBuilder();
        String str = null;
        while ((str = br.readLine()) != null) {
            if (!str.contains("tts")) {
                continue;
            }
            str =  str.substring(str.indexOf("tts"),str.lastIndexOf("\"}"));
            sb.append(str);
            sb.append("\r\n");
        }
        br.close();
        str = sb.toString();
        return str;
    }

    public static void main(String[] args) throws IOException {
        String str = readLine("file2");
        String[] path = str.split("\\r\\n");
        System.out.println(path.length);
        System.out.println(path[0]);
        for (int i = 0; i < path.length; i++) {
            new Multidownload( path[i]).start();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
