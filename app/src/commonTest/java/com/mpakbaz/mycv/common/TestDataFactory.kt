package com.mpakbaz.mycv.common

import com.google.gson.Gson
import com.mpakbaz.mycv.data.model.CVData
import com.mpakbaz.mycv.data.model.LoginRequest
import com.mpakbaz.mycv.data.model.LoginResponse
import com.mpakbaz.mycv.data.response.ApiSingleResponse

/**
 * Factory class that makes instances of data models with random field values.
 * The aim of this class is to help setting up test fixtures.
 */
object TestDataFactory {


    private const val sampleCv = "{\n" +
            "    \"basics\": {\n" +
            "        \"name\": \"Masoud Pakbaz\",\n" +
            "        \"label\": \"Senior Android Developer\",\n" +
            "        \"picture\": \"https://allunac-media-paris.s3.eu-west-3.amazonaws.com/masoud_img.jpg\",\n" +
            "        \"email\": \"mpakbaz@allunac.com\",\n" +
            "        \"phone\": \"(514) 750-5500\",\n" +
            "        \"degree\": \"\",\n" +
            "        \"website\": \"\",\n" +
            "        \"summary\": \"Senior Android Developer\",\n" +
            "        \"bio\": \"I am a senior Android developer with more than 10 years of experience. I am able to communicate well and always try to find the best tech that suits the project. I have experiences with Git, RxJava, Dagger, etc. I am a quick learner with the ability to apply new knowledge quickly and deliver high-quality results. I am attentive to details and a hard worker. I am an open-minded team player who always ready to adopt and share my experience and knowledge.\",\n" +
            "        \"location\": {\n" +
            "            \"address\": \"3 Place Ville Marie Suite 400\",\n" +
            "            \"postalCode\": \"H3B 2E3\",\n" +
            "            \"city\": \"Montreal\",\n" +
            "            \"countryCode\": \"Canada\",\n" +
            "            \"region\": \"QC\"\n" +
            "        },\n" +
            "        \"profiles\": [\n" +
            "            {\n" +
            "                \"network\": \"LinkedIn\",\n" +
            "                \"username\": \"mpakbaz\",\n" +
            "                \"url\": \"https://www.linkedin.com/in/mpakbaz/\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    \"work\": [\n" +
            "        {\n" +
            "            \"company\": \"Allunac INC\",\n" +
            "            \"position\": \"Senior Android Developer\",\n" +
            "            \"website\": \"The URL for the employer's website\",\n" +
            "            \"startDate\": \"Apr 2018\",\n" +
            "            \"endDate\": \"Aug 2019\",\n" +
            "            \"summary\": \"Leading a team of Android developers to develop, maintain and improve mobile applications.\",\n" +
            "            \"highlights\": [\n" +
            "                \"Bullet-point list items that you would like to include along with (or instead of) a summary paragraph.\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"company\": \"Sherpa-Solutions INC\",\n" +
            "            \"position\": \"Senior Android Developer\",\n" +
            "            \"website\": \"The URL for the employer's website\",\n" +
            "            \"startDate\": \"Sep 2016\",\n" +
            "            \"endDate\": \"Apr 2018\",\n" +
            "            \"summary\": \"Worked as part of a team to completely re-organize and re-architect the Android core library to implement a newer streamlined Ul and improved performance.\",\n" +
            "            \"highlights\": [\n" +
            "                \"Bullet-point list items that you would like to include along with (or instead of) a summary paragraph.\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"company\": \"Tripo INC\",\n" +
            "            \"position\": \"Android Developer\",\n" +
            "            \"website\": \"The URL for the employer's website\",\n" +
            "            \"startDate\": \"Dec 2015\",\n" +
            "            \"endDate\": \"Sep 2016\",\n" +
            "            \"summary\": \"Developed high-quality and multi-functional Android Mobile Application.\",\n" +
            "            \"highlights\": [\n" +
            "                \"Bullet-point list items that you would like to include along with (or instead of) a summary paragraph.\"\n" +
            "            ]\n" +
            "        }\n" +
            "    ],\n" +
            "    \"volunteer\": [],\n" +
            "    \"education\": [\n" +
            "        {\n" +
            "            \"institution\": \"John Abbott Collège\",\n" +
            "            \"area\": \"Certificate in Internet Programming and Development \",\n" +
            "            \"studyType\": \"software Engineering \",\n" +
            "            \"startDate\": \"2014\",\n" +
            "            \"endDate\": \"2015\",\n" +
            "            \"gpa\": \"\",\n" +
            "            \"courses\": [\n" +
            "                \"\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"institution\": \"John Abbott Collège\",\n" +
            "            \"area\": \"Certificate in Mobile Applications Development\",\n" +
            "            \"studyType\": \"software Engineering \",\n" +
            "            \"startDate\": \"2013\",\n" +
            "            \"endDate\": \"2014\",\n" +
            "            \"gpa\": \"\",\n" +
            "            \"courses\": [\n" +
            "                \"\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"institution\": \"McGill University\",\n" +
            "            \"area\": \"Certificate of Proficiency\",\n" +
            "            \"studyType\": \"English for Communication\",\n" +
            "            \"startDate\": \"2013\",\n" +
            "            \"endDate\": \"2014\",\n" +
            "            \"gpa\": \"\",\n" +
            "            \"courses\": [\n" +
            "                \"\"\n" +
            "            ]\n" +
            "        }\n" +
            "    ],\n" +
            "    \"awards\": [],\n" +
            "    \"publications\": [],\n" +
            "    \"skills\": [\n" +
            "        {\n" +
            "            \"name\": \"Android Developer\",\n" +
            "            \"level\": \"100\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"Scrum master and team lead\",\n" +
            "            \"level\": \"95\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"Experience using Android Unit tests, UI tests\",\n" +
            "            \"level\": \"90\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"RESTful, Retrofit2, RXJava2\",\n" +
            "            \"level\": \"90\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"Espresso and UIautomator\",\n" +
            "            \"level\": \"80\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"Spring Boot Framework\",\n" +
            "            \"level\": \"80\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"Hibernate\",\n" +
            "            \"level\": \"80\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"Apache Thrift\",\n" +
            "            \"level\": \"60\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"languages\": [\n" +
            "        {\n" +
            "            \"language\": \"English\",\n" +
            "            \"fluency\": \"Fluent\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"language\": \"French\",\n" +
            "            \"fluency\": \"Fluent\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"interests\": [\n" +
            "        {\n" +
            "            \"name\": \"StartUp\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"Technology\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"Science\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"Sports\"\n" +
            "        }\n" +
            "    ]\n" +
            "}\n"

    fun makeCvData(): CVData {
        return Gson().fromJson(sampleCv, CVData::class.java)
    }

    fun makeTestUserName(): String {
        return "masoud.pakbaz80@gmail.com"
    }

    fun makeTestPassword(): String {
        return "123456"
    }

    fun makeTestLoginData(): LoginRequest {
        return LoginRequest(makeTestUserName(), makeTestPassword())
    }

    fun makeTestLoginResponse(): ApiSingleResponse<LoginResponse> {
        return ApiSingleResponse()
    }

    fun makeTestGetCVResponse(): ApiSingleResponse<CVData> {
        val resp = ApiSingleResponse<CVData>()
        resp.result = makeCvData()
        return resp
    }

}