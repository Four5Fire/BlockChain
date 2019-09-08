package com.blockChain.util;

import java.io.*;
import java.util.HashMap;
import java.util.List;

public class MapUtils implements Serializable {
    //存放文件标签与文件id的对应    tag  ids
    public static HashMap<String, List<String>> tagsIds=new HashMap<>();

    //存放文件名与ipfs产生的hash值的对应    filename hash
    public static HashMap<String,String> filenameHash=new HashMap<>();

    //对HashMap进行序列化操作
    public static void writeMap(Object object,String filepath){
        try {
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(
                    new FileOutputStream(new File(filepath)));
            objectOutputStream.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //对HashMap进行反序列化操作
    public static Object readObject(String filepath){
        ObjectInputStream  objectInputStream=null;
        Object object=null;
        try {
            objectInputStream=new ObjectInputStream(
                    new FileInputStream(new File(filepath)));
            object= objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }
}
