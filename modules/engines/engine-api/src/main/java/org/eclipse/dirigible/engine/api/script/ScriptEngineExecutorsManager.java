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
package org.eclipse.dirigible.engine.api.script;

import static java.text.MessageFormat.format;

import java.util.Map;
import java.util.Set;

import org.eclipse.dirigible.commons.api.context.ThreadContextFacade;
import org.eclipse.dirigible.commons.api.helpers.GsonHelper;
import org.eclipse.dirigible.commons.api.scripting.ScriptingException;
import org.eclipse.dirigible.repository.api.IRepositoryStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Script Engine Executors Manager.
 */
public class ScriptEngineExecutorsManager {
	
	private static final Logger logger = LoggerFactory.getLogger(ScriptEngineExecutorsManager.class);

    /**
     * Execute service module.
     *
     * @param engineType       the engine type
     * @param module           the module
     * @param executionContext the execution context
     * @return the object
     * @throws ScriptingException the scripting exception
     */
    public static Object executeServiceModule(String engineType, String module, Map<Object, Object> executionContext) throws ScriptingException {
    	if (engineType == null) {
        	engineType = "javascript";
        }
        IScriptEngineExecutor scriptEngineExecutor = ScriptEngineExecutorFactory.getScriptEngineExecutor(engineType);
        if (scriptEngineExecutor != null) {
            try {
                ThreadContextFacade.setUp();
                if (scriptEngineExecutor.existsModule(IRepositoryStructure.PATH_REGISTRY_PUBLIC, module)) {
                	return scriptEngineExecutor.executeServiceModule(module, executionContext);
                } else {
                	String notFound = format("Script Module [{0}] does not exist, hence cannot be processed", module);
                	logger.error(notFound);
//					throw new ScriptingException(notFound);
                	return null;
                }
            } finally {
                ThreadContextFacade.tearDown();
            }
        }

        throw new ScriptingException(
                format("Script Executor of Type [{0}] does not exist, hence the Module [{1}] cannot be processed", engineType, module));
    }

    /**
     * Evaluate a code snippet
     *
     * @param code             the code snippet
     * @param executionContext the execution context
     * @return the result object
     * @throws ScriptingException in case of exception
     */
    public static Object evalModule(String code, Map<Object, Object> executionContext) throws ScriptingException {
        IScriptEngineExecutor scriptEngineExecutor = ScriptEngineExecutorFactory.getScriptEngineExecutor("javascript");
        if (scriptEngineExecutor != null) {
            try {
                ThreadContextFacade.setUp();

                return scriptEngineExecutor.evalModule(code, executionContext);
            } finally {
                ThreadContextFacade.tearDown();
            }
        }

        throw new ScriptingException(
                format("Script Executor of Type [{0}] does not exist, hence the Script [{1}] cannot be processed", "javascript", code));
    }

    /**
     * Execute service code.
     *
     * @param engineType       the engine type
     * @param code             the code
     * @param executionContext the execution context
     * @return the object
     * @throws ScriptingException the scripting exception
     */
    public static Object executeServiceCode(String engineType, String code, Map<Object, Object> executionContext) throws ScriptingException {
        IScriptEngineExecutor scriptEngineExecutor = ScriptEngineExecutorFactory.getScriptEngineExecutor(engineType);
        if (scriptEngineExecutor != null) {
            try {
                ThreadContextFacade.setUp();

                return scriptEngineExecutor.evalCode(code, executionContext);
            } finally {
                ThreadContextFacade.tearDown();
            }
        }

        throw new ScriptingException(
                format("Script Executor of Type [{0}] does not exist, hence the code [{1}] cannot be processed", engineType, code));
    }

    public static Object executeMethodFromModule(String engineType, String module, String memberClass, String memberMethod, Map<Object, Object> executionContext) throws ScriptingException {
        IScriptEngineExecutor scriptEngineExecutor = ScriptEngineExecutorFactory.getScriptEngineExecutor(engineType);
        if (scriptEngineExecutor != null) {
            try {
                ThreadContextFacade.setUp();

                return scriptEngineExecutor.executeMethodFromModule(module, memberClass, memberMethod, executionContext);
            } finally {
                ThreadContextFacade.tearDown();
            }
        }

        throw new ScriptingException(
                format("Script Executor of Type [{0}] does not exist, hence the Module [{1}] cannot be processed", engineType, module));
    }

    /**
     * Returns all the registered engine types
     *
     * @return engine types
     */
    public static Set<String> getEngineTypes() {
        return ScriptEngineExecutorFactory.getEnginesTypes();
    }

    /**
     * Returns all the registered engine types as JSON
     *
     * @return engine types as JSON
     */
    public static String getEngineTypesAsJson() {
        return GsonHelper.GSON.toJson(getEngineTypes());
    }

}
