package me.raptor.ninja;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import me.raptor.ninja.gears.ArrowproofChestplate;
import me.raptor.ninja.gears.FineLeggings;
import me.raptor.ninja.gears.GrapplingHook;
import me.raptor.ninja.gears.IronVisor;
import me.raptor.ninja.gears.Katana;
import me.raptor.ninja.gears.SenseisBow;
import me.raptor.ninja.gears.Sneakers;
import net.md_5.bungee.api.ChatColor;

public class Ninja extends JavaPlugin  {
	public String prefix() {
		return ChatColor.WHITE + "" + ChatColor.BOLD + "[" + ChatColor.RED + "" + ChatColor.BOLD + "Ninja" + ChatColor.WHITE + "" + ChatColor.BOLD+ "] ";
	}
	public Abilities m;
	public void onEnable() {
		new Smokebomb(this);
		new Sneakers(this);
		new Katana(this);
		new Assault(this);
		new IronVisor(this);
		new ArrowproofChestplate(this);
		new FineLeggings(this);
		new SenseisBow(this);
		new GrapplingHook(this);
		new DeadShot(this);
		new Blink(this);
		ColorLogging.logging(" &cNinja plugin has been enabled!");
		ColorLogging.logging(" &cUnstable Version: 1.0");
		ColorLogging.logging(" &cAuthor: &6RAPTOR");
		ColorLogging.logging(" &cOnly work on 1.13 and above!");
        m = new Abilities(this);

	}
	public boolean onCommand(CommandSender sd, Command cmd, String CommandLabel, String[] args) {
		if (!(sd instanceof Player)) {
			sd.sendMessage(ChatColor.RED + "Awesome gears haven't available for console yet!");
			return true;
		}
		Player p = (Player) sd;
		PlayerInventory pi = p.getInventory();
		if (cmd.getName().equalsIgnoreCase("ninja")) {
			if (args.length == 0) {
				sd.sendMessage(ColorLogging.getHelpMessage());
				return true;
			}
			if (args[0].equalsIgnoreCase("gears")) {
				p.sendMessage(ColorLogging.getNinjaGear());
				return true;
			}
			if (args[0].equalsIgnoreCase("abilities")) {
				m.openMenu(p);
				return true;
			}
			if (args[0].equalsIgnoreCase("chestplate")) {
				ItemStack aa = new ArrowproofChestplate();
				if (pi.getChestplate() == null) {
					pi.setChestplate(aa);
					sd.sendMessage(prefix() + "Here's your chestplate!");
					return true;
				}
				pi.addItem(aa);
				sd.sendMessage(prefix() + "Here's your chestplate!");
				return true;
			}
			if (args[0].equalsIgnoreCase("bow")) {
				ItemStack aa = new SenseisBow();
				pi.addItem(aa);
				sd.sendMessage(prefix()+ "Here's your bow!");
				return true;
			}
			if (args[0].equalsIgnoreCase("hook")) {
				ItemStack aa = new GrapplingHook();
				pi.addItem(aa);
				sd.sendMessage(prefix()+ "Here's your hook!");
				return true;
			}
			if (args[0].equalsIgnoreCase("leggings")) {
				ItemStack aa = new FineLeggings();
				if (pi.getLeggings() == null) {
					pi.setLeggings(aa);
					sd.sendMessage(prefix()+ "Here's your leggings!");
					return true;
				}
				pi.addItem(aa);
				sd.sendMessage(prefix() + "Here's your leggigns!");
				return true;
			}
			if (args[0].equalsIgnoreCase("sneakers")) {
				ItemStack aa = new Sneakers();
				if (pi.getBoots() == null) {
					pi.setBoots(aa);
					sd.sendMessage(prefix() + "Here's your boots!");
					return true;
				}
				pi.addItem(aa);
				sd.sendMessage(prefix() + "Here's your boots!");
				return true;
			}
			if (args[0].equalsIgnoreCase("katana")) {
				ItemStack aa = new Katana();
				pi.addItem(aa);
				sd.sendMessage(prefix() + "Here's your katana!");
				return true;
			} 
			if (args[0].equalsIgnoreCase("visor")) {
				ItemStack aa = new IronVisor();
				if (pi.getHelmet() == null) {
					pi.setHelmet(aa);
					sd.sendMessage(prefix()+ "Here's your chestplate!");
					return true;
				}
				pi.addItem(aa);
				sd.sendMessage(prefix() + "Here's your visor!");
				return true;
			} 
			if (args[0].equalsIgnoreCase("set")) {
				ItemStack a = new Katana();
				ItemStack b = new IronVisor();
				ItemStack c = new Sneakers();
				ItemStack d = new ArrowproofChestplate();
				ItemStack e = new FineLeggings();
				ItemStack f = new SenseisBow();
				ItemStack g = new GrapplingHook();
				ItemStack fish = new ItemStack(Material.COOKED_COD, 16);
				ItemMeta fm = fish.getItemMeta();
				fm.setDisplayName(ChatColor.GOLD + "Mackarel");
				fm.setLore(Arrays.asList(ChatColor.GRAY + "Yum yum, a special food for ninja"));
				fish.setItemMeta(fm);
				if (pi.getBoots() == null) {
					pi.setBoots(c);
				} else 	pi.addItem(c);
				if (pi.getChestplate() == null) {
					pi.setChestplate(d);
				} else	pi.addItem(d);
				if (pi.getHelmet() == null) {
					pi.setHelmet(b);
				}
				if (pi.getLeggings() == null) {
					pi.setLeggings(e);
				}
				pi.addItem(a);
				pi.addItem(f);
				pi.addItem(g);
				pi.addItem(new ItemStack(Material.ARROW, 1));
				pi.addItem(fish);
				p.sendMessage(prefix()+ "Here's your set");
				return true;
			} else { 
				p.sendMessage(ColorLogging.getHelpMessage());
				return true;
				
			}
			
			
		  }
		return true;
	  }
	}
