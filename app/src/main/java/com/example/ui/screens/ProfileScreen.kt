package com.example.ui.screens

import android.widget.Toast
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
import com.example.ui.theme.SetuVerifiedBlue
import com.example.ui.theme.Slate100
import com.example.ui.theme.Slate200
import com.example.ui.theme.Slate400
import com.example.ui.theme.Slate50
import com.example.ui.theme.Slate500
import com.example.ui.theme.Slate600
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate800
import com.example.ui.theme.Slate900

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
  onLogout: () -> Unit
) {
  val context = LocalContext.current

  Scaffold(
    modifier = Modifier
      .fillMaxSize()
      .testTag("profile_screen"),
    containerColor = Slate50,
    topBar = {
      TopAppBar(
        modifier = Modifier.testTag("profile_top_bar"),
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = PureWhite,
          titleContentColor = Slate900
        ),
        title = {
          Text(
            text = stringResource(R.string.profile_title),
            style = MaterialTheme.typography.titleLarge.copy(
              fontWeight = FontWeight.Bold,
              fontSize = 20.sp
            ),
            color = Slate900
          )
        }
      )
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
          .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        // USER INFO CARD
        Card(
          shape = RoundedCornerShape(24.dp),
          colors = CardDefaults.cardColors(containerColor = PureWhite),
          border = BorderStroke(1.2.dp, Slate200),
          elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
          modifier = Modifier
            .fillMaxWidth()
            .testTag("user_info_card")
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            // User Avatar Placeholder
            Box(
              modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(SetuBluePrimary),
              contentAlignment = Alignment.Center
            ) {
              Text(
                text = "AK",
                style = MaterialTheme.typography.titleLarge.copy(
                  fontWeight = FontWeight.Bold,
                  fontSize = 22.sp
                ),
                color = PureWhite
              )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
              ) {
                Text(
                  text = "Aashof Khan",
                  style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                  ),
                  color = Slate900
                )
                Icon(
                  imageVector = Icons.Filled.Verified,
                  contentDescription = "Verified User",
                  tint = SetuVerifiedBlue,
                  modifier = Modifier.size(16.dp)
                )
              }

              Text(
                text = "aashofkha166@gmail.com",
                style = MaterialTheme.typography.bodySmall,
                color = Slate600
              )

              Spacer(modifier = Modifier.height(4.dp))

              Surface(
                shape = RoundedCornerShape(8.dp),
                color = SetuBlueLight,
                border = BorderStroke(1.dp, SetuBlueContainer)
              ) {
                Text(
                  text = "Kargar Prime Member",
                  style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.5.sp
                  ),
                  color = SetuBlueDark,
                  modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                )
              }
            }
          }
        }

        // SAVED ADDRESSES CARD
        Card(
          shape = RoundedCornerShape(22.dp),
          colors = CardDefaults.cardColors(containerColor = PureWhite),
          border = BorderStroke(1.2.dp, Slate200),
          elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
          modifier = Modifier
            .fillMaxWidth()
            .testTag("saved_addresses_card")
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(18.dp)
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = stringResource(R.string.saved_addresses_title),
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                color = Slate900
              )

              Surface(
                shape = RoundedCornerShape(6.dp),
                color = SetuOrangeLight,
                border = BorderStroke(1.dp, SetuOrangeContainer)
              ) {
                Text(
                  text = stringResource(R.string.default_badge),
                  style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 10.sp
                  ),
                  color = SetuOrange,
                  modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
              }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
              verticalAlignment = Alignment.Top,
              modifier = Modifier.fillMaxWidth()
            ) {
              Box(
                modifier = Modifier
                  .size(36.dp)
                  .clip(CircleShape)
                  .background(SetuOrangeLight),
                contentAlignment = Alignment.Center
              ) {
                Icon(
                  imageVector = Icons.Filled.Home,
                  contentDescription = null,
                  tint = SetuOrange,
                  modifier = Modifier.size(18.dp)
                )
              }

              Spacer(modifier = Modifier.width(12.dp))

              Column(modifier = Modifier.weight(1f)) {
                Text(
                  text = stringResource(R.string.home_address_label),
                  style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                  color = Slate800
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                  text = "Flat 302, Royal Residency, City Center, Gwalior, MP - 474011",
                  style = MaterialTheme.typography.bodySmall,
                  color = Slate600
                )
              }
            }
          }
        }

        // SETTINGS & PREFERENCES CARD
        Card(
          shape = RoundedCornerShape(22.dp),
          colors = CardDefaults.cardColors(containerColor = PureWhite),
          border = BorderStroke(1.2.dp, Slate200),
          elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
          modifier = Modifier
            .fillMaxWidth()
            .testTag("settings_card")
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 16.dp, vertical = 8.dp)
          ) {
            Text(
              text = stringResource(R.string.settings_title),
              style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
              color = Slate900,
              modifier = Modifier.padding(top = 10.dp, bottom = 6.dp)
            )

            // Row 1: Payment Methods
            SettingsRow(
              icon = Icons.Filled.Payment,
              title = stringResource(R.string.setting_payment_methods),
              subtitle = stringResource(R.string.setting_payment_methods_sub),
              onClick = {
                Toast.makeText(
                  context,
                  "Active: Google Pay, Paytm, UPI & Cash on Service",
                  Toast.LENGTH_SHORT
                ).show()
              },
              testTag = "setting_payment_methods"
            )

            HorizontalDivider(color = Slate100)

            // Row 2: Help & Support
            SettingsRow(
              icon = Icons.Filled.Headphones,
              title = stringResource(R.string.setting_help_support),
              subtitle = stringResource(R.string.setting_help_support_sub),
              onClick = {
                Toast.makeText(
                  context,
                  "Help & Support: Call 1800-123-KARGAR (24x7)",
                  Toast.LENGTH_SHORT
                ).show()
              },
              testTag = "setting_help_support"
            )

            HorizontalDivider(color = Slate100)

            // Row 3: Privacy & Terms
            SettingsRow(
              icon = Icons.Filled.Security,
              title = stringResource(R.string.setting_privacy_terms),
              subtitle = "Version 1.0 • Verified pan-India service",
              onClick = {
                Toast.makeText(
                  context,
                  "Terms & Privacy policy up to date",
                  Toast.LENGTH_SHORT
                ).show()
              },
              testTag = "setting_privacy_terms"
            )
          }
        }

        // PROMINENT LOG OUT BUTTON
        OutlinedButton(
          onClick = {
            Toast.makeText(
              context,
              "Logged out successfully",
              Toast.LENGTH_SHORT
            ).show()
            onLogout()
          },
          shape = RoundedCornerShape(16.dp),
          border = BorderStroke(1.5.dp, Color(0xFFDC2626)),
          colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Color(0xFFDC2626)
          ),
          contentPadding = PaddingValues(vertical = 14.dp),
          modifier = Modifier
            .fillMaxWidth()
            .testTag("logout_button")
        ) {
          Icon(
            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
            contentDescription = null,
            tint = Color(0xFFDC2626),
            modifier = Modifier.size(18.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = stringResource(R.string.log_out_button),
            style = MaterialTheme.typography.titleMedium.copy(
              fontWeight = FontWeight.Bold,
              fontSize = 15.sp
            )
          )
        }

        Spacer(modifier = Modifier.height(24.dp))
      }
    }
  }
}

@Composable
fun SettingsRow(
  icon: ImageVector,
  title: String,
  subtitle: String,
  onClick: () -> Unit,
  testTag: String
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(12.dp))
      .clickable { onClick() }
      .padding(vertical = 12.dp, horizontal = 4.dp)
      .testTag(testTag),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Box(
      modifier = Modifier
        .size(38.dp)
        .clip(CircleShape)
        .background(SetuBlueLight),
      contentAlignment = Alignment.Center
    ) {
      Icon(
        imageVector = icon,
        contentDescription = null,
        tint = SetuBluePrimary,
        modifier = Modifier.size(18.dp)
      )
    }

    Spacer(modifier = Modifier.width(14.dp))

    Column(modifier = Modifier.weight(1f)) {
      Text(
        text = title,
        style = MaterialTheme.typography.titleSmall.copy(
          fontWeight = FontWeight.SemiBold,
          fontSize = 14.5.sp
        ),
        color = Slate900
      )
      Text(
        text = subtitle,
        style = MaterialTheme.typography.bodySmall,
        color = Slate500
      )
    }

    Icon(
      imageVector = Icons.Filled.ChevronRight,
      contentDescription = null,
      tint = Slate400,
      modifier = Modifier.size(20.dp)
    )
  }
}
