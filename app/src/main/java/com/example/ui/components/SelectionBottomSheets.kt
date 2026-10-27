package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.example.ui.theme.Slate100
import com.example.ui.theme.Slate200
import com.example.ui.theme.Slate400
import com.example.ui.theme.Slate50
import com.example.ui.theme.Slate500
import com.example.ui.theme.Slate600
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate800
import com.example.ui.theme.Slate900

data class CityItem(
  val name: String,
  val state: String,
  val hubZones: String,
  val technicianCount: String
)

data class LanguageItem(
  val code: String,
  val nameNative: String,
  val nameEnglish: String
)

val IndianCitiesList = listOf(
  CityItem(
    name = "Delhi",
    state = "NCR",
    hubZones = "Connaught Place, Noida, Gurugram, Dwarka",
    technicianCount = "1,200+ active"
  ),
  CityItem(
    name = "Mumbai",
    state = "Maharashtra",
    hubZones = "Bandra, Andheri, Colaba, Powai, Thane",
    technicianCount = "1,850+ active"
  ),
  CityItem(
    name = "Bengaluru",
    state = "Karnataka",
    hubZones = "Indiranagar, Koramangala, Whitefield, HSR",
    technicianCount = "1,450+ active"
  ),
  CityItem(
    name = "Gwalior",
    state = "Madhya Pradesh",
    hubZones = "Thatipur, Lashkar, City Center, Morar",
    technicianCount = "380+ active"
  ),
  CityItem(
    name = "Bhopal",
    state = "Madhya Pradesh",
    hubZones = "MP Nagar, Arera Colony, Kolar, Hoshangabad",
    technicianCount = "460+ active"
  )
)

val IndianLanguagesList = listOf(
  LanguageItem("en", "English", "English (Default)"),
  LanguageItem("hi", "हिन्दी", "Hindi"),
  LanguageItem("mr", "मराठी", "Marathi"),
  LanguageItem("bn", "বাংলা", "Bengali"),
  LanguageItem("ta", "தமிழ்", "Tamil"),
  LanguageItem("te", "తెలుగు", "Telugu"),
  LanguageItem("gu", "ગુજરાતી", "Gujarati"),
  LanguageItem("kn", "ಕನ್ನಡ", "Kannada")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitySelectionBottomSheet(
  selectedCity: String,
  sheetState: SheetState,
  onDismiss: () -> Unit,
  onCitySelected: (String) -> Unit
) {
  ModalBottomSheet(
    onDismissRequest = onDismiss,
    sheetState = sheetState,
    containerColor = PureWhite,
    shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
    modifier = Modifier.testTag("city_bottom_sheet")
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp, vertical = 8.dp)
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        Box(
          modifier = Modifier
            .size(44.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(SetuOrangeLight),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Filled.LocationCity,
            contentDescription = null,
            tint = SetuOrange,
            modifier = Modifier.size(24.dp)
          )
        }
        Column {
          Text(
            text = stringResource(R.string.select_city_title),
            style = MaterialTheme.typography.titleLarge.copy(
              fontWeight = FontWeight.Bold,
              fontSize = 20.sp
            ),
            color = Slate900
          )
          Text(
            text = stringResource(R.string.select_city_subtitle),
            style = MaterialTheme.typography.bodySmall,
            color = Slate500
          )
        }
      }

      Spacer(modifier = Modifier.height(18.dp))
      HorizontalDivider(color = Slate200)
      Spacer(modifier = Modifier.height(10.dp))

      LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        items(IndianCitiesList) { city ->
          val isSelected = selectedCity.equals(city.name, ignoreCase = true)
          CityRowItem(
            city = city,
            isSelected = isSelected,
            onClick = {
              onCitySelected(city.name)
              onDismiss()
            }
          )
        }
      }

      Spacer(modifier = Modifier.height(28.dp))
    }
  }
}

