package cn.tedu.down;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Demo {

    public static String readLine(String path) throws IOException {
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
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
        return str;
    }

    public static void main(String[] args) throws IOException {
        String str = readLine("D:\\project\\down\\src\\file");
        String[] path = str.split("/r/n");
        new Multidownload("http://doc.tedu.cn/tts/" + "tts/jsd1811/01-Teaching/13-PROJECT/DAY04/DAY04-01-AM09-PROJECT-USER-PASSWORD-WEB.zip").start();
        new Multidownload("http://doc.tedu.cn/tts/" + "tts/jsd1811/01-Teaching/13-PROJECT/DAY04/DAY04-ALL-AM11.zip").start();
    }


}
