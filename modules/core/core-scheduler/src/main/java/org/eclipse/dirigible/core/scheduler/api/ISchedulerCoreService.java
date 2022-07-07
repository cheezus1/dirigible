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
package org.eclipse.dirigible.core.scheduler.api;

import java.util.Collection;
import java.util.List;

import org.eclipse.dirigible.commons.api.service.ICoreService;
import org.eclipse.dirigible.core.scheduler.service.definition.JobDefinition;
import org.eclipse.dirigible.core.scheduler.service.definition.JobEmailDefinition;
import org.eclipse.dirigible.core.scheduler.service.definition.JobLogDefinition;
import org.eclipse.dirigible.core.scheduler.service.definition.JobParameterDefinition;

/**
 * The Scheduler Core Service interface.
 */
public interface ISchedulerCoreService extends ICoreService {

	/** The job file extension */
	public String FILE_EXTENSION_JOB = ".job";

	/** The internal jobs */
	public String JOB_GROUP_INTERNAL = "dirigible-internal";

	/** The user defined jobs */
	public String JOB_GROUP_DEFINED = "dirigible-defined";

	/** The handler parameter */
	public String JOB_PARAMETER_HANDLER = "dirigible-job-handler";

	/** The engine type */
	public String JOB_PARAMETER_ENGINE = "dirigible-engine-type";

	/**
	 * Creates the job with parameters.
	 *
	 * @param name
	 *            the name
	 * @param group
	 *            the group
	 * @param clazz
	 *            the job class
	 * @param handler
	 *            the handler
	 * @param engine
	 *            the engine type
	 * @param description
	 *            the description
	 * @param expression
	 *            the expression
	 * @param singleton
	 *            the singleton
	 * @param parameters
	 *            the parameters list
	 * @return the job definition
	 * @throws SchedulerException
	 *             the scheduler exception
	 */
	public JobDefinition createJob(String name, String group, String clazz, String handler, String engine, String description, String expression,
			boolean singleton, Collection<JobParameterDefinition> parameters) throws SchedulerException;

	/**
	 * Creates the job by definition.
	 *
	 * @param jobDefinition
	 *            the job definition
	 * @return the job definition
	 * @throws SchedulerException
	 *             the scheduler exception
	 */
	public JobDefinition createOrUpdateJob(JobDefinition jobDefinition) throws SchedulerException;

	/**
	 * Gets the job.
	 *
	 * @param name
	 *            the name
	 * @return the job
	 * @throws SchedulerException
	 *             the scheduler exception
	 */
	public JobDefinition getJob(String name) throws SchedulerException;

	/**
	 * Removes the job.
	 *
	 * @param name
	 *            the name
	 * @throws SchedulerException
	 *             the scheduler exception
	 */
	public void removeJob(String name) throws SchedulerException;

	/**
	 * Update job.
	 *
	 * @param name
	 *            the name
	 * @param group
	 *            the group
	 * @param clazz
	 *            the job class
	 * @param handler
	 *            the handler
	 * @param engine
	 *            the engine type
	 * @param description
	 *            the description
	 * @param expression
	 *            the expression
	 * @param singleton
	 *            the singleton
	 * @param parameters
	 *            the parameters list
	 * @throws SchedulerException
	 *             the scheduler exception
	 */
	public void updateJob(String name, String group, String clazz, String handler, String engine, String description, String expression,
			boolean singleton, Collection<JobParameterDefinition> parameters) throws SchedulerException;

	/**
	 * Gets the jobs.
	 *
	 * @return the jobs
	 * @throws SchedulerException
	 *             the scheduler exception
	 */
	public List<JobDefinition> getJobs() throws SchedulerException;

	/**
	 * Checks whether a job with the given name already exist
	 *
	 * @param name
	 *            the name
	 * @return true if exists and false otherwise
	 * @throws SchedulerException
	 *             in case of an internal error
	 */
	public boolean existsJob(String name) throws SchedulerException;

	/**
	 * Parses the job.
	 *
	 * @param json
	 *            the json
	 * @return the job definition
	 */
	public JobDefinition parseJob(String json);

	/**
	 * Parses the job.
	 *
	 * @param content
	 *            the content
	 * @return the job definition
	 */
	public JobDefinition parseJob(byte[] content);

