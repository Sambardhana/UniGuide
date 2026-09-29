package com.uniguide.app.network


import com.uniguide.app.data.model.*
import retrofit2.http.GET
import retrofit2.http.Path

interface UniGuideApi {

    @GET("api/departments")
    suspend fun getDepartments(): List<Department>

    @GET("api/departments/{id}")
    suspend fun getDepartment(
        @Path("id") id: Long
    ): Department

    @GET("api/departments/{id}/courses")
    suspend fun getDepartmentCourses(
        @Path("id") id: Long
    ): List<Course>

    @GET("api/courses")
    suspend fun getCourses(): List<Course>

    @GET("api/courses/{id}")
    suspend fun getCourse(
        @Path("id") id: Long
    ): Course

    @GET("api/hostels")
    suspend fun getHostels(): List<Hostel>

    @GET("api/hostels/{id}")
    suspend fun getHostel(
        @Path("id") id: Long
    ): Hostel

    @GET("api/facilities")
    suspend fun getFacilities(): List<Facility>

    @GET("api/facilities/{id}")
    suspend fun getFacility(
        @Path("id") id: Long
    ): Facility

    @GET("api/activities")
    suspend fun getActivities(): List<Activity>

    @GET("api/activities/{id}")
    suspend fun getActivity(
        @Path("id") id: Long
    ): Activity

    @GET("api/events")
    suspend fun getEvents(): List<Event>

    @GET("api/events/{id}")
    suspend fun getEvent(
        @Path("id") id: Long
    ): Event

    @GET("api/events/upcoming")
    suspend fun getUpcomingEvents(): List<Event>

    @GET("api/notices")
    suspend fun getNotices(): List<Notice>

    @GET("api/notices/{id}")
    suspend fun getNotice(
        @Path("id") id: Long
    ): Notice

    @GET("api/notices/pinned")
    suspend fun getPinnedNotices(): List<Notice>

    @GET("api/contacts")
    suspend fun getContacts(): List<Contact>

    @GET("api/contacts/{id}")
    suspend fun getContact(
        @Path("id") id: Long
    ): Contact

    @GET("api/contacts/emergency")
    suspend fun getEmergencyContacts(): List<Contact>

    @GET("api/campus-locations")
    suspend fun getCampusLocations(): List<CampusLocation>

    @GET("api/campus-locations/{id}")
    suspend fun getCampusLocation(
        @Path("id") id: Long
    ): CampusLocation

    @GET("api/campus-locations/qr/{qrCodeKey}")
    suspend fun getCampusLocationByQr(
        @Path("qrCodeKey") qrCodeKey: String
    ): CampusLocation
}