package com.softmarshmallow.foodle.Services;

import android.app.Activity;
import android.content.Context;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.services.s3.AmazonS3Client;

/**
 * Created by yuntaeil on 2018. 2. 6..
 */

public class AmazonS3Service {
    public static TransferUtility transferUtility =null;

    public static TransferUtility getTransferUtility(Context context) {
        if(transferUtility ==null){
            transferUtility = TransferUtility.builder()
                    .context(context)
                    .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                    .s3Client(new AmazonS3Client(AWSMobileClient.getInstance().getCredentialsProvider()))
                    .build();
        }
        return transferUtility;
    }
}
