/*
 * Copyright (c) 2015 Nexmo Inc
 * All rights reserved.
 *
 * Licensed only under the Nexmo Verify SDK License Agreement located at
 *
 * https://www.nexmo.com/terms-use/verify-sdk/ (the “License”)
 *
 * You may not use, exercise any rights with respect to or exploit this SDK,
 * or any modifications or derivative works thereof, except in accordance
 * with the License.
 */

package com.softmarshmallow.foodle.Views.SMSVerification;

/**
 * Country information.
 */
//todo -> FQN... eg. CountryInfoContainer
public class Country {

    //todo Add Extra Feilds... eg. DialCode
    private final String name;
    private final String code;

    public Country(final String name, final String code) {
        this.name = name;
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

}
