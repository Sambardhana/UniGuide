package com.uniguide.app.ui.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uniguide.app.data.model.Activity
import com.uniguide.app.data.model.CampusLocation
import com.uniguide.app.data.model.Contact
import com.uniguide.app.data.model.Course
import com.uniguide.app.data.model.Department
import com.uniguide.app.data.model.Event
import com.uniguide.app.data.model.Facility
import com.uniguide.app.data.model.Hostel
import com.uniguide.app.data.model.Notice
import com.uniguide.app.data.repository.UniGuideRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UniGuideViewModel(
    private val repository: UniGuideRepository = UniGuideRepository()
) : ViewModel() {

    private val _departments =
        MutableStateFlow<List<Department>>(emptyList())
    val departments: StateFlow<List<Department>> =
        _departments.asStateFlow()

    private val _courses =
        MutableStateFlow<List<Course>>(emptyList())
    val courses: StateFlow<List<Course>> =
        _courses.asStateFlow()

    private val _hostels =
        MutableStateFlow<List<Hostel>>(emptyList())
    val hostels: StateFlow<List<Hostel>> =
        _hostels.asStateFlow()

    private val _facilities =
        MutableStateFlow<List<Facility>>(emptyList())
    val facilities: StateFlow<List<Facility>> =
        _facilities.asStateFlow()

    private val _activities =
        MutableStateFlow<List<Activity>>(emptyList())
    val activities: StateFlow<List<Activity>> =
        _activities.asStateFlow()

    private val _events =
        MutableStateFlow<List<Event>>(emptyList())
    val events: StateFlow<List<Event>> =
        _events.asStateFlow()

    private val _notices =
        MutableStateFlow<List<Notice>>(emptyList())
    val notices: StateFlow<List<Notice>> =
        _notices.asStateFlow()

    private val _contacts =
        MutableStateFlow<List<Contact>>(emptyList())
    val contacts: StateFlow<List<Contact>> =
        _contacts.asStateFlow()

    private val _emergencyContacts =
        MutableStateFlow<List<Contact>>(emptyList())
    val emergencyContacts: StateFlow<List<Contact>> =
        _emergencyContacts.asStateFlow()

    private val _campusLocations =
        MutableStateFlow<List<CampusLocation>>(emptyList())
    val campusLocations: StateFlow<List<CampusLocation>> =
        _campusLocations.asStateFlow()

    private val _isLoading =
        MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> =
        _isLoading.asStateFlow()

    private val _error =
        MutableStateFlow<String?>(null)
    val error: StateFlow<String?> =
        _error.asStateFlow()


    fun loadDepartments() {
        launchRequest {
            _departments.value = repository.getDepartments()
        }
    }

    fun loadCourses() {
        launchRequest {
            _courses.value = repository.getCourses()
        }
    }

    fun loadHostels() {
        launchRequest {
            _hostels.value = repository.getHostels()
        }
    }

    fun loadFacilities() {
        launchRequest {
            _facilities.value = repository.getFacilities()
        }
    }

    fun loadActivities() {
        launchRequest {
            _activities.value = repository.getActivities()
        }
    }

    fun loadEvents() {
        launchRequest {
            _events.value = repository.getEvents()
        }
    }

    fun loadNotices() {
        launchRequest {
            _notices.value = repository.getNotices()
        }
    }

    fun loadContacts() {
        launchRequest {
            _contacts.value = repository.getContacts()
        }
    }

    fun loadEmergencyContacts() {
        launchRequest {
            _emergencyContacts.value =
                repository.getEmergencyContacts()
        }
    }

    fun loadCampusLocations() {
        launchRequest {
            _campusLocations.value =
                repository.getCampusLocations()
        }
    }


    fun loadAllStudentData() {

        viewModelScope.launch {

            _isLoading.value = true
            _error.value = null

            try {

                _departments.value =
                    repository.getDepartments()

                _courses.value =
                    repository.getCourses()

                _hostels.value =
                    repository.getHostels()

                _facilities.value =
                    repository.getFacilities()

                _activities.value =
                    repository.getActivities()

                _events.value =
                    repository.getEvents()

                _notices.value =
                    repository.getNotices()

                _contacts.value =
                    repository.getContacts()

                _emergencyContacts.value =
                    repository.getEmergencyContacts()

                _campusLocations.value =
                    repository.getCampusLocations()

            } catch (e: Exception) {

                _error.value =
                    e.message ?: "Unable to load UniGuide data"

            } finally {

                _isLoading.value = false
            }
        }
    }


    private fun launchRequest(
        request: suspend () -> Unit
    ) {

        viewModelScope.launch {

            _isLoading.value = true
            _error.value = null

            try {

                request()

            } catch (e: Exception) {

                _error.value =
                    e.message ?: "Unable to load data"

            } finally {

                _isLoading.value = false
            }
        }
    }


    fun clearError() {
        _error.value = null
    }
}