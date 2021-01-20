package testquickresto.archiver;

/**
 * The type Archive util.
 * Utilities archiver
 */
public class ArchiveUtil {
    private ArchiveUtil() {
    }

    /**
     * The constant ARCHIVE_KEY.
     * The key is written to file archive,
     * needed to authenticate that this is an archive file
     */
    public static final int ARCHIVE_KEY = 244569974;

    /**
     * Check is archive.
     *
     * @param archiveKeyOther the archive key other file
     * @return the boolean
     */
    public static boolean checkIsArchive(int archiveKeyOther) {
        return ARCHIVE_KEY == archiveKeyOther;
    }
}
