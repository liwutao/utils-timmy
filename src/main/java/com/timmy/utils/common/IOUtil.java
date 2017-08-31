package com.timmy.utils.common;

import java.io.Closeable;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(IOUtil.class);
    
    public static void closeQuietly(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (IOException e) {
                LOGGER.error("Fail to close resource!", e);
            }
        }
    }
}
