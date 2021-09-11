package de.shiirroo.manhunt.command.subcommands;

import de.shiirroo.manhunt.ManHuntPlugin;
import de.shiirroo.manhunt.command.CommandBuilder;
import de.shiirroo.manhunt.command.SubCommand;
import de.shiirroo.manhunt.event.menu.MenuManager;
import de.shiirroo.manhunt.event.menu.MenuManagerException;
import de.shiirroo.manhunt.event.menu.MenuManagerNotSetupException;
import de.shiirroo.manhunt.event.menu.menus.ClockMenu;
import de.shiirroo.manhunt.event.menu.menus.PlayerMenu;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.*;

public class Help extends SubCommand {

    private static ArrayList<SubCommand> getSubCommands;

    public Help(ArrayList<SubCommand> getSubCommands) {
        Help.getSubCommands = getSubCommands;
    }


    @Override
    public String getName() {
        return "Help";
    }

    @Override
    public String getDescription() {
        return "It will show your information about the plugin";
    }

    @Override
    public String getSyntax() {
        return "/ManHunt Help";
    }

    @Override
    public Boolean getNeedOp() {
        return false;
    }


    @Override
    public CommandBuilder getSubCommandsArgs(String[] args)  {
        CommandBuilder help = new CommandBuilder("Help");
        for (int i = 0; i != ((getSubCommands.size()) /5  + 1); i++) {
            help.addSubCommandBuilder(new CommandBuilder(String.valueOf(i + 1)));
        }
        return help;
    }


    @Override
    public void perform(Player p, String[] args) throws MenuManagerException, MenuManagerNotSetupException {
        if(args.length == 1){
            p.sendMessage(ChatColor.WHITE +"--- "+ChatColor.AQUA+"Show Help Information about ManHunt "+ChatColor.WHITE +"--- "+ChatColor.GREEN +"Page " +ChatColor.GOLD+ (1)+ChatColor.WHITE+ " | "  +ChatColor.GOLD+  ((getSubCommands.size()) /5  + 1) +ChatColor.WHITE+" ---");
            for(int i=0;i<=4;i++){
                p.sendMessage(ChatColor.GOLD + getSubCommands.get(i).getSyntax() + ": " + ChatColor.GRAY + getSubCommands.get(i).getDescription());
            }
            MenuManager.openMenu(ClockMenu.class, p, null, ManHuntPlugin.getPlayerData()).open("");


        } else if(args.length == 2 && ConfigManHunt.isNumeric(args[1]) && !args[1].equalsIgnoreCase("0") ){
            Integer page = Integer.parseInt(args[1]) - 1;
            if(0 +(5 * page) < getSubCommands.size()) {
                Integer CommandSize = 4 + (5 * page);
                if(CommandSize >= getSubCommands.size()){
                    CommandSize = getSubCommands.size() - 1;
                }

                p.sendMessage(ChatColor.WHITE +"--- "+ChatColor.AQUA+"Show Help Information about ManHunt "+ChatColor.WHITE +"--- "+ChatColor.GREEN +"Page " +ChatColor.GOLD+ (page + 1)+ChatColor.WHITE+ " | "  +ChatColor.GOLD+  ((getSubCommands.size()) /5  + 1) +ChatColor.WHITE+" ---");
                for (int i = (0 + (5 * page)); i <= CommandSize; i++) {
                    p.sendMessage(ChatColor.GOLD + getSubCommands.get(i).getSyntax() + ": " + ChatColor.GRAY + getSubCommands.get(i).getDescription());
                }
            }
        }



        /*for (SubCommand getSubCommand : getSubCommands) {
            p.sendMessage(ChatColor.GOLD + getSubCommand.getSyntax() + ": " + ChatColor.GRAY + getSubCommand.getDescription());
        }*/

    }


}
