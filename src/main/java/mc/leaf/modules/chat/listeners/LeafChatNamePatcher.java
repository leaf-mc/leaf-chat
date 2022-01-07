package mc.leaf.modules.chat.listeners;

import mc.leaf.core.events.LeafListener;
import mc.leaf.modules.chat.LeafChatModule;
import net.kyori.adventure.text.Component;
import org.bukkit.event.player.PlayerJoinEvent;

public class LeafChatNamePatcher extends LeafListener {

    private final LeafChatModule module;

    public LeafChatNamePatcher(LeafChatModule module) {

        this.module = module;
    }

    @Override
    public void onPlayerJoin(PlayerJoinEvent event) {

        event.getPlayer().displayName(this.module.getNicknames()
                .getOrDefault(event.getPlayer().getUniqueId(), Component.text(event.getPlayer().getName())));
    }

}
