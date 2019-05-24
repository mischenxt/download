package cn.tedu.down;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class Multidownload {

    static int ThreadCount = 3;   //�̵߳ĸ���
    String path;  //ȷ�����ص�ַ
    String filename;//ȷ���ļ���
    String filepathname;//ȷ���ļ�·��

    public Multidownload(String path) {
        this.path = path;
        this.filename = path.substring(path.lastIndexOf("/") + 1);
        this.filepathname = path.substring(0, path.lastIndexOf("/") + 1);
    }

    public void start() {

        //����get�������������ַ����Դ
        try {
            if (path == null) {
                throw new NULLURLException("urlΪ��");
            }
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            if (conn.getResponseCode() == 200) {
                //��ȡ��������Դ�ļ��ĳ���
                int length = conn.getContentLength();
                File filepath = new File(filepathname);
                if (!filepath.exists()) {
                    filepath.mkdirs();
                }
                File file = new File(path + filename);
                //��������洢�ļ�
                RandomAccessFile raf = new RandomAccessFile(file, "rwd");
                //������ʱ�ļ��Ĵ�С
                raf.setLength(length);
                //�ر�raf
                raf.close();
                //�����ÿһ���߳����ض����ֽ�

                int size = length / Multidownload.ThreadCount;

                for (int i = 0; i < Multidownload.ThreadCount; i++) {
                    //startIndex,endIndex�ֱ�����̵߳Ŀ�ʼ�ͽ���λ��
                    int startIndex = i * size;
                    int endIndex = (i + 1) * size - 1;
                    if (i == ThreadCount - 1) {
                        //��������һ���̣߳���ô����λ��д��
                        endIndex = length - 1;
                    }
                    System.out.println("�߳�" + i + "������������" + startIndex + "��" + endIndex);
                    new DownLoadThread(startIndex, endIndex, i, path, filename).start(); //�����߳���������
                }
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

class DownLoadThread extends Thread {
    int startIndex;
    int endIndex;
    int threadId;
    String path;
    String filename;

    public DownLoadThread(int startIndex, int endIndex, int threadId, String path, String filename) {
        super();
        this.path = path;
        this.filename = filename;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.threadId = threadId;
    }

    public static void main(String[] args) {


    }

    ;

    @Override
    public void run() {
        //ʹ��http�������ذ�װ���ļ�
        URL url;
        try {
            url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            //�����������ݵ�����
            conn.setRequestProperty("Range", "bytes=" + startIndex + "-" + endIndex);
            //���󲿷����ݵ���Ӧ����206
            if (conn.getResponseCode() == 206) {
                //��ȡһ������������ȡ
                InputStream is = conn.getInputStream();
                byte[] b = new byte[1024];
                int len = 0;
                int total = 0;
                //�õ���ʱ�ļ�������
                File file = new File(filename);
                RandomAccessFile raf = new RandomAccessFile(file, "rwd");
                //�����ļ���д��λ�ã�startIndex
                raf.seek(startIndex);
                while ((len = is.read(b)) != -1) {
                    //ÿ�ζ�ȡ����������ݣ�ͬ��������д����ʱ�ļ�
                    raf.write(b, 0, len);
                    total += len;
                    System.out.println("�߳�" + threadId + "������" + total);
                }
                System.out.println("�߳�" + threadId + "���ع��̽���===========================");
                raf.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}