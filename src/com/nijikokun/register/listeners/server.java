package com.nijikokun.register.listeners;

// Imports for MyPlugin
import com.nijikokun.register.Register;
import com.nijikokun.register.payment.Methods;

// Bukkit Imports
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;

public class server implements Listener {
    private Register plugin;

    public server(Register plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPluginDisable(PluginDisableEvent event) {
        // Check to see if the plugin thats being disabled is the one we are using
        if (Methods.hasMethod()) {
            if(Methods.checkDisabled(event.getPlugin())) {
                Methods.reset();
                System.out.println("[" + plugin.info.getName() + "] Payment method was disabled. No longer accepting payments.");
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPluginEnable(PluginEnableEvent event) {
        // Check to see if we need a payment method
        if (!Methods.hasMethod() && Methods.setMethod(plugin.getServer().getPluginManager())) {
            System.out.println("[" + plugin.info.getName() + "] Payment method found (" + Methods.getMethod().getName() + " version: " + Methods.getMethod().getVersion() + ")");
        }
    }
}