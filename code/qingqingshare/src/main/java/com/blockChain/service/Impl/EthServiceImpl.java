package com.blockChain.service.Impl;


import com.blockChain.service.EthService;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.BooleanResponse;
import org.web3j.protocol.admin.methods.response.NewAccountIdentifier;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;

import static sun.plugin.javascript.navig.JSType.URL;

public class EthServiceImpl implements EthService {

    @Override
    public Web3j initWeb3j() {
        return Web3j.build(getService());
    }

    @Override
    public Geth initGeth() {
        return Geth.build(getService());
    }

    @Override
    public Admin initAdmin() {
        return Admin.build(getService());
    }

    @Override
    public HttpService getService() {
        return new HttpService(URL);
    }

    @Override
    public String newAccount(String password) throws IOException {
        Admin admin = initAdmin();
        Request<?, NewAccountIdentifier> request = admin.personalNewAccount(password);
        NewAccountIdentifier result = request.send();
        return result.getAccountId();
    }

    @Override
    public BigInteger getCurrentBlockNumber() throws IOException {
        Web3j web3j = initWeb3j();
        Request<?, EthBlockNumber> request = web3j.ethBlockNumber();
        return request.send().getBlockNumber();
    }

    @Override
    public Boolean unlockAccount(String address, String password, BigInteger duration) throws IOException {
        Admin admin = initAdmin();
        Request<?, PersonalUnlockAccount> request = admin.personalUnlockAccount(address, password, duration);
        PersonalUnlockAccount account = request.send();
        return account.accountUnlocked();
    }

    @Override
    public Boolean lockAccount(String address) throws IOException {
        Geth geth = initGeth();
        Request<?, BooleanResponse> request = geth.personalLockAccount(address);
        BooleanResponse response = request.send();
        return response.success();
    }

    @Override
    public EthTransaction getTransactionByHash(String hash) throws IOException {
        Web3j web3j = initWeb3j();
        Request<?, EthTransaction> request = web3j.ethGetTransactionByHash(hash);
        return request.send();
    }

    @Override
    public EthBlock getBlockEthBlock(Integer blockNumber) throws IOException {
        Web3j web3j = initWeb3j();

        DefaultBlockParameter defaultBlockParameter = new DefaultBlockParameterNumber(blockNumber);
        Request<?, EthBlock> request = web3j.ethGetBlockByNumber(defaultBlockParameter, true);
        EthBlock ethBlock = request.send();
        return ethBlock;
    }

    @Override
    public String sendTransaction(Transaction transaction, String password) throws IOException {
        Admin admin = initAdmin();
        Request<?, EthSendTransaction> request = admin.personalSendTransaction(transaction, password);
        EthSendTransaction ethSendTransaction = request.send();
        return ethSendTransaction.getTransactionHash();
    }

    @Override
    public BigInteger getNonce(String address) throws IOException {
        Web3j web3j = initWeb3j();
        Request<?, EthGetTransactionCount> request = web3j.ethGetTransactionCount(address, DefaultBlockParameterName.LATEST);
        return request.send().getTransactionCount();
    }


}
