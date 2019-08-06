package me.raptor.ninja.gears;

import java.util.Arrays;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.raptor.ninja.Ninja;
import net.md_5.bungee.api.ChatColor;

public class IronVisor extends ItemStack implements Listener{
	Ninja plugin;
	public IronVisor(Ninja plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	public IronVisor() {
		super(Material.IRON_HELMET);
		ItemMeta im = this.getItemMeta();
		im.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Iron Visor");
		im.setLore(Arrays.asList(ChatColor.RED + "An epic item of an epic set!",
				ChatColor.RED + "" + ChatColor.BOLD + "Help you breath underwater and see through nights",
				ChatColor.GOLD + "" + ChatColor.BOLD + "SPECIAL: Super reflex: " + ChatColor.GRAY + "Has a 15% chance of dodging to reduce 100% damage",
				ChatColor.GRAY + "or block to reduce 50% of the damage from an enemies attack"));
	    im.setUnbreakable(true);
	    im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	    im.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
	    im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);


		this.setItemMeta(im);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 20);
		this.addUnsafeEnchantment(Enchantment.OXYGEN, 100);
	}
	public String prefix() {
		return ChatColor.WHITE + "" + ChatColor.BOLD + "[" + ChatColor.RED + "" + ChatColor.BOLD + "Ninja" + ChatColor.WHITE + "" + ChatColor.BOLD+ "] ";
	}
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		PlayerInventory pi = p.getInventory();
		if (pi.getHelmet() == null || pi.getHelmet().getItemMeta() == null || pi.getHelmet().getItemMeta().getDisplayName() == null || !pi.getHelmet().getItemMeta().getDisplayName().equals("§c§lIron Visor")) {
			p.removePotionEffect(PotionEffectType.NIGHT_VISION);
			return;
		}
		if (pi.getHelmet().getItemMeta().getDisplayName().equals("§c§lIron Visor")) {
		p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, 1, false, false));
		return;
			
		} else 	if (!pi.getHelmet().getItemMeta().getDisplayName().equals("§c§lIron Visor")) { 

			return;
		}
	}
	@EventHandler
	public void onNinjaDodge(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) return;
		Player p = (Player) e.getEntity();
		PlayerInventory pi = p.getInventory();
	
		if (pi.getHelmet() == null || pi.getHelmet().getItemMeta() == null || pi.getHelmet().getItemMeta().getDisplayName() == null) return;
		if (pi.getHelmet().getItemMeta().getDisplayName().equals("§c§lIron Visor")) {
			Random r2 = new Random();
			Random r = new Random();
			int ran2 = r2.nextInt(2);
			int ran = r.nextInt(100);
			if (ran > 15) return;
			if (ran2 == 1) {
				e.setCancelled(true);
				p.setVelocity(p.getLocation().getDirection().multiply(-1).setY(0));
				p.sendMessage(prefix() + ChatColor.RED + "" + ChatColor.BOLD  + "Dodged!");
				return;
			} if (ran2 == 0) {
				e.setDamage(e.getDamage() / 2);
				p.sendMessage(prefix() + ChatColor.RED + "" + ChatColor.BOLD  + "Blocked!");
				return;
			}
			
	}
}
}
