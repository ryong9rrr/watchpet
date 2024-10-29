/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.ryong.watchpet

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope

class MainActivity : ComponentActivity() {

    //private lateinit var permissionLauncher: ActivityResultLauncher<String> // 이놈을 주석해제하면 앱팅김
    //private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

//        permissionLauncher =
//            registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
//                when (result) {
//                    true -> {
//                        Log.i(TAG, "Body sensors permission granted")
//                        // Only measure while the activity is at least in STARTED state.
//                        // MeasureClient provides frequent updates, which requires increasing the
//                        // sampling rate of device sensors, so we must be careful not to remain
//                        // registered any longer than necessary.
//                        lifecycleScope.launchWhenStarted {
//                            // TODO: 여기서 App을 띄운다.
//                            Log.i(TAG, "App Running...")
//                            viewModel.measureHeartRate()
//                        }
//                    }
//                    false -> Log.i(TAG, "Body sensors permission not granted")
//                }
//            }

        setTheme(android.R.style.Theme_DeviceDefault)

        setContent {
            WearApp()
        }
    }

    override fun onStart() {
        super.onStart()
        //permissionLauncher.launch(android.Manifest.permission.BODY_SENSORS)
    }

//    private fun updateViewVisiblity(uiState: UiState) {
//        (uiState is UiState.Startup).let {
//            binding.progress.isVisible = it
//        }
//        // These views are visible when heart rate capability is not available.
//        (uiState is UiState.HeartRateNotAvailable).let {
//            binding.brokenHeart.isVisible = it
//            binding.notAvailable.isVisible = it
//        }
//        // These views are visible when the capability is available.
//        (uiState is UiState.HeartRateAvailable).let {
//            binding.statusText.isVisible = it
//            binding.lastMeasuredLabel.isVisible = it
//            binding.lastMeasuredValue.isVisible = it
//            binding.heart.isVisible = it
//        }
//    }
}