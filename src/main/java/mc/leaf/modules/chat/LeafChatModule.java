package mc.leaf.modules.chat;

import mc.leaf.core.interfaces.ILeafCore;
import mc.leaf.core.interfaces.ILeafModule;
import mc.leaf.modules.chat.commands.LeafChatCommand;
import mc.leaf.modules.chat.listeners.LeafChatListener;
import mc.leaf.modules.chat.listeners.LeafChatNamePatcher;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class LeafChatModule implements ILeafModule {

    private final JavaPlugin           plugin;
    private final ILeafCore            core;
    private       boolean              enabled = false;
    private       Map<UUID, Component> nicknames;

    public LeafChatModule(JavaPlugin plugin, ILeafCore core) {

        this.plugin = plugin;
        this.core   = core;
        this.core.registerModule(this);
    }

    @Override
    public void onEnable() {

        Optional.ofNullable(Bukkit.getPluginCommand("nickname"))
                .ifPresent(command -> command.setExecutor(new LeafChatCommand(this)));

        this.getCore().getEventBridge().register(this, new LeafChatListener());
        this.getCore().getEventBridge().register(this, new LeafChatNamePatcher(this));

        this.nicknames = new HashMap<>();
        for (Player players : Bukkit.getOnlinePlayers()) {
            nicknames.put(players.getUniqueId(), players.displayName());
        }

        this.enabled = true;
    }

    @Override
    public void onDisable() {

        Optional.ofNullable(Bukkit.getPluginCommand("nickname")).ifPresent(command -> command.setExecutor(null));
        this.nicknames.clear();
        this.nicknames = null;
        this.enabled   = false;
    }

    @Override
    public ILeafCore getCore() {

        return this.core;
    }

    @Override
    public String getName() {

        return "Chat";
    }

    @Override
    public boolean isEnabled() {

        return this.enabled;
    }

    @Override
    public JavaPlugin getPlugin() {

        return this.plugin;
    }

    public Map<UUID, Component> getNicknames() {

        return nicknames;
    }

}
