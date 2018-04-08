package come.cocopixelmc.FastSpawn;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import come.cocopixelmc.FastSpawn.team.Teams;

public class Cmd implements Listener, CommandExecutor {

	private final main plugin;

	public Cmd(main plugin) {
		this.plugin=plugin;
	    plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		if(cmd.getName().equalsIgnoreCase("fastspawn") || cmd.getName().equalsIgnoreCase("fs")){
			
			if(args.length >= 1){
				
				Player player = ((Player) sender).getPlayer();
				
				//join
				if(args[0].equalsIgnoreCase("join")){
					if(!Teams.getPlayerInWhereArea().containsKey(player)){
						if(args.length >= 2){
							if(plugin.getConfig().getConfigurationSection("area").contains(args[1])){
							
								Teams.JoinTeam(player,args[1]);
							
							}else{
								player.sendMessage(ChatColor.RED + "場地不存在");
							}
						}else{
							player.sendMessage(ChatColor.RED + "請輸入場地名稱");
						}
					}else{
						player.sendMessage(ChatColor.RED + "You is in game");
					}
				}else
				
				//quit
				if(args[0].equalsIgnoreCase("quit")){
					if(Teams.getPlayerInWhereArea().containsKey(player)){
						Teams.QuitTeam(player);
					}else{
						player.sendMessage(ChatColor.RED + "You not in game");
					}
				}else
				
				//create
				if(args[0].equalsIgnoreCase("create")){
					if(player.hasPermission("FastSpawn.admin")){
						if(args.length >= 2){
						
							plugin.getConfig().createSection("area."+args[1]);
							plugin.getConfig().createSection("area."+args[1]+".world");
							plugin.getConfig().createSection("area."+args[1]+".Location");
						
							plugin.saveConfig();
							plugin.reloadConfig();
						
							Teams.createNewTeam(args[1]);
						
							player.sendMessage(ChatColor.GREEN + "Area is create");
						
						}else{
							player.sendMessage(ChatColor.RED + "請輸入名稱");
						}
					}else{
						player.sendMessage(ChatColor.RED + "You not have permission");
					}
				}else
				
				//list
				if(args[0].equalsIgnoreCase("list")){
					if(player.hasPermission("FastSpawn.admin")){
						if(plugin.getConfig().getConfigurationSection("area").getKeys(false) != null){
							
							sender.sendMessage(ChatColor.GREEN+"目前有的場地: ");
							for(String area : plugin.getConfig().getConfigurationSection("area").getKeys(false)){
								sender.sendMessage(area);
							}
							
						}else{
							player.sendMessage(ChatColor.RED + "沒有任何場地被創建");
						}
					}else{
						player.sendMessage(ChatColor.RED + "You not have permission");
					}
				}else
				
				//remove
				if(args[0].equalsIgnoreCase("remove")){
					if(player.hasPermission("FastSpawn.admin")){
						if(args.length >= 2){
							if(plugin.getConfig().getConfigurationSection("area").contains(args[1])){
							
								plugin.getConfig().set("area." + args[1], null);
								plugin.saveConfig();
								plugin.reloadConfig();
								
								Teams.unregisterTeam(args[1]);
								sender.sendMessage(ChatColor.GREEN+"場地已刪除");
								
							}else{
								player.sendMessage(ChatColor.RED + "場地不存在");
							}
						}else{
							player.sendMessage(ChatColor.RED + "請輸入名稱");
						}
					}else{
						player.sendMessage(ChatColor.RED + "You not have permission");
					}
				}else
				
				//set
				if(args[0].equalsIgnoreCase("set")){
					if(player.hasPermission("FastSpawn.admin")){
						if(args.length >= 2){
							
							if(args[1].equalsIgnoreCase("spawn")){
								if(args.length >= 3){
									if(plugin.getConfig().getConfigurationSection("area").contains(args[2])){
									
										String world = player.getWorld().getName();
										int x = (int) player.getLocation().getX();
										int y = (int) player.getLocation().getY();
										int z = (int) player.getLocation().getZ();
								
										plugin.getConfig().set("area."+ args[2] +".world", world);
										String Location = x+","+y+","+z;
										plugin.getConfig().set("area."+args[2]+".Location", Location);
								
										plugin.saveConfig();
										plugin.reloadConfig();
								
										player.sendMessage(ChatColor.GREEN + "Spawn is save");
									}else{
										player.sendMessage(ChatColor.RED + "場地不存在");
									}
								}else{
									player.sendMessage(ChatColor.RED + "請輸入名稱");
								}
							}else
						
							if(args[1].equalsIgnoreCase("lobby")){
								
								String world = player.getWorld().getName();
								int x = (int) player.getLocation().getX();
								int y = (int) player.getLocation().getY();
								int z = (int) player.getLocation().getZ();
								
								plugin.getConfig().set("lobby.world", world);
								String Location = x+","+y+","+z;
								plugin.getConfig().set("lobby.Location", Location);
								
								plugin.saveConfig();
								plugin.reloadConfig();
								
								player.sendMessage(ChatColor.GREEN + "lobby is save");
								
							}else{
								player.sendMessage(ChatColor.RED + "/fs set spawn <AreaName>   -->  set game spawn");
								player.sendMessage(ChatColor.RED + "/fs set lobby   -->  set game lobby");
								player.sendMessage(ChatColor.RED + "/fs remove <AreaName>  -->  remove Area");
								player.sendMessage(ChatColor.RED + "/fs list   -->  list Area");
							}
							
						}else{
							player.sendMessage(ChatColor.RED + "/fs set spawn <AreaName>   -->  set game spawn");
							player.sendMessage(ChatColor.RED + "/fs set lobby   -->  set game lobby");
							player.sendMessage(ChatColor.RED + "/fs remove <AreaName>  -->  remove Area");
							player.sendMessage(ChatColor.RED + "/fs list   -->  list Area");
						}
					}else{
						player.sendMessage(ChatColor.RED + "You not have permission");
					}
					
				}else{
					sender.sendMessage(ChatColor.RED + "Enter '/fs join <AreaName>' to join game");
				}
				
			}else{
				sender.sendMessage(ChatColor.RED + "Enter '/fs join <AreaName>' to join game");
			}
			return true;
		}
		return false;
	}
}
