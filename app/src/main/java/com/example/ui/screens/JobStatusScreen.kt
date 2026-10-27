package com.example.ui.screens

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Handyman
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import com.example.ui.theme.SetuOrangeLight
import com.example.ui.theme.SetuVerifiedBlue
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

enum class StepState {
  DONE,
  ACTIVE,
  PENDING
}

data class TimelineStep(
  val title: String,
  val subtitle: String,
  val state: StepState,
  val tag: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobStatusScreen(
  workerName: String,
  workerFee: String,
  workerProfession: String = "Verified Technician",
  paymentMode: String = "Paid via UPI",
  onBackToHome: () -> Unit,
  onSimulateJobCompletion: () -> Unit
) {
  val context = LocalContext.current
  BackHandler { onBackToHome() }

  val steps = listOf(
    TimelineStep(
      title = "Booking Accepted",
      subtitle = "Technician confirmed your service request",
      state = StepState.DONE,
      tag = "timeline_step_0"
    ),
    TimelineStep(
      title = "Worker on the way",
      subtitle = "In transit • ETA 12-15 mins to Gwalior address",
      state = StepState.ACTIVE,
      tag = "timeline_step_1"
    ),
    TimelineStep(
      title = "Work in progress",
      subtitle = "Inspection and fix starts on technician arrival",
      state = StepState.PENDING,
      tag = "timeline_step_2"
    )
  )

  Scaffold(
    modifier = Modifier
      .fillMaxSize()
      .testTag("job_status_screen"),
    containerColor = Slate50,
    topBar = {
      // Top Bar: The new App Name ("Kargar")
      TopAppBar(
        modifier = Modifier.testTag("status_top_bar"),
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = PureWhite,
          titleContentColor = Slate900
        ),
        title = {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            Box(
              modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(
                  Brush.linearGradient(
                    colors = listOf(SetuBluePrimary, SetuBlueSecondary)
                  )
                ),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Filled.Handyman,
                contentDescription = "Kargar Brand Icon",
                tint = PureWhite,
                modifier = Modifier.size(18.dp)
              )
            }
            Column {
              Text(
                text = "Kargar",
                style = MaterialTheme.typography.titleLarge.copy(
                  fontWeight = FontWeight.Bold,
                  letterSpacing = (-0.5).sp
                ),
                color = SetuBluePrimary
              )
              Text(
                text = "Order #KG-9142 • Live Status",
                style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                color = Slate500
              )
            }
          }
        }
      )
    },
    bottomBar = {
      // Bottom Action: A "Back to Home" button
      Surface(
        modifier = Modifier
          .fillMaxWidth()
          .windowInsetsPadding(WindowInsets.navigationBars)
          .testTag("status_bottom_action_bar"),
        color = PureWhite,
        shadowElevation = 8.dp,
        border = BorderStroke(1.dp, Slate200)
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 14.dp)
        ) {
          Button(
            onClick = onBackToHome,
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
              containerColor = SetuBluePrimary,
              contentColor = PureWhite
            ),
            contentPadding = PaddingValues(vertical = 14.dp),
            modifier = Modifier
              .fillMaxWidth()
              .testTag("back_to_home_button")
          ) {
            Text(
              text = androidx.compose.ui.res.stringResource(R.string.back_to_home),
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
              )
            )
          }

          Spacer(modifier = Modifier.height(8.dp))

          OutlinedButton(
            onClick = onSimulateJobCompletion,
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.5.dp, SetuOrange),
            colors = ButtonDefaults.outlinedButtonColors(
              contentColor = SetuOrange
            ),
            contentPadding = PaddingValues(vertical = 12.dp),
            modifier = Modifier
              .fillMaxWidth()
              .testTag("simulate_completion_button")
          ) {
            Text(
              text = androidx.compose.ui.res.stringResource(R.string.simulate_job_completion),
              style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
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
        // Confirmation Hero
        Card(
          shape = RoundedCornerShape(24.dp),
          colors = CardDefaults.cardColors(containerColor = PureWhite),
          border = BorderStroke(1.2.dp, Slate200),
          elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
          modifier = Modifier
            .fillMaxWidth()
            .testTag("booking_confirmed_hero")
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 20.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            // Prominent Material Icon (Zero green! Royal Blue check circle with soft container)
            Box(
              modifier = Modifier
                .size(76.dp)
                .clip(CircleShape)
                .background(SetuBlueLight)
                .border(2.dp, SetuBlueContainer, CircleShape),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = "Confirmed",
                tint = SetuBluePrimary,
                modifier = Modifier.size(46.dp)
              )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
              text = "Booking Confirmed!",
              style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp
              ),
              color = Slate900,
              textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
              text = "Technician is dispatched to your Gwalior address. Base charge $workerFee payable after work.",
              style = MaterialTheme.typography.bodyMedium,
              color = Slate600,
              textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Live ETA Pill
            Surface(
              shape = RoundedCornerShape(20.dp),
              color = SetuOrangeLight,
              border = BorderStroke(1.dp, SetuOrangeContainer)
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
              ) {
                Icon(
                  imageVector = Icons.Filled.Schedule,
                  contentDescription = null,
                  tint = SetuOrange,
                  modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                  text = "Estimated Arrival: ~12-15 mins",
                  style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                  color = SetuOrange
                )
              }
            }
          }
        }

        // Status Timeline Card
        Card(
          shape = RoundedCornerShape(24.dp),
          colors = CardDefaults.cardColors(containerColor = PureWhite),
          border = BorderStroke(1.2.dp, Slate200),
          elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
          modifier = Modifier
            .fillMaxWidth()
            .testTag("status_timeline")
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(20.dp)
          ) {
            Text(
              text = "Live Service Timeline",
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
              color = Slate900
            )

            Spacer(modifier = Modifier.height(16.dp))

            steps.forEachIndexed { index, step ->
              TimelineStepRow(
                step = step,
                isLast = index == steps.lastIndex
              )
            }
          }
        }

        // Worker Info Card
        Card(
          shape = RoundedCornerShape(24.dp),
          colors = CardDefaults.cardColors(containerColor = PureWhite),
          border = BorderStroke(1.2.dp, Slate200),
          elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
          modifier = Modifier
            .fillMaxWidth()
            .testTag("worker_status_card")
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(18.dp)
          ) {
            Text(
              text = "Assigned Technician",
              style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
              color = Slate500
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
              modifier = Modifier.fillMaxWidth(),
              verticalAlignment = Alignment.CenterVertically
            ) {
              // Worker avatar image placeholder
              Box(
                modifier = Modifier
                  .size(60.dp)
                  .clip(RoundedCornerShape(16.dp))
                  .background(SetuBlueLight)
                  .border(1.dp, SetuBlueContainer, RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
              ) {
                Image(
                  painter = painterResource(id = R.drawable.worker_avatar_placeholder_1792902358208),
                  contentDescription = "Worker Profile Picture",
                  contentScale = ContentScale.Crop,
                  modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(16.dp))
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
                      fontSize = 17.sp
                    ),
                    color = Slate900
                  )
                  // Verified Badge Icon (Royal Blue, strictly zero green!)
                  Icon(
                    imageVector = Icons.Filled.Verified,
                    contentDescription = "Verified Worker Badge",
                    tint = SetuVerifiedBlue,
                    modifier = Modifier.size(16.dp)
                  )
                }

                Text(
                  text = workerProfession,
                  style = MaterialTheme.typography.bodySmall,
                  color = Slate600
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                  Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = SetuAmberStar,
                    modifier = Modifier.size(14.dp)
                  )
                  Text(
                    text = "4.8 (Verified Partner)",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                    color = Slate700
                  )
                }
              }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // "Call Worker" outline button
            OutlinedButton(
              onClick = {
                Toast.makeText(
                  context,
                  "Calling $workerName (+91 98765 43210)...",
                  Toast.LENGTH_SHORT
                ).show()
              },
              shape = RoundedCornerShape(14.dp),
              border = BorderStroke(1.5.dp, SetuBluePrimary),
              colors = ButtonDefaults.outlinedButtonColors(
                contentColor = SetuBluePrimary
              ),
              contentPadding = PaddingValues(vertical = 12.dp),
              modifier = Modifier
                .fillMaxWidth()
                .testTag("call_worker_button")
            ) {
              Icon(
                imageVector = Icons.Filled.Phone,
                contentDescription = "Call",
                tint = SetuBluePrimary,
                modifier = Modifier.size(18.dp)
              )
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = "Call Worker",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
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
fun TimelineStepRow(
  step: TimelineStep,
  isLast: Boolean
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .testTag(step.tag)
  ) {
    // Indicator Column (Circle + Connector Line)
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier.width(36.dp)
    ) {
      when (step.state) {
        StepState.DONE -> {
          // Done State: Filled Royal Blue Circle with Checkmark
          Box(
            modifier = Modifier
              .size(32.dp)
              .clip(CircleShape)
              .background(SetuBluePrimary),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Filled.Check,
              contentDescription = "Step Completed",
              tint = PureWhite,
              modifier = Modifier.size(16.dp)
            )
          }
        }
        StepState.ACTIVE -> {
          // Active/Highlighted State: Vibrant Blue Double Ring with Location Arrow
          Box(
            modifier = Modifier
              .size(32.dp)
              .clip(CircleShape)
              .background(SetuBlueLight)
              .border(2.5.dp, SetuBlueSecondary, CircleShape),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Filled.Navigation,
              contentDescription = "Active Step",
              tint = SetuBlueSecondary,
              modifier = Modifier.size(16.dp)
            )
          }
        }
        StepState.PENDING -> {
          // Pending State: Muted Slate Outline with build icon
          Box(
            modifier = Modifier
              .size(32.dp)
              .clip(CircleShape)
              .background(Slate100)
              .border(1.5.dp, Slate300, CircleShape),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Filled.Build,
              contentDescription = "Pending Step",
              tint = Slate400,
              modifier = Modifier.size(14.dp)
            )
          }
        }
      }

      if (!isLast) {
        // Vertical connector line
        val lineColor = if (step.state == StepState.DONE) SetuBluePrimary else Slate200
        Box(
          modifier = Modifier
            .width(2.5.dp)
            .height(44.dp)
            .background(lineColor)
        )
      }
    }

    Spacer(modifier = Modifier.width(12.dp))

    // Step Details Column
    Column(
      modifier = Modifier
        .weight(1f)
        .padding(bottom = if (isLast) 0.dp else 20.dp)
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Text(
          text = step.title,
          style = MaterialTheme.typography.titleSmall.copy(
            fontWeight = if (step.state == StepState.ACTIVE) FontWeight.ExtraBold else FontWeight.Bold,
            fontSize = 15.sp
          ),
          color = when (step.state) {
            StepState.ACTIVE -> SetuBlueDark
            StepState.DONE -> Slate900
            StepState.PENDING -> Slate500
          }
        )

        if (step.state == StepState.ACTIVE) {
          // Highlight badge for active step
          Surface(
            shape = RoundedCornerShape(8.dp),
            color = SetuBlueLight,
            border = BorderStroke(1.dp, SetuBlueContainer)
          ) {
            Text(
              text = "IN TRANSIT",
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 10.sp
              ),
              color = SetuBlueSecondary,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(2.dp))

      Text(
        text = step.subtitle,
        style = MaterialTheme.typography.bodySmall,
        color = if (step.state == StepState.PENDING) Slate400 else Slate600
      )
    }
  }
}
