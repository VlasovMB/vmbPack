package testquickresto.archiver;

import java.io.InputStream;


/**
 * The type Archive entry.
 */
public class ArchiveEntry {
    private String strPath;
    private long creationTime;
    private long lastModifiedTime;
    private boolean isDirectory;
    private InputStream inputStream;

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getStrPath() {
        return strPath;
    }

    /**
     * Sets name.
     *
     * @param strPath the name
     */
    public void setStrPath(String strPath) {
        this.strPath = strPath;
    }

    /**
     * Gets creation time.
     *
     * @return the creation time
     */
    public long getCreationTime() {
        return creationTime;
    }

    /**
     * Sets creation time.
     *
     * @param creationTime the creation time
     */
    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    /**
     * Gets last modified time.
     *
     * @return the last modified time
     */
    public long getLastModifiedTime() {
        return lastModifiedTime;
    }

    /**
     * Sets last modified time.
     *
     * @param lastModifiedTime the last modified time
     */
    public void setLastModifiedTime(long lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    /**
     * Is directory boolean.
     *
     * @return the boolean
     */
    public boolean isDirectory() {
        return isDirectory;
    }

    /**
     * Sets directory.
     *
     * @param directory the directory
     */
    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }

    /**
     * Gets input stream.
     *
     * @return the input stream
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     * Sets input stream.
     *
     * @param inputStream the input stream
     */
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public String toString() {
        return strPath;
    }

    /**
     * Get path without invalid chars string.
     *
     * @return the string
     */
    public String getPathWithoutInvalidChars() {
        return strPath.replace(":", "");
    }
}
