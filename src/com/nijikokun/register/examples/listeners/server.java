package com.nijikokun.register.examples.listeners;

// Imports for Register
import com.nijikokun.register.payment.Method;
import com.nijikokun.register.payment.Methods;

// Bukkit Imports
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.server.ServerListener;

public class server extends ServerListener {
    // Change "Object" to the name of your MAIN class file: IE in iConomy you would use iConomy.. and so forth.
    private Object plugin;
    private Methods Methods;

    public server(Object plugin) {
        this.plugin = plugin;
        this.Methods = new Methods();
    }

    @Override
    public void onPluginDisable(PluginDisableEvent event) {
        // Check to see if the plugin thats being disabled is the one we are using
        if (this.Methods != null && this.Methods.hasMethod()) {
            Boolean check = this.Methods.checkDisabled(event.getPlugin());

            if(check) {
                System.out.println("[Plugin] Payment method was disabled. No longer accepting payments.");
            }
        }
    }

    @Override
    public void onPluginEnable(PluginEnableEvent event) {
        // Check to see if we need a payment method
        if (!this.Methods.hasMethod()) {
            if(this.Methods.setMethod(event.getPlugin())) {
                // You might want to make this a public variable inside your MAIN class public Method Method = null;
                // then reference it through this.plugin.Method so that way you can use it in the rest of your plugin ;)
                Method method = this.Methods.getMethod();
                System.out.println("[Plugin] Payment method found (" + method.getName() + " version: " + method.getVersion() + ")");
            }
        }
    }
}