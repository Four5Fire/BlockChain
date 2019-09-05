package com.blockChain.entity;

import lombok.Data;

@Data
public class FileEntity {
    private int id;
    private String filename;
    private String username;
    private String filesize;
    private String createTime;
    private String modifyTime;
    private int shareState;
}
