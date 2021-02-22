package space.debian.odeliajobs.utils.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class DiscUtils {
    private static final String UTF8 = "UTF-8";

    public static byte[] readBytes(File file) throws IOException {
        int length = (int)file.length();
        byte[] output = new byte[length];
        FileInputStream in = new FileInputStream(file);
        for (int offset = 0; offset < length; offset += ((InputStream)in).read(output, offset, length - offset)) {
        }
        ((InputStream)in).close();
        return output;
    }

    public static void writeBytes(File file, byte[] bytes) throws IOException {
        FileOutputStream out = new FileOutputStream(file);
        out.write(bytes);
        out.close();
    }

    public static void write(File file, String content) throws IOException {
        DiscUtils.writeBytes(file, DiscUtils.utf8(content));
    }

    public static String read(File file) throws IOException {
        return DiscUtils.utf8(DiscUtils.readBytes(file));
    }

    public static boolean writeCatch(File file, String content) {
        try {
            DiscUtils.write(file, content);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public static String readCatch(File file) {
        try {
            return DiscUtils.read(file);
        }
        catch (IOException e) {
            return null;
        }
    }

    public static boolean downloadUrl(String urlstring, File file) {
        try {
            URL url = new URL(urlstring);
            ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            FileOutputStream fos = new FileOutputStream(file);
            fos.getChannel().transferFrom(rbc, 0L, 0x1000000L);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean downloadUrl(String urlstring, String filename) {
        return DiscUtils.downloadUrl(urlstring, new File(filename));
    }

    public static boolean deleteRecursive(File path) throws FileNotFoundException {
        if (!path.exists()) {
            throw new FileNotFoundException(path.getAbsolutePath());
        }
        boolean ret = true;
        if (path.isDirectory()) {
            for (File f : path.listFiles()) {
                ret = ret && DiscUtils.deleteRecursive(f);
            }
        }
        return ret && path.delete();
    }

    public static byte[] utf8(String string) {
        try {
            return string.getBytes(UTF8);
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String utf8(byte[] bytes) {
        try {
            return new String(bytes, UTF8);
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}

