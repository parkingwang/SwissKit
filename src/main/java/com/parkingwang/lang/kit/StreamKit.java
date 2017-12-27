package com.parkingwang.lang.kit;

import java.io.*;

/**
 * @author 陈小锅 (yoojiachen@gmail.com)
 * @since 2.8.0
 */
public class StreamKit {

    public static void copyAndClose(InputStream in, OutputStream out) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(in);
             OutputStreamWriter writer = new OutputStreamWriter(out)) {
            char[] buffer = new char[1024];
            int read;
            while ((read = reader.read(buffer)) != -1){
                writer.write(buffer, 0, read);
            }
        }
    }

    public static void closeSilently(AutoCloseable object){
        try{
            object.close();
        }catch (Exception e){ e.printStackTrace(); }
    }
}
