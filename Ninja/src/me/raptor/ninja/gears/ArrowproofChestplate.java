package me.raptor.ninja.gears;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.raptor.ninja.Ninja;
import net.md_5.bungee.api.ChatColor;




public class ArrowproofChestplate extends ItemStack implements Listener{

	ArrayList<String> cooldowns = new ArrayList<String>();
	Ninja plugin;
	public String prefix() {
		return ChatColor.WHITE + "" + ChatColor.BOLD + "[" + ChatColor.RED + "" + ChatColor.BOLD + "Ninja" + ChatColor.WHITE + "" + ChatColor.BOLD+ "] ";
	}
	public ArrowproofChestplate(Ninja plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	public ArrowproofChestplate() {
		super(Material.LEATHER_CHESTPLATE);
		ItemMeta aam = this.getItemMeta();
		LeatherArmorMeta lam =  (LeatherArmorMeta) aam;
		lam.setColor(Color.BLACK);
		lam.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Arrowproof Chestplate");
		lam.setLore(Arrays.asList(ChatColor.RED + "" + ChatColor.BOLD + "An epic chestplate of an epic set!",
				ChatColor.RED + "" + ChatColor.BOLD + "This special armor is made of epic leather, which make it unbreakable",
				ChatColor.RED + "" + ChatColor.BOLD + "Reduce 90% damage of arrows",
				ChatColor.GOLD + "" + ChatColor.BOLD + "SPECIAL: " + ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Dragon Heart: ", ChatColor.RED + "" + ChatColor.ITALIC + "Will give Regeneration III when",
				ChatColor.GRAY + "" + ChatColor.ITALIC + "your health is below 30%, 120 seconds cooldowns"));
	    lam.setUnbreakable(true);
	    lam.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	    lam.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
	    lam.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		this.setItemMeta(lam);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 50);
	}
	
	@EventHandler
	public void onNinjaDamaged2(EntityDamageEvent e) {
		
		if (!(e.getEntity() instanceof Player)) return;
		Player def = (Player) e.getEntity();
		if (cooldowns.contains(def.getName())) return;
		PlayerInventory pi = def.getInventory();
		if (pi.getChestplate() == null) return;
		if (pi.getChestplate().getItemMeta().getDisplayName() == null) return;
		if (pi.getChestplate().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lArrowproof Chestplate")) {
			if (def.getHealth() <= 6) {
				def.sendMessage(prefix() + "Dragon Heart activated");
				def.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 4));
				cooldowns.add(def.getName());
				def.sendMessage(prefix() + "You have to wait for 60 seconds before you can use Dragon Heart again!");
		        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
		        	public void run() {
		        		cooldowns.remove(def.getName());
		        		def.sendMessage(prefix()+ "You can now use Dragon Heart");
		        	}
		        }, 20 * 135);
			}
		}
		}
	@EventHandler
	public void onDamageDeflect(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Arrow)) return;
		Entity hit = e.getEntity();
		if (!(hit instanceof Player)) return;
		Player ninja = (Player) hit;
		PlayerInventory ni = ninja.getInventory();
		if (ni.getChestplate() == null || ni.getChestplate().getItemMeta() == null) return;
		if (ni.getChestplate().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lArrowproof Chestplate")) {
			e.setDamage(e.getDamage() / 10);
		}
	}

}
