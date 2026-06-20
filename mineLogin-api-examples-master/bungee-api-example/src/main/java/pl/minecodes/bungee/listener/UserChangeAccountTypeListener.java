package pl.minecodes.bungee.listener;

import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import pl.minecodes.minelogin.api.user.UserApi;
import pl.minecodes.minelogin.bungee.api.event.UserChangeAccountTypeEvent;

public class UserChangeAccountTypeListener implements Listener {

  @EventHandler
  public void onUserChangeAccountType(UserChangeAccountTypeEvent event) {
    UserApi user = event.getUser();
    System.out.println("[API] -> " + user.getUsername() + " is on change account type!");
  }
}
