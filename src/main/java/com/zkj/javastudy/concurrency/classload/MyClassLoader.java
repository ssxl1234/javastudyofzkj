package com.zkj.javastudy.concurrency.classload;

import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyClassLoader extends ClassLoader{
    private final static Path DEFAULT_CLASS_DIR=Paths.get("E:");
    private final Path classDir;
    public MyClassLoader(){
        super();
        this.classDir=DEFAULT_CLASS_DIR;
    }
    public MyClassLoader(String path){
        super();
        this.classDir=Paths.get(path);
    }
    public MyClassLoader(String path,ClassLoader parent){
        super(parent);
        this.classDir=Paths.get(path);
    }
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classBytes=this.readClassBytes(name);
        return this.defineClass(name, classBytes,0,classBytes.length);
    }
    private byte[] readClassBytes(String name) {
        String path=name.replace(".","/");
        Path fullpath=classDir.resolve(Paths.get(path+".class"));
        if(!fullpath.toFile().exists()){
            throw  new RuntimeException();
        }
        try(ByteArrayOutputStream baos=new ByteArrayOutputStream()) {
            Files.copy(fullpath, baos);
            return baos.toByteArray();
        } catch (Exception e) {
            //TODO: handle exception
            throw new RuntimeException();
        }
    }
    
    
}
