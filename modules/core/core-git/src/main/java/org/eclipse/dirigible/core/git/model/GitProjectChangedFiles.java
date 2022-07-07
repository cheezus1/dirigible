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

import org.eclipse.dirigible.core.git.GitChangedFile;

public class GitProjectChangedFiles {
	
	private List<GitChangedFile> files;
	
	/**
	 * Getter for the files
	 * 
	 * @return the files
	 */
	public List<GitChangedFile> getFiles() {
		return files;
	}

	/**
	 * Setter for the files
	 * 
	 * @param files the files to set
	 */
	public void setFiles(List<GitChangedFile> files) {
		this.files = files;
	}

}
