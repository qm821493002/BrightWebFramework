package cn.bright.webframework.carrier;

import java.io.File;

/**
 * Created by hp on 2014/8/5.
 */
public class FileDomain {
    private String fieldName;
    private String fileName;
    private String contentType;
    private boolean inMemory;
    private long sizeInBytes;
    private File file;

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setInMemory(boolean inMemory) {
        this.inMemory = inMemory;
    }

    public boolean isInMemory() {
        return inMemory;
    }

    public void setSizeInBytes(long sizeInBytes) {
        this.sizeInBytes = sizeInBytes;
    }

    public long getSizeInBytes() {
        return sizeInBytes;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }
}
