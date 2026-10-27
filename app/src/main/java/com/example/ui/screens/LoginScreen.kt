package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.Handyman
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.PureWhite
import com.example.ui.theme.SetuBlueContainer
import com.example.ui.theme.SetuBlueDark
import com.example.ui.theme.SetuBlueLight
import com.example.ui.theme.SetuBluePrimary
import com.example.ui.theme.SetuBlueSecondary
import com.example.ui.theme.SetuOrange
import com.example.ui.theme.SetuOrangeContainer
import com.example.ui.theme.SetuOrangeLight
import com.example.ui.theme.Slate100
import com.example.ui.theme.Slate200
import com.example.ui.theme.Slate400
import com.example.ui.theme.Slate50
import com.example.ui.theme.Slate500
import com.example.ui.theme.Slate600
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate800
import com.example.ui.theme.Slate900
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
  onLoginSuccess: () -> Unit,
  onPartnerLogin: () -> Unit
) {
  var isLoading by remember { mutableStateOf(false) }
  val coroutineScope = rememberCoroutineScope()

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(Slate50)
      .testTag("login_screen"),
    contentAlignment = Alignment.Center
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .widthIn(max = 480.dp)
        .padding(horizontal = 24.dp, vertical = 32.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Spacer(modifier = Modifier.weight(1f))

      // Center Brand Logo
      Box(
        modifier = Modifier
          .size(88.dp)
          .clip(RoundedCornerShape(26.dp))
          .background(
            Brush.linearGradient(
              colors = listOf(SetuBluePrimary, SetuBlueSecondary)
            )
          )
          .shadow(8.dp, RoundedCornerShape(26.dp)),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = Icons.Filled.Handyman,
          contentDescription = "Kargar Logo",
          tint = PureWhite,
          modifier = Modifier.size(46.dp)
        )
      }

      Spacer(modifier = Modifier.height(20.dp))

      // App Title
      Text(
        text = stringResource(R.string.app_name),
        style = MaterialTheme.typography.headlineLarge.copy(
          fontWeight = FontWeight.ExtraBold,
          fontSize = 36.sp,
          letterSpacing = (-1).sp
        ),
        color = SetuBluePrimary
      )

      Spacer(modifier = Modifier.height(6.dp))

      // Tagline
      Text(
        text = stringResource(R.string.login_tagline),
        style = MaterialTheme.typography.titleMedium.copy(
          fontWeight = FontWeight.Bold,
          fontSize = 16.sp
        ),
        color = Slate900,
        textAlign = TextAlign.Center
      )

      Spacer(modifier = Modifier.height(8.dp))

      // Subtitle
      Text(
        text = stringResource(R.string.login_subtitle),
        style = MaterialTheme.typography.bodyMedium,
        color = Slate600,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(horizontal = 12.dp)
      )

      Spacer(modifier = Modifier.height(28.dp))

      // Value proposition pills
      Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        LoginFeaturePill(
          icon = Icons.Filled.ElectricBolt,
          text = stringResource(R.string.login_badge_speed),
          iconTint = SetuOrange,
          bgTint = SetuOrangeLight
        )
        LoginFeaturePill(
          icon = Icons.Filled.Shield,
          text = stringResource(R.string.login_badge_verified),
          iconTint = SetuBluePrimary,
          bgTint = SetuBlueLight
        )
        LoginFeaturePill(
          icon = Icons.Filled.Payments,
          text = stringResource(R.string.login_badge_pricing),
          iconTint = SetuBlueSecondary,
          bgTint = SetuBlueLight
        )
      }

      Spacer(modifier = Modifier.weight(1f))

      // "Continue with Google" Button
      Button(
        onClick = {
          if (!isLoading) {
            isLoading = true
            coroutineScope.launch {
              // Simulate OAuth latency (2 seconds)
              delay(2000)
              isLoading = false
              onLoginSuccess()
            }
          }
        },
        enabled = !isLoading,
        shape = RoundedCornerShape(18.dp),
        border = BorderStroke(1.2.dp, Slate200),
        colors = ButtonDefaults.buttonColors(
          containerColor = PureWhite,
          contentColor = Slate900,
          disabledContainerColor = PureWhite,
          disabledContentColor = Slate500
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 15.dp),
        modifier = Modifier
          .fillMaxWidth()
          .height(56.dp)
          .testTag("login_google_button")
      ) {
        if (isLoading) {
          CircularProgressIndicator(
            color = SetuBluePrimary,
            strokeWidth = 2.5.dp,
            modifier = Modifier
              .size(24.dp)
              .testTag("login_progress_indicator")
          )
          Spacer(modifier = Modifier.width(12.dp))
          Text(
            text = stringResource(R.string.signing_in),
            style = MaterialTheme.typography.titleMedium.copy(
              fontWeight = FontWeight.Bold,
              fontSize = 15.sp
            ),
            color = Slate700
          )
        } else {
          // Standard Google "G" Icon Badge
          GoogleGEmblem()

          Spacer(modifier = Modifier.width(14.dp))

          Text(
            text = stringResource(R.string.continue_with_google),
            style = MaterialTheme.typography.titleMedium.copy(
              fontWeight = FontWeight.Bold,
              fontSize = 16.sp
            ),
            color = Slate900
          )
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Role Selection: Kargar Partner Login
      Surface(
        shape = RoundedCornerShape(16.dp),
        color = PureWhite,
        border = BorderStroke(1.2.dp, SetuBlueContainer),
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(16.dp))
          .clickable {
            onPartnerLogin()
          }
          .testTag("login_as_partner_button")
      ) {
        Row(
          modifier = Modifier.padding(horizontal = 14.dp, vertical = 14.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center
        ) {
          Icon(
            imageVector = Icons.Filled.Handyman,
            contentDescription = null,
            tint = SetuBluePrimary,
            modifier = Modifier.size(18.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = stringResource(R.string.login_as_partner),
            style = MaterialTheme.typography.labelMedium.copy(
              fontWeight = FontWeight.Bold,
              fontSize = 13.5.sp
            ),
            color = SetuBlueDark
          )
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Terms of service note
      Text(
        text = stringResource(R.string.login_terms),
        style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
        color = Slate400,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(horizontal = 20.dp)
      )
    }
  }
}

@Composable
fun LoginFeaturePill(
  icon: androidx.compose.ui.graphics.vector.ImageVector,
  text: String,
  iconTint: Color,
  bgTint: Color
) {
  Surface(
    shape = RoundedCornerShape(14.dp),
    color = PureWhite,
    border = BorderStroke(1.dp, Slate200),
    modifier = Modifier.fillMaxWidth()
  ) {
    Row(
      modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Box(
        modifier = Modifier
          .size(32.dp)
          .clip(CircleShape)
          .background(bgTint),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = icon,
          contentDescription = null,
          tint = iconTint,
          modifier = Modifier.size(16.dp)
        )
      }
      Spacer(modifier = Modifier.width(12.dp))
      Text(
        text = text,
        style = MaterialTheme.typography.labelMedium.copy(
          fontWeight = FontWeight.SemiBold,
          fontSize = 13.5.sp
        ),
        color = Slate800
      )
    }
  }
}

@Composable
fun GoogleGEmblem() {
  // Google 'G' letter mark in crisp official Blue circle container
  Box(
    modifier = Modifier
      .size(26.dp)
      .clip(CircleShape)
      .background(Color(0xFF4285F4)),
    contentAlignment = Alignment.Center
  ) {
    Text(
      text = "G",
      style = MaterialTheme.typography.titleMedium.copy(
        fontWeight = FontWeight.ExtraBold,
        fontSize = 15.sp
      ),
      color = PureWhite
    )
  }
}
