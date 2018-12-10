package com.tpadsz.after.service;


import com.tpadsz.after.entity.FileRecord;
import com.tpadsz.after.entity.NewestFile;

public interface UpdateService {

    NewestFile getNewestFile(String appId);

    FileRecord getFileRecords(String appId, int versionCode);

}
