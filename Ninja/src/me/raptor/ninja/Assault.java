package me.raptor.ninja;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.md_5.bungee.api.ChatColor;



public class Assault implements Listener{
	ArrayList<String> tar = new ArrayList<String>();
	ArrayList<LivingEntity> ent = new ArrayList<LivingEntity>();
	ArrayList<String> dam = new ArrayList<String>();
	Player p1 ;
	Ninja plugin;
	public Assault(Ninja plugin) {
		this.plugin = plugin;
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}
	public String prefix() {
		return ChatColor.WHITE + "" + ChatColor.BOLD + "[" + ChatColor.RED + "" + ChatColor.BOLD + "Ninja" + ChatColor.WHITE + "" + ChatColor.BOLD+ "] ";
	}
	@EventHandler
	public void onNinjaAssault(PlayerInteractEvent e) {
		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction() == Action.RIGHT_CLICK_AIR) {
			EquipmentSlot hand = e.getHand();
			if (!hand.equals(EquipmentSlot.HAND)) return;
			Player p = e.getPlayer();
			PlayerInventory pi = p.getInventory();
			if (pi.getChestplate() == null || pi.getChestplate().getItemMeta() == null || pi.getChestplate().getItemMeta().getDisplayName() == null) return;
			if (pi.getChestplate().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lArrowproof Chestplate")) {
				if (pi.getLeggings() == null || pi.getLeggings().getItemMeta() == null || pi.getLeggings().getItemMeta().getDisplayName() == null) return;
				if (pi.getLeggings().getItemMeta().getDisplayName().equals("§c§lFine Leggings")) {
					if (pi.getHelmet() == null || pi.getHelmet().getItemMeta() == null || pi.getHelmet().getItemMeta().getDisplayName() == null) return;
					if (pi.getHelmet().getItemMeta().getDisplayName().equals("§c§lIron Visor")) {
						if (pi.getBoots() == null || pi.getBoots().getItemMeta() == null || pi.getBoots().getItemMeta().getDisplayName() == null) return;
						if (pi.getBoots().getItemMeta().getDisplayName().equals("§c§l'Sneak'ers")) {
				if (pi.getItemInMainHand() == null || pi.getItemInMainHand().getItemMeta() == null || pi.getItemInMainHand().getItemMeta().getDisplayName() == null) return;
				if (pi.getItemInMainHand().getItemMeta().getDisplayName().equals("§c§lKeen Katana")) {

				
					tar.clear();
					ent.clear();
					if (Cooldown.isOnCooldown(p, "Assault")) {
						p.sendMessage(prefix() + "You can use Assault after " + Cooldown.getTimeLeft(p, "Assault"));
						return;
					}
				for (Entity a : p.getNearbyEntities(15, 15, 15)) {
					if (!(a instanceof LivingEntity)) continue;
					if (a == p) continue;
					else if (a instanceof Player) 
						tar.add(a.getName());
					 else if (a instanceof LivingEntity) {
						 LivingEntity b = (LivingEntity) a ;
						 ent.add(b);
					 } else continue;


				}
				if (tar.size() == 0 && ent.size() == 0) {
					p.sendMessage(prefix() + "There are no entities nearby to assault");
					return;
				}
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				public void run() {
					if (dam.contains(p.getName())) {
						dam.remove(p.getName());
	                    p.sendMessage(prefix() + "Critical hit expired!");
	                    return;
					}
				}
			}, 20 * 3);
				if (tar.size() != 0) {
					p.sendMessage(prefix()+ "You have to wait 60 seconds before using it again");
					Cooldown.setCooldown(p, "Assault", 60);
					Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						public void run() {
							p.sendMessage(prefix() + "Assault available again");
							return;
						}
					}, 20 * 60);
				Random r = new Random();
				String p1 = tar.get(r.nextInt(tar.size()));
				Player p2 = Bukkit.getServer().getPlayer(p1);
				p.teleport(p2.getLocation());
				p.setVelocity(p2.getLocation().getDirection().multiply(-0.25));
				p.getLocation().setDirection(p2.getLocation().getDirection());
				p2.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 3, 1));
				p.sendMessage(prefix() + "Assaulted " + p2.getName());
				dam.add(p.getName());

				return;
				} else if (tar.size() == 0 || ent.size() != 0){
					p.sendMessage(prefix() + "You have to wait 60 seconds before using it again");

					Cooldown.setCooldown(p, "Assault", 60);
					Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						public void run() {
							p.sendMessage(prefix() + "Assault available again");
							return;
						}
					}, 20 * 60);
					Random r = new Random();
					LivingEntity e1 = ent.get(r.nextInt(ent.size()));
					
					p.teleport(e1.getLocation());
					p.sendMessage(prefix() + "Assaulted " + e1.getType().toString());
					p.setVelocity(e1.getLocation().getDirection().multiply(-0.25));
					p.getLocation().setDirection(e1.getLocation().getDirection());
					dam.add(p.getName());

					return;
				  
		}
	}
		}
		}
				}
			}
		}
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent e) {
		if (e.getEntity() instanceof Player) {
		   Player p = (Player) e.getEntity();
		   if (tar.contains(p.getName())) {
			   tar.remove(p.getName());
			   return;
		   }
		} else {
			if (ent.contains(e.getEntity())) {
			ent.remove(e.getEntity());
			return;
			}
		}
	}
	@EventHandler
	public void onNinjaAssault(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player)) return;
		Player p = (Player) e.getDamager();
		if (dam.contains(p.getName())) {
			e.setDamage(e.getDamage() * 3);
			p.sendMessage(prefix() + "Critical!");
			dam.remove(p.getName());

		}
	}
}
