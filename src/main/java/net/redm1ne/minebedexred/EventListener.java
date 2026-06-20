package net.redm1ne.minebedexred;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import org.geysermc.floodgate.api.FloodgateApi;
import pl.minecodes.minelogin.api.user.UserApi;
import pl.minecodes.minelogin.bungee.api.event.UserLoginFailedEvent;

public class EventListener implements Listener {

    private final MineBedExReD plugin;

    public EventListener(MineBedExReD plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPostLogin(PostLoginEvent event) {
        ProxiedPlayer player = event.getPlayer();
        if (FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId())) {
            plugin.getFormManager().scheduleInitialForm(player);
        }
    }

    @EventHandler
    public void onLoginFailed(UserLoginFailedEvent event) {
        // Obtenemos el usuario de la API de MineLogin
        UserApi user = event.getUser();

        // Obtenemos al jugador real del proxy usando el UUID
        ProxiedPlayer player = plugin.getProxy().getPlayer(user.getUniqueId());

        if (player != null && FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId())) {
            // El jugador se equivocó. Esperamos el delay para que lea el mensaje de MineLogin y reabrimos el menú.
            plugin.getFormManager().scheduleReopen(player);
        }
    }
}