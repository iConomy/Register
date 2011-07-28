package com.nijikokun.register.example;

import com.nijikokun.register.example.listeners.server;
import com.nijikokun.register.payment.Method;

import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Example plugin that utilizes register.
 *
 * @author Nijikokun <nijikokun@shortmail.com> (@nijikokun)
 * @copyright (c) 2011
 * @license AOL license <http://aol.nexua.org>
 */
public class MyPlugin extends JavaPlugin {
    public PluginDescriptionFile info = null;
    public PluginManager pluginManager = null;

    // This is public so we can
    public Method Method = null;

    public void onDisable() {
        info = null;
        Method = null;
        pluginManager = null;
    }

    public void onEnable() {
        info = getDescription();
        pluginManager = getServer().getPluginManager();

        pluginManager.registerEvent(Event.Type.PLUGIN_ENABLE, new server(this), Priority.Monitor, this);
        pluginManager.registerEvent(Event.Type.PLUGIN_DISABLE, new server(this), Priority.Monitor, this);

        System.out.println("["+ info.getName() +"] Enabled.");
    }
}
