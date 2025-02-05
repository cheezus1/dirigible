/*
 * Copyright (c) 2010-2021 SAP SE or an SAP affiliate company and Eclipse Dirigible contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * SPDX-FileCopyrightText: 2010-2021 SAP SE or an SAP affiliate company and Eclipse Dirigible contributors
 * SPDX-License-Identifier: EPL-2.0
 */
var java = require('core/v3/java');

exports.encode = function(input) {
	var bytes = input;
	if (typeof bytes === 'string') {
		var streams = require('io/v3/streams');
		var baos = streams.createByteArrayOutputStream();
		baos.writeText(bytes);
		bytes = baos.getBytes();
	}
	var output = java.call('org.eclipse.dirigible.api.v3.utils.Base64Facade', 'encode', [JSON.stringify(bytes)]);
	return output;
};

exports.decode = function(input) {
	var output = java.call('org.eclipse.dirigible.api.v3.utils.Base64Facade', 'decode', [input]);
	if (output && output != null) {
		return JSON.parse(output);
	}
	return output;
};

