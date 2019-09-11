package com.mpakbaz.mycv.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Keep
class CVData : Serializable {


    /**
     * id : 20
     * created : 1568026616866
     * email : null
     * basics : {"name":"Masoud Pakbaz","label":"Senior Android Developer","picture":"https://allunac-media-paris.s3.eu-west-3.amazonaws.com/masoud_img.jpg","email":"mpakbaz@allunac.com","phone":"(514) 750-5500","degree":"","website":"","summary":"Senior Android Developer","location":{"id":null,"created":null,"updated":null,"address":"3 Place Ville Marie Suite 400","postalCode":"H3B 2E3","city":"Montreal","countryCode":"Canada)","region":"QC"},"profiles":[{"id":null,"created":null,"updated":null,"network":"LinkedIn","username":"mpakbaz","url":"https://www.linkedin.com/in/mpakbaz/"}]}
     * work : [{"id":70,"created":1568026616871,"updated":1568026616871,"company":"Allunac INC","position":"Senior Android Developer","website":"The URL for the employer's website","startDate":"Apr 2018","endDate":"Aug 2019","summary":"Leading a team of Android developers to maintain and improve mobile applications."},{"id":71,"created":1568026616871,"updated":1568026616871,"company":"Sherpa-Solutions INC","position":"Senior Android Developer","website":"The URL for the employer's website","startDate":"Sep 2016","endDate":"Apr 2018","summary":"Worked as part of a team to completely re-organize and re-architect the Android core library to implement a newer streamlined Ul and improved performance."},{"id":72,"created":1568026616871,"updated":1568026616871,"company":"Tripo INC","position":"Android Developer","website":"The URL for the employer's website","startDate":"Dec 2015","endDate":"Sep 2016","summary":"Developed high-quality and multi-functional Android Mobile Application."}]
     * volunteer : []
     * education : [{"id":30,"created":1568026616867,"updated":1568026616867,"institution":"John Abbott Collège","area":"Certificate in Internet Programming and Development ","studyType":"software Engineering ","startDate":"2014","endDate":"2015","gpa":""},{"id":31,"created":1568026616868,"updated":1568026616868,"institution":"John Abbott Collège","area":"Certificate in Mobile Applications Development","studyType":"software Engineering ","startDate":"2013","endDate":"2014","gpa":""},{"id":32,"created":1568026616868,"updated":1568026616868,"institution":"McGill University","area":"Certificate of Proficiency","studyType":"English for Communication","startDate":"2013","endDate":"2014","gpa":""}]
     * awards : []
     * publications : []
     * skills : [{"id":60,"created":1568026616870,"updated":1568026616870,"name":"Android mobile applications","level":"100"},{"id":61,"created":1568026616870,"updated":1568026616870,"name":"Scrum master and team lead","level":"95"},{"id":62,"created":1568026616870,"updated":1568026616870,"name":"Experience using Android Unit tests, UI tests","level":"90"},{"id":63,"created":1568026616870,"updated":1568026616870,"name":"RESTful, Retrofit2, RXJava2","level":"90"},{"id":64,"created":1568026616871,"updated":1568026616871,"name":"Espresso and Uiautomator","level":"80"}]
     * languages : [{"id":50,"created":1568026616869,"updated":1568026616869,"language":"English","fluency":"Fluent"},{"id":51,"created":1568026616869,"updated":1568026616869,"language":"French","fluency":"Fluent"}]
     * interests : [{"id":40,"created":1568026616868,"updated":1568026616868,"name":"StartUp"},{"id":41,"created":1568026616869,"updated":1568026616869,"name":"Technology"},{"id":42,"created":1568026616869,"updated":1568026616869,"name":"Science"},{"id":43,"created":1568026616869,"updated":1568026616869,"name":"Sports"}]
     */

    val id: Int = 0

    val basics: BasicsBean? = null
    val work: List<WorkBean>? = null
    val volunteer: List<*>? = null
    val education: List<EducationBean>? = null
    val awards: List<*>? = null
    val publications: List<*>? = null
    val skills: List<SkillsBean>? = null
    val languages: List<LanguagesBean>? = null
    val interests: List<InterestsBean>? = null


