package co.junwei;

import co.junwei.cpabe.Cpabe;

public class Encrypt {
    public static void main(String[] args) throws Exception {
        Cpabe test = new Cpabe();
        println("//start to setup");
        //pubfile为生成PK的位置 -输出
        //mskfile为生成MK的位置 -输出
        test.setup("../PK", "../MK");
        println("//end to setup");

        //pubfile为PK的位置 -输入
        //prvfile为生成私钥的位置 输出
        //mskfile为MK的位置 -输入
        //attr_str为参与生成私钥的属性 -输入
        println("//start to keygen");
        test.keygen("../PK",
                "../sKey",
                "../MK",
                "objectClass:inetOrgPerson objectClass:organizationalPerson " +
                        "sn:student2 cn:student2 userPassword:student2 uid:student2 ou:idp " +
                        "o:computer mail:student2@sdu.edu.cn title:student");
        println("//end to keygen");

        //pubfile为生成PK的位置 -输入
        //policy为生成密文的策略 -输入
        //inputfile为明文位置 -输入
        //encfile为密文位置 -输出
        println("//start to enc");
        test.enc("../PK",
                "sn:student2 cn:student2 uid:student2 2of3",
                "../input.pdf",
                "../encfile.pdf.cpabe");
        println("//end to enc");

        //pubfile为PK位置 -输入
        //prvfile为私钥的位置 -输入
        //encfile为密文位置 -输入
        //decfile为生成明文的位置 -输出
        println("//start to dec");
        test.dec("../PK",
                "../sKey",
                "../encfile.pdf.cpabe",
                "../output.pdf");
        println("//end to dec");
    }

    private static void println(Object o) {
        System.out.println(o);
    }
}
