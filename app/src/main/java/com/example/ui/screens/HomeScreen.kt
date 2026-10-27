package com.example.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.MockDataProvider
import com.example.model.WorkerProfile
import com.example.ui.components.CitySelectionBottomSheet
import com.example.ui.components.HeaderSection
import com.example.ui.components.KargarGuaranteeBanner
import com.example.ui.components.KargarTopAppBar
import com.example.ui.components.LanguageSelectionBottomSheet
import com.example.ui.components.RecommendedWorkersSection
import com.example.ui.components.ServicesSection
import com.example.ui.theme.PureWhite
import com.example.ui.theme.Slate50
import com.example.ui.theme.Slate900
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
  onBookWorker: (WorkerProfile) -> Unit
) {
  val context = LocalContext.current
  val snackbarHostState = remember { SnackbarHostState() }
  val coroutineScope = rememberCoroutineScope()

  // Dynamic city and language state
  var selectedCity by remember { mutableStateOf("Gwalior") }
  var selectedLanguage by remember { mutableStateOf("English") }

  // Bottom sheets state
  var showCitySheet by remember { mutableStateOf(false) }
  var showLanguageSheet by remember { mutableStateOf(false) }
  val citySheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
  val languageSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

  val services = remember { MockDataProvider.defaultServices }
  val workers = remember { MockDataProvider.defaultWorkers }

  val onShowMessage: (String) -> Unit = { message ->
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    coroutineScope.launch {
      snackbarHostState.currentSnackbarData?.dismiss()
      snackbarHostState.showSnackbar(message = message)
    }
  }

  Scaffold(
    modifier = Modifier
      .fillMaxSize()
      .testTag("home_screen"),
    containerColor = Slate50,
    contentWindowInsets = WindowInsets.safeDrawing,
    topBar = {
      KargarTopAppBar(
        selectedCity = selectedCity,
        selectedLanguage = selectedLanguage,
        onLocationClick = {
          showCitySheet = true
        },
        onLanguageClick = {
          showLanguageSheet = true
        },
        onNotificationClick = {
          onShowMessage("No new notifications")
        }
      )
    },
    snackbarHost = {
      SnackbarHost(
        hostState = snackbarHostState,
        modifier = Modifier.testTag("snackbar_host")
      ) { data ->
        Snackbar(
          snackbarData = data,
          containerColor = Slate900,
          contentColor = PureWhite,
          shape = RoundedCornerShape(14.dp)
        )
      }
    }
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
        .background(Slate50),
      contentAlignment = Alignment.TopCenter
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 680.dp)
          .verticalScroll(rememberScrollState())
      ) {
        // Welcoming header: "Aaj kya theek karana hai?" with dynamic city
        HeaderSection(
          selectedCity = selectedCity,
          onSearchClick = {
            onShowMessage("Search available for plumbing, electrician & AC repair in $selectedCity")
          }
        )

        Spacer(modifier = Modifier.height(4.dp))

        // 3 prominent service cards: Plumbing, Electrician, AC / Fridge Repair
        ServicesSection(
          services = services,
          onServiceClick = { service ->
            onShowMessage("${service.name} selected in $selectedCity")
          }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Recommended Workers Section: "Verified Workers Near You" + horizontal scrollable cards
        RecommendedWorkersSection(
          workers = workers,
          onWorkerClick = { worker ->
            // Navigates to WorkerBookingScreen
            onBookWorker(worker)
          }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Trust & Guarantee Banner
        KargarGuaranteeBanner()

        Spacer(modifier = Modifier.height(28.dp))
      }
    }

    // ModalBottomSheet: Dynamic City Selection (Delhi, Mumbai, Bengaluru, Gwalior, Bhopal)
    if (showCitySheet) {
      CitySelectionBottomSheet(
        selectedCity = selectedCity,
        sheetState = citySheetState,
        onDismiss = { showCitySheet = false },
        onCitySelected = { newCity ->
          selectedCity = newCity
          onShowMessage("Location updated to $newCity")
        }
      )
    }

    // ModalBottomSheet: Dynamic Language Selection
    if (showLanguageSheet) {
      LanguageSelectionBottomSheet(
        selectedLanguage = selectedLanguage,
        sheetState = languageSheetState,
        onDismiss = { showLanguageSheet = false },
        onLanguageSelected = { langItem ->
          selectedLanguage = langItem.nameEnglish
          onShowMessage("Language set to ${langItem.nameNative} (${langItem.nameEnglish})")
        }
      )
    }
  }
}
