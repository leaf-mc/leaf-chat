package mc.leaf.modules.chat;

import mc.leaf.core.interfaces.ILeafCore;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class LeafChat extends JavaPlugin {

    public static final String PREFIX = "§l[§aLeaf§bChat§r§l]§r";

    @Override
    public void onEnable() {
        // Plugin startup logic
        Plugin plugin = Bukkit.getPluginManager().getPlugin("LeafCore");
        if (plugin instanceof ILeafCore core) {
            new LeafChatModule(this, core);
        } else {
            this.getLogger().severe("Unable to find LeafCore instance.");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
