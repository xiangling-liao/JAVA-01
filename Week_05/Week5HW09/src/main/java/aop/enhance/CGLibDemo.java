package aop.enhance;

import aop.enhance.entity.CompactDisc;
import aop.enhance.entity.SgtPeppers;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
public class CGLibDemo {

    public static void main(String[] args) {
        CompactDisc cd = new SgtPeppers();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SgtPeppers.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                log.info("before running...");
                Object obj = method.invoke(cd, args);
                log.info("after running...");
                return obj;
            }
        });


        Object service = enhancer.create();

        SgtPeppers proxy = (SgtPeppers) enhancer.create();
        proxy.play();
    }
}
