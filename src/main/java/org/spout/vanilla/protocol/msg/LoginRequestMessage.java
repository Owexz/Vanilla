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
package org.spout.vanilla.protocol.msg;

import org.apache.commons.lang3.builder.ToStringBuilder;

import org.spout.api.protocol.Message;
import org.spout.api.util.SpoutToStringStyle;
import org.spout.vanilla.VanillaPlugin;

public final class LoginRequestMessage extends Message {
	private final int id, dimension, mode, difficulty, worldHeight, maxPlayers;
	private final String name, worldType;
	
	public LoginRequestMessage(String name) {
		this(VanillaPlugin.MINECRAFT_PROTOCOL_ID, name, 0, 0, 0, 0, 0, "");
	}

	public LoginRequestMessage(int id, String name, int mode, int dimension, int difficulty, int worldHeight, int maxPlayers, String worldType) {
		this.id = id;
		this.name = name;
		this.mode = mode;
		this.dimension = dimension;
		this.difficulty = difficulty;
		this.worldHeight = worldHeight;
		this.maxPlayers = maxPlayers;
		this.worldType = worldType;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getGameMode() {
		return mode;
	}

	public int getDimension() {
		return dimension;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public int getWorldHeight() {
		return worldHeight;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public String getWorldType() {
		return worldType;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, SpoutToStringStyle.INSTANCE)
				.append("id", id)
				.append("dimension", dimension)
				.append("mode", mode)
				.append("difficulty", difficulty)
				.append("worldHeight", worldHeight)
				.append("maxPlayers", maxPlayers)
				.append("name", name)
				.append("worldType", worldType)
				.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!getClass().equals(obj.getClass())) {
			return false;
		}
		final LoginRequestMessage other = (LoginRequestMessage) obj;
		return new org.apache.commons.lang3.builder.EqualsBuilder()
				.append(this.id, other.id)
				.append(this.dimension, other.dimension)
				.append(this.mode, other.mode)
				.append(this.difficulty, other.difficulty)
				.append(this.worldHeight, other.worldHeight)
				.append(this.maxPlayers, other.maxPlayers)
				.append(this.name, other.name)
				.append(this.worldType, other.worldType)
				.isEquals();
	}
}
