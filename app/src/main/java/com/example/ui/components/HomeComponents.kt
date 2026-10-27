package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Handyman
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Plumbing
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.ServiceItem
import com.example.model.WorkerProfile
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
import com.example.ui.theme.Slate500
import com.example.ui.theme.Slate600
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate800
import com.example.ui.theme.Slate900

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KargarTopAppBar(
  selectedCity: String = "Select City",
  selectedLanguage: String = "English",
  modifier: Modifier = Modifier,
  onLocationClick: () -> Unit = {},
  onLanguageClick: () -> Unit = {},
  onNotificationClick: () -> Unit = {}
) {
  TopAppBar(
    modifier = modifier.testTag("top_app_bar"),
    colors = TopAppBarDefaults.topAppBarColors(
      containerColor = PureWhite,
      titleContentColor = Slate900
    ),
    title = {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        // App brand emblem (zero green)
        Box(
          modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(12.dp))
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
            modifier = Modifier.size(20.dp)
          )
        }

        Column {
          // App Title
          Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.titleLarge.copy(
              fontWeight = FontWeight.Bold,
              letterSpacing = (-0.5).sp,
              fontSize = 19.sp
            ),
            color = SetuBluePrimary
          )

          // Clickable Location Chip on Top Bar Left
          Surface(
            shape = RoundedCornerShape(20.dp),
            color = SetuOrangeLight,
            border = BorderStroke(1.dp, SetuOrangeContainer),
            modifier = Modifier
              .clip(RoundedCornerShape(20.dp))
              .clickable { onLocationClick() }
              .testTag("location_chip_button")
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp)
            ) {
              Icon(
                imageVector = Icons.Filled.LocationOn,
                contentDescription = "Current Location",
                tint = SetuOrange,
                modifier = Modifier.size(12.dp)
              )
              Spacer(modifier = Modifier.width(3.dp))
              Text(
                text = if (selectedCity.isBlank()) stringResource(R.string.select_city_chip) else selectedCity,
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                color = SetuOrange
              )
              Spacer(modifier = Modifier.width(2.dp))
              Icon(
                imageVector = Icons.Filled.KeyboardArrowDown,
                contentDescription = "Change Location",
                tint = SetuOrange,
                modifier = Modifier.size(13.dp)
              )
            }
          }
        }
      }
    },
    actions = {
      // Language / Translate Icon Button on Top Bar Right
      Surface(
        shape = CircleShape,
        color = SetuBlueLight,
        modifier = Modifier.size(38.dp)
      ) {
        IconButton(
          onClick = onLanguageClick,
          modifier = Modifier.testTag("language_select_button")
        ) {
          Icon(
            imageVector = Icons.Filled.Translate,
            contentDescription = "Change Language",
            tint = SetuBluePrimary,
            modifier = Modifier.size(19.dp)
          )
        }
      }

      Spacer(modifier = Modifier.width(6.dp))

      // Notification button
      Surface(
        shape = CircleShape,
        color = Slate100,
        modifier = Modifier.size(38.dp)
      ) {
        IconButton(
          onClick = onNotificationClick,
          modifier = Modifier.testTag("notification_button")
        ) {
          Icon(
            imageVector = Icons.Filled.NotificationsNone,
            contentDescription = "Notifications",
            tint = Slate700,
            modifier = Modifier.size(19.dp)
          )
        }
      }
      Spacer(modifier = Modifier.width(8.dp))
    }
  )
}

// Backwards compatibility alias
@Composable
fun SetuTopAppBar(
  selectedCity: String = "Gwalior",
  modifier: Modifier = Modifier,
  onLocationClick: () -> Unit = {},
  onLanguageClick: () -> Unit = {},
  onNotificationClick: () -> Unit = {}
) {
  KargarTopAppBar(
    selectedCity = selectedCity,
    modifier = modifier,
    onLocationClick = onLocationClick,
    onLanguageClick = onLanguageClick,
    onNotificationClick = onNotificationClick
  )
}

