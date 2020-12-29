package com.bjtu.redis;

import java.io.File;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//文件监听器类
final class FileListener extends FileAlterationListenerAdaptor {

    private final Logger logger = LoggerFactory.getLogger(FileListener.class);
    // 文件更改
    @Override
    public void onFileChange(File file) {
        Main.lock.compareAndSet(false, true);
        System.out.println(file.getName() + " has be changed..");
        Main.loadConfigJson();
        System.out.println("JSON file has be overloaded..");
        Main.lock.set(false);
    }

}
