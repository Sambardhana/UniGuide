package com.uniguide.app.data.repository

import com.uniguide.app.data.model.Activity
import com.uniguide.app.data.model.CampusLocation
import com.uniguide.app.data.model.Contact
import com.uniguide.app.data.model.Course
import com.uniguide.app.data.model.Department
import com.uniguide.app.data.model.Event
import com.uniguide.app.data.model.Facility
import com.uniguide.app.data.model.Hostel
import com.uniguide.app.data.model.Notice
import com.uniguide.app.network.RetrofitClient

class UniGuideRepository {

    private val api = RetrofitClient.uniGuideApi

    suspend fun getDepartments(): List<Department> =
        api.getDepartments()

    suspend fun getDepartment(id: Long): Department =
        api.getDepartment(id)

    suspend fun getDepartmentCourses(id: Long): List<Course> =
        api.getDepartmentCourses(id)

    suspend fun getCourses(): List<Course> =
        api.getCourses()

    suspend fun getCourse(id: Long): Course =
        api.getCourse(id)

    suspend fun getHostels(): List<Hostel> =
        api.getHostels()

    suspend fun getHostel(id: Long): Hostel =
        api.getHostel(id)

    suspend fun getFacilities(): List<Facility> =
        api.getFacilities()

    suspend fun getFacility(id: Long): Facility =
        api.getFacility(id)

    suspend fun getActivities(): List<Activity> =
        api.getActivities()

    suspend fun getActivity(id: Long): Activity =
        api.getActivity(id)

    suspend fun getEvents(): List<Event> =
        api.getEvents()

    suspend fun getEvent(id: Long): Event =
        api.getEvent(id)

    suspend fun getUpcomingEvents(): List<Event> =
        api.getUpcomingEvents()

    suspend fun getNotices(): List<Notice> =
        api.getNotices()

    suspend fun getNotice(id: Long): Notice =
        api.getNotice(id)

    suspend fun getPinnedNotices(): List<Notice> =
        api.getPinnedNotices()

    suspend fun getContacts(): List<Contact> =
        api.getContacts()

    suspend fun getContact(id: Long): Contact =
        api.getContact(id)

    suspend fun getEmergencyContacts(): List<Contact> =
        api.getEmergencyContacts()

    suspend fun getCampusLocations(): List<CampusLocation> =
        api.getCampusLocations()

    suspend fun getCampusLocation(id: Long): CampusLocation =
        api.getCampusLocation(id)

    suspend fun getCampusLocationByQr(qrCodeKey: String): CampusLocation =
        api.getCampusLocationByQr(qrCodeKey)
}
