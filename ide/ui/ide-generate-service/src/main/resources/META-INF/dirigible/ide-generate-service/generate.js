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
angular.module('ideGenerate', [])
    .provider('generateApi', function GenerateApiProvider() {
        this.generateServiceUrl = '/services/v4/ide/generate';
        this.$get = ['$http', function generateApiFactory($http) {

            let generateFromTemplate = function (workspace, project, file, template, parameters = []) {
                let url = new UriBuilder().path(this.generateServiceUrl.split('/')).path('file').path(workspace).path(project).path(file.split('/')).build();
                return $http.post(url, { "template": template, "parameters": parameters })
                    .then(function successCallback(response) {
                        return { status: response.status, data: response.data };
                    }, function errorCallback(response) {
                        console.error('Publisher service:', response);
                        return { status: response.status };
                    });
            }.bind(this);

            let generateFromModel = function (workspace, project, file, template, parameters = []) {
                let url = new UriBuilder().path(this.generateServiceUrl.split('/')).path('model').path(workspace).path(project).path(file.split('/')).build();
                return $http.post(url, { "template": template, "parameters": parameters, "model": file })
                    .then(function successCallback(response) {
                        return { status: response.status, data: response.data };
                    }, function errorCallback(response) {
                        console.error('Publisher service:', response);
                        return { status: response.status };
                    });
            }.bind(this);

            return {
                generateFromTemplate: generateFromTemplate,
                generateFromModel: generateFromModel,
            };
        }];
    });