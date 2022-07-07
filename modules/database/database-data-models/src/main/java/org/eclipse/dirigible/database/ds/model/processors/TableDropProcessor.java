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
package org.eclipse.dirigible.database.ds.model.processors;

import static java.text.MessageFormat.format;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.database.ds.model.DataStructureTableConstraintForeignKeyModel;
import org.eclipse.dirigible.database.ds.model.DataStructureTableModel;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Table Drop Processor.
 */
public class TableDropProcessor {

	private static final Logger logger = LoggerFactory.getLogger(TableDropProcessor.class);

	/**
	 * Execute the corresponding statement.
	 *
	 * @param connection
	 *            the connection
	 * @param tableModel
	 *            the table model
	 * @throws SQLException
	 *             the SQL exception
	 */
	public static void execute(Connection connection, DataStructureTableModel tableModel) throws SQLException {
		boolean caseSensitive = Boolean.parseBoolean(Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false"));
		String tableName = tableModel.getName();
		if (caseSensitive) {
			tableName = "\"" + tableName + "\"";
		}
		logger.info("Processing Drop Table: " + tableName);
		if (SqlFactory.getNative(connection).exists(connection, tableName)) {
			String sql = SqlFactory.getNative(connection).select().column("COUNT(*)").from(tableName)
					.build();
			PreparedStatement statement = connection.prepareStatement(sql);
			try {
				logger.info(sql);
				ResultSet resultSet = statement.executeQuery();
				if (resultSet.next()) {
					int count = resultSet.getInt(1);
					if (count > 0) {
						logger.error(
								format("Drop operation for the non empty Table [{0}] will not be executed. Delete all the records in the table first.",
										tableName));
						return;
					}
				}
			} catch (SQLException e) {
				logger.error(sql);
				logger.error(e.getMessage(), e);
			} finally {
				if (statement != null) {
					statement.close();
				}
			}
			
			if (tableModel.getConstraints().getForeignKeys() != null && !tableModel.getConstraints().getForeignKeys().isEmpty()) {
				for (DataStructureTableConstraintForeignKeyModel foreignKeyModel : tableModel.getConstraints().getForeignKeys()) {
					sql = SqlFactory.getNative(connection).drop().constraint(foreignKeyModel.getName()).fromTable(tableName).build();
					executeUpdate(connection, sql);
				}
			}

			sql = SqlFactory.getNative(connection).drop().table(tableName).build();
			executeUpdate(connection, sql);
		}
	}

	private static void executeUpdate(Connection connection, String sql) throws SQLException {
		PreparedStatement statement;
		logger.info(sql);
		statement = connection.prepareStatement(sql);
		try {
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.warn(sql);
			logger.warn(e.getMessage());
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
	}

}
