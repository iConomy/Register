package com.nijikokun.register.payment;


import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

public class Methods {
    private Method Method = null;
    
    public boolean setMethod(Plugin method) {
        //PluginManager loader = method.getServer().getPluginManager();

        if(method.isEnabled()) {
            Method pluginMethod = MethodFactory.createMethod(method);
            if (pluginMethod != null) {
                Method = pluginMethod;
            }
        }
        
        /*if(!hasMethod()) {
            if(loader.getPlugin("iConomy") != null) {
                method =  loader.getPlugin("iConomy");
                if(method.getClass().getName().equals("com.iConomy.iConomy"))
                    Method = new iCo5((iConomy)method);
                else { Method = new iCo4((com.nijiko.coelho.iConomy.iConomy)method); }
            } else if(loader.getPlugin("BOSEconomy") != null) {
                method = loader.getPlugin("BOSEconomy");
                Method = new BOSE((BOSEconomy)method);
            } else if(loader.getPlugin("Essentials") != null) {
                method = loader.getPlugin("Essentials");
                Method = new EE17((Essentials)method);
            }
        }*/
        
        return hasMethod();
    }

    public boolean checkDisabled(Plugin method) {
        PluginDescriptionFile info = method.getDescription();
        String name = info.getName();

        if(name.equalsIgnoreCase("iconomy")) {
            if(method.getClass().getName().equals("com.iConomy.iConomy"))
                Method = null;
            else { Method = null; }
        } else if(name.equalsIgnoreCase("boseconomy")) {
            Method = null;
        } else if(name.equalsIgnoreCase("essentials")) {
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
