package com.example.ui.screens

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.Handyman
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Plumbing
import androidx.compose.material.icons.filled.PowerOff
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.PartnerJobRequest
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
fun WorkerDashboardScreen(
  onBackToLogin: () -> Unit,
  onSwitchToCustomer: () -> Unit
) {
  val context = LocalContext.current
  var isOnline by remember { mutableStateOf(true) }

  // Dynamic earnings and jobs tracker for the worker session
  var todayEarnings by remember { mutableIntStateOf(450) }
  var completedCount by remember { mutableIntStateOf(2) }

  // Active Job State (null when idle)
  var activeJob by remember { mutableStateOf<PartnerJobRequest?>(null) }

  // List of incoming available requests nearby
  val incomingRequests = remember {
    mutableStateListOf(
      PartnerJobRequest(
        id = "req_1",
        serviceCategory = "Plumbing",
        title = "Plumbing Issue - Tap & Leakage Fix",
        customerName = "Amit Verma",
        customerPhone = "+91 98261 44521",
        customerAddress = "Flat 402, Royal Residency, Lashkar, Gwalior",
        issueDescription = "Kitchen sink pipe is leaking heavily and low water pressure in bathroom.",
        distance = "2.5 km away",
        locality = "Lashkar",
        basePrice = "₹150 Base",
        basePriceAmount = 150,
        postedAgo = "3 mins ago"
      ),
      PartnerJobRequest(
        id = "req_2",
        serviceCategory = "Electrical",
        title = "Electrical - MCB & Wiring Repair",
        customerName = "Pooja Sharma",
        customerPhone = "+91 97520 88319",
        customerAddress = "House 14B, Green Park Colony, City Center, Gwalior",
        issueDescription = "Main circuit breaker tripping every 15 minutes when AC is switched on.",
        distance = "1.8 km away",
        locality = "City Center",
        basePrice = "₹180 Base",
        basePriceAmount = 180,
        postedAgo = "6 mins ago"
      ),
      PartnerJobRequest(
        id = "req_3",
        serviceCategory = "AC / Appliance",
        title = "AC Servicing - Filter & Cooling Check",
        customerName = "Rahul Gupta",
        customerPhone = "+91 94251 77650",
        customerAddress = "Plot 88, Near Mayur Market, Thatipur, Gwalior",
        issueDescription = "Split AC cooling reduced significantly; deep cleaning and gas inspection required.",
        distance = "3.2 km away",
        locality = "Thatipur",
        basePrice = "₹250 Base",
        basePriceAmount = 250,
        postedAgo = "11 mins ago"
      )
    )
  }

  // Handle hardware / gesture back button
  BackHandler {
    onBackToLogin()
  }

  Scaffold(
    modifier = Modifier
      .fillMaxSize()
      .testTag("partner_dashboard_screen"),
    containerColor = Slate50,
    topBar = {
      TopAppBar(
        modifier = Modifier.testTag("partner_top_bar"),
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = PureWhite,
          titleContentColor = Slate900
        ),
        navigationIcon = {
          IconButton(
            onClick = onBackToLogin,
            modifier = Modifier.testTag("partner_back_button")
          ) {
            Icon(
              imageVector = Icons.AutoMirrored.Filled.ArrowBack,
              contentDescription = "Back to Login",
              tint = Slate800
            )
          }
        },
        title = {
          Column {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Text(
                text = stringResource(R.string.partner_dashboard_title),
                style = MaterialTheme.typography.titleLarge.copy(
                  fontWeight = FontWeight.ExtraBold,
                  fontSize = 20.sp
                ),
                color = SetuBluePrimary
              )
              Icon(
                imageVector = Icons.Filled.Verified,
                contentDescription = "Verified Partner",
                tint = SetuVerifiedBlue,
                modifier = Modifier.size(16.dp)
              )
            }
            Text(
              text = if (isOnline) stringResource(R.string.partner_ready_for_jobs) else stringResource(R.string.partner_status_offline),
              style = MaterialTheme.typography.bodySmall,
              color = if (isOnline) SetuBlueDark else Slate500
            )
          }
        },
        actions = {
          // Online / Offline Switch
          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(end = 12.dp)
          ) {
            // Pill status label (Strictly ZERO GREEN: Royal Blue or Slate)
            Surface(
              shape = RoundedCornerShape(12.dp),
              color = if (isOnline) SetuBlueLight else Slate100,
              border = BorderStroke(1.dp, if (isOnline) SetuBlueContainer else Slate200),
              modifier = Modifier.padding(end = 8.dp)
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
              ) {
                Box(
                  modifier = Modifier
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(if (isOnline) SetuBlueSecondary else Slate400)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                  text = if (isOnline) stringResource(R.string.partner_status_online) else stringResource(R.string.partner_status_offline),
                  style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp
                  ),
                  color = if (isOnline) SetuBlueDark else Slate600
                )
              }
            }

            Switch(
              checked = isOnline,
              onCheckedChange = { checked ->
                isOnline = checked
                Toast.makeText(
                  context,
                  if (checked) "You are now ONLINE for bookings" else "You are now OFFLINE",
                  Toast.LENGTH_SHORT
                ).show()
              },
              colors = SwitchDefaults.colors(
                checkedThumbColor = PureWhite,
                checkedTrackColor = SetuBluePrimary,
                checkedBorderColor = SetuBluePrimary,
                uncheckedThumbColor = Slate400,
                uncheckedTrackColor = Slate200,
                uncheckedBorderColor = Slate200
              ),
              modifier = Modifier.testTag("partner_online_switch")
            )
          }
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
        // 1. PARTNER SUMMARY / EARNINGS STATS CARD (Warm Amber / Royal Blue, Zero Green)
        Card(
          shape = RoundedCornerShape(22.dp),
          colors = CardDefaults.cardColors(containerColor = PureWhite),
          border = BorderStroke(1.2.dp, Slate200),
          elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
          modifier = Modifier
            .fillMaxWidth()
            .testTag("partner_stats_card")
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            // Stat 1: Today's Earnings
            Column(modifier = Modifier.weight(1f)) {
              Text(
                text = stringResource(R.string.partner_stats_today_earnings),
                style = MaterialTheme.typography.labelMedium,
                color = Slate500
              )
              Spacer(modifier = Modifier.height(2.dp))
              Text(
                text = "₹$todayEarnings",
                style = MaterialTheme.typography.titleLarge.copy(
                  fontWeight = FontWeight.ExtraBold,
                  fontSize = 22.sp
                ),
                color = SetuOrange
              )
            }

            // Stat 2: Jobs Completed
            Column(
              modifier = Modifier.weight(1f),
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              Text(
                text = stringResource(R.string.partner_stats_jobs_done),
                style = MaterialTheme.typography.labelMedium,
                color = Slate500
              )
              Spacer(modifier = Modifier.height(2.dp))
              Text(
                text = "$completedCount",
                style = MaterialTheme.typography.titleLarge.copy(
                  fontWeight = FontWeight.Bold,
                  fontSize = 22.sp
                ),
                color = SetuBluePrimary
              )
            }

            // Stat 3: Rating
            Column(
              modifier = Modifier.weight(1f),
              horizontalAlignment = Alignment.End
            ) {
              Text(
                text = stringResource(R.string.partner_stats_rating),
                style = MaterialTheme.typography.labelMedium,
                color = Slate500
              )
              Spacer(modifier = Modifier.height(2.dp))
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                  imageVector = Icons.Filled.Star,
                  contentDescription = null,
                  tint = SetuAmberStar,
                  modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                  text = "4.9",
                  style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                  ),
                  color = Slate800
                )
              }
            }
          }
        }

        // 2. ACTIVE TASK CARD (Visible when an active job is accepted)
        if (activeJob != null) {
          val job = activeJob!!
          Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = stringResource(R.string.partner_active_task_title),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = Slate900,
                modifier = Modifier.testTag("partner_active_task_title")
              )

              Surface(
                shape = RoundedCornerShape(12.dp),
                color = SetuBlueLight,
                border = BorderStroke(1.dp, SetuBlueContainer)
              ) {
                Text(
                  text = "IN TRANSIT",
                  style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 10.5.sp
                  ),
                  color = SetuBlueDark,
                  modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                )
              }
            }

            Card(
              shape = RoundedCornerShape(22.dp),
              colors = CardDefaults.cardColors(containerColor = PureWhite),
              border = BorderStroke(1.8.dp, SetuBluePrimary),
              elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
              modifier = Modifier
                .fillMaxWidth()
                .testTag("partner_active_task_card")
            ) {
              Column(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
              ) {
                // Customer Name & Contact Row
                Row(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.SpaceBetween,
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                  ) {
                    Box(
                      modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(SetuBlueLight),
                      contentAlignment = Alignment.Center
                    ) {
                      Text(
                        text = job.customerName.take(2).uppercase(),
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        color = SetuBluePrimary
                      )
                    }

                    Column {
                      Text(
                        text = job.customerName,
                        style = MaterialTheme.typography.titleMedium.copy(
                          fontWeight = FontWeight.Bold,
                          fontSize = 16.sp
                        ),
                        color = Slate900
                      )
                      Text(
                        text = job.title,
                        style = MaterialTheme.typography.bodySmall,
                        color = Slate600
                      )
                    }
                  }

                  // Call Customer Action
                  OutlinedButton(
                    onClick = {
                      Toast.makeText(
                        context,
                        "Calling ${job.customerName}: ${job.customerPhone}",
                        Toast.LENGTH_SHORT
                      ).show()
                    },
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, SetuBluePrimary),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                    modifier = Modifier.testTag("partner_call_customer_button")
                  ) {
                    Icon(
                      imageVector = Icons.Filled.Phone,
                      contentDescription = "Call",
                      tint = SetuBluePrimary,
                      modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                      text = stringResource(R.string.partner_call_customer),
                      style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                      color = SetuBluePrimary
                    )
                  }
                }

                HorizontalDivider(color = Slate200)

                // Customer Address Row
                Row(
                  verticalAlignment = Alignment.Top,
                  horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                  Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = null,
                    tint = SetuOrange,
                    modifier = Modifier
                      .size(20.dp)
                      .padding(top = 1.dp)
                  )
                  Column {
                    Text(
                      text = "Service Address",
                      style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                      color = Slate500
                    )
                    Text(
                      text = job.customerAddress,
                      style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                      color = Slate800
                    )
                  }
                }

                // Issue Description Box
                Surface(
                  shape = RoundedCornerShape(14.dp),
                  color = Slate50,
                  border = BorderStroke(1.dp, Slate200),
                  modifier = Modifier.fillMaxWidth()
                ) {
                  Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                      text = "Customer Reported Issue",
                      style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                      color = Slate500
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                      text = job.issueDescription,
                      style = MaterialTheme.typography.bodyMedium,
                      color = Slate800
                    )
                  }
                }

                // Fee and ETA Summary
                Row(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.SpaceBetween,
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Column {
                    Text(
                      text = "Collect from Customer",
                      style = MaterialTheme.typography.labelSmall,
                      color = Slate500
                    )
                    Text(
                      text = job.basePrice,
                      style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp
                      ),
                      color = SetuOrange
                    )
                  }

                  Text(
                    text = "${job.distance} (${job.locality})",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = Slate600
                  )
                }

                // Action Buttons: "Navigate" and "Mark as Completed"
                Row(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                  // Button 1: Navigate
                  OutlinedButton(
                    onClick = {
                      Toast.makeText(
                        context,
                        "Opening Google Maps navigation to ${job.customerAddress}",
                        Toast.LENGTH_SHORT
                      ).show()
                    },
                    shape = RoundedCornerShape(14.dp),
                    border = BorderStroke(1.5.dp, SetuBluePrimary),
                    colors = ButtonDefaults.outlinedButtonColors(
                      contentColor = SetuBluePrimary
                    ),
                    modifier = Modifier
                      .weight(1f)
                      .height(48.dp)
                      .testTag("partner_navigate_button")
                  ) {
                    Icon(
                      imageVector = Icons.Filled.Navigation,
                      contentDescription = null,
                      tint = SetuBluePrimary,
                      modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                      text = stringResource(R.string.partner_navigate_button),
                      style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                    )
                  }

                  // Button 2: Mark as Completed
                  Button(
                    onClick = {
                      val feeAmount = job.basePriceAmount
                      todayEarnings += feeAmount
                      completedCount += 1
                      activeJob = null
                      Toast.makeText(
                        context,
                        "Job completed! ₹$feeAmount added to today's earnings.",
                        Toast.LENGTH_LONG
                      ).show()
                    },
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                      containerColor = SetuBluePrimary,
                      contentColor = PureWhite
                    ),
                    modifier = Modifier
                      .weight(1.3f)
                      .height(48.dp)
                      .testTag("partner_mark_completed_button")
                  ) {
                    Icon(
                      imageVector = Icons.Filled.CheckCircle,
                      contentDescription = null,
                      tint = PureWhite,
                      modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                      text = stringResource(R.string.partner_mark_completed_button),
                      style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                    )
                  }
                }
              }
            }
          }
        }

        // 3. INCOMING REQUESTS SECTION
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column {
              Text(
                text = stringResource(R.string.partner_incoming_title),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = Slate900,
                modifier = Modifier.testTag("partner_incoming_title")
              )
              Text(
                text = stringResource(R.string.partner_incoming_subtitle),
                style = MaterialTheme.typography.bodySmall,
                color = Slate500
              )
            }

            Surface(
              shape = RoundedCornerShape(10.dp),
              color = SetuBlueLight,
              border = BorderStroke(1.dp, SetuBlueContainer)
            ) {
              Text(
                text = "${incomingRequests.size} nearby",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                color = SetuBlueDark,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
              )
            }
          }

          if (!isOnline) {
            // Offline Banner
            Surface(
              shape = RoundedCornerShape(18.dp),
              color = Slate100,
              border = BorderStroke(1.dp, Slate200),
              modifier = Modifier
                .fillMaxWidth()
                .testTag("partner_offline_banner")
            ) {
              Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Box(
                  modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Slate200),
                  contentAlignment = Alignment.Center
                ) {
                  Icon(
                    imageVector = Icons.Filled.PowerOff,
                    contentDescription = null,
                    tint = Slate600,
                    modifier = Modifier.size(20.dp)
                  )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                  Text(
                    text = "You are currently Offline",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = Slate800
                  )
                  Spacer(modifier = Modifier.height(2.dp))
                  Text(
                    text = stringResource(R.string.partner_offline_banner),
                    style = MaterialTheme.typography.bodySmall,
                    color = Slate600
                  )
                }
              }
            }
          } else if (incomingRequests.isEmpty()) {
            Surface(
              shape = RoundedCornerShape(18.dp),
              color = PureWhite,
              border = BorderStroke(1.dp, Slate200),
              modifier = Modifier.fillMaxWidth()
            ) {
              Column(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
              ) {
                Icon(
                  imageVector = Icons.Filled.CheckCircle,
                  contentDescription = null,
                  tint = SetuBluePrimary,
                  modifier = Modifier.size(36.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                  text = "All caught up!",
                  style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                  color = Slate800
                )
                Text(
                  text = "New customer booking requests will appear here instantly.",
                  style = MaterialTheme.typography.bodySmall,
                  color = Slate500
                )
              }
            }
          } else {
            // List of incoming job cards
            incomingRequests.forEachIndexed { index, request ->
              Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = PureWhite),
                border = BorderStroke(1.dp, Slate200),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                modifier = Modifier
                  .fillMaxWidth()
                  .testTag("incoming_job_card_$index")
              ) {
                Column(
                  modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                  verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                  Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                  ) {
                    Box(
                      modifier = Modifier
                        .size(42.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(SetuBlueLight),
                      contentAlignment = Alignment.Center
                    ) {
                      Icon(
                        imageVector = when {
                          request.serviceCategory.contains("Plumb", true) -> Icons.Filled.Plumbing
                          request.serviceCategory.contains("Elect", true) -> Icons.Filled.ElectricBolt
                          else -> Icons.Filled.Handyman
                        },
                        contentDescription = null,
                        tint = SetuBluePrimary,
                        modifier = Modifier.size(20.dp)
                      )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                      Text(
                        text = request.title,
                        style = MaterialTheme.typography.titleSmall.copy(
                          fontWeight = FontWeight.Bold,
                          fontSize = 15.sp
                        ),
                        color = Slate900
                      )
                      Spacer(modifier = Modifier.height(2.dp))
                      Text(
                        text = "${request.distance} • ${request.locality} • ${request.postedAgo}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Slate600
                      )
                    }

                    // Price Badge
                    Text(
                      text = request.basePrice,
                      style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp
                      ),
                      color = SetuOrange
                    )
                  }

                  // Issue Description
                  Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Slate50,
                    modifier = Modifier.fillMaxWidth()
                  ) {
                    Text(
                      text = request.issueDescription,
                      style = MaterialTheme.typography.bodySmall,
                      color = Slate700,
                      modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)
                    )
                  }

                  // Accept Job Button
                  Button(
                    onClick = {
                      if (activeJob != null) {
                        Toast.makeText(
                          context,
                          "You already have an active job! Complete it before accepting another.",
                          Toast.LENGTH_SHORT
                        ).show()
                      } else {
                        activeJob = request
                        incomingRequests.remove(request)
                        Toast.makeText(
                          context,
                          "Job accepted! Customer notified that technician is en route.",
                          Toast.LENGTH_SHORT
                        ).show()
                      }
                    },
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                      containerColor = SetuBluePrimary,
                      contentColor = PureWhite
                    ),
                    contentPadding = PaddingValues(vertical = 12.dp),
                    modifier = Modifier
                      .fillMaxWidth()
                      .testTag("partner_accept_job_${request.id}")
                  ) {
                    Text(
                      text = stringResource(R.string.partner_accept_job),
                      style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.5.sp
                      )
                    )
                  }
                }
              }
            }
          }
        }

        // 4. SWITCH TO CUSTOMER MODE OR LOG OUT
        Surface(
          shape = RoundedCornerShape(16.dp),
          color = PureWhite,
          border = BorderStroke(1.dp, Slate200),
          modifier = Modifier
            .fillMaxWidth()
            .testTag("partner_switch_customer_card")
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
              Box(
                modifier = Modifier
                  .size(36.dp)
                  .clip(CircleShape)
                  .background(SetuBlueLight),
                contentAlignment = Alignment.Center
              ) {
                Icon(
                  imageVector = Icons.Filled.SwapHoriz,
                  contentDescription = null,
                  tint = SetuBluePrimary,
                  modifier = Modifier.size(20.dp)
                )
              }
              Column {
                Text(
                  text = "Need home services?",
                  style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                  color = Slate800
                )
                Text(
                  text = "Book local professionals as customer",
                  style = MaterialTheme.typography.bodySmall,
                  color = Slate500
                )
              }
            }

            OutlinedButton(
              onClick = onSwitchToCustomer,
              shape = RoundedCornerShape(12.dp),
              border = BorderStroke(1.2.dp, SetuBluePrimary),
              contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
              modifier = Modifier.testTag("partner_switch_customer_button")
            ) {
              Text(
                text = "Customer App",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                color = SetuBluePrimary
              )
            }
          }
        }

        Spacer(modifier = Modifier.height(20.dp))
      }
    }
  }
}
