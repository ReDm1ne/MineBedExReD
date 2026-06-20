package pl.minecodes.bungee.listener;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import pl.minecodes.minelogin.api.user.UserApi;
import pl.minecodes.minelogin.bungee.api.event.post.UserPostPasswordChangeEvent;
import pl.minecodes.minelogin.bungee.api.event.pre.UserPrePasswordChangeEvent;

public class UserPasswordChangeListener implements Listener {

  @EventHandler
  public void onUserPrePasswordChange(UserPrePasswordChangeEvent event) {
    UserApi user = event.getUser();
    ProxiedPlayer proxiedPlayer = event.getPlayer();
    if (proxiedPlayer == null) {
      System.out.println("[API] -> " + user.getUsername() + " is on pre password change!");
      return;
    }

    proxiedPlayer.sendMessage("[API] -> " + user.getUsername() + " is on pre password change!");
  }

  @EventHandler
  public void onUserPostPasswordChange(UserPostPasswordChangeEvent event) {
    UserApi user = event.getUser();
    ProxiedPlayer proxiedPlayer = event.getPlayer();
    if (proxiedPlayer == null) {
      System.out.println("[API] -> " + user.getUsername() + " is on post password change!");
      return;
    }

    proxiedPlayer.sendMessage("[API] -> " + user.getUsername() + " is on post password change!");
  }
}
