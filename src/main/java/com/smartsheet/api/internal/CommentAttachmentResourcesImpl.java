package com.smartsheet.api.internal;

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

import com.smartsheet.api.AuthorizationException;
import com.smartsheet.api.InvalidRequestException;
import com.smartsheet.api.ResourceNotFoundException;
import com.smartsheet.api.ServiceUnavailableException;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.util.Util;
import com.smartsheet.api.models.Attachment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * This is the implementation of the CommentAttachmentResources.
 *
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class CommentAttachmentResourcesImpl extends AbstractResources implements com.smartsheet.api.CommentAttachmentResources{

    public CommentAttachmentResourcesImpl(SmartsheetImpl smartsheet) {
        super(smartsheet);
    }

    /**
     * Attach a URL to a comment.
     *
     * The URL can be a normal URL (attachmentType "URL"), a Google Drive URL (attachmentType "GOOGLE_DRIVE") or a
     * Box.com URL (attachmentType "BOX_COM").
     *
     * It mirrors to the following Smartsheet REST API method: POST /sheets/{sheetId}/comments/{commentId}/attachments
     *
     * @param sheetId the sheet id
     * @param commentId the comment id
     * @param attachment the attachment object
     * @return the created attachment
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Attachment attachUrl(long sheetId, long commentId, Attachment attachment) throws SmartsheetException
    {
        return this.createResource("sheets/" + sheetId + "/comments/" + commentId + "/attachments", Attachment.class, attachment);
    }

    /**
     * Attach a file to a comment with simple upload.
     *
     * It mirrors to the following Smartsheet REST API method: POST /sheets/{sheetId}/comments/{commentId}/attachments
     *
     * @param sheetId the id of the sheet
     * @param commentId the id of the comment
     * @param file the file to attach
     * @param contentType the content type of the file
     * @return the created attachment
     * @throws FileNotFoundException the file not found exception
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Attachment attachFile(long sheetId, long commentId, File file, String contentType) throws FileNotFoundException,
            SmartsheetException {
        Util.throwIfNull(sheetId, commentId, file, contentType);
        Util.throwIfEmpty(contentType);

        return attachFile(sheetId, commentId, new FileInputStream(file), contentType, file.length(), file.getName());
    }

    /**
     * Attach file for simple upload.
     *
     * @param sheetId the sheet id
     * @param commentId the comment id
     * @param inputStream the attachment data inputStream
     * @param contentType the content type
     * @param contentLength the content length
     * @param attachmentName the name of the attachment
     * @return the attachment
     * @throws SmartsheetException the smartsheet exception
     */
    public Attachment attachFile(long sheetId, long commentId, InputStream inputStream, String contentType, long contentLength, String attachmentName)
            throws SmartsheetException {
        Util.throwIfNull(inputStream, contentType);
        return super.attachFile("sheets/" + sheetId + "/comments/" + commentId + "/attachments", inputStream, contentType, contentLength, attachmentName);
    }
}
