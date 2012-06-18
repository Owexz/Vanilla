/*
 * This file is part of Vanilla.
 *
 * Copyright (c) 2011-2012, VanillaDev <http://www.spout.org/>
 * Vanilla is licensed under the SpoutDev License Version 1.
 *
 * Vanilla is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the SpoutDev License Version 1.
 *
 * Vanilla is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the SpoutDev License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://www.spout.org/SpoutDevLicenseV1.txt> for the full license,
 * including the MIT license.
 */
package org.spout.vanilla.controller.block;

import org.spout.api.inventory.Inventory;

import org.spout.vanilla.controller.Container;
import org.spout.vanilla.controller.VanillaBlockController;
import org.spout.vanilla.controller.VanillaControllerTypes;
import org.spout.vanilla.controller.WindowController;
import org.spout.vanilla.controller.living.player.VanillaPlayer;
import org.spout.vanilla.inventory.block.DispenserInventory;
import org.spout.vanilla.inventory.window.Window;
import org.spout.vanilla.inventory.window.block.DispenserWindow;
import org.spout.vanilla.material.VanillaMaterials;

public class Dispenser extends WindowController implements Container {
	private final DispenserInventory inventory = new DispenserInventory();

	public Dispenser() {
		super(VanillaControllerTypes.DISPENSER, VanillaMaterials.DISPENSER);
	}

	@Override
	public void onAttached() {
	}

	@Override
	public Window createWindow(VanillaPlayer player) {
		return new DispenserWindow(inventory, player);
	}

	@Override
	public Inventory getInventory() {
		return inventory;
	}
}
