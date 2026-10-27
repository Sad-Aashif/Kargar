package com.example.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.ui.theme.SetuAmberStar
import com.example.ui.theme.SetuBlueDark
import com.example.ui.theme.SetuBlueLight
import com.example.ui.theme.SetuBluePrimary
import com.example.ui.theme.SetuIndigo
import com.example.ui.theme.SetuIndigoLight
import com.example.ui.theme.SetuOrange
import com.example.ui.theme.SetuOrangeLight

data class ServiceItem(
  val id: String,
  val name: String,
  val subtitle: String,
  val icon: ImageVector,
  val startingPrice: String,
  val accentColor: Color,
  val containerColor: Color,
  val tagText: String
)

data class WorkerProfile(
  val id: String,
  val name: String,
  val profession: String,
  val isVerified: Boolean = true,
  val rating: Double,
  val reviewCount: Int,
  val eta: String,
  val basePrice: String,
  val locality: String,
  val initials: String,
  val avatarBgColor: Color
)
