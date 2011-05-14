package com.nijikokun.register.examples;

import com.nijikokun.register.examples.listeners.server;
import com.nijikokun.register.payment.Method;
import com.nijikokun.register.payment.Methods;

import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MyPlugin extends JavaPlugin {
    public PluginDescriptionFile info = null;
    public PluginManager pluginManager = null;

    // This is public so we can
    public Method Method = null;
    public Methods Methods = null;

    public void onDisable() { }

    public void onEnable() {
        this.Methods = new Methods();

        // Watch plugins enable / disable
        pluginManager.registerEvent(Event.Type.PLUGIN_ENABLE, new server(this), Priority.Monitor, this);
        pluginManager.registerEvent(Event.Type.PLUGIN_DISABLE, new server(this), Priority.Monitor, this);

        // Check after events, incase your plugin loads last.
        this.Methods.checkDisabled(null);

        if(this.Method != null) {
            this.Method = this.Methods.getMethod();
            System.out.println("[" + info.getName() + "] Payment method found (" + this.Method.getName() + " version: " + this.Method.getVersion() + ")");
        }
    }
}
