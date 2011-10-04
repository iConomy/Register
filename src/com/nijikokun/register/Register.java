package com.nijikokun.register;

import com.nijikokun.register.listeners.server;
import com.nijikokun.register.payment.Methods;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

import java.io.File;

/**
 * Register
 *
 * Initializes on startup and attaches to preferred method or
 * first found method.
 *
 * @author Nijikokun <nijikokun@shortmail.com> (@nijikokun)
 * @author LRFLEW
 * @author Spice-King
 * @copyright (c) 2011
 * @license AOL license <http://aol.nexua.org>
 */
public class Register extends JavaPlugin {

    public Configuration config;
    public String preferred;
    public PluginDescriptionFile info;

    private String getPreferred() {
        return config.getString("economy.preferred");
    }

    private void setPreferred(String preferences) {
        config.setProperty("economy.preferred", preferences);
        config.save();
    }

    private boolean hasPreferred() {
        return Methods.setPreferred(getPreferred());
    }

    public void onDisable() {
        Methods.reset();
    }

    @Override
    public void onLoad() {
        config = new Configuration(new File("bukkit.yml"));
        info = this.getDescription();
        config.load();

        if (!hasPreferred()) {
            System.out.println("[" + info.getName() + "] Preferred method [" + getPreferred() + "] not found, using first found.");

            Methods.setVersion(info.getVersion());
            Methods.setMethod(this.getServer().getPluginManager());
        }

        if (Methods.getMethod() != null)
            System.out.println("[" + info.getName() + "] Payment method found (" + Methods.getMethod().getName() + " version: " + Methods.getMethod().getVersion() + ")");

        System.out.print("[" + info.getName() + "] version " + info.getVersion()+ " is enabled.");
    }
    //No override, as we're using Java version 1.5
    public void onEnable() {
        this.getServer().getPluginManager().registerEvent(Type.PLUGIN_ENABLE, new server(this), Priority.Low, this);
        this.getServer().getPluginManager().registerEvent(Type.PLUGIN_DISABLE, new server(this), Priority.Low, this);
    }
}
