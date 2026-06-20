package pl.minecodes.bungee.listener;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import pl.minecodes.minelogin.api.user.UserApi;
import pl.minecodes.minelogin.bungee.api.event.post.UserPostRegisterEvent;
import pl.minecodes.minelogin.bungee.api.event.pre.UserPreRegisterEvent;

public class UserRegisterListener implements Listener {

  @EventHandler
  public void onUserPreRegister(UserPreRegisterEvent event) {
    UserApi user = event.getUser();
    ProxiedPlayer proxiedPlayer = event.getPlayer();
    if (proxiedPlayer == null) {
      System.out.println("[API] -> " + user.getUsername() + " is on pre register!");
      return;
    }

    proxiedPlayer.sendMessage("[API] -> " + user.getUsername() + " is on pre register!");
  }

  @EventHandler
  public void onUserPostRegister(UserPostRegisterEvent event) {
    UserApi user = event.getUser();
    ProxiedPlayer proxiedPlayer = event.getPlayer();
    if (proxiedPlayer == null) {
      System.out.println("[API] -> " + user.getUsername() + " is on post register!");
      return;
    }

    proxiedPlayer.sendMessage("[API] -> " + user.getUsername() + " is on post register!");
  }
}
