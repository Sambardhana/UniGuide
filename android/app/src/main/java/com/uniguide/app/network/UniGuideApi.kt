package com.uniguide.app.network

import com.uniguide.app.data.model.*
import retrofit2.http.GET
import retrofit2.http.Path

interface UniGuideApi {

    @GET("api/departments")
    suspend fun getDepartments(): ApiResponse<Department>

    @GET("api/departments/{id}")
    suspend fun getDepartment(
        @Path("id") id: Long
    ): Department

    @GET("api/departments/{id}/courses")
    suspend fun getDepartmentCourses(
        @Path("id") id: Long
    ): ApiResponse<Course>


    @GET("api/courses")
    suspend fun getCourses(): ApiResponse<Course>

    @GET("api/courses/{id}")
    suspend fun getCourse(
        @Path("id") id: Long
    ): Course


    @GET("api/hostels")
    suspend fun getHostels(): ApiResponse<Hostel>

    @GET("api/hostels/{id}")
    suspend fun getHostel(
        @Path("id") id: Long
    ): Hostel


    @GET("api/facilities")
    suspend fun getFacilities(): ApiResponse<Facility>

    @GET("api/facilities/{id}")
    suspend fun getFacility(
        @Path("id") id: Long
    ): Facility


    @GET("api/activities")
    suspend fun getActivities(): ApiResponse<Activity>

    @GET("api/activities/{id}")
    suspend fun getActivity(
        @Path("id") id: Long
    ): Activity


    @GET("api/events")
    suspend fun getEvents(): ApiResponse<Event>

    @GET("api/events/{id}")
    suspend fun getEvent(
        @Path("id") id: Long
    ): Event

    @GET("api/events/upcoming")
    suspend fun getUpcomingEvents(): ApiResponse<Event>


    @GET("api/notices")
    suspend fun getNotices(): ApiResponse<Notice>

    @GET("api/notices/{id}")
    suspend fun getNotice(
        @Path("id") id: Long
    ): Notice

    @GET("api/notices/pinned")
    suspend fun getPinnedNotices(): ApiResponse<Notice>


    @GET("api/contacts")
    suspend fun getContacts(): ApiResponse<Contact>

    @GET("api/contacts/{id}")
    suspend fun getContact(
        @Path("id") id: Long
    ): Contact

    @GET("api/contacts/emergency")
    suspend fun getEmergencyContacts(): ApiResponse<Contact>


    @GET("api/campus-locations")
    suspend fun getCampusLocations(): ApiResponse<CampusLocation>

    @GET("api/campus-locations/{id}")
    suspend fun getCampusLocation(
        @Path("id") id: Long
    ): CampusLocation

    @GET("api/campus-locations/qr/{qrCodeKey}")
    suspend fun getCampusLocationByQr(
        @Path("qrCodeKey") qrCodeKey: String
    ): CampusLocation
}