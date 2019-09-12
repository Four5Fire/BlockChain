package com.blockChain.util;

import org.apache.http.annotation.Contract;
import org.apache.http.protocol.HttpService;
import org.junit.Before;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MapUtils implements Serializable {
    //存放文件标签与文件id的对应    tag  ids
    public static HashMap<String, List<String>> tagsIds=new HashMap<>();

    //存放文件名与ipfs产生的hash值的对应    filename hash
    public static HashMap<String,String> filenameHash=new HashMap<>();

    @Before
    public static void initHashMapStr() throws IOException, CipherException {
        Web3j web3j = Web3j.build(new HttpService("http://playcall.cn:8454"));
        Credentials credentials = WalletUtils.loadCredentials("123456","http://playcall.cn:7777/keyStore");
        String contractAddress = "http://playcall.cn:7777/hello.js";
        Contract erc20 = Contract.load(contractAddress, web3j, credentials);

        RemoteCall<TransactionReceipt> transfer = erc20.transfer(to , price);
        CompletableFuture<TransactionReceipt> sendAsync = transfer.sendAsync();
        String result1 = erc20.someMethod(<getMapStr1>, ...).send();
        String result2 = erc20.someMethod(<getMapStr2>, ...).send();
        TransactionReceipt transactionReceipt = sendAsync.get();//返回文件关联信息
        tagsIds = readObject(result1);
        filenameHash = readObject(result2);
    }

    public static void storeHashMapStr() throws IOException, CipherException {
        Web3j web3j = Web3j.build(new HttpService("http://playcall.cn:8454"));
        Credentials credentials = WalletUtils.loadCredentials("123456","http://playcall.cn:7777/keyStore");
        String contractAddress = "http://playcall.cn:7777/hello.js";
        Contract erc20 = Contract.load(contractAddress, web3j, credentials);

        RemoteCall<TransactionReceipt> transfer = erc20.transfer(to , price);
        CompletableFuture<TransactionReceipt> sendAsync = transfer.sendAsync();
        String result1;
        String result2;
        writeMap(tagsIds,result1);
        writeMap(filenameHash,result2);

        erc20.someMethod(<setMapStr1>, result1).send();
        erc20.someMethod(<setMapStr2>, result2).send();
        TransactionReceipt transactionReceipt = sendAsync.get();//返回文件关联信息
        tagsIds = readObject(result1);
        filenameHash = readObject(result2);
    }



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
