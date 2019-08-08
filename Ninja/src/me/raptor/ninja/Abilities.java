package me.raptor.ninja;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class Abilities implements Listener{
	Inventory inv, inv2, inv3, helm, katm, arrm, legm, hoom, snem, bowm;
	ItemStack ful, kat, hel, arr, bow, leg, sne, hoo, gea, back;
	ItemStack inte, sha, ken, mas, dra, arp, con, dou, eag, aqu, miz, cur, fis, ref, clo, crit;
	ItemStack ass, smo, bli, dea;
	String name;
public Abilities(Plugin p) {
	

	inv = Bukkit.getServer().createInventory(null, 27, "Abilities");
    inv2 = Bukkit.getServer().createInventory(null, 45, ChatColor.GOLD + "" +  ChatColor.BOLD  + "Gear's Abilities");
    inv3 = Bukkit.getServer().createInventory(null, 45, ChatColor.GOLD + "" +  ChatColor.BOLD  + "Set's Abilities");
	helm = Bukkit.getServer().createInventory(null, 27, ChatColor.RED + "" +  ChatColor.BOLD  + "Iron Visor's Abilities");
    katm = Bukkit.getServer().createInventory(null, 27, ChatColor.RED + "" +  ChatColor.BOLD  + "Keen Katana's Abilities");
    bowm = Bukkit.getServer().createInventory(null, 27, ChatColor.RED + "" +  ChatColor.BOLD  + "Sensei's Bow's Abilities");
	legm = Bukkit.getServer().createInventory(null, 27, ChatColor.RED + "" +  ChatColor.BOLD  + "Fine Leggings's Abilities");
    snem = Bukkit.getServer().createInventory(null, 27, ChatColor.RED + "" +  ChatColor.BOLD  + "'Sneak'ers's Abilities");
    hoom = Bukkit.getServer().createInventory(null, 27, ChatColor.RED + "" +  ChatColor.BOLD  + "Steel Hook's Abilities");
    arrm = Bukkit.getServer().createInventory(null, 27, ChatColor.RED + "" +  ChatColor.BOLD  + "Arrowproof Chestplate's Abilities");
	kat = createItem(ChatColor.RED + "Keen Katana", Material.IRON_SWORD, ChatColor.GRAY + "" + ChatColor.BOLD + "Show abilities of " + ChatColor.RED + "" + ChatColor.BOLD + "Keen Katana");
	hel = createItem(ChatColor.RED + "Iron Visor", Material.IRON_HELMET, ChatColor.GRAY + "" + ChatColor.BOLD + "Show abilities of " + ChatColor.RED + "" + ChatColor.BOLD + "Iron Visor");	
	arr = createItem(ChatColor.RED + "Arrowproof Chestplate", Material.LEATHER_CHESTPLATE, ChatColor.GRAY + "" + ChatColor.BOLD + "Show abilities of " + ChatColor.RED + "" + ChatColor.BOLD + "Arrowproof Chestplate", Color.BLACK);
	bow = createItem(ChatColor.RED + "Sensei's Bow", Material.BOW, ChatColor.GRAY + "" + ChatColor.BOLD + "Show abilities of "+ ChatColor.RED + "" + ChatColor.BOLD +"Sensei's Bow");	
	leg = createItem(ChatColor.RED + "Fine Leggings", Material.LEATHER_LEGGINGS, ChatColor.GRAY + "" + ChatColor.BOLD + "Show abilities of "+ ChatColor.RED + "" + ChatColor.BOLD +"Fine Leggings", Color.BLACK);
	sne = createItem(ChatColor.RED + "'Sneak'ers", Material.LEATHER_BOOTS, ChatColor.GRAY + "" + ChatColor.BOLD + "Show abilities of "+ ChatColor.RED + "" + ChatColor.BOLD +"'Sneak'ers", Color.BLACK);	
	hoo = createItem(ChatColor.RED + "Steel Hook", Material.FISHING_ROD, ChatColor.GRAY + "" + ChatColor.BOLD + "Show abilities of "+ ChatColor.RED + "" + ChatColor.BOLD +"Steel Hook");
	ful = createItem(ChatColor.RED + "Set's Abilities", Material.IRON_SWORD, ChatColor.GRAY + "" + ChatColor.BOLD + "Show full set's abilities");
	gea = createItem(ChatColor.RED + "Gear's Abilities", Material.IRON_HELMET, ChatColor.GRAY + "" + ChatColor.BOLD + "Show gear's abilities");	
	back = createItem(ChatColor.GRAY + "Back", Material.BLACK_STAINED_GLASS_PANE, ChatColor.GRAY + "" + ChatColor.BOLD + "Back to previous page");
	clo = createItem(ChatColor.RED + "Close", Material.BLACK_STAINED_GLASS_PANE, ChatColor.RED + "" + ChatColor.BOLD + "Close menu");	
	
	/*
	 ...
	 */
	inte = createItem(ChatColor.RED + "Interference", Material.BARRIER, Arrays.asList(ChatColor.GRAY + "" + ChatColor.BOLD + "Has a 5% chance to make enemies unable",
																					  ChatColor.GRAY + "" + ChatColor.BOLD + "to use shield for 5 seconds"));
	sha = createItem(ChatColor.RED + "Sharpened Hook", Material.GRINDSTONE, Arrays.asList(ChatColor.GRAY + "" + ChatColor.BOLD + "Hook has been sharpened so it can",
		      																	  	    ChatColor.GRAY + "" + ChatColor.BOLD + "deal 3 damage to enemies when thrown"));
	ken = createItem(ChatColor.RED + "Kenjutsu", Material.IRON_SWORD, Arrays.asList(ChatColor.GRAY + "" + ChatColor.BOLD + "Has a 20% to perform a combo of 3",
		  	    ChatColor.GRAY + "" + ChatColor.BOLD + "hits with the same damage as the last hit!"));	
	mas = createItem(ChatColor.RED + "Master Of Bow Shooting", Material.BOW, Arrays.asList(ChatColor.GRAY + "" + ChatColor.BOLD + "The 4th shot with fire 5 arrows",
		  	    ChatColor.GRAY + "" + ChatColor.BOLD + "instead of one."));	
	dra = createItem(ChatColor.RED + "Dragon's Heart", Material.DRAGON_HEAD, Arrays.asList(ChatColor.GRAY + "" + ChatColor.BOLD + "When your health is below 6, you",
		  	    ChatColor.GRAY + "" + ChatColor.BOLD + "will receive Regeneration 5 for 10 seconds. 60 seconds cooldown"));	
	arp = createItem(ChatColor.RED + "Arrowproof", Material.SHIELD, Arrays.asList(ChatColor.GRAY + "" + ChatColor.BOLD + "Deflect 90% damage of arrows"));	
	con = createItem(ChatColor.RED + "Concentrating", Material.NETHER_STAR, Arrays.asList(ChatColor.GRAY + "" + ChatColor.BOLD + "You can regenerate health and reduce",
		  	    ChatColor.GRAY + "" + ChatColor.BOLD + "damage of incoming attack by sneaking for 10 seconds",	
	    		ChatColor.GRAY + "" + ChatColor.BOLD + "If you move during the process, you will",
	    		ChatColor.GRAY + "" + ChatColor.BOLD + "have to do it all over again"));
	dou = createItem(ChatColor.RED + "Double Jump", Material.SLIME_BLOCK, Arrays.asList(ChatColor.GRAY + "" + ChatColor.BOLD + "Give you the ability to double jump"));	
	eag = createItem(ChatColor.RED + "Eagle Eye", Material.ENDER_EYE, Arrays.asList(ChatColor.GRAY + "" + ChatColor.BOLD + "Give you the ability to look in the dark"));	
	aqu = createItem(ChatColor.RED + "Aquaman", Material.TURTLE_HELMET, Arrays.asList(ChatColor.GRAY + "" + ChatColor.BOLD + "You can breath underwater for a very long time"));	
	miz = createItem(ChatColor.RED + "Mizugomo no Jutsu", Material.HEART_OF_THE_SEA, Arrays.asList(ChatColor.GRAY + "" + ChatColor.BOLD + "You have the ability to water on water!"));	
	cur = createItem(ChatColor.RED + "Curse of the Elder", Material.SPECTRAL_ARROW, Arrays.asList(ChatColor.GRAY + "" + ChatColor.BOLD + "Hightlights the enemies that have been shot"));	
	fis = createItem(ChatColor.RED + "The Way Of The Fish", Material.COD, Arrays.asList(ChatColor.GRAY + "" + ChatColor.BOLD + "You can swim faster underwater"));	
	ref = createItem(ChatColor.RED + "Super Reflexe", Material.CHORUS_FRUIT, Arrays.asList(ChatColor.GRAY + "" + ChatColor.BOLD + "Has a 15% chance of dodge or block to receive ",
			ChatColor.GRAY + "" + ChatColor.BOLD + "only halves of the damage"));	
	crit = createItem(ChatColor.RED + "Sharpened Blade", Material.ANVIL, Arrays.asList(ChatColor.GRAY + "" + ChatColor.BOLD + "Has a 50% chance of dealing a critical hit ",
			ChatColor.GRAY + "" + ChatColor.BOLD + "that does 150% damage"));	
	/*
	 ...
	 */
	
	ass = createItem(ChatColor.RED + "Assault", Material.ENDER_PEARL, Arrays.asList(ChatColor.RED + "" + ChatColor.BOLD + "Require Keen Katana",
			ChatColor.GRAY + "" + ChatColor.BOLD + "Right click while holding Keen Katana to",
			ChatColor.GRAY + "" + ChatColor.BOLD + "assault an entity in the radius of 15 blocks",
			ChatColor.GRAY + "" + ChatColor.BOLD + "(will be player, if have)",
			ChatColor.GRAY + "" + ChatColor.BOLD + "And make the next attack dealt 300% damage",
			ChatColor.GRAY + "" + ChatColor.BOLD + "60 seconds cooldowns",
			ChatColor.GOLD + "" + ChatColor.BOLD + "Set Exclusive"));	
	smo = createItem(ChatColor.RED + "Smokebomb", Material.POTION, Arrays.asList(
			ChatColor.GRAY + "" + ChatColor.BOLD + "Shift + Right click to create a smoke screen",
			ChatColor.GRAY + "" + ChatColor.BOLD + "and make yourself invisible for 15 seconds.",
			ChatColor.GRAY + "" + ChatColor.BOLD + "You will also got applied with Speed 3 for 15 seconds",
			ChatColor.GRAY + "" + ChatColor.BOLD + "During the process, you are invulnerable to",
			ChatColor.GRAY + "" + ChatColor.BOLD + "melee attacks",
			ChatColor.GRAY + "" + ChatColor.BOLD + "120 seconds cooldowns",
			ChatColor.GOLD + "" + ChatColor.BOLD + "Set Exclusive"));	
	dea = createItem(ChatColor.RED + "DeadShot", Material.ARROW, Arrays.asList(
			ChatColor.GRAY + "" + ChatColor.BOLD + "Shift + Left click to unleash a hall of kunais.",
			ChatColor.GRAY + "" + ChatColor.BOLD + "Kunais have a 10% chance to reduce enemies",
			ChatColor.GRAY + "" + ChatColor.BOLD + "health to 25% of the current health",
			ChatColor.GRAY + "" + ChatColor.BOLD + "This ability can be use 2 more times in 5 seconds",
			ChatColor.GRAY + "" + ChatColor.BOLD + "45 seconds cooldowns",
			ChatColor.GOLD + "" + ChatColor.BOLD + "Set Exclusive"));	
	bli = createItem(ChatColor.RED + "Blink", Material.ARROW, Arrays.asList(
			ChatColor.GRAY + "" + ChatColor.BOLD + "A instrinsic ability",
			ChatColor.GRAY + "" + ChatColor.BOLD + "Right click your enemies to make them nausea",
			ChatColor.GRAY + "" + ChatColor.BOLD + "for 15 seconds and blind for 10 seconds",
			ChatColor.GRAY + "" + ChatColor.BOLD + "60 seconds cooldowns",
			ChatColor.GOLD + "" + ChatColor.BOLD + "Set Exclusive"));	
	inv.setItem(11, ful);
	inv.setItem(15, gea);
	for (int i = 0; i < 27; i++) {
	if (inv.getItem(i) != null) continue;
	inv.setItem(i, clo);
}

inv2.setItem(11, kat);
inv2.setItem(13, bow);
inv2.setItem(15, hoo);
inv2.setItem(28, hel);
inv2.setItem(30, arr);
inv2.setItem(32, leg);
inv2.setItem(34, sne);
for (int i = 0; i < 45; i++) {
	if (inv2.getItem(i) != null) continue;
	inv2.setItem(i, back);
}
inv3.setItem(11, dea);
inv3.setItem(15, ass);
inv3.setItem(29, smo);
inv3.setItem(33, bli);
for (int i = 0; i < 45; i++) {
	if (inv3.getItem(i) != null) continue;
	inv3.setItem(i, back);
}
helm.setItem(11, eag);
helm.setItem(13, ref);
helm.setItem(15, aqu);
for (int i = 0; i < 27; i++) {
	if (helm.getItem(i) != null) continue;
	helm.setItem(i, back);
}
katm.setItem(11, ken);
katm.setItem(15, crit);
for (int i = 0; i < 27; i++) {
	if (katm.getItem(i) != null) continue;
	katm.setItem(i, back);
}
legm.setItem(13, con);
for (int i = 0; i < 27; i++) {
	if (legm.getItem(i) != null) continue;
	legm.setItem(i, back);
}
arrm.setItem(11, dra);
arrm.setItem(15, arp);
for (int i = 0; i < 27; i++) {
	if (arrm.getItem(i) != null) continue;
	arrm.setItem(i, back);
}
hoom.setItem(11, inte);
hoom.setItem(15, sha);
for (int i = 0; i < 27; i++) {
	if (hoom.getItem(i) != null) continue;
	hoom.setItem(i, back);
}
snem.setItem(11, miz);
snem.setItem(15, fis);
for (int i = 0; i < 27; i++) {
	if (snem.getItem(i) != null) continue;
	snem.setItem(i, back);
}
bowm.setItem(11, mas);
bowm.setItem(15, cur);
for (int i = 0; i < 27; i++) {
	if (bowm.getItem(i) != null) continue;
	bowm.setItem(i, back);
}
    Bukkit.getServer().getPluginManager().registerEvents(this, p);
	
	
}
	private ItemStack createItem(String name, Material mat, String lore) {
		ItemStack i = new ItemStack(mat);
		ItemMeta meta = i.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(Arrays.asList(lore));
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		i.setItemMeta(meta);
		return i;
	}
	private ItemStack createItem(String name, Material mat, String lore, Color c) {
		ItemStack i = new ItemStack(mat);
		ItemMeta meta = i.getItemMeta();
		LeatherArmorMeta lam = (LeatherArmorMeta) meta;
		lam.setColor(c);
		lam.setDisplayName(name);
		lam.setLore(Arrays.asList(lore));
		lam.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		i.setItemMeta(lam);

		return i;
	}
	private ItemStack createItem(String name, Material mat, List<String> lore) {
		ItemStack i = new ItemStack(mat);
		ItemMeta meta = i.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		i.setItemMeta(meta);

		return i;
	}
	public void openMenu(Player p) {
		p.openInventory(inv);
	}
	@EventHandler
	public void onMenuInteract(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (!p.getOpenInventory().getTitle().equals("Abilities")) return;
		if  (e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null) return;
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Set's Abilities")) {
			e.setCancelled(true);
			p.closeInventory();
			p.openInventory(inv3);
		}
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Gear's Abilities")) {
			e.setCancelled(true);
			p.closeInventory();
			p.openInventory(inv2);
		}
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Close")) {
			e.setCancelled(true);
			p.closeInventory();
		}
	}
	@EventHandler
	public void onMenuInteract2(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (!p.getOpenInventory().getTitle().contains("Gear's Abilities")) return;
		if  (e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null) return;
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Back")) {
			e.setCancelled(true);
			p.closeInventory();
			p.openInventory(inv);
		}
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Iron Visor")) {
			e.setCancelled(true);
			p.closeInventory();
			p.openInventory(helm);
		}
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Keen Katana")) {
			e.setCancelled(true);
			p.closeInventory();
			p.openInventory(katm);
		}
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("'Sneak'ers")) {
			e.setCancelled(true);
			p.closeInventory();
			p.openInventory(snem);
		}
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Fine Leggings")) {
			e.setCancelled(true);
			p.closeInventory();
			p.openInventory(legm);
		}
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Arrowproof Chestplate")) {
			e.setCancelled(true);
			p.closeInventory();
			p.openInventory(arrm);
		}
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Sensei's Bow")) {
			e.setCancelled(true);
			p.closeInventory();
			p.openInventory(bowm);
		}
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Steel Hook")) {
			e.setCancelled(true);
			p.closeInventory();
			p.openInventory(hoom);
		}

	}
	@EventHandler
