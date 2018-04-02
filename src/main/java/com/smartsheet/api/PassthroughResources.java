package com.smartsheet.api;

/*
 * #[license]
 * Smartsheet SDK for Java
 * %%
 * Copyright (C) 2018 Smartsheet
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * %[license]
 */

import java.util.HashMap;

/**
 * <p>This interface provides methods to access Passthrough resources.</p>
 *
 * <p>Thread Safety: Implementation of this interface must be thread safe.</p>
 */
public interface PassthroughResources {

    /**
     * <p>Issue an HTTP GET request.</p>
     *
     * @param endpoint the API endpoint
     * @param parameters optional list of resource parameters
     * @return a JSON response string
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public String getRequest(String endpoint, HashMap<String, Object> parameters) throws SmartsheetException;

    /**
     * <p>Issue an HTTP POST request.</p>
     *
     * @param endpoint the API endpoint
     * @param payload a JSON payload string
     * @param parameters optional list of resource parameters
     * @return a JSON response string
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public String postRequest(String endpoint, String payload, HashMap<String, Object> parameters) throws SmartsheetException;

    /**
     * <p>Issue an HTTP PUT request.</p>
     *
     * @param endpoint the API endpoint
     * @param payload a JSON payload string
     * @param parameters optional list of resource parameters
     * @return a JSON response string
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public String putRequest(String endpoint, String payload,  HashMap<String, Object> parameters) throws SmartsheetException;

    /**
     * <p>Issue an HTTP DELETE request.</p>
     *
     * @param endpoint the API endpoint
     * @return a JSON response string
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public String deleteRequest(String endpoint) throws SmartsheetException;
}
