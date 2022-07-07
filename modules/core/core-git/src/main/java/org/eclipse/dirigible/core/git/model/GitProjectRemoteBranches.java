/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company and Eclipse Dirigible contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and Eclipse Dirigible contributors
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.dirigible.core.git.model;

import java.util.List;

import org.eclipse.dirigible.core.git.GitBranch;

public class GitProjectRemoteBranches {
	
	private List<GitBranch> remote;

	/**
	 * Getter for the remote branches
	 * 
	 * @return the remote
	 */
	public List<GitBranch> getRemote() {
		return remote;
	}

	/**
	 * Setter for the remote branches
	 * 
	 * @param remote the remote to set
	 */
	public void setRemote(List<GitBranch> remote) {
		this.remote = remote;
	}

}
