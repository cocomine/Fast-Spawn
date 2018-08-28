package come.cocopixelmc.FastSpawn.spawn;

import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Team;

import com.cocopixelmc.playerdeath.API.Titles;
import com.connorlinfoot.actionbarapi.ActionBarAPI;
import come.cocopixelmc.FastSpawn.main;
import come.cocopixelmc.FastSpawn.team.Teams;
import io.netty.util.internal.ThreadLocalRandom;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Spawn implements Listener{
	
	private final main plugin;
	private Player Killer;
	private Entity EntityKiller;
	private static int id;
	public static HashSet <String> Deathquest = new HashSet <String >();

	public Spawn(main plugin){
	    this.plugin=plugin;
	    plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	public static void cancel(Player player){
		if(Deathquest.contains(player.getName())){
			Bukkit.getScheduler().cancelTask(id);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
    //Death
    public void onEntityDeath(EntityDamageByEntityEvent e){
		
		Entity ent = e.getEntity();
    	
    	
    	if (ent instanceof Player){//check player
    		Player player = (Player) e.getEntity();
    		
    		if(Teams.getPlayerInWhereArea().containsKey(player)){
    		if(player.getHealth() - e.getDamage() < 1){//check health
    		
    			
    			
    			e.setCancelled(true);
    			
    			player.setGameMode(GameMode.SPECTATOR);//SPECTATOR
    			String Title = ChatColor.RED + ChatColor.BOLD.toString() + "你已死亡";//title
    			String SubTitle = ChatColor.YELLOW + "即將於 " + ChatColor.RED + "5" + ChatColor.YELLOW + " 秒後重生!";
    			Titles.sendFulltitle(player, Title, SubTitle, 10, 40, 10);//sentitle
    			player.setHealth(20);//health
    			
    			player.sendMessage(ChatColor.GREEN + "/fastspawn quit <-- 離開場地");
    			
    			//ads
    			player.sendMessage(ChatColor.DARK_GRAY + "ADS: 快點來 [精靈之夢] 玩吧!! IP: play.cocopixelmc.com");
				
				//creater
    			player.sendMessage(ChatColor.DARK_GRAY + "此插件由 Minecraft Coco (cocomine)製作!!  歡迎大家留意我!!");
				
				//Youtube
				TextComponent Youtube = new TextComponent("Youtube ");
				Youtube.setColor(net.md_5.bungee.api.ChatColor.RED);
				Youtube.setBold(true);
				Youtube.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.youtube.com/minecraftcoco"));
				Youtube.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("點擊開啟網頁").color(net.md_5.bungee.api.ChatColor.RED).create()));
				
				//facebook
				TextComponent facebook = new TextComponent("Facebook ");
				facebook.setColor(net.md_5.bungee.api.ChatColor.BLUE);
				facebook.setBold(true);
				facebook.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.facebook.com/minecraftcocoooooooooo/"));
				facebook.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("點擊開啟網頁").color(net.md_5.bungee.api.ChatColor.BLUE).create()));
			
				//web
				TextComponent web = new TextComponent("Animation Cloud");
				web.setColor(net.md_5.bungee.api.ChatColor.AQUA);
				web.setBold(true);
				web.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://animationcloud.cocopixelmc.com/"));
				web.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("點擊開啟網頁").color(net.md_5.bungee.api.ChatColor.AQUA).create()));
				
				//sender
				Youtube.addExtra(facebook);
				Youtube.addExtra(web);
				player.spigot().sendMessage(Youtube);
    			
				
    			player.setFoodLevel(20);//food
    			player.playSound(player.getLocation(), Sound.ANVIL_LAND, 2F, 20F);//sound
    			
    			Deathquest.add(player.getName());//hashmap
    			
    			//ActionBar
    			String ActionBar = ChatColor.YELLOW + "重新再: " + ChatColor.GREEN + "█████" + ChatColor.YELLOW + " 5 秒";
				ActionBarAPI.sendActionBar(player,ActionBar);
				player.playSound(player.getLocation(), Sound.CLICK, 2F, 15F);
				
    			plugin.getLogger().info(e.getDamager().toString());//debug
    			
				//death message
				if(e.getDamager() instanceof Player){//player
					
					Killer = (Player) e.getDamager();
					ActionBarAPI.sendActionBar(Killer, ChatColor.GREEN + "你殺掉了" + ChatColor.RED + player.getName());
					Killer.playSound(Killer.getLocation(), Sound.ANVIL_LAND, 2F, 20F);
					Bukkit.broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + "已被" + ChatColor.GREEN + Killer.getName() + ChatColor.GOLD + "殺死了");
					
				}else if(e.getDamager() instanceof Entity){
					
					EntityKiller = (Entity) e.getDamager();
						
						if(EntityKiller instanceof Arrow){
							Arrow arrow = (Arrow) EntityKiller;
							Player arrowKiller = (Player) arrow.getShooter();
							ActionBarAPI.sendActionBar(arrowKiller, ChatColor.GREEN + "你殺掉了" + ChatColor.RED + player.getName());
							arrowKiller.playSound(arrowKiller.getLocation(), Sound.ANVIL_LAND, 2F, 20F);
							Bukkit.broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + "已被" + ChatColor.GREEN + arrowKiller.getName() + ChatColor.GOLD + "殺死了");
						}
						if(EntityKiller instanceof Snowball){
							Snowball arrow = (Snowball) EntityKiller;
							Player arrowKiller = (Player) arrow.getShooter();
							ActionBarAPI.sendActionBar(arrowKiller, ChatColor.GREEN + "你殺掉了 " + ChatColor.RED + player.getName());
							arrowKiller.playSound(arrowKiller.getLocation(), Sound.ANVIL_LAND, 2F, 20F);
							Bukkit.broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GOLD + "已被" + ChatColor.GREEN + arrowKiller.getName() + ChatColor.GOLD + "殺死了");
						}
				}
				
					
    			//delay
				id = new BukkitRunnable(){
    				
    				
    				int time = 5;
					
    				@Override
    			    public void run() 
    			    {
    					
    					time--;//show time
    					
    					if(time == 4){
    						String ActionBar = ChatColor.YELLOW + "重新再: " + ChatColor.GREEN + "████" + ChatColor.RED + "█" + ChatColor.YELLOW + " 4秒";
    						ActionBarAPI.sendActionBar(player,ActionBar);
    						player.playSound(player.getLocation(), Sound.CLICK, 2F, 15F);
    					}
    					if(time == 3){
    						String ActionBar = ChatColor.YELLOW + "重新再: " + ChatColor.GREEN + "███" + ChatColor.RED + "██" + ChatColor.YELLOW + " 3秒";
    						ActionBarAPI.sendActionBar(player,ActionBar);
    						player.playSound(player.getLocation(), Sound.CLICK, 2F, 15F);
    					}
    					if(time == 2){
    						String ActionBar = ChatColor.YELLOW + "重新再: " + ChatColor.GREEN + "██" + ChatColor.RED + "███" + ChatColor.YELLOW + " 2秒";
    						ActionBarAPI.sendActionBar(player,ActionBar);
    						player.playSound(player.getLocation(), Sound.CLICK, 2F, 15F);
    					}
    					if(time == 1){
    						String ActionBar = ChatColor.YELLOW + "重新再: " + ChatColor.GREEN + "█" + ChatColor.RED + "████" + ChatColor.YELLOW + " 1秒";
    						ActionBarAPI.sendActionBar(player,ActionBar);
    						player.playSound(player.getLocation(), Sound.CLICK, 2F, 15F);
    					}
    					if(time == 0){//time up
    						
    						respawn(player);
    						cancel();
    					}
    					
    			    }
    				
    			}.runTaskTimer(plugin, 20l, 20l).getTaskId();
    		}
    		}
    	}
	}
	
	@SuppressWarnings("deprecation")
	public void respawn(Player player){
		
		Team team = Teams.getPlayerInWhereArea().get(player);
		
		String worldconfig = plugin.getConfig().getString("area."+team.getName()+".world");
		World world = Bukkit.getServer().getWorld(worldconfig);
		
		String min = plugin.getConfig().getString("area."+team.getName()+".Location.Range.Min");
		String max = plugin.getConfig().getString("area."+team.getName()+".Location.Range.Max");
		String[] MIN = min.split(",");
		String[] MAX = max.split(",");
		
		int x;
		int z;
		if(Integer.valueOf(MIN[0]) > Integer.valueOf(MAX[0])){
			x = ThreadLocalRandom.current().nextInt(Integer.valueOf(MAX[0]), Integer.valueOf(MIN[0]));
		}else{
			x = ThreadLocalRandom.current().nextInt(Integer.valueOf(MIN[0]), Integer.valueOf(MAX[0]));
		}
		if(Integer.valueOf(MIN[2]) > Integer.valueOf(MAX[2])){
			z = ThreadLocalRandom.current().nextInt(Integer.valueOf(MAX[2]), Integer.valueOf(MIN[2]));
		}else{
			z = ThreadLocalRandom.current().nextInt(Integer.valueOf(MIN[2]), Integer.valueOf(MAX[2]));
		}
		int y = Integer.valueOf(MAX[1])-2;
		
		player.addPotionEffect(new PotionEffect(PotionEffectType.getById(11), 5*20, 99));
		player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 5*20, 99));
		
		Location location2 = new Location(world, x, y, z);
		
		if(location2.getBlock().getType().equals(Material.AIR)){
			player.teleport(location2);
			player.setGameMode(GameMode.SURVIVAL);
			ActionBarAPI.sendActionBar(player,ChatColor.YELLOW + "重生!!");
			player.playSound(player.getLocation(), Sound.LEVEL_UP, 2F, 15F);
			
			Deathquest.remove(player.getName());
		}else{
			respawn(player);
		}
		
	}
}
