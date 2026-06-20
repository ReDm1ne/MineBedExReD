package pl.minecodes.bungee.listener;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import pl.minecodes.minelogin.api.user.UserApi;
import pl.minecodes.minelogin.bungee.api.event.post.UserPostUnregisterEvent;
import pl.minecodes.minelogin.bungee.api.event.pre.UserPreUnregisterEvent;

public class UserUnregisterListener implements Listener {

  @EventHandler
  public void onUserPreUnregister(UserPreUnregisterEvent event) {
    UserApi user = event.getUser();
    ProxiedPlayer proxiedPlayer = event.getPlayer();
    if (proxiedPlayer == null) {
      System.out.println("[API] -> " + user.getUsername() + " is on pre unregister!");
      return;
    }

    proxiedPlayer.sendMessage("[API] -> " + user.getUsername() + " is on pre unregister!");
  }

  @EventHandler
  public void onUserPostUnregister(UserPostUnregisterEvent event) {
    UserApi user = event.getUser();
    ProxiedPlayer proxiedPlayer = event.getPlayer();
    if (proxiedPlayer == null) {
      System.out.println("[API] -> " + user.getUsername() + " is on post unregister!");
      return;
    }

    proxiedPlayer.sendMessage("[API] -> " + user.getUsername() + " is on post unregister!");
  }
}
