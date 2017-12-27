package pl.artur9010.FlarumAuth.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.artur9010.FlarumAuth.FlarumAuthPlugin;

public class LoginCommand implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
       if(!(sender instanceof Player)){
           sender.sendMessage("To use LOGIN command, you must be player m8.");
           return true;
       }
       Player player = (Player)sender;
       if(!FlarumAuthPlugin.accountExist(player.getName())){
           player.kickPlayer("Nie posiadasz konta na serwerze.");
       }
       if(args.length == 0){
           sender.sendMessage("Poprawne uzycie: " + ChatColor.AQUA + "/login <haslo>");
           return true;
       }
       if(FlarumAuthPlugin.logIn(player.getName(), args[0])){
            sender.sendMessage(ChatColor.GREEN + "Pomyslnie zalogowano.");
       }else{
           sender.sendMessage(ChatColor.RED + "Niepoprawne haslo. Jesli nie pamietasz swojego hasla mozesz je zresetowac na stronie www.ucraft.pl");
           return true;
       }
        return true;
    }
}
