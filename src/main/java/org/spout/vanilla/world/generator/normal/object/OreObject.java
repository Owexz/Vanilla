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
package org.spout.vanilla.world.generator.normal.object;

import java.util.Random;

import org.spout.api.geo.World;
import org.spout.api.material.BlockMaterial;

import org.spout.vanilla.material.VanillaMaterials;
import org.spout.vanilla.world.generator.object.RandomObject;

public class OreObject extends RandomObject {
	private byte count;
	private short maxHeight;
	private byte clusterSize;
	private BlockMaterial material;

	public OreObject(BlockMaterial material, byte count, byte clusterSize, short maxHeight) {
		this(null, material, count, clusterSize, maxHeight);
	}

	public OreObject(Random random, BlockMaterial material, byte count, byte clusterSize, short maxHeight) {
		super(random);
		this.count = count;
		this.material = material;
		this.maxHeight = maxHeight;
		this.clusterSize = clusterSize;
	}

	public OreObject(Random random, OreType type) {
		this(random, type.material, type.count, type.clusterSize, type.maxHeight);
	}

	public OreObject(OreType type) {
		this(null, type);
	}

	/**
	 * Gets the maximum height this ore should be placed
	 *
	 * @return maximum height
	 */
	public short getMaxHeight() {
		return this.maxHeight;
	}

	/**
	 * Gets the amount of this ore placed per chunk
	 *
	 * @return placement count
	 */
	public byte getCount() {
		return this.count;
	}

	/**
	 * Gets the size of a cluster of this ore
	 *
	 * @return cluster size
	 */
	public byte getClusterSize() {
		return this.clusterSize;
	}

	/**
	 * Gets the ore material used
	 *
	 * @return ore material
	 */
	public BlockMaterial getMaterial() {
		return this.material;
	}

	public void setClusterSize(byte clusterSize) {
		this.clusterSize = clusterSize;
	}

	public void setCount(byte count) {
		this.count = count;
	}

	public void setMaterial(BlockMaterial material) {
		this.material = material;
	}

	public void setMaxHeight(short maxHeight) {
		this.maxHeight = maxHeight;
	}

	@Override
	public boolean canPlaceObject(World w, int x, int y, int z) {
		return true;
	}

	/**
	 * Generates a cluster of this ore at the position using the random given
	 *
	 * @param world to place in
	 * @param originX of the cluster
	 * @param originY of the cluster
	 * @param originZ of the cluster
	 */
	@Override
	public void placeObject(World world, int originX, int originY, int originZ) {
		final double angle = random.nextDouble() * Math.PI;
		final double x1 = ((originX + 8) + Math.sin(angle) * this.clusterSize / 8);
		final double x2 = ((originX + 8) - Math.sin(angle) * this.clusterSize / 8);
		final double z1 = ((originZ + 8) + Math.cos(angle) * this.clusterSize / 8);
		final double z2 = ((originZ + 8) - Math.cos(angle) * this.clusterSize / 8);
		final double y1 = (originY + random.nextInt(3) + 2);
		final double y2 = (originY + random.nextInt(3) + 2);

		for (int i = 0; i <= this.clusterSize; i++) {
			final double seedX = x1 + (x2 - x1) * i / this.clusterSize;
			final double seedY = y1 + (y2 - y1) * i / this.clusterSize;
			final double seedZ = z1 + (z2 - z1) * i / this.clusterSize;
			final double size = ((Math.sin(i * Math.PI / this.clusterSize) + 1) * random.nextDouble() * this.clusterSize / 16 + 1) / 2;

			final int startX = (int) (seedX - size);
			final int startY = (int) (seedY - size);
			final int startZ = (int) (seedZ - size);
			final int endX = (int) (seedX + size);
			final int endY = (int) (seedY + size);
			final int endZ = (int) (seedZ + size);

			for (int x = startX; x <= endX; x++) {
				double sizeX = (x + 0.5 - seedX) / size;
				sizeX *= sizeX;

				if (sizeX < 1) {
					for (int y = startY; y <= endY; y++) {
						double sizeY = (y + 0.5 - seedY) / size;
						sizeY *= sizeY;

						if (sizeX + sizeY < 1) {
							for (int z = startZ; z <= endZ; z++) {
								double sizeZ = (z + 0.5 - seedZ) / size;
								sizeZ *= sizeZ;
								if (sizeX + sizeY + sizeZ < 1 && world.getBlockMaterial(x, y, z) == VanillaMaterials.STONE) {
									world.getBlock(x, y, z).setMaterial(this.material);
								}
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void randomize() {
	}

	public static enum OreType {
		DIRT(VanillaMaterials.DIRT, (byte) 20, (byte) 32, (short) 128),
		GRAVEL(VanillaMaterials.GRAVEL, (byte) 10, (byte) 32, (short) 128),
		COAL(VanillaMaterials.COAL_ORE, (byte) 20, (byte) 16, (short) 128),
		IRON(VanillaMaterials.IRON_ORE, (byte) 20, (byte) 8, (short) 64),
		REDSTONE(VanillaMaterials.REDSTONE_ORE, (byte) 8, (byte) 7, (short) 16),
		LAPIS_LAZULI(VanillaMaterials.LAPIS_LAZULI_ORE, (byte) 1, (byte) 6, (short) 32),
		GOLD(VanillaMaterials.GOLD_ORE, (byte) 2, (byte) 8, (short) 32),
		DIAMOND(VanillaMaterials.DIAMOND_ORE, (byte) 1, (byte) 7, (short) 16);
		private final BlockMaterial material;
		private final byte count;
		private final byte clusterSize;
		private final short maxHeight;

		private OreType(BlockMaterial material, byte count, byte clusterSize, short maxHeight) {
			this.material = material;
			this.count = count;
			this.clusterSize = clusterSize;
			this.maxHeight = maxHeight;
		}

		public int getClusterSize() {
			return clusterSize;
		}

		public int getCount() {
			return count;
		}

		public BlockMaterial getMaterial() {
			return material;
		}

		public int getMaxHeight() {
			return maxHeight;
		}
	}
}
