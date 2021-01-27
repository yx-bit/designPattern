package com.java.learn.minio;/*
 * MinIO Java SDK for Amazon S3 Compatible Cloud Storage, (C) 2017 MinIO, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidBucketNameException;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.XmlParserException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import me.tongfei.progressbar.ProgressBarStyle;

public class PutObjectProgressBar {
  /** MinioClient.putObjectProgressBar() example. */
  public static void main(String[] args)
      throws InvalidKeyException, NoSuchAlgorithmException, InvalidEndpointException,
          InvalidPortException, InvalidBucketNameException, InsufficientDataException,
          ErrorResponseException, InternalException, IllegalArgumentException, IOException,
          XmlParserException, InvalidResponseException {
    /* play.min.io for test and development. */
    MinioClient minioClient =
        new MinioClient(
            "https://play.min.io",
            "Q3AM3UQ867SPQQA43P2F",
            "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG");
    /* Amazon S3: */
    // MinioClient minioClient = new MinioClient("https://s3.amazonaws.com",
    // "YOUR-ACCESSKEYID",
    // "YOUR-SECRETACCESSKEY");

    String objectName = "my-objectname";
    String bucketName = "my-bucketname";

    File file = new File("my-filename");
    InputStream pis =
        new BufferedInputStream(
            new ProgressStream("Uploading... ", ProgressBarStyle.ASCII, new FileInputStream(file)));
    minioClient.putObject(bucketName, objectName, pis, new PutObjectOptions(pis.available(), -1));
    pis.close();
    System.out.println("my-objectname is uploaded successfully");
  }
}
