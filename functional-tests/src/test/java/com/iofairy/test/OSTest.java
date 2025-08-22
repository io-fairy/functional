package com.iofairy.test;

import com.iofairy.os.OS;
import org.junit.jupiter.api.Test;

/**
 * @author GG
 * @version 1.0
 */
public class OSTest {

    @Test
    public void testOS() {
        System.out.println("os name: " + OS.OS_NAME);
        System.out.println("os version: " + OS.OS_VERSION);
        System.out.println("os arch: " + OS.OS_ARCH);
        System.out.println("user home: " + OS.USER_HOME);
        System.out.println("module root dir: " + OS.USER_DIR);
        System.out.println("os language: " + OS.USER_LANGUAGE);
        System.out.println("user timezone: " + OS.USER_TIMEZONE);
        System.out.println("user timezone: " + System.getProperty("user.timezone"));
        System.out.println("awt toolkit classname: " + OS.AWT_TOOLKIT_CLASSNAME);
        System.out.println("line separator: " + OS.LINE_SEPARATOR);
        System.out.println("file separator: " + OS.FILE_SEPARATOR);
        System.out.println("path separator: " + OS.PATH_SEPARATOR);
        System.out.println("is mac os: " + OS.IS_MAC);
        System.out.println("is windows system: " + OS.IS_WINDOWS);
        System.out.println("is windows 10: " + OS.IS_WINDOWS_10);
        System.out.println("DEFAULT_LOCALE: " + OS.DEFAULT_LOCALE);
        System.out.println("DEFAULT_LANG: " + OS.DEFAULT_LANG);
        System.out.println("DEFAULT_COUNTRY: " + OS.DEFAULT_COUNTRY);
        System.out.println("IS_ZH_LANG: " + OS.IS_ZH_LANG);
    }

    @Test
    public void testOSJavaInfo() {
        System.out.println("JAVA_HOME: " + OS.JAVA_HOME);
        System.out.println("JAVA_CLASS_PATH: " + OS.JAVA_CLASS_PATH);
        System.out.println("JAVA_COMPILER: " + OS.JAVA_COMPILER);
        System.out.println("JAVA_LIBRARY_PATH: " + OS.JAVA_LIBRARY_PATH);
        System.out.println("JAVA_EXT_DIRS: " + OS.JAVA_EXT_DIRS);
        System.out.println("JAVA_VM_INFO: " + OS.JAVA_VM_INFO);
        System.out.println("JAVA_AWT_PRINTERJOB: " + OS.JAVA_AWT_PRINTERJOB);
        System.out.println("JAVA_IO_TMPDIR: " + OS.JAVA_IO_TMPDIR);
        System.out.println("JAVA_VENDOR_URL_BUG: " + OS.JAVA_VENDOR_URL_BUG);
        System.out.println("JAVA_ENDORSED_DIRS: " + OS.JAVA_ENDORSED_DIRS);
        System.out.println("JAVA_AWT_GRAPHICSENV: " + OS.JAVA_AWT_GRAPHICSENV);
        System.out.println("JAVA_VENDOR_URL: " + OS.JAVA_VENDOR_URL);
        System.out.println("JAVA_VERSION: " + OS.JAVA_VERSION);
        System.out.println("JAVA_SPECIFICATION_VERSION: " + OS.JAVA_SPECIFICATION_VERSION);
        System.out.println("J_VERSION: " + OS.J_VERSION);
        System.out.println("JAVA_VERSION_NUMBER: " + OS.JAVA_VERSION_NUMBER);
        System.out.println("JAVA_CLASS_VERSION: " + OS.JAVA_CLASS_VERSION);
        System.out.println("JAVA_RUNTIME_VERSION: " + OS.JAVA_RUNTIME_VERSION);
        System.out.println("JAVA_VM_VERSION: " + OS.JAVA_VM_VERSION);
        System.out.println("JAVA_VM_SPECIFICATION_VERSION: " + OS.JAVA_VM_SPECIFICATION_VERSION);
        System.out.println("JAVA_SPECIFICATION_NAME: " + OS.JAVA_SPECIFICATION_NAME);
        System.out.println("JAVA_RUNTIME_NAME: " + OS.JAVA_RUNTIME_NAME);
        System.out.println("JAVA_VM_NAME: " + OS.JAVA_VM_NAME);
        System.out.println("JAVA_VM_SPECIFICATION_NAME: " + OS.JAVA_VM_SPECIFICATION_NAME);
        System.out.println("JAVA_VENDOR: " + OS.JAVA_VENDOR);
        System.out.println("JAVA_SPECIFICATION_VENDOR: " + OS.JAVA_SPECIFICATION_VENDOR);
        System.out.println("JAVA_VM_VENDOR: " + OS.JAVA_VM_VENDOR);
        System.out.println("JAVA_VM_SPECIFICATION_VENDOR: " + OS.JAVA_VM_SPECIFICATION_VENDOR);
    }
}
