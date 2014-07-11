
package test.ClassLoader;

import java.net.URL;

/*
 * http://blog.csdn.net/xyang81/article/details/7292380*/

public class ClassLoaderTest {
    public static void main(String[] args) {
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i].toExternalForm());
        }

        String[] paths = System.getProperty("sun.boot.class.path").split(":");
        for (int i = 0; i < paths.length; i++) {
            System.out.println(paths[i]);
        }

        ClassLoader loader = ClassLoaderTest.class.getClassLoader(); // 获得加载ClassLoaderTest.class这个类的类加载器
        System.out.println(loader);
        while (loader != null) {
            loader = loader.getParent(); // 获得父类加载器的引用
            System.out.println(loader);
        }

        String rootUrl = "http://localhost:8080/httpweb/classes";
        NetworkClassLoader networkClassLoader = new NetworkClassLoader(rootUrl);
        String classname = "org.classloader.simple.NetClassLoaderTest";

        try {
            Class<?> clazz = networkClassLoader.loadClass(classname);
            System.out.println(clazz.getClassLoader());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
