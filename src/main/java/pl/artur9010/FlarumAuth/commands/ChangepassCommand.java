package pl.artur9010.FlarumAuth.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.artur9010.FlarumAuth.FlarumAuthPlugin;

public class ChangepassCommand implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
       sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lZmiana hasla na serwerze:"));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&fWejdz na strone &b&nwww.ucraft.pl&f i zaloguj sie swoim haslem, po zalogowaniu przejdz do ustawien konta (prawy gorny rog - tam gdzie widzisz nick) i wybierz opcje Zmien haslo."));
       return true;
    }
}
