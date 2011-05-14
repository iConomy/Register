package com.nijikokun.register.payment;

import java.util.HashSet;
import java.util.Set;
import org.bukkit.plugin.Plugin;


public class MethodFactory {
    private static Set<Method> methods = new HashSet<Method>();
	
    public static Method createMethod(Plugin plugin) {
        for (Method method : methods) {
            if (method.isCompatible(plugin)) {
                method.setPlugin(plugin);
                return method;
            }
        }
        return null;
    }

    public static void addMethod(Method method) {
        methods.add(method);
    }
}
