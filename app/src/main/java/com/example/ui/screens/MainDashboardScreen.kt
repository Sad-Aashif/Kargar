package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.model.WorkerProfile
import com.example.ui.theme.PureWhite
import com.example.ui.theme.SetuBlueContainer
import com.example.ui.theme.SetuBlueDark
import com.example.ui.theme.SetuBluePrimary
import com.example.ui.theme.Slate200
import com.example.ui.theme.Slate50
import com.example.ui.theme.Slate500
import com.example.ui.theme.Slate600

enum class DashboardTab(
  val labelRes: Int,
  val selectedIcon: ImageVector,
  val unselectedIcon: ImageVector,
  val testTag: String
) {
  HOME(
    R.string.tab_home,
    Icons.Filled.Home,
    Icons.Outlined.Home,
    "bottom_nav_home"
  ),
  BOOKINGS(
    R.string.tab_bookings,
    Icons.Filled.ReceiptLong,
    Icons.Outlined.ReceiptLong,
    "bottom_nav_bookings"
  ),
  PROFILE(
    R.string.tab_profile,
    Icons.Filled.Person,
    Icons.Outlined.Person,
    "bottom_nav_profile"
  )
}

@Composable
fun MainDashboardScreen(
  onBookWorker: (WorkerProfile) -> Unit,
  onNavigateToJobStatus: (workerName: String, fee: String, profession: String) -> Unit,
  onLogout: () -> Unit
) {
  var currentTab by remember { mutableStateOf(DashboardTab.HOME) }

  Scaffold(
    modifier = Modifier
      .fillMaxSize()
      .testTag("main_dashboard_screen"),
    containerColor = Slate50,
    bottomBar = {
      Surface(
        color = PureWhite,
        shadowElevation = 8.dp,
        border = BorderStroke(1.dp, Slate200),
        modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
      ) {
        NavigationBar(
          containerColor = PureWhite,
          tonalElevation = 0.dp,
          modifier = Modifier.testTag("bottom_nav_bar")
        ) {
          DashboardTab.values().forEach { tab ->
            val isSelected = currentTab == tab
            NavigationBarItem(
              selected = isSelected,
              onClick = { currentTab = tab },
              icon = {
                Icon(
                  imageVector = if (isSelected) tab.selectedIcon else tab.unselectedIcon,
                  contentDescription = stringResource(tab.labelRes),
                  modifier = Modifier.size(24.dp)
                )
              },
              label = {
                Text(
                  text = stringResource(tab.labelRes),
                  style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                  )
                )
              },
              colors = NavigationBarItemDefaults.colors(
                selectedIconColor = SetuBlueDark,
                selectedTextColor = SetuBlueDark,
                indicatorColor = SetuBlueContainer,
                unselectedIconColor = Slate500,
                unselectedTextColor = Slate600
              ),
              modifier = Modifier.testTag(tab.testTag)
            )
          }
        }
      }
    }
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
    ) {
      when (currentTab) {
        DashboardTab.HOME -> {
          HomeScreen(
            onBookWorker = onBookWorker
          )
        }
        DashboardTab.BOOKINGS -> {
          BookingsHistoryScreen(
            onNavigateToJobStatus = onNavigateToJobStatus,
            onRebookWorker = onBookWorker
          )
        }
        DashboardTab.PROFILE -> {
          ProfileScreen(
            onLogout = onLogout
          )
        }
      }
    }
  }
}
