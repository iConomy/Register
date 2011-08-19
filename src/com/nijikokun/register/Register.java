package com.nijikokun.register;

import java.util.logging.Level;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijikokun.register.payment.Methods;

public class Register extends JavaPlugin {

	@Override
	public void onDisable() {
		Methods.reset();
		
		PluginDescriptionFile pdfFile = this.getDescription();
		System.out.println( pdfFile.getName() + " is dissabled and methods have been reset!" );
	}

	@Override
	public void onEnable() {
		Methods.setVersion(this.getDescription().getVersion());
		
		Methods.setMethod(this.getServer().getPluginManager());
		
		PluginDescriptionFile pdfFile = this.getDescription();
		if (Methods.getMethod() == null) getServer().getLogger().log(Level.WARNING, 
				"[Register] No Meathod Found.  Plugins may not work");
		System.out.println( pdfFile.getName() + " version " + pdfFile.getVersion() + 
				" is enabled and avaiable for hooking!" );
	}

}
