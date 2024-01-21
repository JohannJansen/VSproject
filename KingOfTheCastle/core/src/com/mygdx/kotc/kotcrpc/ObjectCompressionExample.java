package com.mygdx.kotc.kotcrpc;

import com.mygdx.kotc.gamemodel.entities.Map;
import com.mygdx.kotc.gamemodel.factories.MapFactory;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class ObjectCompressionExample {

    public static byte[] compressObject(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(gzipOutputStream)) {
            objectOutputStream.writeObject(obj);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static Object decompressObject(byte[] compressedBytes) throws IOException, ClassNotFoundException {
        try (GZIPInputStream gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(compressedBytes));
             ObjectInputStream objectInputStream = new ObjectInputStream(gzipInputStream)) {
            return objectInputStream.readObject();
        }
    }

    public static void main(String[] args) {
        try {
            // Example object to be compressed
            String exampleString = "Hello, this is an example string.";


            Map map = MapFactory.createDefaultMap();

            // Compress the object
            byte[] compressedBytes = compressObject(map);
            System.out.println("Compressed Object: " + compressedBytes);

            // Decompress the object
            Map decompressedMap = (Map) decompressObject(compressedBytes);
            System.out.println(decompressedMap);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
