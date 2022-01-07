package mc.leaf.modules.chat.listeners;

import io.papermc.paper.chat.ChatRenderer;
import io.papermc.paper.event.player.AsyncChatEvent;
import mc.leaf.core.events.LeafListener;
import mc.leaf.core.utils.MinecraftColors;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LeafChatListener extends LeafListener {

    @Override
    public void onAsyncChat(AsyncChatEvent event) {

        ChatRenderer renderer = ChatRenderer.viewerUnaware((source, sourceDisplayName, message) -> {

            Component originalName = Component.text()
                    .append(Component.text("Player Name: "))
                    .append(Component.text(source.getName()))
                    .asComponent();

            Component displayName = sourceDisplayName.hoverEvent(HoverEvent.showText(originalName))
                    .style(Style.style(MinecraftColors.AQUA, TextDecoration.BOLD));

            LocalTime         time      = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String            timeStr   = time.format(formatter);

            Component timeComponent = Component.text(timeStr).color(MinecraftColors.GRAY);
            Component worldComponent = Component
                    .text(event.getPlayer().getWorld().getName().substring(0, 1).toUpperCase())
                    .hoverEvent(HoverEvent.showText(Component.text("World: " + event.getPlayer().getWorld().getName())))
                    .color(MinecraftColors.GOLD);

            return Component.text()
                    .append(worldComponent)
                    .append(Component.space())
                    .append(timeComponent)
                    .append(Component.space())
                    .append(displayName)
                    .append(Component.space())
                    .append(message)
                    .asComponent();
        });
        event.renderer(renderer);
    }

}
