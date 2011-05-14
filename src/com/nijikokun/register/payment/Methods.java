package com.nijikokun.register.payment;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

public class Methods {

    private Method Method = null;

    public boolean setMethod(Plugin method) {
        if (method.isEnabled()) {
            Method plugin = MethodFactory.createMethod(method);
            if (plugin != null) Method = plugin;
        }

        return hasMethod();
    }

    public boolean checkDisabled(Plugin method) {
        if(!hasMethod()) return true;

        PluginDescriptionFile info = method.getDescription();
        String name = info.getName(), current = Method.getName();

        if (name.equalsIgnoreCase("iconomy") && current.equalsIgnoreCase("iconomy")) {
            if (method.getClass().getName().equals("com.iConomy.iConomy") && Method.getVersion().equals("5")) {
                Method = null;
            } else {
                Method = null;
            }
        } else if (name.equalsIgnoreCase("boseconomy") && current.equalsIgnoreCase("boseconomy")) {
            Method = null;
        } else if (name.equalsIgnoreCase("essentials") && current.equalsIgnoreCase("essentialseco")) {
            Method = null;
        }

        return (Method == null);
    }

    public boolean hasMethod() {
        return (Method != null);
    }

    public Method getMethod() {
        return Method;
    }
}
