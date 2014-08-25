package cn.bright.webframework.utils;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 2014/7/30.
 */
public class PackageUtil {


    /**
     * 获取某包下（包括该包的所有子包）所有类
     *
     * @param packageName 包名
     * @return 类的完整名称
     */
    public static List<String> getClassNames(String packageName) {
        return getClassName(packageName, true,packageName.substring(0,packageName.indexOf(".")));
    }

    /**
     * 获取某包下所有类
     *
     * @param packageName  包名
     * @param childPackage 是否遍历子包
     * @return 类的完整名称
     */
    public static List<String> getClassName(String packageName, boolean childPackage,String originalPackage) {
        List<String> fileNames = null;
        ClassLoader loader = PackageUtil.class.getClassLoader();
        String packagePath = packageName.replace(".", "/");

        if (packagePath.startsWith("classes")){
            packagePath="";

        }
        URL url = loader.getResource(packagePath);

        if (url != null) {
                fileNames = getClassNameByFile(url.getPath(), null, childPackage,originalPackage);
        }
        return fileNames;
    }

    /**
     * 从项目文件获取某包下所有类
     *
     * @param filePath     文件路径
     * @param className    类名集合
     * @param childPackage 是否遍历子包
     * @return 类的完整名称
     */
    private static List<String> getClassNameByFile(String filePath, List<String> className, boolean childPackage,String originalPackage) {
        List<String> myClassName = new ArrayList<String>();
        File file = new File(filePath);


        File[] childFiles = file.listFiles();
        for (File childFile : childFiles) {
            if (childFile.isDirectory()) {
                if (childPackage) {
                    myClassName.addAll(getClassNameByFile(childFile.getPath(), myClassName, childPackage,originalPackage));
                }
            } else {
                String childFilePath = childFile.getPath();
                if (childFilePath.endsWith(".class")) {
                    childFilePath = childFilePath.substring(childFilePath.indexOf(originalPackage) , childFilePath.lastIndexOf("."));
                    childFilePath = childFilePath.replace("\\", ".");
                    myClassName.add(childFilePath);
                }
            }
        }

        return myClassName;
    }

}