@Composable
fun CityRowItem(
  city: CityItem,
  isSelected: Boolean,
  onClick: () -> Unit
) {
  val borderColor = if (isSelected) SetuBluePrimary else Slate200
  val bgColor = if (isSelected) SetuBlueLight else PureWhite

  Surface(
    shape = RoundedCornerShape(16.dp),
    color = bgColor,
    border = BorderStroke(if (isSelected) 1.5.dp else 1.dp, borderColor),
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(16.dp))
      .clickable { onClick() }
      .testTag("city_item_${city.name.lowercase()}")
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 14.dp, vertical = 12.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Box(
        modifier = Modifier
          .size(38.dp)
          .clip(CircleShape)
          .background(if (isSelected) SetuBlueContainer else Slate100),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = Icons.Filled.LocationOn,
          contentDescription = null,
          tint = if (isSelected) SetuBluePrimary else Slate500,
          modifier = Modifier.size(20.dp)
        )
      }

      Spacer(modifier = Modifier.width(12.dp))

      Column(modifier = Modifier.weight(1f)) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Text(
            text = city.name,
            style = MaterialTheme.typography.titleMedium.copy(
              fontWeight = FontWeight.Bold,
              fontSize = 16.sp
            ),
            color = if (isSelected) SetuBlueDark else Slate900
          )
          Text(
            text = "(${city.state})",
            style = MaterialTheme.typography.bodySmall,
            color = Slate500
          )
        }
        Text(
          text = city.hubZones,
          style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.5.sp),
          color = Slate600,
          maxLines = 1
        )
      }

      if (isSelected) {
        Box(
          modifier = Modifier
            .size(26.dp)
            .clip(CircleShape)
            .background(SetuBluePrimary),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = "Selected",
            tint = PureWhite,
            modifier = Modifier.size(16.dp)
          )
        }
      } else {
        Text(
          text = city.technicianCount,
          style = MaterialTheme.typography.labelSmall.copy(
            fontWeight = FontWeight.SemiBold,
            fontSize = 11.sp
          ),
          color = SetuBlueSecondary
        )
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageSelectionBottomSheet(
  selectedLanguage: String,
  sheetState: SheetState,
  onDismiss: () -> Unit,
  onLanguageSelected: (LanguageItem) -> Unit
) {
  ModalBottomSheet(
    onDismissRequest = onDismiss,
    sheetState = sheetState,
    containerColor = PureWhite,
    shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
    modifier = Modifier.testTag("language_bottom_sheet")
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp, vertical = 8.dp)
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        Box(
          modifier = Modifier
            .size(44.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(SetuBlueLight),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Filled.Translate,
            contentDescription = null,
            tint = SetuBluePrimary,
            modifier = Modifier.size(24.dp)
          )
        }
        Column {
          Text(
            text = stringResource(R.string.select_language_title),
            style = MaterialTheme.typography.titleLarge.copy(
              fontWeight = FontWeight.Bold,
              fontSize = 19.sp
            ),
            color = Slate900
          )
          Text(
            text = stringResource(R.string.select_language_subtitle),
            style = MaterialTheme.typography.bodySmall,
            color = Slate500
          )
        }
      }

      Spacer(modifier = Modifier.height(18.dp))
      HorizontalDivider(color = Slate200)
      Spacer(modifier = Modifier.height(10.dp))

      LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        items(IndianLanguagesList) { lang ->
          val isSelected = selectedLanguage.equals(lang.nameEnglish, ignoreCase = true) ||
            selectedLanguage.equals(lang.nameNative, ignoreCase = true)
          LanguageRowItem(
            lang = lang,
            isSelected = isSelected,
            onClick = {
              onLanguageSelected(lang)
              onDismiss()
            }
          )
        }
      }

      Spacer(modifier = Modifier.height(28.dp))
    }
  }
}

@Composable
fun LanguageRowItem(
  lang: LanguageItem,
  isSelected: Boolean,
  onClick: () -> Unit
) {
  val borderColor = if (isSelected) SetuBluePrimary else Slate200
  val bgColor = if (isSelected) SetuBlueLight else PureWhite

  Surface(
    shape = RoundedCornerShape(16.dp),
    color = bgColor,
    border = BorderStroke(if (isSelected) 1.5.dp else 1.dp, borderColor),
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(16.dp))
      .clickable { onClick() }
      .testTag("lang_item_${lang.code}")
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 12.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Box(
        modifier = Modifier
          .size(36.dp)
          .clip(CircleShape)
          .background(if (isSelected) SetuBlueContainer else Slate100),
        contentAlignment = Alignment.Center
      ) {
        Text(
          text = lang.code.uppercase(),
          style = MaterialTheme.typography.labelSmall.copy(
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
          ),
          color = if (isSelected) SetuBlueDark else Slate600
        )
      }

      Spacer(modifier = Modifier.width(12.dp))

      Column(modifier = Modifier.weight(1f)) {
        Text(
          text = lang.nameNative,
          style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
          ),
          color = if (isSelected) SetuBlueDark else Slate900
        )
        Text(
          text = lang.nameEnglish,
          style = MaterialTheme.typography.bodySmall,
          color = Slate500
        )
      }

      if (isSelected) {
        Box(
          modifier = Modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(SetuBluePrimary),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = "Selected",
            tint = PureWhite,
            modifier = Modifier.size(15.dp)
          )
        }
      }
    }
  }
}
