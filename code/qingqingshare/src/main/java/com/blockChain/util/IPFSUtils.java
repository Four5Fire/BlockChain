package com.blockChain.util;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;

import java.io.File;
import java.io.IOException;

public class IPFSUtils {
    //初始化ipfs实例
    static IPFS ipfs=new IPFS("/ip4/39.106.190.132/tcp/5001");

    public static String addFile(String filepath){
        MerkleNode addResult=null;
        try {
            NamedStreamable.FileWrapper fileWrapper=new NamedStreamable.FileWrapper(new File(filepath));
            addResult = ipfs.add(fileWrapper).get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return addResult.hash.toString();
    }

    public static byte[] getFile(String hashkey){
        byte[] fileContent=null;
        try {
            fileContent = ipfs.cat(Multihash.fromBase58(hashkey));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }
}
