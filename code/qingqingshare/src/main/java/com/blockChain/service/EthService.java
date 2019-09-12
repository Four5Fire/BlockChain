package com.blockChain.service;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;

public interface EthService {

    Web3j initWeb3j();

    Geth initGeth();

    Admin initAdmin();

    HttpService getService();

    String newAccount(String password) throws IOException;

    BigInteger getCurrentBlockNumber() throws IOException;

    Boolean unlockAccount(String address, String password, BigInteger duration) throws IOException;

    Boolean lockAccount(String address) throws IOException;

    EthTransaction getTransactionByHash(String hash) throws IOException;

    EthBlock getBlockEthBlock(Integer blockNumber) throws IOException;

    String sendTransaction(Transaction transaction, String password) throws IOException;

    BigInteger getNonce(String address) throws IOException;
}
