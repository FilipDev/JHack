package me.pauzen.jhack.hotspot;

import com.sun.management.VMOption;
import me.pauzen.jhack.mbean.MBeanObject;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeDataSupport;
import java.io.File;
import java.io.IOException;

/*
 * Written by FilipDev on 12/24/14 12:19 AM.
 */

public final class HotSpotDiagnostic extends MBeanObject {

    private static final ObjectName                                objectName;
    private static final HotSpotDiagnostic instance;
    private              Integer                                   alignment;
    private              Boolean                                   compressedOops;

    static {
        ObjectName objectName1 = null;
        try {
            objectName1 = new ObjectName("com.sun.management", "type", "HotSpotDiagnostic");
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        }
        objectName = objectName1;
        instance = new HotSpotDiagnostic();
    }

    private HotSpotDiagnostic() {
        super(objectName);
    }

    public static HotSpotDiagnostic getInstance() {
        return instance;
    }

    /**
     * Return whether or not JVM uses compressed oops.
     *
     * @return Whether HotSpot uses compressed oops.
     */
    public boolean useCompressedOops() {
        if (compressedOops == null) return compressedOops = Boolean.parseBoolean(getVMOption("UseCompressedOops"));
        return compressedOops;
    }

    /**
     * Object alignment in bytes.
     *
     * @return Object alignment in bytes.
     */
    public int getAlignment() {
        if (alignment == null) return alignment = Integer.parseInt(getVMOption("ObjectAlignmentInBytes"));
        return alignment;
    }

    public boolean usingJava8() {
        return System.getProperty("java.version").startsWith("1.8");
    }

    /**
     * Gets VM option.
     *
     * @param optionKey Option key.
     * @return Option value.
     */
    public String getVMOption(String optionKey) {
        CompositeDataSupport compositeDataSupport = (CompositeDataSupport) invoke("getVMOption", optionKey);
        VMOption option = VMOption.from(compositeDataSupport);
        return option.getValue();
    }

    /**
     * Dumps JVM heap into a file.
     *
     * @param file
     * @param liveObjects
     */
    public void dumpHeap(File file, boolean liveObjects) {
        try {
            if (file.exists()) file.delete();
            this.invoke("dumpHeap", file.getCanonicalPath(), liveObjects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dumpHeap(String path, boolean liveObjects) {
        dumpHeap(new File(path), liveObjects);
    }
}