@Composable
fun HeaderSection(
  selectedCity: String = "Gwalior",
  modifier: Modifier = Modifier,
  onSearchClick: () -> Unit = {}
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 8.dp)
  ) {
    // Hyperlocal prompt & badge
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(6.dp),
      modifier = Modifier
        .clip(RoundedCornerShape(30.dp))
        .background(SetuBlueLight)
        .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
      Box(
        modifier = Modifier
          .size(6.dp)
          .clip(CircleShape)
          .background(SetuBlueSecondary)
      )
      Text(
        text = stringResource(R.string.quick_dispatch_badge, selectedCity),
        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
        color = SetuBlueDark
      )
    }

    Spacer(modifier = Modifier.height(10.dp))

    // Bold Header Text extracted into string resource
    Text(
      text = stringResource(R.string.home_header_prompt),
      style = MaterialTheme.typography.headlineMedium.copy(
        fontWeight = FontWeight.ExtraBold,
        fontSize = 26.sp,
        lineHeight = 32.sp
      ),
      color = Slate900,
      modifier = Modifier.testTag("header_greeting_text")
    )

    Spacer(modifier = Modifier.height(4.dp))

    Text(
      text = stringResource(R.string.home_header_subtitle, selectedCity),
      style = MaterialTheme.typography.bodyMedium,
      color = Slate600
    )

    Spacer(modifier = Modifier.height(14.dp))

    // Search bar surface
    Surface(
      shape = RoundedCornerShape(16.dp),
      color = PureWhite,
      border = BorderStroke(1.dp, Slate200),
      shadowElevation = 1.dp,
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(16.dp))
        .clickable { onSearchClick() }
        .testTag("search_bar_clickable")
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Icon(
          imageVector = Icons.Filled.Search,
          contentDescription = "Search Services",
          tint = SetuBlueSecondary,
          modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
          text = stringResource(R.string.search_placeholder),
          style = MaterialTheme.typography.bodyMedium,
          color = Slate400,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis
        )
      }
    }
  }
}

@Composable
fun ServicesSection(
  services: List<ServiceItem>,
  modifier: Modifier = Modifier,
  onServiceClick: (ServiceItem) -> Unit
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 8.dp)
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column {
        Text(
          text = stringResource(R.string.popular_services),
          style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
          color = Slate900
        )
        Text(
          text = stringResource(R.string.popular_services_subtitle),
          style = MaterialTheme.typography.bodySmall,
          color = Slate500
        )
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    // 3 prominent clickable service cards
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      services.forEach { service ->
        ProminentServiceCard(
          service = service,
          modifier = Modifier.weight(1f),
          onClick = { onServiceClick(service) }
        )
      }
    }
  }
}

@Composable
fun ProminentServiceCard(
  service: ServiceItem,
  modifier: Modifier = Modifier,
  onClick: () -> Unit
) {
  val tag = when (service.id) {
    "plumbing" -> "service_card_plumbing"
    "electrician" -> "service_card_electrician"
    "ac_fridge" -> "service_card_ac_fridge"
    else -> "service_card_${service.id}"
  }

  Surface(
    shape = RoundedCornerShape(20.dp),
    color = PureWhite,
    border = BorderStroke(1.2.dp, service.accentColor.copy(alpha = 0.25f)),
    shadowElevation = 2.dp,
    modifier = modifier
      .clip(RoundedCornerShape(20.dp))
      .clickable { onClick() }
      .testTag(tag)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // Icon Box with rounded container
      Box(
        modifier = Modifier
          .size(54.dp)
          .clip(RoundedCornerShape(16.dp))
          .background(service.containerColor),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = service.icon,
          contentDescription = service.name,
          tint = service.accentColor,
          modifier = Modifier.size(28.dp)
        )
      }

      Spacer(modifier = Modifier.height(10.dp))

      Text(
        text = service.name,
        style = MaterialTheme.typography.titleSmall.copy(
          fontWeight = FontWeight.Bold,
          fontSize = 13.5.sp,
          lineHeight = 17.sp
        ),
        color = Slate900,
        textAlign = TextAlign.Center,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
      )

      Spacer(modifier = Modifier.height(6.dp))

      // Price Tag Badge
      Box(
        modifier = Modifier
          .clip(RoundedCornerShape(8.dp))
          .background(Slate100)
          .padding(horizontal = 6.dp, vertical = 3.dp)
      ) {
        Text(
          text = service.startingPrice,
          style = MaterialTheme.typography.labelSmall.copy(
            fontWeight = FontWeight.SemiBold,
            fontSize = 11.sp
          ),
          color = service.accentColor
        )
      }
    }
  }
}

@Composable
fun RecommendedWorkersSection(
  workers: List<WorkerProfile>,
  modifier: Modifier = Modifier,
  onWorkerClick: (WorkerProfile) -> Unit
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(vertical = 12.dp)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column {
        Text(
          text = stringResource(R.string.verified_workers_title),
          style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.Bold,
            fontSize = 19.sp
          ),
          color = Slate900,
          modifier = Modifier.testTag("workers_section_title")
        )
        Text(
          text = stringResource(R.string.verified_workers_subtitle),
          style = MaterialTheme.typography.bodySmall,
          color = Slate500
        )
      }

      // Nearby count pill
      Surface(
        shape = RoundedCornerShape(20.dp),
        color = SetuBlueLight,
        modifier = Modifier.padding(start = 8.dp)
      ) {
        Text(
          text = stringResource(R.string.online_count, workers.size),
          style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
          color = SetuBlueDark,
          modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    // Horizontal scrollable row of worker profile cards
    LazyRow(
      contentPadding = PaddingValues(horizontal = 16.dp),
      horizontalArrangement = Arrangement.spacedBy(14.dp),
      modifier = Modifier
        .fillMaxWidth()
        .testTag("workers_scroll_row")
    ) {
      itemsIndexed(workers) { index, worker ->
        WorkerCard(
          worker = worker,
          index = index,
          onClick = { onWorkerClick(worker) }
        )
      }
    }
  }
}

