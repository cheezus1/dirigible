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
package org.eclipse.dirigible.engine.api.resource;

import org.eclipse.dirigible.repository.api.IRepositoryStructure;
import org.eclipse.dirigible.repository.api.RepositoryException;

public class RegistryResourceExecutor extends AbstractResourceExecutor {
	
	public static final String ENGINE_TYPE = "registry";
	
	public static final String ENGINE_NAME = "Registry Content Engine";

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.dirigible.engine.api.script.IEngineExecutor#getType()
	 */
	@Override
	public String getType() {
		return ENGINE_TYPE;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.dirigible.engine.api.script.IEngineExecutor#getName()
	 */
	@Override
	public String getName() {
		return ENGINE_NAME;
	}
	
	public byte[] getRegistryContent(String path) {
		try {
			return this.getResourceContent(IRepositoryStructure.PATH_REGISTRY_PUBLIC, path);
		} catch (RepositoryException e) {
			return null;
		}
	}

}
