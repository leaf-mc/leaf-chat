package mc.leaf.modules.chat.converters;

import mc.leaf.core.api.command.interfaces.IParameterConverter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerConverter implements IParameterConverter<String, Player> {

    @Override
    public Player convert(String in) {

        return Bukkit.getPlayer(in);
    }

}
