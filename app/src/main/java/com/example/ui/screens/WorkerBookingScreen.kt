package com.example.ui.screens

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.PureWhite
import com.example.ui.theme.SetuAmberStar
import com.example.ui.theme.SetuBlueContainer
import com.example.ui.theme.SetuBlueDark
import com.example.ui.theme.SetuBlueLight
import com.example.ui.theme.SetuBluePrimary
import com.example.ui.theme.SetuBlueSecondary
import com.example.ui.theme.SetuOrange
import com.example.ui.theme.SetuOrangeContainer
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

enum class PaymentMode(val title: String, val subtitle: String) {
  UPI("UPI", "GPay, PhonePe, Paytm or QR"),
  CASH("Cash", "Pay cash after service completion")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkerBookingScreen(
  workerName: String,
  workerFee: String,
  workerProfession: String = "Verified Technician",
  workerRating: String = "4.8",
  onBackClick: () -> Unit,
  onBookingConfirmed: (paymentMode: String) -> Unit
) {
  val context = LocalContext.current
  BackHandler { onBackClick() }

  var issueText by remember { mutableStateOf("") }
  var addressText by remember {
    mutableStateOf("Flat 302, Royal Residency, City Center, Gwalior, MP - 474011")
  }
  var selectedPaymentMode by remember { mutableStateOf(PaymentMode.UPI) }

  Scaffold(
    modifier = Modifier
      .fillMaxSize()
      .testTag("worker_booking_screen"),
    containerColor = Slate50,
    topBar = {
      TopAppBar(
        modifier = Modifier.testTag("booking_top_app_bar"),
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = PureWhite,
          titleContentColor = Slate900
        ),
        navigationIcon = {
          IconButton(
            onClick = onBackClick,
            modifier = Modifier.testTag("booking_back_button")
          ) {
            Icon(
              imageVector = Icons.AutoMirrored.Filled.ArrowBack,
              contentDescription = "Back to Home",
              tint = Slate800
            )
          }
        },
        title = {
          Text(
            text = androidx.compose.ui.res.stringResource(R.string.complete_booking_title),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = Slate900
          )
        }
      )
    },
    bottomBar = {
      // Sticky Bottom Action Bar
      Surface(
        modifier = Modifier
          .fillMaxWidth()
          .windowInsetsPadding(WindowInsets.navigationBars)
          .testTag("booking_bottom_action_bar"),
        color = PureWhite,
        shadowElevation = 8.dp,
        border = BorderStroke(1.dp, Slate200)
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 14.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          // Total Price on Left
          Column {
            Text(
              text = "Total Payable",
              style = MaterialTheme.typography.labelSmall,
              color = Slate500
            )
            Text(
              text = workerFee,
              style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 22.sp
              ),
              color = SetuOrange
            )
            Text(
              text = "Includes base visiting charge",
              style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
              color = Slate400
            )
          }

          Spacer(modifier = Modifier.width(16.dp))

          // Prominent Wide Confirm & Book Button
          Button(
            onClick = {
              Toast.makeText(
                context,
                "Booking Confirmed! Worker is on the way.",
                Toast.LENGTH_LONG
              ).show()
              val modeString = if (selectedPaymentMode == PaymentMode.UPI) "Paid via UPI" else "Paid via Cash"
              onBookingConfirmed(modeString)
            },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
              containerColor = SetuBluePrimary,
              contentColor = PureWhite
            ),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 14.dp),
            modifier = Modifier
              .weight(1f)
              .testTag("confirm_book_button")
          ) {
            Text(
              text = "Confirm & Book",
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
              )
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
        .background(Slate50),
      contentAlignment = Alignment.TopCenter
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 640.dp)
          .verticalScroll(rememberScrollState())
          .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        // Worker Info Card
        Card(
          shape = RoundedCornerShape(20.dp),
          colors = CardDefaults.cardColors(containerColor = PureWhite),
          border = BorderStroke(1.2.dp, Slate200),
          elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
          modifier = Modifier
            .fillMaxWidth()
            .testTag("worker_info_card")
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            // Profile image placeholder
            Box(
              modifier = Modifier
                .size(68.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(SetuBlueLight)
                .border(1.dp, SetuBlueContainer, RoundedCornerShape(18.dp)),
              contentAlignment = Alignment.Center
            ) {
              Image(
                painter = painterResource(id = R.drawable.worker_avatar_placeholder_1792902358208),
                contentDescription = "Worker Profile Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                  .size(68.dp)
                  .clip(RoundedCornerShape(18.dp))
              )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
              ) {
                Text(
                  text = workerName,
                  style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                  ),
                  color = Slate900
                )
                // Verified Badge (Royal Blue, strictly zero green!)
                Icon(
                  imageVector = Icons.Filled.Verified,
                  contentDescription = "Verified Badge",
                  tint = SetuVerifiedBlue,
                  modifier = Modifier.size(18.dp)
                )
              }

              Text(
                text = workerProfession,
                style = MaterialTheme.typography.bodySmall,
                color = Slate600
              )

              Spacer(modifier = Modifier.height(6.dp))

              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
              ) {
                // Rating
                Surface(
                  shape = RoundedCornerShape(8.dp),
                  color = Slate100,
                  border = BorderStroke(1.dp, Slate200)
                ) {
                  Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
                  ) {
                    Icon(
                      imageVector = Icons.Filled.Star,
                      contentDescription = "Rating",
                      tint = SetuAmberStar,
                      modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                      text = workerRating,
                      style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                      color = Slate800
                    )
                  }
                }

