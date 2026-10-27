package com.example.ui.screens

import android.widget.Toast
import androidx.activity.compose.BackHandler
import com.example.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Handyman
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.PureWhite
import com.example.ui.theme.SetuAmberStar
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
import com.example.ui.theme.Slate300
import com.example.ui.theme.Slate400
import com.example.ui.theme.Slate50
import com.example.ui.theme.Slate500
import com.example.ui.theme.Slate600
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate800
import com.example.ui.theme.Slate900

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvoiceRatingScreen(
  workerName: String,
  serviceProvided: String,
  totalAmountPaid: String,
  paymentMode: String,
  onBackClick: () -> Unit,
  onSubmitAndReturnHome: () -> Unit
) {
  val context = LocalContext.current
  BackHandler { onBackClick() }

  // Rating UI state: 5 clickable stars toggling from outline to filled
  var selectedRating by remember { mutableIntStateOf(5) }
  var reviewText by remember { mutableStateOf("") }

  val ratingLabels = listOf(
    "Tap to rate",
    "Needs improvement",
    "Fair service",
    "Good service",
    "Very good!",
    "Exceptional service! ⭐"
  )

  Scaffold(
    modifier = Modifier
      .fillMaxSize()
      .testTag("invoice_rating_screen"),
    containerColor = Slate50,
    topBar = {
      TopAppBar(
        modifier = Modifier.testTag("invoice_top_bar"),
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = PureWhite,
          titleContentColor = Slate900
        ),
        navigationIcon = {
          IconButton(
            onClick = onBackClick,
            modifier = Modifier.testTag("invoice_back_button")
          ) {
            Icon(
              imageVector = Icons.AutoMirrored.Filled.ArrowBack,
              contentDescription = "Back",
              tint = Slate800
            )
          }
        },
        title = {
          Text(
            text = androidx.compose.ui.res.stringResource(R.string.job_completed_title),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = Slate900
          )
        }
      )
    },
    bottomBar = {
      // Sticky Bottom Action Bar with prominent "Submit & Return Home"
      Surface(
        modifier = Modifier
          .fillMaxWidth()
          .windowInsetsPadding(WindowInsets.navigationBars)
          .testTag("invoice_bottom_action_bar"),
        color = PureWhite,
        shadowElevation = 8.dp,
        border = BorderStroke(1.dp, Slate200)
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 14.dp)
        ) {
          val feedbackToastMsg = androidx.compose.ui.res.stringResource(R.string.feedback_submitted_toast)
          Button(
            onClick = {
              Toast.makeText(
                context,
                feedbackToastMsg,
                Toast.LENGTH_SHORT
              ).show()
              onSubmitAndReturnHome()
            },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
              containerColor = SetuBluePrimary,
              contentColor = PureWhite
            ),
            contentPadding = PaddingValues(vertical = 14.dp),
            modifier = Modifier
              .fillMaxWidth()
              .testTag("submit_return_home_button")
          ) {
            Text(
              text = androidx.compose.ui.res.stringResource(R.string.submit_and_return_home),
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
        // Digital Receipt Section
        Card(
          shape = RoundedCornerShape(24.dp),
          colors = CardDefaults.cardColors(containerColor = PureWhite),
          border = BorderStroke(1.2.dp, Slate200),
          elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
          modifier = Modifier
            .fillMaxWidth()
            .testTag("digital_receipt_card")
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(20.dp)
          ) {
            // Receipt Header
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
              ) {
                Box(
                  modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(SetuBlueLight),
                  contentAlignment = Alignment.Center
                ) {
                  Icon(
                    imageVector = Icons.Filled.Receipt,
                    contentDescription = null,
                    tint = SetuBluePrimary,
                    modifier = Modifier.size(18.dp)
                  )
                }
                Column {
                  Text(
                    text = "Tax Invoice",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = Slate900
                  )
                  Text(
                    text = "Order #KG-9142 • Gwalior",
                    style = MaterialTheme.typography.labelSmall,
                    color = Slate500
                  )
                }
              }

              // Paid badge (Zero green! Royal Blue container)
              Surface(
                shape = RoundedCornerShape(8.dp),
                color = SetuBlueLight,
                border = BorderStroke(1.dp, SetuBlueContainer)
              ) {
                Row(
                  verticalAlignment = Alignment.CenterVertically,
                  modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                  Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    tint = SetuBluePrimary,
                    modifier = Modifier.size(12.dp)
                  )
                  Spacer(modifier = Modifier.width(3.dp))
                  Text(
                    text = "PAID",
                    style = MaterialTheme.typography.labelSmall.copy(
                      fontWeight = FontWeight.Bold,
                      fontSize = 11.sp
                    ),
                    color = SetuBlueDark
                  )
                }
              }
            }

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = Slate200)
            Spacer(modifier = Modifier.height(14.dp))

            // 1. Worker Name
            ReceiptItemRow(
              label = "Service Technician",
              value = workerName,
              isBold = true
            )

            Spacer(modifier = Modifier.height(10.dp))

            // 2. Service Provided
            ReceiptItemRow(
              label = "Service Provided",
              value = serviceProvided,
              isBold = false
            )

            Spacer(modifier = Modifier.height(10.dp))

            // 3. Payment Mode (show "Paid via UPI" or "Paid via Cash")
            ReceiptItemRow(
              label = "Payment Mode",
              value = paymentMode,
              isBold = false,
              valueColor = SetuBluePrimary
            )

            Spacer(modifier = Modifier.height(14.dp))
            HorizontalDivider(color = Slate200)
            Spacer(modifier = Modifier.height(14.dp))

            // 4. Total Amount Paid (Worker's Base Fee)
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Column {
                Text(
                  text = "Total Amount Paid",
                  style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                  color = Slate900
                )
                Text(
                  text = "Inclusive of all local taxes",
                  style = MaterialTheme.typography.labelSmall,
                  color = Slate500
                )
              }

              Text(
                text = totalAmountPaid,
                style = MaterialTheme.typography.headlineSmall.copy(
                  fontWeight = FontWeight.ExtraBold,
                  fontSize = 24.sp
                ),
                color = SetuOrange
              )
            }
          }
        }

        // Rating UI Section
        Card(
          shape = RoundedCornerShape(24.dp),
          colors = CardDefaults.cardColors(containerColor = PureWhite),
          border = BorderStroke(1.2.dp, Slate200),
          elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
          modifier = Modifier
            .fillMaxWidth()
            .testTag("service_rating_section")
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            // Prompt asking: "How was the service?"
            Text(
              text = "How was the service?",
              style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp
              ),
              color = Slate900,
              textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
              text = "Rate your experience with $workerName",
              style = MaterialTheme.typography.bodySmall,
              color = Slate600,
              textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(14.dp))

            // 5 clickable Material Outline/Filled Stars for rating
            Row(
              horizontalArrangement = Arrangement.spacedBy(8.dp),
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier.padding(vertical = 4.dp)
            ) {
              for (starIndex in 1..5) {
                val isFilled = starIndex <= selectedRating
                val interactionSource = remember { MutableInteractionSource() }

                Box(
                  modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .clickable(
                      interactionSource = interactionSource,
                      indication = null
                    ) {
                      selectedRating = starIndex
                    }
                    .testTag("star_rating_$starIndex"),
                  contentAlignment = Alignment.Center
                ) {
                  Icon(
                    imageVector = if (isFilled) Icons.Filled.Star else Icons.Filled.StarBorder,
                    contentDescription = "Star $starIndex",
                    tint = if (isFilled) SetuAmberStar else Slate300,
                    modifier = Modifier.size(36.dp)
                  )
                }
              }
            }

            // Rating description feedback
            Text(
              text = ratingLabels.getOrElse(selectedRating) { "Very Good" },
              style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp
              ),
              color = if (selectedRating >= 4) SetuOrange else Slate600
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Text field for "Add a review (Optional)"
            Column(
              modifier = Modifier.fillMaxWidth(),
              verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Text(
                text = "Add a review (Optional)",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                color = Slate700
              )
              OutlinedTextField(
                value = reviewText,
                onValueChange = { reviewText = it },
                modifier = Modifier
                  .fillMaxWidth()
                  .testTag("review_text_field"),
                placeholder = {
                  Text(
                    text = "Tell us about technician punctuality, work quality, behavior...",
                    color = Slate400,
                    style = MaterialTheme.typography.bodySmall
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
          }
        }

        Spacer(modifier = Modifier.height(10.dp))
      }
    }
  }
}

@Composable
fun ReceiptItemRow(
  label: String,
  value: String,
  isBold: Boolean,
  valueColor: androidx.compose.ui.graphics.Color = Slate800
) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Text(
      text = label,
      style = MaterialTheme.typography.bodyMedium,
      color = Slate600
    )
    Text(
      text = value,
      style = MaterialTheme.typography.bodyMedium.copy(
        fontWeight = if (isBold) FontWeight.Bold else FontWeight.SemiBold
      ),
      color = valueColor
    )
  }
}
