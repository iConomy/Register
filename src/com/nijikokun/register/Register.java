package com.nijikokun.register;

import com.nijikokun.register.listeners.server;
import com.nijikokun.register.payment.Methods;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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

	private File configFile = new File("bukkit.yml");
    public YamlConfiguration config = new YamlConfiguration();
    public String preferred;
    public PluginDescriptionFile info;
    public server serverListener = new server(this);

    private String getPreferred() {
        return config.getString("economy.preferred");
    }

    @SuppressWarnings("unused")
	private void setPreferred(String preferences) {
        config.set("economy.preferred", preferences);
        try {
			config.save(configFile);
		} catch (IOException e) {
		}
    }

    private boolean hasPreferred() {
        return Methods.setPreferred(getPreferred());
    }

    public void onDisable() {
        Methods.reset();
    }

    @Override
    public void onLoad() {
        try {
			config.load(configFile);
		
	        info = this.getDescription();
	
	        if (!hasPreferred()) {
	            System.out.println("[" + info.getName() + "] Preferred method [" + getPreferred() + "] not found, using first found.");
	
	            Methods.setVersion(info.getVersion());
	            Methods.setMethod(this.getServer().getPluginManager());
	        }
	
	        if (Methods.getMethod() != null)
	            System.out.println("[" + info.getName() + "] Payment method found (" + Methods.getMethod().getName() + " version: " + Methods.getMethod().getVersion() + ")");
	
	        System.out.print("[" + info.getName() + "] version " + info.getVersion()+ " is enabled.");
        } catch (FileNotFoundException e) {
		} catch (IOException e) {
		} catch (InvalidConfigurationException e) {
		}
    }
    //No override, as we're using Java version 1.5
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(serverListener, this);
    }
}
