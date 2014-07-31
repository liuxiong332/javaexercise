package javaio;

/**
 * Created by liuxi_000 on 2014/7/31.
 */
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

public final class Directory {
    public static File[] local(File dir, final String patternStr) {
        return dir.listFiles(new FilenameFilter() {
            Pattern pattern = Pattern.compile(patternStr);
            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        });
    }

    public static class TreeInfo implements Iterable<File> {
        public ArrayList<File> dirs = new ArrayList<File>();
        public ArrayList<File> files = new ArrayList<File>();

        @Override
        public Iterator<File> iterator() {
            return files.iterator();
        }

        void addAll(TreeInfo treeInfo) {
            dirs.addAll(treeInfo.dirs);
            files.addAll(treeInfo.files);
        }
    }

    static TreeInfo recurseDirs(File startDir, String regex) {
        TreeInfo result = new TreeInfo();
        for(File item: startDir.listFiles()) {
            if(item.isDirectory()) {
                result.dirs.add(item);
                result.addAll(recurseDirs(item, regex));
            } else {
                if(item.getName().matches(regex))
                    result.files.add(item);
            }
        }
        return result;
    }
}