@Composable
fun WorkerCard(
  worker: WorkerProfile,
  index: Int,
  modifier: Modifier = Modifier,
  onClick: () -> Unit
) {
  Card(
    shape = RoundedCornerShape(20.dp),
    colors = CardDefaults.cardColors(containerColor = PureWhite),
    border = BorderStroke(1.2.dp, Slate200),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    modifier = modifier
      .width(260.dp)
      .clip(RoundedCornerShape(20.dp))
      .clickable { onClick() }
      .testTag("worker_card_$index")
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(14.dp)
    ) {
      // Header row: Avatar, Name + Verified Badge, and Rating
      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
      ) {
        // Avatar with initials
        Box(
          modifier = Modifier
            .size(46.dp)
            .clip(CircleShape)
            .background(worker.avatarBgColor),
          contentAlignment = Alignment.Center
        ) {
          Text(
            text = worker.initials,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = PureWhite
          )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Column(modifier = Modifier.weight(1f)) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
          ) {
            Text(
              text = worker.name,
              style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
              ),
              color = Slate900,
              maxLines = 1,
              overflow = TextOverflow.Ellipsis
            )

            // Verified Badge Icon (Royal Blue, strictly zero green!)
            if (worker.isVerified) {
              Icon(
                imageVector = Icons.Filled.Verified,
                contentDescription = "Verified Worker Badge",
                tint = SetuVerifiedBlue,
                modifier = Modifier.size(16.dp)
              )
            }
          }

          Text(
            text = worker.profession,
            style = MaterialTheme.typography.bodySmall,
            color = Slate600,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
          )
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Locality & reviews
      Text(
        text = "${worker.locality} • ${worker.reviewCount} jobs completed",
        style = MaterialTheme.typography.labelSmall,
        color = Slate500,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
      )

      Spacer(modifier = Modifier.height(12.dp))

      // Metric Chips: Rating and ETA
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        // Rating Chip (Amber star)
        Surface(
          shape = RoundedCornerShape(10.dp),
          color = Slate100,
          border = BorderStroke(1.dp, Slate200)
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
          ) {
            Icon(
              imageVector = Icons.Filled.Star,
              contentDescription = "Rating",
              tint = SetuAmberStar,
              modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
              text = "${worker.rating}",
              style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
              color = Slate800
            )
          }
        }

        // ETA Chip (Clock icon + mins)
        Surface(
          shape = RoundedCornerShape(10.dp),
          color = SetuBlueLight,
          border = BorderStroke(1.dp, SetuBlueContainer)
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
          ) {
            Icon(
              imageVector = Icons.Filled.Schedule,
              contentDescription = "ETA Time",
              tint = SetuBlueSecondary,
              modifier = Modifier.size(13.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
              text = worker.eta,
              style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
              color = SetuBlueDark
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Footer: Base Price & Action Button
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = stringResource(R.string.base_visiting_fee),
            style = MaterialTheme.typography.labelSmall,
            color = Slate500
          )
          Text(
            text = worker.basePrice,
            style = MaterialTheme.typography.titleMedium.copy(
              fontWeight = FontWeight.ExtraBold,
              fontSize = 17.sp
            ),
            color = SetuOrange
          )
        }

        Button(
          onClick = onClick,
          shape = RoundedCornerShape(12.dp),
          colors = ButtonDefaults.buttonColors(
            containerColor = SetuBluePrimary,
            contentColor = PureWhite
          ),
          contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
          modifier = Modifier.testTag("book_button_$index")
        ) {
          Text(
            text = stringResource(R.string.book_now),
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
          )
        }
      }
    }
  }
}

@Composable
fun KargarGuaranteeBanner(
  modifier: Modifier = Modifier
) {
  Surface(
    shape = RoundedCornerShape(18.dp),
    color = Slate100,
    border = BorderStroke(1.dp, Slate200),
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 8.dp)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(14.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Box(
        modifier = Modifier
          .size(40.dp)
          .clip(CircleShape)
          .background(SetuBlueContainer),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = Icons.Filled.Shield,
          contentDescription = "Kargar Guarantee",
          tint = SetuBluePrimary,
          modifier = Modifier.size(20.dp)
        )
      }
      Spacer(modifier = Modifier.width(12.dp))
      Column {
        Text(
          text = stringResource(R.string.trust_guarantee_title),
          style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
          color = Slate900
        )
        Text(
          text = stringResource(R.string.trust_guarantee_subtitle),
          style = MaterialTheme.typography.bodySmall,
          color = Slate600
        )
      }
    }
  }
}

// Backwards compatibility alias
@Composable
fun SetuGuaranteeBanner(
  modifier: Modifier = Modifier
) {
  KargarGuaranteeBanner(modifier = modifier)
}