	/**
	 * Serializes the job definition
	 *
	 * @param jobDefinition
	 *            the job definition
	 * @return serialized definition as string
	 */
	public String serializeJob(JobDefinition jobDefinition);
	
	// Job Log
	
	/**
	 * Register triggered job event
	 * 
	 * @param name the name of the job 
	 * @param handler the current handler
	 * @return the Job Log
	 * @throws SchedulerException exception
	 */
	public JobLogDefinition jobTriggered(String name, String handler) throws SchedulerException;
	
	/**
	 * Register finished job event
	 * 
	 * @param name the name of the job 
	 * @param handler the current handler
	 * @param triggeredId id of the triggering event
	 * @param triggeredAt the time when has been triggered 
	 * @return the Job Log
	 * @throws SchedulerException exception
	 */
	public JobLogDefinition jobFinished(String name, String handler, long triggeredId, java.util.Date triggeredAt) throws SchedulerException;
	
	/**
	 * Register failed job event
	 * 
	 * @param name the name of the job 
	 * @param handler the current handler
	 * @param triggeredId id of the triggering event
	 * @param triggeredAt the time when has been triggered
	 * @param message the error message
	 * @return the Job Log
	 * @throws SchedulerException exception
	 */
	public JobLogDefinition jobFailed(String name, String handler, long triggeredId, java.util.Date triggeredAt, String message) throws SchedulerException;
	
	/**
	 * Register logged job event
	 * 
	 * @param name the name of the job 
	 * @param handler the current handler
	 * @param message the message logged
	 * @return the Job Log
	 * @throws SchedulerException exception
	 */
	public JobLogDefinition jobLogged(String name, String handler, String message) throws SchedulerException;
	
	/**
	 * Register logged job event for error
	 * 
	 * @param name the name of the job 
	 * @param handler the current handler
	 * @param message the message logged
	 * @return the Job Log
	 * @throws SchedulerException exception
	 */
	public JobLogDefinition jobLoggedError(String name, String handler, String message) throws SchedulerException;
	
	/**
	 * Register logged job event for warning
	 * 
	 * @param name the name of the job 
	 * @param handler the current handler
	 * @param message the message logged
	 * @return the Job Log
	 * @throws SchedulerException exception
	 */
	public JobLogDefinition jobLoggedWarning(String name, String handler, String message) throws SchedulerException;
	
	/**
	 * Register logged job event for info
	 * 
	 * @param name the name of the job 
	 * @param handler the current handler
	 * @param message the message logged
	 * @return the Job Log
	 * @throws SchedulerException exception
	 */
	public JobLogDefinition jobLoggedInfo(String name, String handler, String message) throws SchedulerException;
	
	/**
	 * Get all the log per job's name
	 * 
	 * @param name the job name
	 * @return the list of events
	 * @throws SchedulerException exception
	 */
	public List<JobLogDefinition> getJobLogs(String name) throws SchedulerException;
	
	/**
	 * Clear all the log per job's name
	 * 
	 * @param name the job name
	 * @throws SchedulerException exception
	 */
	public void clearJobLogs(String name) throws SchedulerException;
	
	/**
	 * Delete Job Logs older than a week
	 * 
	 * @throws SchedulerException exception
	 */
	public void deleteOldJobLogs() throws SchedulerException;
	
	/**
	 * Get all the parameters per job's name
	 * 
	 * @param name the job name
	 * @return the list of parameters
	 * @throws SchedulerException exception
	 */
	public List<JobParameterDefinition> getJobParameters(String name) throws SchedulerException;

	/**
	 * Get e-mail addresses assigned as watchers of this job
	 *
	 * @param name the job name
	 * @return the list of e-mails
	 * @throws SchedulerException in case of an error
	 */
	public List<JobEmailDefinition> getJobEmails(String name) throws SchedulerException;

	/**
	 * Add an e-mail to the list of e-mail watchers
	 *
	 * @param name the name of the job
	 * @param email the e-mail
	 * @throws SchedulerException in case of an error
	 */
	public void addJobEmail(String name, String email) throws SchedulerException;

	/**
	 * Remove the e-mail from the list
	 * @param id the id of the e-mail definition
	 * @throws SchedulerException in case of an error
	 */
	public void removeJobEmail(Long id) throws SchedulerException;

}
