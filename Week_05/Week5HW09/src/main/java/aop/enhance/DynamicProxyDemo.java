package aop.enhance;

import aop.enhance.entity.CompactDisc;
import aop.enhance.entity.SgtPeppers;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static com.sun.tools.classfile.AccessFlags.Kind.Method;

@Slf4j
public class DynamicProxyDemo {
    public static void main(String[] args) {
        CompactDisc cd = new SgtPeppers();
        CompactDisc proxyObj = (CompactDisc) Proxy.newProxyInstance(
                CompactDisc.class.getClassLoader(),
                new Class[]{CompactDisc.class},
                (Object proxy, java.lang.reflect.Method method, Object[] a) ->
                           {
                        log.info("before running...");
                        Object obj = method.invoke(cd, a);
                        log.info("after running...");
                        return obj;
                });

        proxyObj.play();
    }



}
