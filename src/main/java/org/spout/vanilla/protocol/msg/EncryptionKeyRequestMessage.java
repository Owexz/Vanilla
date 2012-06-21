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
import org.bouncycastle.crypto.CipherParameters;

import org.spout.api.security.SecurityHandler;
import org.spout.api.util.SpoutToStringStyle;

public final class EncryptionKeyRequestMessage extends EncryptionKeyResponseMessage {
	private final String sessionId;

	public EncryptionKeyRequestMessage(String sessionId, CipherParameters publicKey, boolean locking) {
		this(sessionId, SecurityHandler.getInstance().encodeKey(publicKey), locking);
	}

	public EncryptionKeyRequestMessage(String sessionId, byte[] encoded, boolean locking) {
		super(encoded, locking);
		this.sessionId = sessionId;
	}

	public String getSessionId() {
		return sessionId;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final EncryptionKeyRequestMessage other = (EncryptionKeyRequestMessage) obj;
		return new org.apache.commons.lang3.builder.EqualsBuilder()
				.append(this.encoded, other.encoded)
				.append(this.sessionId, other.sessionId)
				.isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, SpoutToStringStyle.INSTANCE)
				.append("encoded", encoded)
				.append("sessionId", sessionId)
				.toString();
	}
}
