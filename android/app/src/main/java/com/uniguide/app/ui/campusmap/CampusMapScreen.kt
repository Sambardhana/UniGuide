package com.uniguide.app.ui.campusmap

import android.Manifest
import android.content.pm.PackageManager
import android.view.ViewGroup
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uniguide.app.ui.viewmodel.UniGuideViewModel
import com.uniguide.app.ui.viewmodel.UniGuideViewModelFactory
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.CopyrightOverlay
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

@Composable
fun CampusMapScreen(
    onBackClick: () -> Unit = {}
) {

    val context = LocalContext.current

    /*
     * ==============================
     * UniGuide ViewModel
     * ==============================
     */

    val viewModel: UniGuideViewModel = viewModel(
        factory = UniGuideViewModelFactory()
    )

    val campusLocations by viewModel.campusLocations.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadCampusLocations()
    }

    /*
     * ==============================
     * Location permission state
     * ==============================
     */

    var locationPermissionGranted by remember {

        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    /*
     * Request location permission.
     */
    val locationPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->

            locationPermissionGranted =
                permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                        permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        }

    /*
     * ==============================
     * OpenStreetMap configuration
     * ==============================
     */

    Configuration.getInstance().userAgentValue =
        context.packageName

    /*
     * ==============================
     * Create MapView
     * ==============================
     */

    val mapView = remember(context) {

        MapView(context).apply {

            setTileSource(
                TileSourceFactory.MAPNIK
            )

            setMultiTouchControls(true)

            zoomController.setVisibility(
                CustomZoomButtonsController.Visibility.SHOW_AND_FADEOUT
            )

            /*
             * Centurion University
             * Bhubaneswar / Jatni campus.
             */
            val campusPoint = GeoPoint(
                20.17524,
                85.70664
            )

            /*
             * Initial campus view.
             */
            controller.setZoom(16.0)
            controller.setCenter(campusPoint)

            /*
             * OpenStreetMap attribution.
             */
            overlays.add(
                CopyrightOverlay(context)
            )

            /*
             * University marker.
             */
            val universityMarker = Marker(this).apply {

                position = campusPoint

                title =
                    "Centurion University of Technology and Management"

                snippet =
                    "Bhubaneswar Campus, Ramchandrapur, Jatni"
            }

            overlays.add(universityMarker)

            invalidate()
        }
    }

    /*
     * ==============================
     * My Location Overlay
     * ==============================
     */

    val locationOverlay = remember {

        MyLocationNewOverlay(
            GpsMyLocationProvider(context),
            mapView
        )
    }

    /*
     * ==============================
     * Enable / disable location
     * ==============================
     */

    LaunchedEffect(locationPermissionGranted) {

        if (locationPermissionGranted) {

            /*
             * Start displaying the user's
             * current position.
             */
            locationOverlay.enableMyLocation()

            /*
             * Center map when the first location
             * becomes available.
             */
            locationOverlay.enableFollowLocation()

            mapView.overlays.add(locationOverlay)

            mapView.invalidate()

        } else {

            locationOverlay.disableMyLocation()
            locationOverlay.disableFollowLocation()
        }
    }

    /*
     * ==============================
     * Database Campus Markers
     * ==============================
     */

    LaunchedEffect(campusLocations) {

        if (campusLocations.isNotEmpty()) {

            /*
             * Remove old database markers.
             *
             * Do not remove:
             * - CopyrightOverlay
             * - MyLocationNewOverlay
             */
            mapView.overlays.removeAll { overlay ->

                overlay is Marker
            }

            /*
             * Re-add university marker.
             */
            val campusPoint = GeoPoint(
                20.17524,
                85.70664
            )

            val universityMarker =
                Marker(mapView).apply {

                    position = campusPoint

                    title =
                        "Centurion University of Technology and Management"

                    snippet =
                        "Bhubaneswar Campus, Ramchandrapur, Jatni"
                }

            mapView.overlays.add(
                universityMarker
            )

            /*
             * Add database locations.
             */
            campusLocations.forEach { location ->

                val latitude =
                    location.latitude

                val longitude =
                    location.longitude

                if (
                    latitude != null &&
                    longitude != null
                ) {

                    val marker =
                        Marker(mapView).apply {

                            position = GeoPoint(
                                latitude,
                                longitude
                            )

                            title =
                                location.name

                            snippet = buildString {

                                if (
                                    !location.code.isNullOrBlank()
                                ) {
                                    append(
                                        location.code
                                    )
                                }

                                if (
                                    !location.category.isNullOrBlank()
                                ) {

                                    if (isNotEmpty()) {
                                        append(" • ")
                                    }

                                    append(
                                        location.category
                                    )
                                }

                                if (
                                    !location.description.isNullOrBlank()
                                ) {

                                    if (isNotEmpty()) {
                                        append("\n")
                                    }

                                    append(
                                        location.description
                                    )
                                }
                            }
                        }

                    mapView.overlays.add(marker)
                }
            }

            mapView.invalidate()
        }
    }

    /*
     * ==============================
     * Get current location
     * ==============================
     *
     * Used by the "My Location" button.
     */

    fun moveToMyLocation() {

        if (!locationPermissionGranted) {

            locationPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )

            return
        }

        val fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(
                context
            )

        val cancellationTokenSource =
            CancellationTokenSource()

        fusedLocationClient
            .getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                cancellationTokenSource.token
            )
            .addOnSuccessListener { location ->

                if (location != null) {

                    val currentPoint =
                        GeoPoint(
                            location.latitude,
                            location.longitude
                        )

                    mapView.controller.animateTo(
                        currentPoint
                    )

                    mapView.controller.setZoom(
                        18.0
                    )

                    mapView.invalidate()
                }
            }
    }

    /*
     * ==============================
     * UI
     * ==============================
     */

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        /*
         * OpenStreetMap
         */
        AndroidView(
            factory = {

                mapView.apply {

                    layoutParams =
                        ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )

                    onResume()

                    invalidate()
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        /*
         * Request location button.
         *
         * Shown only until permission is granted.
         */
        if (!locationPermissionGranted) {

            Button(
                onClick = {

                    locationPermissionLauncher.launch(
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                    )
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            ) {

                Text(
                    text = "Enable My Location"
                )
            }
        }

        /*
         * My Location button.
         *
         * Once permission is granted, this
         * lets the student return to their
         * current position.
         */
        if (locationPermissionGranted) {

            Button(
                onClick = {
                    moveToMyLocation()
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            ) {

                Text(
                    text = "📍 My Location"
                )
            }
        }

        /*
         * Loading indicator for campus data.
         */
        if (campusLocations.isEmpty()) {

            CircularProgressIndicator(
                modifier =
                    Modifier.align(
                        Alignment.Center
                    )
            )
        }
    }

    /*
     * ==============================
     * Lifecycle cleanup
     * ==============================
     */

    DisposableEffect(Unit) {

        onDispose {

            locationOverlay.disableMyLocation()
            locationOverlay.disableFollowLocation()

            mapView.onPause()
            mapView.onDetach()
        }
    }
}