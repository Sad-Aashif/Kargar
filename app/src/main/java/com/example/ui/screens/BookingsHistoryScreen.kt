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
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Handyman
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Plumbing
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.MockDataProvider
import com.example.model.WorkerProfile
import com.example.ui.theme.PureWhite
import com.example.ui.theme.SetuAmberStar
import com.example.ui.theme.SetuBlueContainer
import com.example.ui.theme.SetuBlueDark
import com.example.ui.theme.SetuBlueLight
import com.example.ui.theme.SetuBluePrimary
import com.example.ui.theme.SetuBlueSecondary
import com.example.ui.theme.SetuIndigo
import com.example.ui.theme.SetuIndigoLight
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

data class PastBookingItem(
  val id: String,
  val serviceTitle: String,
  val workerName: String,
  val workerFee: String,
  val date: String,
  val ratingGiven: String,
  val icon: androidx.compose.ui.graphics.vector.ImageVector,
  val workerProfile: WorkerProfile?
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingsHistoryScreen(
  onNavigateToJobStatus: (workerName: String, fee: String, profession: String) -> Unit,
  onRebookWorker: (WorkerProfile) -> Unit
) {
  val context = LocalContext.current

  val pastBookings = listOf(
    PastBookingItem(
      id = "past_1",
      serviceTitle = "Plumbing - Tap & Leakage Fix",
      workerName = "Vikram Rajput",
      workerFee = "₹180",
      date = "22 Oct • Lashkar",
      ratingGiven = "5.0",
      icon = Icons.Filled.Plumbing,
      workerProfile = MockDataProvider.getWorkerById("worker_2")
    ),
    PastBookingItem(
      id = "past_2",
      serviceTitle = "Electrical - Switchboard Repair",
      workerName = "Ramesh Sharma",
      workerFee = "₹150",
      date = "14 Oct • Thatipur",
      ratingGiven = "4.8",
      icon = Icons.Filled.Bolt,
      workerProfile = MockDataProvider.getWorkerById("worker_1")
    )
  )

  Scaffold(
    modifier = Modifier
      .fillMaxSize()
      .testTag("bookings_history_screen"),
    containerColor = Slate50,
    topBar = {
      TopAppBar(
        modifier = Modifier.testTag("bookings_top_bar"),
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = PureWhite,
          titleContentColor = Slate900
        ),
        title = {
          Column {
            Text(
              text = stringResource(R.string.bookings_title),
              style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
              ),
              color = Slate900
            )
            Text(
              text = stringResource(R.string.bookings_subtitle),
              style = MaterialTheme.typography.bodySmall,
              color = Slate500
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
        verticalArrangement = Arrangement.spacedBy(18.dp)
      ) {
        // ACTIVE JOBS SECTION
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = stringResource(R.string.active_jobs_title),
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
              color = Slate900,
              modifier = Modifier.testTag("active_jobs_title")
            )

            // Live status badge (Zero green! Royal blue)
            Surface(
              shape = RoundedCornerShape(20.dp),
              color = SetuBlueLight,
              border = BorderStroke(1.dp, SetuBlueContainer)
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
              ) {
                Box(
                  modifier = Modifier
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(SetuBlueSecondary)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                  text = stringResource(R.string.status_in_transit),
                  style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.ExtraBold),
                  color = SetuBlueDark
                )
              }
            }
          }

          // Active Job Card (Tapping navigates to JobStatusScreen)
          Card(
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = PureWhite),
            border = BorderStroke(1.5.dp, SetuBlueSecondary.copy(alpha = 0.5f)),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(22.dp))
              .clickable {
                onNavigateToJobStatus(
                  "Sunil Prajapati",
                  "₹250",
                  "AC & Fridge Specialist"
                )
              }
              .testTag("active_job_card")
          ) {
            Column(
              modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
            ) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Box(
                  modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(SetuIndigoLight),
                  contentAlignment = Alignment.Center
                ) {
                  Icon(
                    imageVector = Icons.Filled.AcUnit,
                    contentDescription = null,
                    tint = SetuIndigo,
                    modifier = Modifier.size(24.dp)
                  )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                  Text(
                    text = "AC Repair - Worker on the way",
                    style = MaterialTheme.typography.titleMedium.copy(
                      fontWeight = FontWeight.Bold,
                      fontSize = 16.sp
                    ),
                    color = Slate900
                  )
                  Spacer(modifier = Modifier.height(2.dp))
                  Text(
                    text = "Technician: Sunil Prajapati • Order #KG-9142",
                    style = MaterialTheme.typography.bodySmall,
                    color = Slate600
                  )
                }

                Text(
                  text = "₹250",
                  style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp
                  ),
                  color = SetuOrange
                )
              }

              Spacer(modifier = Modifier.height(14.dp))
              HorizontalDivider(color = Slate200)
              Spacer(modifier = Modifier.height(12.dp))

              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Row(
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                  Icon(
                    imageVector = Icons.Filled.Navigation,
                    contentDescription = null,
                    tint = SetuBlueSecondary,
                    modifier = Modifier.size(15.dp)
                  )
                  Text(
                    text = "12 mins away from your location",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                    color = SetuBlueDark
                  )
                }

                Row(
                  verticalAlignment = Alignment.CenterVertically,
                  modifier = Modifier.padding(start = 8.dp)
                ) {
                  Text(
                    text = stringResource(R.string.track_live_order),
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = SetuBluePrimary
                  )
                  Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = SetuBluePrimary,
                    modifier = Modifier.size(16.dp)
                  )
                }
              }
            }
          }
        }

        // PAST JOBS SECTION
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
          Text(
            text = stringResource(R.string.past_jobs_title),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = Slate900,
            modifier = Modifier.testTag("past_jobs_title")
          )

          pastBookings.forEachIndexed { index, job ->
            PastJobCard(
              job = job,
              index = index,
              onRebook = {
                val worker = job.workerProfile ?: MockDataProvider.defaultWorkers[0]
                onRebookWorker(worker)
              }
            )
          }
        }

        Spacer(modifier = Modifier.height(20.dp))
      }
    }
  }
}

