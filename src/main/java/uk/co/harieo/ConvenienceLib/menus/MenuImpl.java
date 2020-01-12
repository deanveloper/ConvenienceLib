package uk.co.harieo.ConvenienceLib.menus;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * An implementation of {@link Inventory} based on the values provided by the {@link MenuFactory}, specific to a single
 * player.
 *
 * @author Harieo
 */
public class MenuImpl {

	private MenuFactory factory;
	private Player player;
	private Inventory inventory;

	/**
	 * An implementation of {@link Inventory} for a Player
	 *
	 * @param factory which this implementation is being created for
	 * @param player who owns this implementation
	 */
	MenuImpl(MenuFactory factory, Player player) {
		this.factory = factory;
		this.player = player;
		this.inventory = Bukkit.createInventory(null, factory.getSlotSize(), factory.getInventoryName());
		populateInventory();
	}

	/**
	 * Adds all the items from {@link MenuFactory#getItems()} into the inventory
	 */
	public void populateInventory() {
		factory.getItems().forEach((slot, menuItem) -> inventory.setItem(slot, menuItem.getItem()));
	}

	/**
	 * Re-sets the item for the specified slot, updating it if it has changed
	 *
	 * @param slot to be updated
	 */
	public void updateSlot(int slot) {
		inventory.setItem(slot, factory.getItem(slot).getItem());
	}

	/**
	 * Displays the current {@link Inventory} to the Player
	 */
	public void showInventory() {
		player.openInventory(inventory);
	}

	/**
	 * @return the inventory associated with the {@link MenuFactory}
	 */
	public Inventory getInventory() {
		return inventory;
	}

}
