package pl.minecodes.bungee.listener;

import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import pl.minecodes.minelogin.api.user.UserApi;
import pl.minecodes.minelogin.bungee.api.event.UserLoginMaxAttemptsEvent;

public class UserLoginMaxAttemptsListener implements Listener {

  @EventHandler
  public void onUserLoginMaxAttempts(UserLoginMaxAttemptsEvent event) {
    UserApi user = event.getUser();
    System.out.println("[API] -> " + user.getUsername() + " is on login max attempts!");
  }
}