                // Gwalior locality tag
                Text(
                  text = "Doorstep in 15 mins",
                  style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                  color = SetuBluePrimary
                )
              }
            }
          }
        }

        // Booking Form Card
        Card(
          shape = RoundedCornerShape(20.dp),
          colors = CardDefaults.cardColors(containerColor = PureWhite),
          border = BorderStroke(1.2.dp, Slate200),
          elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
          modifier = Modifier
            .fillMaxWidth()
            .testTag("booking_form_card")
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
          ) {
            Text(
              text = "Service Details",
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
              color = Slate900
            )

            // Text Field 1: "Your Issue"
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
              Text(
                text = "Your Issue",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                color = Slate700
              )
              OutlinedTextField(
                value = issueText,
                onValueChange = { issueText = it },
                modifier = Modifier
                  .fillMaxWidth()
                  .testTag("issue_input_field"),
                placeholder = {
                  Text(
                    text = "e.g., Tap is leaking, switchboard repair, fan making noise...",
                    color = Slate400,
                    style = MaterialTheme.typography.bodyMedium
                  )
                },
                leadingIcon = {
                  Icon(
                    imageVector = Icons.Filled.Build,
                    contentDescription = null,
                    tint = SetuBlueSecondary,
                    modifier = Modifier.size(20.dp)
                  )
                },
                shape = RoundedCornerShape(14.dp),
                minLines = 2,
                maxLines = 4,
                colors = OutlinedTextFieldDefaults.colors(
                  focusedBorderColor = SetuBluePrimary,
                  unfocusedBorderColor = Slate200,
                  focusedContainerColor = PureWhite,
                  unfocusedContainerColor = PureWhite
                )
              )
            }

            // Text Field 2: "Address" (Pre-filled with dummy Gwalior address)
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
              Text(
                text = "Service Address (Gwalior)",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                color = Slate700
              )
              OutlinedTextField(
                value = addressText,
                onValueChange = { addressText = it },
                modifier = Modifier
                  .fillMaxWidth()
                  .testTag("address_input_field"),
                leadingIcon = {
                  Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = null,
                    tint = SetuOrange,
                    modifier = Modifier.size(20.dp)
                  )
                },
                shape = RoundedCornerShape(14.dp),
                minLines = 2,
                maxLines = 3,
                colors = OutlinedTextFieldDefaults.colors(
                  focusedBorderColor = SetuBluePrimary,
                  unfocusedBorderColor = Slate200,
                  focusedContainerColor = PureWhite,
                  unfocusedContainerColor = PureWhite
                )
              )
            }

            // Segmented Button / Selector: "Payment Mode" (Options: UPI, Cash)
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
              Text(
                text = "Payment Mode",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                color = Slate700
              )

              Row(
                modifier = Modifier
                  .fillMaxWidth()
                  .testTag("payment_mode_group"),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
              ) {
                PaymentOptionItem(
                  title = "UPI",
                  subtitle = "GPay / PhonePe / QR",
                  icon = Icons.Filled.QrCode2,
                  isSelected = selectedPaymentMode == PaymentMode.UPI,
                  modifier = Modifier
                    .weight(1f)
                    .testTag("payment_mode_upi"),
                  onSelect = { selectedPaymentMode = PaymentMode.UPI }
                )

                PaymentOptionItem(
                  title = "Cash",
                  subtitle = "Pay after repair",
                  icon = Icons.Filled.Payments,
                  isSelected = selectedPaymentMode == PaymentMode.CASH,
                  modifier = Modifier
                    .weight(1f)
                    .testTag("payment_mode_cash"),
                  onSelect = { selectedPaymentMode = PaymentMode.CASH }
                )
              }
            }
          }
        }

        // Trust note
        Surface(
          shape = RoundedCornerShape(16.dp),
          color = SetuBlueLight,
          border = BorderStroke(1.dp, SetuBlueContainer),
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Icon(
              imageVector = Icons.Filled.Shield,
              contentDescription = null,
              tint = SetuBluePrimary,
              modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
              text = "Zero upfront deposit required. Inspect the work before paying.",
              style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
              color = SetuBlueDark
            )
          }
        }

        Spacer(modifier = Modifier.height(10.dp))
      }
    }
  }
}

@Composable
fun PaymentOptionItem(
  title: String,
  subtitle: String,
  icon: androidx.compose.ui.graphics.vector.ImageVector,
  isSelected: Boolean,
  modifier: Modifier = Modifier,
  onSelect: () -> Unit
) {
  val borderColor = if (isSelected) SetuBluePrimary else Slate200
  val backgroundColor = if (isSelected) SetuBlueLight else PureWhite

  Surface(
    shape = RoundedCornerShape(16.dp),
    color = backgroundColor,
    border = BorderStroke(if (isSelected) 1.8.dp else 1.dp, borderColor),
    modifier = modifier
      .clip(RoundedCornerShape(16.dp))
      .clickable { onSelect() }
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Box(
        modifier = Modifier
          .size(36.dp)
          .clip(CircleShape)
          .background(if (isSelected) SetuBlueContainer else Slate100),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = icon,
          contentDescription = title,
          tint = if (isSelected) SetuBluePrimary else Slate600,
          modifier = Modifier.size(18.dp)
        )
      }

      Spacer(modifier = Modifier.width(8.dp))

      Column(modifier = Modifier.weight(1f)) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
          Text(
            text = title,
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
            color = if (isSelected) SetuBlueDark else Slate900
          )
          if (isSelected) {
            Icon(
              imageVector = Icons.Filled.Check,
              contentDescription = "Selected",
              tint = SetuBluePrimary,
              modifier = Modifier.size(14.dp)
            )
          }
        }
        Text(
          text = subtitle,
          style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
          color = Slate500,
          maxLines = 1
        )
      }
    }
  }
}