    @Keep
    class BasicsBean : Serializable {
        /**
         * name : Masoud Pakbaz
         * label : Senior Android Developer
         * picture : https://allunac-media-paris.s3.eu-west-3.amazonaws.com/masoud_img.jpg
         * email : mpakbaz@allunac.com
         * phone : (514) 750-5500
         * degree :
         * website :
         * summary : Senior Android Developer
         * location : {"id":null,"created":null,"updated":null,"address":"3 Place Ville Marie Suite 400","postalCode":"H3B 2E3","city":"Montreal","countryCode":"Canada)","region":"QC"}
         * profiles : [{"id":null,"created":null,"updated":null,"network":"LinkedIn","username":"mpakbaz","url":"https://www.linkedin.com/in/mpakbaz/"}]
         */

        val name: String? = null
        val label: String? = null
        val picture: String? = null
        val email: String? = null
        val phone: String? = null
        val degree: String? = null
        val website: String? = null
        val summary: String? = null
        val bio: String? = null
        val location: LocationBean? = null
        val profiles: List<ProfilesBean>? = null


        @Keep
        class LocationBean : Serializable {
            /**
             * id : null
             * created : null
             * updated : null
             * address : 3 Place Ville Marie Suite 400
             * postalCode : H3B 2E3
             * city : Montreal
             * countryCode : Canada)
             * region : QC
             */

            val id: Any? = null
            val address: String? = null
            @SerializedName("postalCode")
            val postalCode: String? = null
            val city: String? = null
            @SerializedName("countryCode")
            val countryCode: String? = null
            val region: String? = null
        }


        @Keep
        class ProfilesBean : Serializable {
            /**
             * id : null
             * created : null
             * updated : null
             * network : LinkedIn
             * username : mpakbaz
             * url : https://www.linkedin.com/in/mpakbaz/
             */

            val id: Any? = null
            val network: String? = null
            val username: String? = null
            val url: String? = null
        }
    }

    @Keep
    class WorkBean : Serializable {
        /**
         * id : 70
         * created : 1568026616871
         * updated : 1568026616871
         * company : Allunac INC
         * position : Senior Android Developer
         * website : The URL for the employer's website
         * startDate : Apr 2018
         * endDate : Aug 2019
         * summary : Leading a team of Android developers to maintain and improve mobile applications.
         */

        val id: Int = 0
        val company: String? = null
        val position: String? = null
        val website: String? = null
        @SerializedName("startDate")
        val startDate: String? = null
        @SerializedName("endDate")
        val endDate: String? = null
        val summary: String? = null
    }

    @Keep
    class EducationBean : Serializable {
        /**
         * id : 30
         * created : 1568026616867
         * updated : 1568026616867
         * institution : John Abbott Collège
         * area : Certificate in Internet Programming and Development
         * studyType : software Engineering
         * startDate : 2014
         * endDate : 2015
         * gpa :
         */

        val id: Int = 0
        val institution: String? = null
        val area: String? = null
        @SerializedName("studyType")
        val studyType: String? = null
        @SerializedName("startDate")
        val startDate: String? = null
        @SerializedName("endDate")
        val endDate: String? = null
        val gpa: String? = null
    }

    @Keep
    class SkillsBean : Serializable {
        /**
         * id : 60
         * created : 1568026616870
         * updated : 1568026616870
         * name : Android mobile applications
         * level : 100
         */

        val id: Int = 0
        val name: String? = null
        val level: String? = null
    }

    @Keep
    class LanguagesBean : Serializable {
        /**
         * id : 50
         * created : 1568026616869
         * updated : 1568026616869
         * language : English
         * fluency : Fluent
         */

        val id: Int = 0
        val language: String? = null
        val fluency: String? = null
    }

    @Keep
    class InterestsBean : Serializable {
        /**
         * id : 40
         * created : 1568026616868
         * updated : 1568026616868
         * name : StartUp
         */

        val id: Int = 0
        val name: String? = null
    }
}

