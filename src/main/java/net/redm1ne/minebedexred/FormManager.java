package net.redm1ne.minebedexred;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.geysermc.cumulus.form.CustomForm;
import org.geysermc.cumulus.response.CustomFormResponse;
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.floodgate.api.player.FloodgatePlayer;

import java.util.concurrent.TimeUnit;

public class FormManager {

    private final MineBedExReD plugin;

    public FormManager(MineBedExReD plugin) {
        this.plugin = plugin;
    }

    public void scheduleInitialForm(ProxiedPlayer player) {
        long delayMs = plugin.getConfig().getLong("initial-open-delay-ms", 1000);
        plugin.getProxy().getScheduler().schedule(plugin, () -> sendForm(player), delayMs, TimeUnit.MILLISECONDS);
    }

    public void scheduleReopen(ProxiedPlayer player) {
        long delaySecs = plugin.getConfig().getLong("reopen-delay-seconds", 4);
        plugin.getProxy().getScheduler().schedule(plugin, () -> sendForm(player), delaySecs, TimeUnit.SECONDS);
    }

    private void sendForm(ProxiedPlayer player) {
        if (player == null || !player.isConnected()) return;

        FloodgatePlayer fPlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());
        if (fPlayer == null) return;

        // Construir el formulario explícitamente evitando constructores externos.
        CustomForm.Builder formBuilder = CustomForm.builder()
                .title(plugin.getConfig().getString("form.title", "§l§bRegistro / Login"))
                .label(plugin.getConfig().getString("form.description", "Por favor, introduce tu contraseña para continuar."))
                .input(plugin.getConfig().getString("form.input-text", "Contraseña:"), plugin.getConfig().getString("form.input-placeholder", "Tu contraseña secreta"))
                .toggle(plugin.getConfig().getString("form.toggle-recovery", "¿Olvidaste tu contraseña?"), false);

        formBuilder.validResultHandler((CustomFormResponse response) -> {
            String password = response.asInput(1);
            Boolean recovery = response.asToggle(2);

            if (recovery != null && recovery) {
                String recCmd = plugin.getConfig().getString("form.recovery-command", "/recuperar");
                player.chat(recCmd);
            } else if (password != null && !password.isEmpty()) {
                player.chat("/login " + password);
            } else {
                scheduleReopen(player);
            }
        });

        formBuilder.closedOrInvalidResultHandler(() -> {
            scheduleReopen(player);
        });

        fPlayer.sendForm(formBuilder.build());
    }
}