@Composable
fun PastJobCard(
  job: PastBookingItem,
  index: Int,
  onRebook: () -> Unit
) {
  Card(
    shape = RoundedCornerShape(20.dp),
    colors = CardDefaults.cardColors(containerColor = PureWhite),
    border = BorderStroke(1.dp, Slate200),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    modifier = Modifier
      .fillMaxWidth()
      .testTag("past_job_card_$index")
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Box(
          modifier = Modifier
            .size(44.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(SetuBlueLight),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = job.icon,
            contentDescription = null,
            tint = SetuBluePrimary,
            modifier = Modifier.size(22.dp)
          )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
          Text(
            text = job.serviceTitle,
            style = MaterialTheme.typography.titleSmall.copy(
              fontWeight = FontWeight.Bold,
              fontSize = 15.sp
            ),
            color = Slate900
          )
          Spacer(modifier = Modifier.height(2.dp))
          Text(
            text = "${job.workerName} • ${job.date}",
            style = MaterialTheme.typography.bodySmall,
            color = Slate600
          )
        }

        // Status badge: Completed (Royal Blue container, zero green!)
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
              imageVector = Icons.Filled.Check,
              contentDescription = null,
              tint = SetuBluePrimary,
              modifier = Modifier.size(12.dp)
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
              text = stringResource(R.string.status_completed),
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp
              ),
              color = Slate700
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(12.dp))
      HorizontalDivider(color = Slate200)
      Spacer(modifier = Modifier.height(10.dp))

      // Footer: Amount, Rating & "Rebook" Outline Button
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Text(
            text = job.workerFee,
            style = MaterialTheme.typography.titleMedium.copy(
              fontWeight = FontWeight.Bold,
              fontSize = 16.sp
            ),
            color = Slate900
          )

          Text(
            text = "•",
            color = Slate400
          )

          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
              imageVector = Icons.Filled.Star,
              contentDescription = null,
              tint = SetuAmberStar,
              modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
              text = job.ratingGiven,
              style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
              color = Slate700
            )
          }
        }

        // Rebook Button
        OutlinedButton(
          onClick = onRebook,
          shape = RoundedCornerShape(12.dp),
          border = BorderStroke(1.2.dp, SetuBluePrimary),
          colors = ButtonDefaults.outlinedButtonColors(
            contentColor = SetuBluePrimary
          ),
          contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
          modifier = Modifier.testTag("rebook_button_$index")
        ) {
          Icon(
            imageVector = Icons.Filled.Refresh,
            contentDescription = null,
            tint = SetuBluePrimary,
            modifier = Modifier.size(14.dp)
          )
          Spacer(modifier = Modifier.width(4.dp))
          Text(
            text = stringResource(R.string.rebook_button),
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
          )
        }
      }
    }
  }
}
