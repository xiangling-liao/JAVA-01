import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HelloClassLoader extends ClassLoader{
    public static void main(String[] args) {
        try {
            HelloClassLoader helloClassLoader = new HelloClassLoader();
            Class clz = helloClassLoader.findClass("Hello.xlass");
            if(clz != null){
                Object obj = clz.newInstance();
                Method hello = clz.getMethod("hello");
                hello.invoke(obj);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> clz = findLoadedClass(name);
        // If the class has already been loaded into JVM, return it
        if (clz != null)
            return clz;

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(name);
        byte[] bytes;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int nextValue = 0;
        try {
            while ( (nextValue = inputStream.read()) != -1 ) {
                byteStream.write(nextValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        bytes = decode(byteStream.toByteArray());

        return defineClass("Hello", bytes, 0, bytes.length);
    }

    private byte[] decode(byte[] srcBytes) {
        byte[] results = new byte[srcBytes.length];
        int i = 0;
        for (byte b : srcBytes) {
            results[i] = (byte) (255 - Byte.toUnsignedInt(b));
            i++;
        }

        return results;
    }
}
