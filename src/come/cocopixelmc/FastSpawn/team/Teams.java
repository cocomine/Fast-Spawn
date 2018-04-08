package come.cocopixelmc.FastSpawn.team;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import come.cocopixelmc.FastSpawn.main;
import come.cocopixelmc.FastSpawn.spawn.Spawn;
import come.cocopixelmc.FastSpawn.spawn.Spawn2;

public class Teams implements Listener{
	
	private static main plugin;
	private static Scoreboard board;
	public static HashMap <Player, Team> PlayerInWhereArea = new HashMap <Player, Team>();
	
	@SuppressWarnings("static-access")
	public Teams(main plugin){
	    this.plugin=plugin;
	    plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	

	public static void createTeam(){
		board = Bukkit.getScoreboardManager().getNewScoreboard();
		for(String area : plugin.getConfig().getConfigurationSection("area").getKeys(false)){
			board.registerNewTeam(area);
		}
	}
	
	public static void createNewTeam(String area){
		board.registerNewTeam(area);
		
	}
	
	public static void unregisterTeam(String area){
		Team team = board.getTeam(area);
		team.unregister();
		
	}
	
	@SuppressWarnings("deprecation")
	public static void JoinTeam(Player player,String area){
		
		String worldconfig = plugin.getConfig().getString("area."+area+".world");
		World world = Bukkit.getServer().getWorld(worldconfig);
		String Location = plugin.getConfig().getString("area."+area+".Location");
		String[] location = Location.split(",");
		int x = Integer.parseInt(location[0]);
		int y = Integer.parseInt(location[1]);
		int z = Integer.parseInt(location[2]);
		
		Location location2 = new Location(world, x, y, z);
		player.teleport(location2);
		player.setGameMode(GameMode.SURVIVAL);
		
		Team team = board.getTeam(area);
		PlayerInWhereArea.put(player, team);
		team.addPlayer(player);
		player.playSound(player.getLocation(), Sound.CLICK, 2F, 15F);
		player.sendMessage(ChatColor.BLUE + "你加入了遊戲");
		
	}
	
	@SuppressWarnings("deprecation")
	public static void QuitTeam(Player player){
		
		String worldconfig = plugin.getConfig().getString("lobby.world");
		World world = Bukkit.getServer().getWorld(worldconfig);
		
		String Location = plugin.getConfig().getString("lobby.Location");
		String[] location = Location.split(",");
		int x = Integer.parseInt(location[0]);
		int y = Integer.parseInt(location[1]);
		int z = Integer.parseInt(location[2]);
		
		Location location2 = new Location(world, x, y, z);
		player.teleport(location2);
		
		Team team = PlayerInWhereArea.get(player);
		team.removePlayer(player);
		PlayerInWhereArea.remove(player);
		
		player.sendMessage(ChatColor.BLUE + "你退出了遊戲");
		player.playSound(player.getLocation(), Sound.NOTE_PIANO, 2F, 15F);
		
		player.setGameMode(GameMode.SURVIVAL);
		
		Spawn.cancel(player);
		Spawn2.cancel(player);
	}
	
	public static HashMap<Player, Team> getPlayerInWhereArea(){
		return PlayerInWhereArea;
	}
	
}