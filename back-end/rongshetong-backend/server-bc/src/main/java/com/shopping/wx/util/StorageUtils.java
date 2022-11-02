package com.shopping.wx.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author ljy
 */
public abstract class StorageUtils {

    private StorageUtils() {

    }

    public static boolean createDirOrFile(String path) throws IOException {
        final File target = new File(path);
        final File parent = target.getParentFile();
        if (parent != null && !parent.exists() && !parent.mkdirs()) {
            return false;
        }
        return target.createNewFile();
    }

    public static String pathJoin(String... subPath) {
        return String.join(File.separator, subPath);
    }
}
