package com.LRFLEW.register;

import java.util.logging.Level;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

import com.LRFLEW.register.payment.Methods;

public class Register extends JavaPlugin {
    
    public Configuration config;
    public String preferred;

	@Override
	public void onDisable() {
		Methods.reset();
		
		PluginDescriptionFile pdfFile = this.getDescription();
		System.out.println( pdfFile.getName() + " is dissabled and methods have been reset!" );
	}

	@Override
	public void onEnable() {
		
		config = getConfiguration();
		preferred = config.getString("Preferred");
		
		if (Methods.setPreferred(preferred)){
			config.save();
			System.out.print("[Register] Preferred economy selected: " + preferred);
                } else {
			config.setProperty("Preferred", "");
			config.save();
                        System.out.print("[Register] Preferred economy not vaild! Cleared.");
		}
		
		Methods.setVersion(this.getDescription().getVersion());
		
		Methods.setMethod(this.getServer().getPluginManager());
		
		PluginDescriptionFile pdfFile = this.getDescription();
		if (Methods.getMethod() == null) getServer().getLogger().log(Level.WARNING, 
				"[Register] No Meathod Found.  Plugins may not work");
		System.out.print("[Register] loaded method: " + Methods.getMethod().getName());
		System.out.println( pdfFile.getName() + " version " + pdfFile.getVersion() + 
				" is enabled and avaiable for hooking!" );
	}

}