public void onMenuInteract5(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (!p.getOpenInventory().getTitle().contains("Keen Katana's Abilities")) return;
		e.setCancelled(true);
		if  (e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null) return;
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Back")) {
			e.setCancelled(true);
p.closeInventory();
p.openInventory(inv);
		}
	}
	@EventHandler
public void onMenuInteract6(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (!p.getOpenInventory().getTitle().contains("Sensei's Bow's Abilities")) return;
		e.setCancelled(true);
		if  (e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null) return;
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Back")) {
			e.setCancelled(true);
p.closeInventory();
p.openInventory(inv);
		}
	}
	@EventHandler
public void onMenuInteract7(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (!p.getOpenInventory().getTitle().contains("Steel Hook's Abilities")) return;
		e.setCancelled(true);
		if  (e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null) return;
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Back")) {
			e.setCancelled(true);
p.closeInventory();
p.openInventory(inv);
		}
	}
	@EventHandler
public void onMenuInteract8(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (!p.getOpenInventory().getTitle().contains("Iron Visor's Abilities")) return;
		e.setCancelled(true);
		if  (e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null) return;
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Back")) {
			e.setCancelled(true);
p.closeInventory();
p.openInventory(inv);
		}
	}	@EventHandler
public void onMenuInteract9(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (!p.getOpenInventory().getTitle().contains("Arrowproof Chestplate's Abilities")) return;
		e.setCancelled(true);
		if  (e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null) return;
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Back")) {
			e.setCancelled(true);
p.closeInventory();
p.openInventory(inv);
		}
	}
	@EventHandler
