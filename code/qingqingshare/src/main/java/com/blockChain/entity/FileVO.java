package com.blockChain.entity;

import lombok.Data;

import java.util.Date;

@Data
public class FileVO {
    private int id;
    private String filename;
    private String filesize;
    private String uploadTime;
    private int shareState;

}
