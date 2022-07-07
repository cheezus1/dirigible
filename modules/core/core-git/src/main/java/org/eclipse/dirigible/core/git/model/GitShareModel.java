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

import io.swagger.annotations.ApiModelProperty;

/**
 * The Git Share Model.
 */
public class GitShareModel extends BaseGitModel {

	@ApiModelProperty(hidden = true)
	private String project;

	@ApiModelProperty(value = "The Git Repository URL", example = "https://github.com/dirigiblelabs/sample_git_test.git", required = true)
	private String repository;

	@ApiModelProperty(value = "The Commit Message", example = "Initial Commit", required = true)
	private String commitMessage;

	@ApiModelProperty(value = "Whether to Share the Project in the Root Folder", example = "true", required = true)
	private boolean shareInRootFolder;

	/**
	 * Gets the project.
	 *
	 * @return the project
	 */
	public String getProject() {
		return project;
	}

	/**
	 * Sets the project.
	 *
	 * @param project the new project
	 */
	public void setProject(String project) {
		this.project = project;
	}

	/**
	 * Gets the repository.
	 *
	 * @return the repository
	 */
	public String getRepository() {
		return repository;
	}

	/**
	 * Sets the repository.
	 *
	 * @param repository the new repository
	 */
	public void setRepository(String repository) {
		this.repository = repository;
	}

	/**
	 * Gets the commit message.
	 *
	 * @return the commit message
	 */
	public String getCommitMessage() {
		return commitMessage;
	}

	/**
	 * Sets the commit message.
	 *
	 * @param commitMessage the new commit message
	 */
	public void setCommitMessage(String commitMessage) {
		this.commitMessage = commitMessage;
	}

	/**
	 * Returns whether should share the project in the root folder.
	 *
	 * @return true if should share the project in the root folder
	 */
	public boolean isShareInRootFolder() {
		return shareInRootFolder;
	}

	/**
	 * Sets whether should share the project in the root folder.
	 *
	 * @param shareInRootFolder the value
	 */
	public void setShareInRootFolder(boolean shareInRootFolder) {
		this.shareInRootFolder = shareInRootFolder;
	}
}
