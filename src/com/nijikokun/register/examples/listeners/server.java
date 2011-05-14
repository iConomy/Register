package com.nijikokun.register.examples.listeners;

// Example plugin
import com.nijikokun.register.examples.MyPlugin;

// Imports for Register
import com.nijikokun.register.payment.Method;
import com.nijikokun.register.payment.Methods;

// Bukkit Imports
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.server.ServerListener;

public class server extends ServerListener {
    // Change "MyPlugin" to the name of your MAIN class file.
    // Let's say my plugins MAIN class is: Register.java
    // I would change "MyPlugin" to "Register"
    private MyPlugin plugin;

    public server(MyPlugin plugin) {
        this.plugin = plugin;
        this.plugin.Methods = new Methods();
    }

    @Override
    public void onPluginDisable(PluginDisableEvent event) {
        // Check to see if the plugin thats being disabled is the one we are using
        if (this.plugin.Methods != null && this.plugin.Methods.hasMethod()) {
            Boolean check = this.plugin.Methods.checkDisabled(event.getPlugin());

            if(check) {
                this.plugin.Method = null;
                System.out.println("[" + plugin.info.getName() + "] Payment method was disabled. No longer accepting payments.");
            }
        }
    }

    @Override
    public void onPluginEnable(PluginEnableEvent event) {
        // Check to see if we need a payment method
        if (!this.plugin.Methods.hasMethod()) {
            if(this.plugin.Methods.setMethod(event.getPlugin())) {
                // You might want to make this a public variable inside your MAIN class public Method Method = null;
                // then reference it through this.plugin.Method so that way you can use it in the rest of your plugin ;)
                this.plugin.Method = this.plugin.Methods.getMethod();
            }
        }
    }
}