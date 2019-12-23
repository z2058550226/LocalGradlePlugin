package com.suika.localplugin

import javassist.ClassPool
import javassist.CtClass
import javassist.CtConstructor

public class MyInject {
    private static ClassPool pool = ClassPool.getDefault()
    private static String injectStr = "System.out.println(\"I Love Suika\");"
//    private static String injectStr = "Timber.e(\"transform output\");"

    public static void injectDir(String path, String packageName) {
        pool.appendClassPath(path)
        File dir = new File(path)

        if (!dir.isDirectory()) return

        dir.eachFileRecurse { File file ->
            String filePath = file.absolutePath
            //确保当前文件是class文件，并且不是系统自动生成的class文件
            if (filePath.endsWith(".class") &&
                    !filePath.contains('R$') &&
                    !filePath.contains('R.class') &&
                    !filePath.contains("BuildConfig.class")) {
                // 判断当前目录是否是在我们的应用包里面
                int index = filePath.indexOf(packageName)
                println "filePath: " + filePath
                println "index: " + index
                if (index != -1) {
                    Log.i("path:" + path)
                    Log.i("packageName:" + packageName)
                    int end = filePath.length() - 6
                    String className = filePath.substring(index, end)
                            .replace(File.separator, '.')
                    println "className: " + className
                    // 开始修改class文件
                    CtClass c = pool.getCtClass(className)

                    if (c.isFrozen()) {
                        c.defrost()
                    }
                    println "#1"
                    CtConstructor[] cts = c.getDeclaredConstructor()
                    println "#2"
                    if (cts == null || cts.length == 0) {
                        // 手动创建一个构造函数
                        println "manually create constructor"
                        CtConstructor constructor = new CtConstructor(new CtClass[0], c)
                        constructor.insertBeforeBody(injectStr)
                        c.addConstructor(constructor)
                    } else {
                        println "already has constructor"
                        cts[0].insertBeforeBody(injectStr)
                    }
                    println "over"
                    c.writeFile(path)
                    c.detach()
                }
            }
        }
    }
}