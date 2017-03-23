/**
 * Copyright 2014-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance with the License. A copy of the License is located at
 *
 *
 * http://aws.amazon.com/apache2.0/
 *
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package org.dnltsk.bestvehicletypeskill

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler
import com.google.common.collect.ImmutableSet
import com.google.inject.Guice

/**
 * This class could be the handler for an AWS Lambda function powering an Alexa Skills Kit
 * experience. To do this, simply set the handler field in the AWS Lambda console to
 * "helloworld.HelloWorldSpeechletRequestStreamHandler" For this to work, you'll also need to build
 * this project using the `lambda-compile` Ant task and upload the resulting zip file to power
 * your function.
 */
class SkillSpeechletHandler : SpeechletRequestStreamHandler(
        Guice.createInjector(SkillSpeechletModule()).getInstance(SkillSpeechlet::class.java),
        supportedApplicationIds
) {

    companion object {
        private val supportedApplicationIds = ImmutableSet.of("amzn1.ask.skill.f69bb9f8-992f-4b47-8994-8ea4297cfedd")
    }

}
