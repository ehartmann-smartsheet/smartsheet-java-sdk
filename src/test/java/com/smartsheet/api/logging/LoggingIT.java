package com.smartsheet.api.logging;

/*
 * #[license]
 * Smartsheet Java SDK
 * %%
 * Copyright (C) 2023 Smartsheet
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

import com.smartsheet.api.Smartsheet;
import com.smartsheet.api.SmartsheetBuilder;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.Trace;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.internal.json.JacksonJsonSerializer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

// Note this is an IT test because at least one of the tests requires an internet connection
class LoggingIT {
    @BeforeAll
    public static void dontFailOnUnrecongnizedFields() {
        // Setup the serializer
        JacksonJsonSerializer.setFailOnUnknownProperties(false);    // no idea why we enable this in ResourcesImplBase.baseSetup
    }

    @AfterAll
    public static void failOnUnrecognizedFields() {
        // Setup the serializer
        JacksonJsonSerializer.setFailOnUnknownProperties(true);    // put it back the way we found it
    }

    @Test
    void testConsoleLogging() {
        ByteArrayOutputStream traceStream = new ByteArrayOutputStream();
        DefaultHttpClient.setTraceStream(traceStream);
        Smartsheet client = new SmartsheetBuilder().build();
        client.setTraces(Trace.Request, Trace.Response);    // should log entire request and response

        // Note this requires an internet connection
        assertThatThrownBy(() -> client.sheetResources().getSheet(42, null, null, null, null, null, 1, 1))
                .isInstanceOf(SmartsheetException.class);

        String output = traceStream.toString();
        // not super-robust but asserts some of the important parts
        assertThat(output)
                .contains("\"request\" : {")
                .contains("\"Authorization\" : \"Bearer ****null") // truncated Auth header
                .contains("\"response\" : {")
                .contains("\"body\" : \"{\\n  \\\"errorCode\\\" : 1002,\\n  \\\"message\\\" : " +
                            "\\\"Your Access Token is invalid.\\\",\\n  \\\"refId\\\" :")
                .contains("\"status\" : \"HTTP/1.1 401 Unauthorized\"");
    }

    @Test
    void testCustomLogging() {
        ByteArrayOutputStream traceStream = new ByteArrayOutputStream();
        DefaultHttpClient.setTraceStream(traceStream);
        Smartsheet client = new SmartsheetBuilder().setAccessToken("just_a_random_dummy_token").build(); // using "null" as token results in NPE
        client.setTraces(Trace.Request, Trace.Response);    // should log entire request and response


        // Note this requires an internet connection
        assertThatThrownBy(() -> client.sheetResources().getSheet(42, null, null, null, null, null, 1, 1))
                .isInstanceOf(SmartsheetException.class);

        String output = traceStream.toString();
        // not super-robust but asserts some of the important parts
        assertThat(output)
                .contains("request:{")
                .contains("'Authorization':'Bearer ****ae05")
                .contains("response:{")
                .contains("body:'{\n  \"errorCode\" : 1002,\n  \"message\" : \"Your Access Token is invalid.\",\n  \"refId\" :")
                .contains("status:'HTTP/1.1 401 Unauthorized'");
    }
}