public void onMenuInteract10(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (!p.getOpenInventory().getTitle().contains("Fine Leggings's Abilities")) return;
		e.setCancelled(true);
		if  (e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null) return;
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Back")) {
			e.setCancelled(true);
p.closeInventory();
p.openInventory(inv);
		}
	}
	@EventHandler
public void onMenuInteract11(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (!p.getOpenInventory().getTitle().contains("'Sneak'ers's Abilities")) return;
		e.setCancelled(true);
		if  (e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null) return;
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Back")) {
			e.setCancelled(true);
p.closeInventory();
p.openInventory(inv);
		}
	}
	@EventHandler
	public void onMenuInteract4(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (!p.getOpenInventory().getTitle().contains("Set's Abilities")) return;
		if  (e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null) return;
		e.setCancelled(true);
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Back")) {
			e.setCancelled(true);
p.closeInventory();
p.openInventory(inv);
		}
	}
	@EventHandler
	public void onNinjaEatFish(PlayerItemConsumeEvent e) {
		if (e.getItem().getType() != Material.COOKED_COD) return;
		ItemStack mac = e.getItem();
		if (mac.getItemMeta() == null || mac.getItemMeta().getDisplayName() == null) return;
		if (mac.getItemMeta().getDisplayName().equals("§6Mackarel")) {
			e.getPlayer().setFoodLevel(20);
			e.getPlayer().setSaturation(20);
		}
	}
}
