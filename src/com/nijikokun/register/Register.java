package com.nijikokun.register;

import java.io.FileInputStream;
import java.util.Properties;
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
		String prefered = "";
		try {
			FileInputStream in = new FileInputStream("server.properties");
			Properties p = new Properties();
			p.load(in);
			prefered = p.getProperty("economy");
			if (prefered == null) prefered = "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		String version = this.getDescription().getVersion();
		Methods.setup(version, prefered);
		
		Methods.setMethod(this.getServer().getPluginManager());
		
		PluginDescriptionFile pdfFile = this.getDescription();
		if (Methods.getMethod() == null) getServer().getLogger().log(Level.WARNING, 
				"[Register] No Meathod Found.  Plugins may not work");
		System.out.println( pdfFile.getName() + " version " + pdfFile.getVersion() + 
				" is enabled and avaiable for hooking!" );
	}

}
