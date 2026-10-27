package com.example.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Plumbing
import com.example.model.ServiceItem
import com.example.model.WorkerProfile
import com.example.ui.theme.SetuBlueLight
import com.example.ui.theme.SetuBluePrimary
import com.example.ui.theme.SetuIndigo
import com.example.ui.theme.SetuIndigoLight
import com.example.ui.theme.SetuOrange
import com.example.ui.theme.SetuOrangeContainer

object MockDataProvider {
  val defaultServices = listOf(
    ServiceItem(
      id = "plumbing",
      name = "Plumbing",
      subtitle = "Pipes, taps & leaks",
      icon = Icons.Filled.Plumbing,
      startingPrice = "₹149",
      accentColor = SetuBluePrimary,
      containerColor = SetuBlueLight,
      tagText = "Starting ₹149"
    ),
    ServiceItem(
      id = "electrician",
      name = "Electrician",
      subtitle = "Switches, fans & wiring",
      icon = Icons.Filled.Bolt,
      startingPrice = "₹129",
      accentColor = SetuOrange,
      containerColor = SetuOrangeContainer,
      tagText = "Starting ₹129"
    ),
    ServiceItem(
      id = "ac_fridge",
      name = "AC / Fridge Repair",
      subtitle = "Cooling & gas refill",
      icon = Icons.Filled.AcUnit,
      startingPrice = "₹299",
      accentColor = SetuIndigo,
      containerColor = SetuIndigoLight,
      tagText = "Starting ₹299"
    )
  )

  val defaultWorkers = listOf(
    WorkerProfile(
      id = "worker_1",
      name = "Ramesh Sharma",
      profession = "Senior Electrician",
      isVerified = true,
      rating = 4.8,
      reviewCount = 124,
      eta = "15 mins",
      basePrice = "₹150",
      locality = "Thatipur, Gwalior",
      initials = "RS",
      avatarBgColor = SetuBluePrimary
    ),
    WorkerProfile(
      id = "worker_2",
      name = "Vikram Rajput",
      profession = "Master Plumber",
      isVerified = true,
      rating = 4.9,
      reviewCount = 182,
      eta = "18 mins",
      basePrice = "₹180",
      locality = "Lashkar, Gwalior",
      initials = "VR",
      avatarBgColor = SetuIndigo
    ),
    WorkerProfile(
      id = "worker_3",
      name = "Sunil Prajapati",
      profession = "AC & Fridge Specialist",
      isVerified = true,
      rating = 4.7,
      reviewCount = 95,
      eta = "22 mins",
      basePrice = "₹250",
      locality = "City Center, Gwalior",
      initials = "SP",
      avatarBgColor = SetuOrange
    )
  )

  fun getWorkerById(id: String): WorkerProfile? {
    return defaultWorkers.find { it.id == id }
  }

  fun getWorkerByName(name: String): WorkerProfile? {
    return defaultWorkers.find { it.name.equals(name, ignoreCase = true) }
  }
}
