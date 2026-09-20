package com.uniguide.app

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.common.InputImage
import com.uniguide.app.databinding.ActivityMainBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var cameraExecutor: ExecutorService

    private var qrAlreadyScanned = false

    // Camera permission launcher
    private val cameraPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->

            if (isGranted) {
                startCamera()
            } else {
                Toast.makeText(
                    this,
                    "Camera permission is required to scan the QR code",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cameraExecutor = Executors.newSingleThreadExecutor()

        checkCameraPermission()
    }

    private fun checkCameraPermission() {

        if (
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {

            startCamera()

        } else {

            cameraPermissionLauncher.launch(
                Manifest.permission.CAMERA
            )
        }
    }

    private fun startCamera() {

        val cameraProviderFuture =
            ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({

            val cameraProvider = cameraProviderFuture.get()

            // Camera preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.surfaceProvider =
                        binding.previewView.surfaceProvider
                }

            // QR code analyzer
            val imageAnalyzer = ImageAnalysis.Builder()
                .setBackpressureStrategy(
                    ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
                )
                .build()
                .also { analysis ->

                    analysis.setAnalyzer(
                        cameraExecutor
                    ) { imageProxy ->

                        processImage(imageProxy)
                    }
                }

            // Back camera
            val cameraSelector =
                CameraSelector.DEFAULT_BACK_CAMERA

            try {

                cameraProvider.unbindAll()

                cameraProvider.bindToLifecycle(
                    this,
                    cameraSelector,
                    preview,
                    imageAnalyzer
                )

            } catch (exception: Exception) {

                Toast.makeText(
                    this,
                    "Unable to start camera",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }, ContextCompat.getMainExecutor(this))
    }

    private fun processImage(imageProxy: ImageProxy) {

        val mediaImage = imageProxy.image ?: run {
            imageProxy.close()
            return
        }

        // Configure ML Kit to scan only QR codes
        val options =
            BarcodeScannerOptions.Builder()
                .setBarcodeFormats(
                    Barcode.FORMAT_QR_CODE
                )
                .build()

        val scanner =
            BarcodeScanning.getClient(options)

        // Convert camera frame to ML Kit image
        val image = InputImage.fromMediaImage(
            mediaImage,
            imageProxy.imageInfo.rotationDegrees
        )

        scanner.process(image)
            .addOnSuccessListener { barcodes ->

                if (!qrAlreadyScanned) {

                    for (barcode in barcodes) {

                        val rawValue = barcode.rawValue

                        if (!rawValue.isNullOrEmpty()) {

                            handleQRCode(rawValue)

                            break
                        }
                    }
                }

            }
            .addOnFailureListener {

                // QR processing failed.
            }
            .addOnCompleteListener {

                // Release camera frame
                imageProxy.close()
            }
    }

    private fun handleQRCode(
        qrContent: String
    ) {

        if (qrAlreadyScanned) {
            return
        }

        // Check whether QR contains a valid HTTP/HTTPS URL
        val uri = android.net.Uri.parse(qrContent)

        val isValidUrl =
            (uri.scheme == "http" || uri.scheme == "https") &&
                    !uri.host.isNullOrEmpty()

        if (isValidUrl) {

            qrAlreadyScanned = true

            runOnUiThread {

                Toast.makeText(
                    this,
                    "QR code detected! Opening UniGuide...",
                    Toast.LENGTH_SHORT
                ).show()
            }

            // Open Student section
            openStudentActivity()

        } else {

            runOnUiThread {

                Toast.makeText(
                    this,
                    "Invalid UniGuide QR code",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun openStudentActivity() {

        val intent =
            Intent(
                this,
                StudentActivity::class.java
            )

        startActivity(intent)

        finish()
    }

    override fun onDestroy() {

        super.onDestroy()

        cameraExecutor.shutdown()
    }
}