package com.mobatia.naisapp.constants

import com.mobatia.naisapp.activity.common.banner.model.Bannerresponse
import com.mobatia.naisapp.activity.common.model.LoginResponse
import com.mobatia.naisapp.activity.common.studentlist.model.StudentListModel
import com.mobatia.naisapp.activity.parentsessential.busservice.model.BusServiceResponse
import com.mobatia.naisapp.activity.parentsessential.information.model.InformationResponse
import com.mobatia.naisapp.activity.parentsessential.naslunchboxmenu.model.LunchBoxResponse
import com.mobatia.naisapp.activity.parentsessential.termdates.model.TermDatesResponse
import com.mobatia.naisapp.activity.parentsessential.uniform.model.UniformResponse
import com.mobatia.naisapp.fragment.contact_us.model.Contactusresponse
import com.mobatia.naisapp.fragment.early_years.model.Earlyyearsresponse
import com.mobatia.naisapp.fragment.ibprogramme.model.IBdetailsresponse
import com.mobatia.naisapp.fragment.ibprogramme.model.ibprogrammeresponse
import com.mobatia.naisapp.fragment.parentsessentials.model.ParentsEssentialBannerResponse
import com.mobatia.naisapp.fragment.performing_arts.model.Performingarts_bannerresponse
import com.mobatia.naisapp.fragment.performing_arts.model.Performingartslistresponse
import com.mobatia.naisapp.fragment.permissionslips.model.PermissionFormStatusApiModel
import com.mobatia.naisapp.fragment.permissionslips.model.PermissionFormsApiModel
import com.mobatia.naisapp.fragment.permissionslips.model.PermissionFormsResponse
import com.mobatia.naisapp.fragment.permissionslips.model.PermissionFormsStatusResponse
import com.mobatia.naisapp.fragment.primary.model.Primarydetailsresponse
import com.mobatia.naisapp.fragment.primary.model.Primaryresponse
import com.mobatia.naisapp.fragment.primary.model.comingup.Primarycomingupresponse
import com.mobatia.naisapp.fragment.reports.model.ReportApiModel
import com.mobatia.naisapp.fragment.reports.model.ReportListModel
import com.mobatia.naisapp.fragment.secondary.model.Secondarydetailsresponse
import com.mobatia.naisapp.fragment.secondary.model.Secondaryresponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {

    /*************LOGIN****************/
    @POST("api/v1/parent/auth/login")
    @FormUrlEncoded
    fun login(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("devicetype") devicetype: Int,
        @Field("deviceid") fcmid: String,
        @Field("device_identifier") deviceid: String
    ): Call<LoginResponse>

    /*************FORGOT PASSWORD****************/
    @POST("api/v1/parent/auth/forgotpassword")
    @FormUrlEncoded
    fun forgotpassword(
        @Field("email") email: String
    ): Call<ResponseBody>

    /*************FORGOT PASSWORD****************/
    @POST("api/v1/parent/auth/register")
    @FormUrlEncoded
    fun registeruser(
        @Field("email") email: String
    ): Call<ResponseBody>

    /*************PRIMARY****************/
    @GET("api/v1/parent/departmentprimary")
    fun primarylist(): Call<Primaryresponse>

    /*************PRIMARY DETAIL****************/
    @POST("api/v1/parent/departmentprimary/detail")
    @FormUrlEncoded
    fun primarydetails(
        @Field("departmentprimary_id") departmentprimary_id: Int,
        @Field("page_number") page_number: Int
    ): Call<Primarydetailsresponse>

    /*************PRIMARY COMING UP****************/
    @POST("api/v1/parent/primary_coming_up")
    @FormUrlEncoded
    fun primarycomingup(
        @Field("page_number") page_number: Int
    ): Call<Primarycomingupresponse>

    /*************SECONDARY****************/
    @GET("api/v1/parent/departmentsecondary")
    fun secondarylist(): Call<Secondaryresponse>

    /*************SECONDARY DETAIL****************/
    @POST("api/v1/parent/departmentsecondary/detail")
    @FormUrlEncoded
    fun secondarydetails(
        @Field("departmentsecondary_id") departmentprimary_id: Int,
        @Field("page_number") page_number: Int
    ): Call<Secondarydetailsresponse>

    /*************SECONDARY COMING UP****************/
    @POST("api/v1/parent/secondary_coming_up")
    @FormUrlEncoded
    fun secondarycomingup(
        @Field("page_number") page_number: Int
    ): Call<Primarycomingupresponse>

    /*************IB PROGRAMME****************/
    @GET("api/v1/parent/department_ib_programmes")
    fun ibprogrammelist(): Call<ibprogrammeresponse>

    /*************IB PROGRAMME DETAIL****************/
    @POST("api/v1/parent/department_ib_programme/detail")
    @FormUrlEncoded
    fun ibdetails(
        @Field("department_ib_programme_id") department_ib_programme_id: Int,
        @Field("page_number") page_number: Int
    ): Call<IBdetailsresponse>

    /*************IB PROGRAMME COMING UP****************/
    @POST("api/v1/parent/ib_programmes_coming_up")
    @FormUrlEncoded
    fun ibprogrammecomingup(
        @Field("page_number") page_number: Int
    ): Call<Primarycomingupresponse>

    /*************EARLY YEARS****************/
    @GET("api/v1/parent/department_early_years")
    fun earlyyearslist(): Call<Earlyyearsresponse>

    /*************IB PROGRAMME DETAIL****************/
    @POST("api/v1/parent/department_early_year/detail")
    @FormUrlEncoded
    fun earlydetails(
        @Field("department_early_year_id") department_early_year_id: Int,
        @Field("page_number") page_number: Int
    ): Call<IBdetailsresponse>

    /*************EARLY YEARS COMING UP****************/
    @POST("api/v1/parent/early_years_coming_up")
    @FormUrlEncoded
    fun early_yearscomingup(
        @Field("page_number") page_number: Int
    ): Call<Primarycomingupresponse>

    /*************Report List****************/
    @POST("api/v1/parent/student_reports")
    @Headers("Content-Type: application/json")
    fun reportList(
        @Body reportListModel: ReportApiModel,
        @Header("Authorization") token:String
    ): Call<ReportListModel>

    /*************STUDENT_LIST****************/
    @GET("api/v1/parent/studentlist")
    @Headers("Content-Type: application/x-www-form-urlencode","Accept: application/json")
    fun studentList(
        @Header("Authorization") token:String
    ): Call<StudentListModel>

    /*************PERFORMING ARTS BANNER****************/
    @GET("api/v1/parent/performing_arts/banner")
    fun performiong_arts(): Call<Performingarts_bannerresponse>

    /*************PERFORMING ARTS LIST****************/
    @POST("api/v1/parent/performing_arts")
    @FormUrlEncoded
    fun performingarts_list(
        @Field("page_number") page_number: Int
    ): Call<Performingartslistresponse>


    /*************PARENTS ESSENTIAL BANNER****************/
    @GET("api/v1/parent/parent_essentials/banner")
    fun parentsEssentialBanner(): Call<ParentsEssentialBannerResponse>

    /*************TERM DATES****************/
    @POST("api/v1/parent/term_dates")
    @FormUrlEncoded
    fun termDates(
        @Field("page_number") page_number: Int
    ): Call<TermDatesResponse>

    /*************UNIFORM****************/
    @POST("api/v1/parent/uniforms")
    @FormUrlEncoded
    fun uniform(
        @Field("page_number") page_number: Int
    ): Call<UniformResponse>

    /*************LUNCH BOX****************/
    @POST("api/v1/parent/lunch_box_menus")
    @FormUrlEncoded
    fun lunchbox(
        @Field("page_number") page_number: Int
    ): Call<LunchBoxResponse>

    /*************BUS SERVICE****************/
    @POST("api/v1/parent/bus_services")
    @FormUrlEncoded
    fun busservice(
        @Field("page_number") page_number: Int
    ): Call<BusServiceResponse>

    /*************PARENTS ESSENTIAL INFORMATION****************/
    @POST("api/v1/parent/informations")
    @FormUrlEncoded
    fun parentsEssentialInformation(
        @Field("page_number") page_number: Int
    ): Call<InformationResponse>

    /*************NAE PROGRAMME****************/
    @GET("api/v1/parent/nae_programmes")
    fun nae_programmes(): Call<Primaryresponse>

    /*************NAE PROGRAMME DETAIL****************/
    @POST("api/v1/parent/nae_programme/detail")
    @FormUrlEncoded
    fun nae_programmesdetails(
        @Field("nae_programme_id") nae_programme_id: Int,
        @Field("page_number") page_number: Int
    ): Call<Primarydetailsresponse>


    /*************PERMISSION FORMS LIST****************/
    @POST("api/v1/parent/permission_slips")
    @Headers("Content-Type: application/json")
    fun permissionFormsList(
        @Body permissionApi: PermissionFormsApiModel,
        @Header("Authorization") token:String
    ): Call<PermissionFormsResponse>

    /*************PERMISSION FORMS STATUS****************/
    @POST("api/v1/parent/permission_slip_status_change")
    @Headers("Content-Type: application/json")
    fun permissionFormStatus(
        @Body permissionStatusApi: PermissionFormStatusApiModel,
        @Header("Authorization") token:String
    ): Call<PermissionFormsStatusResponse>

    /*************CONTACT US****************/
    @GET("api/v1/parent/contact_us")
    fun contact_us(): Call<Contactusresponse>

    /*************HOME BANNER****************/

    @GET("api/v1/parent/home_banner_images")
    fun bannerimages(): Call<Bannerresponse>
}