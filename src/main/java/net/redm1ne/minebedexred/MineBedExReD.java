package net.redm1ne.minebedexred;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class MineBedExReD extends Plugin {

	private static MineBedExReD instance;
	private Configuration config;
	private FormManager formManager;

	@Override
	public void onEnable() {
		instance = this;
		loadConfig();

		this.formManager = new FormManager(this);

		getProxy().getPluginManager().registerListener(this, new EventListener(this));
		getProxy().getPluginManager().registerCommand(this, new ReloadCommand(this));

		getLogger().info("MineBedExReD se ha activado correctamente.");
	}

	public static MineBedExReD getInstance() {
		return instance;
	}

	public void loadConfig() {
		try {
			if (!getDataFolder().exists()) {
				getDataFolder().mkdir();
			}
			File file = new File(getDataFolder(), "config.yml");
			if (!file.exists()) {
				try (InputStream in = getResourceAsStream("config.yml")) {
					Files.copy(in, file.toPath());
				}
			}
			config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Configuration getConfig() {
		return config;
	}

	public FormManager getFormManager() {
		return formManager;
	}
}