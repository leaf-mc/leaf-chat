package mc.leaf.modules.chat.commands;

import mc.leaf.core.api.command.PluginCommandImpl;
import mc.leaf.core.api.command.annotations.Param;
import mc.leaf.core.api.command.annotations.Runnable;
import mc.leaf.core.api.command.annotations.Sender;
import mc.leaf.core.utils.MinecraftColors;
import mc.leaf.modules.chat.LeafChatModule;
import mc.leaf.modules.chat.converters.PlayerConverter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LeafChatCommand extends PluginCommandImpl {

    private final LeafChatModule module;

    public LeafChatCommand(LeafChatModule module) {

        super(module.getCore());
        this.module = module;
    }

    private Component getNotificationComponent(Player player, String name) {

        return Component.text().color(MinecraftColors.GRAY)
                .append(Component.text("#"))
                .append(Component.space())
                .append(Component.text(player.getName()))
                .append(Component.space())
                .append(Component.text("is now known as"))
                .append(Component.space())
                .append(Component.text(name).color(MinecraftColors.WHITE)).asComponent();
    }

    @Runnable("[name]")
    public void changeOwnNickname(@Sender Player player, @Param String name) {

        player.displayName(Component.text(name));
        Bukkit.broadcast(this.getNotificationComponent(player, name));
        this.module.getNicknames().put(player.getUniqueId(), Component.text(name));
    }

    @Runnable(value = "{player} [name]", opOnly = true, allowConsole = true)
    public void changeOtherNickname(@Sender CommandSender sender, @Param(converter = PlayerConverter.class) Player player, @Param String name) {

        player.displayName(Component.text(name));
        Bukkit.broadcast(this.getNotificationComponent(player, name));
        this.module.getNicknames().put(player.getUniqueId(), Component.text(name));
    }

}
