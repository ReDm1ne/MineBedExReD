package pl.minecodes.bungee;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import pl.minecodes.bungee.listener.UserChangeAccountTypeListener;
import pl.minecodes.bungee.listener.UserLoginFailedListener;
import pl.minecodes.bungee.listener.UserLoginListener;
import pl.minecodes.bungee.listener.UserLoginMaxAttemptsListener;
import pl.minecodes.bungee.listener.UserPasswordChangeListener;
import pl.minecodes.bungee.listener.UserRegisterListener;
import pl.minecodes.bungee.listener.UserUnregisterListener;

public class BungeePlugin extends Plugin {

  @Override
  public void onEnable() {
    ProxyServer proxy = this.getProxy();

    PluginManager pluginManager = proxy.getPluginManager();

    pluginManager.registerListener(this, new UserChangeAccountTypeListener());
    pluginManager.registerListener(this, new UserLoginFailedListener());
    pluginManager.registerListener(this, new UserLoginListener());
    pluginManager.registerListener(this, new UserLoginMaxAttemptsListener());
    pluginManager.registerListener(this, new UserPasswordChangeListener());
    pluginManager.registerListener(this, new UserRegisterListener());
    pluginManager.registerListener(this, new UserUnregisterListener());
  }
}
