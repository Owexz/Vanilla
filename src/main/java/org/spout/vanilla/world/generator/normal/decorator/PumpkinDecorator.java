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
package org.spout.vanilla.world.generator.normal.decorator;

import java.util.Random;

import org.spout.api.generator.biome.Decorator;
import org.spout.api.geo.World;
import org.spout.api.geo.cuboid.Chunk;

import org.spout.vanilla.material.VanillaMaterials;

/**
 * Decorator that decorates a biome with pumpkins.
 */
public class PumpkinDecorator implements Decorator {
	// Control how many pumpkins per patch
	private static final byte BASE_AMOUNT = 1;
	private static final byte RAND_AMOUNT = 8;
	// Offset from main point control
	private static final byte RAND_X = 8;
	private static final byte RAND_Z = 8;

	@Override
	public void populate(Chunk chunk, Random random) {
		if (chunk.getY() < 4) {
			return;
		}
		generatePumpkinPatch(chunk.getWorld(), random, chunk.getBlockX(random) + 8, chunk.getBlockZ(random) + 8);
	}

	private void generatePumpkinPatch(World world, Random random, int x, int z) {
		int amount = random.nextInt(RAND_AMOUNT) + BASE_AMOUNT;
		for (int i = 0; i < amount; i++) {
			final int px = x + (random.nextBoolean() ? random.nextInt(RAND_X) : -random.nextInt(RAND_X));
			final int pz = z + (random.nextBoolean() ? random.nextInt(RAND_Z) : -random.nextInt(RAND_Z));
			final int py = getHighestWorkableBlock(world, px, pz);
			if (canPlacePumpkin(world, px, py, pz)) {
				world.getBlock(px, py, pz).setMaterial(VanillaMaterials.PUMPKIN, (short) random.nextInt(4));
			}
		}
	}

	private boolean canPlacePumpkin(World world, int x, int y, int z) {
		return world.getBlockMaterial(x, y - 1, z) == VanillaMaterials.GRASS;
	}

	private int getHighestWorkableBlock(World w, int x, int z) {
		int y = w.getHeight() - 1;
		while (w.getBlockMaterial(x, y, z) == VanillaMaterials.AIR) {
			y--;
			if (y == 0 || w.getBlockMaterial(x, y, z) == VanillaMaterials.WATER) {
				return -1;
			}
		}
		y++;
		return y;
	}
}
