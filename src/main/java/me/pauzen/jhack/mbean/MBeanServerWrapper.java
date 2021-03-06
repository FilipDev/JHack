package me.pauzen.jhack.mbean;

import me.pauzen.jhack.reflection.ReflectionFactory;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;

/*
 * Written by FilipDev on 12/24/14 12:19 AM.
 */

public final class MBeanServerWrapper {

    private static final MBeanServer server = ManagementFactory.getPlatformMBeanServer();

    /**
     * Prevents initialization.
     */
    private MBeanServerWrapper() {
    }

    /**
     * Invokes operation in MBeanServer.
     *
     * @param objectName ObjectName to invoke operation in.
     * @param method     The operation to invoke in Method form.
     * @param args       Object args used to invoke operation.
     * @return Object returned by invoking operation.
     */
    public static Object invoke(ObjectName objectName, Method method, Object... args) {
        try {
            return server.invoke(objectName, method.getName(), args, ReflectionFactory.toStringArray(method.getParameterTypes()));
        } catch (ReflectionException | MBeanException | InstanceNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Invokes operation in MBeanServer.
     *
     * @param objectName ObjectName to invoke operation in.
     * @param methodName The operation name to invoke.
     * @param args       Object args used to invoke operation.
     * @return Object returned by invoking operation.
     */
    public static Object invoke(ObjectName objectName, String methodName, Object[] args, String[] params) {
        try {
            return server.invoke(objectName, methodName, args, params);
        } catch (InstanceNotFoundException | MBeanException | ReflectionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
