package com.smartsheet.api.models;

/*
 * #[license]
 * Smartsheet SDK for Java
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

import com.smartsheet.api.models.enums.AttachmentParentType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AttachmentParentTypeTest {

    @Test
    void test() {
        assertThat(AttachmentParentType.valueOf("SHEET")).isNotNull();
        assertThat(AttachmentParentType.valueOf("ROW")).isNotNull();
        assertThat(AttachmentParentType.valueOf("COMMENT")).isNotNull();
        assertThat(AttachmentParentType.values()).hasSize(3);
    }

}
