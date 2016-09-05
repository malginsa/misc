package misc;

import java.io.*;

public class DataInputOutputStream {

    public static void main(String[] args) throws IOException {

        OutputStream fos = new FileOutputStream("filestream.data");
        DataOutputStream dos = new DataOutputStream(fos);
        dos.writeUTF("one");
        dos.writeUTF("two");

        InputStream fis = new FileInputStream("filestream.data");
        DataInputStream dis = new DataInputStream(fis);
        System.out.println(dis.readUTF());
        System.out.println(dis.readUTF());

        String str = dis.readUTF();

    }
}
