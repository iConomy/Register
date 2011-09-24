package com.nijikokun.register.listeners;

// Imports for MyPlugin
import com.nijikokun.register.Register;
import com.nijikokun.register.payment.Methods;

// Bukkit Imports
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.server.ServerListener;

public class server extends ServerListener {
    private Register plugin;
    private Methods Methods = null;

    public server(Register plugin) {
        this.plugin = plugin;
        this.Methods = new Methods();
    }

    @Override
    public void onPluginDisable(PluginDisableEvent event) {
        // Check to see if the plugin thats being disabled is the one we are using
        if (this.Methods != null && this.Methods.hasMethod()) {
            Boolean check = this.Methods.checkDisabled(event.getPlugin());

            if(check) {
                Methods.reset();
                System.out.println("[" + plugin.info.getName() + "] Payment method was disabled. No longer accepting payments.");
            }
        }
    }

    @Override
    public void onPluginEnable(PluginEnableEvent event) {
        // Check to see if we need a payment method
        if (!this.Methods.hasMethod()) {
            if(this.Methods.setMethod(plugin.getServer().getPluginManager())) {
                if(this.Methods.hasMethod())
                    System.out.println("[" + plugin.info.getName() + "] Payment method found (" + this.Methods.getMethod().getName() + " version: " + this.Methods.getMethod().getVersion() + ")");
            }
        }
    }
}