package net.redm1ne.minebedexred;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class ReloadCommand extends Command {

    private final MineBedExReD plugin;

    public ReloadCommand(MineBedExReD plugin) {
        super("mbex", "minebedex.admin");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            plugin.loadConfig();
            sender.sendMessage(new TextComponent("§a[MineBedExReD] Configuración recargada exitosamente."));
        } else {
            sender.sendMessage(new TextComponent("§cUso: /mbex reload"));
        }
    }
}