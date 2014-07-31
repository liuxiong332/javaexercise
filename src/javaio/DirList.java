package javaio;
/**
 * Created by liuxi_000 on 2014/7/31.
 */
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class DirList {
    static FilenameFilter filter(final String patternStr) {
        return new FilenameFilter() {
            Pattern pattern = Pattern.compile(patternStr);
            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        };
    }
    public static void printDirFiles(String filePattern) {
        File filePath = new File(".");
        String[] fileNames = filePath.list(filter(filePattern));
        Arrays.sort(fileNames, String.CASE_INSENSITIVE_ORDER);
        for(String name: fileNames) {
            System.out.println(name);
        }
    }
}
