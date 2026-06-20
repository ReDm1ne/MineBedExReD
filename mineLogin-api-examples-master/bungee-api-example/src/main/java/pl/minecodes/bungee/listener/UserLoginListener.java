package pl.minecodes.bungee.listener;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import pl.minecodes.minelogin.api.user.UserApi;
import pl.minecodes.minelogin.bungee.api.event.post.UserPostLoginEvent;
import pl.minecodes.minelogin.bungee.api.event.pre.UserPreLoginEvent;

public class UserLoginListener implements Listener {

  @EventHandler
  public void onUserPreLogin(UserPreLoginEvent event) {
    UserApi user = event.getUser();

    ProxiedPlayer proxiedPlayer = event.getPlayer();
    if (proxiedPlayer == null) {
      System.out.println("[API] -> " + user.getUsername() + " is on pre logged in!");
      return;
    }

    proxiedPlayer.sendMessage("[API] -> " + user.getUsername() + " is on pre logged in!");
  }

  @EventHandler
  public void onUserPostLogin(UserPostLoginEvent event) {
    UserApi user = event.getUser();

    ProxiedPlayer proxiedPlayer = event.getPlayer();
    if (proxiedPlayer == null) {
      System.out.println("[API] -> " + user.getUsername() + " is on post logged in!");
      return;
    }

    proxiedPlayer.sendMessage("[API] -> " + user.getUsername() + " is on post logged in!");
  }
}
