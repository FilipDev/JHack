package me.pauzen.jhack.mbean;

import me.pauzen.jhack.reflection.ReflectionFactory;

import javax.management.ObjectName;
import java.lang.reflect.Method;

/*
 * Written by FilipDev on 12/24/14 12:19 AM.
 */

public abstract class MBeanObject {

    private final ObjectName objectName;

    public MBeanObject(ObjectName objectName) {
        this.objectName = objectName;
    }

    /**
     * Gets ObjectName value.
     *
     * @return ObjectName value.
     */
    public ObjectName getObjectName() {
        return objectName;
    }

    /**
     * Invokes an operation in the MBeanServer.
     *
     * @param method The method to invoke.
     * @param args   Arguments to use.
     * @return Returned object from calling operation.
     */
    public Object invoke(Method method, Object... args) {
        return MBeanServerWrapper.invoke(objectName, method, args, ReflectionFactory.toStringArray(method.getParameterTypes()));
    }

    /**
     * Invokes an operation given its name.
     *
     * @param methodName Name of the operation to invoke.
     * @param args       Arguments to use.
     * @return Returned object from calling operation.
     */
    public Object invoke(String methodName, Object... args) {
        return MBeanServerWrapper.invoke(objectName, methodName, args, ReflectionFactory.toStringArray(ReflectionFactory.toClassArray(args)));
    }
}
