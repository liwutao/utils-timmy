/**
 * Copyright @2015 海尔集团 All rights reserved.
 * 广科数字技术有限公司专有/保密源代码,未经许可禁止任何人通过任何渠道使用、修改源代码.
 * 
 */

package com.timmy.utils.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 创建文件工具类
 * @author jiangp
 *
 */
public class FileUtil {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
  
    /**
     * 该方法用来获取文件纯名称，即去掉格式后缀后的名称
     * 
     * @param file 文件
     * @return 文件名
     */
    public static String getFilePureName(File file) {
        String schemaName = file.getName();
        int pureNameTail = schemaName.lastIndexOf(".");
        return schemaName.substring(0, pureNameTail);
    }
    
    public static boolean writeFile(String content, URI regInfoPath) {
        final File destFile = new File(regInfoPath);
        return writeFile(content, destFile);
    }
    
    public static boolean createFile(File destFile) {
        LOGGER.info("Creating file {}.", destFile.getName());
        try {
            File parentFile = destFile.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            LOGGER.info("Finish creating file.");
            return destFile.createNewFile();
        } catch (IOException e) {
            LOGGER.error("Fail to create file {}!", destFile.getName());
            return false;
        }
    }
    
    public static boolean writeFile(String content, String regInfoPath) {
    	LOGGER.debug("Writing file {}.",regInfoPath);
    	final File destFile = new File(regInfoPath);
        return writeFile(content, destFile);
    }
    
    public static boolean writeFile(String content, File destFile) {
        LOGGER.debug("Writing file {}.", destFile.getName());
        if (!destFile.exists() && !createFile(destFile)) {
                LOGGER.error("Fail to create file {}.", destFile.getName());
                return false;
        }
        InputStream is = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        try {
            is = new ByteArrayInputStream(content.getBytes("UTF-8"));
            bis = new BufferedInputStream(is);
            byte[] bytes = new byte[1024];
            fos = new FileOutputStream(destFile);
            int realLength = -1;
            while ((realLength = bis.read(bytes)) != -1) {
                fos.write(bytes, 0 , realLength);
            }
            fos.flush();
            LOGGER.info("Finish writing file.");
            return true;
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Fail to write data into file {}, caused by encoding format not supported when convert content to bytes.", destFile.getName());
            return false;
        } catch (IOException e) {
            LOGGER.error("Fail to write data into file {}.", destFile.getName());
            return false;
        } finally {
            IOUtil.closeQuietly(fos);
            IOUtil.closeQuietly(bis);
            IOUtil.closeQuietly(is);
        }
    }
    
    public static boolean writeFile(byte[] bytes, File destFile) {
        if (null == destFile) {
            LOGGER.error("There is no file to write.");
            return false;
        }
        LOGGER.info("Start time stamp is {}.", System.currentTimeMillis());
        FileOutputStream fos = null;
        BufferedOutputStream bfos = null;
        InputStream is = null;
        BufferedInputStream bis = null;
        try {
            if (!destFile.exists() && !FileUtil.createFile(destFile)) {
                return false;
            }
            fos = new FileOutputStream(destFile);
            bfos = new BufferedOutputStream(fos);
            is = new ByteArrayInputStream(bytes);
            bis = new BufferedInputStream(is);
            byte[] tmpBytes = new byte[1024];
            int realLength = -1;
            while ((realLength = bis.read(tmpBytes)) != -1) {
                fos.write(tmpBytes, 0 , realLength);
            }
            bfos.flush();
        } catch (FileNotFoundException e) {
            LOGGER.error("Destnate file not found when do writing on it!");
            return false;
        } catch (IOException e) {
            LOGGER.error("Error occurred when writing temple file!", e);
            return false;
        } finally {
            IOUtil.closeQuietly(is);
            IOUtil.closeQuietly(bis);
            IOUtil.closeQuietly(bfos);
            IOUtil.closeQuietly(fos);
        }
        LOGGER.info("End time stamp is {}.", System.currentTimeMillis());
        return true;
    }
    
}
