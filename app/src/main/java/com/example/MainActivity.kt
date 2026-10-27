package com.example

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ui.screens.InvoiceRatingScreen
import com.example.ui.screens.JobStatusScreen
import com.example.ui.screens.LoginScreen
import com.example.ui.screens.MainDashboardScreen
import com.example.ui.screens.WorkerBookingScreen
import com.example.ui.screens.WorkerDashboardScreen
import com.example.ui.theme.KargarTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      KargarTheme {
        KargarApp()
      }
    }
  }
}

@Composable
fun KargarApp() {
  val navController = rememberNavController()

  NavHost(
    navController = navController,
    startDestination = "login"
  ) {
    // 0. Login Screen (Starting destination)
    composable("login") {
      LoginScreen(
        onLoginSuccess = {
          navController.navigate("home") {
            popUpTo("login") { inclusive = true }
          }
        },
        onPartnerLogin = {
          navController.navigate("worker_dashboard") {
            popUpTo("login") { inclusive = false }
          }
        }
      )
    }

    // 0b. Kargar Partner Worker Dashboard Screen
    composable("worker_dashboard") {
      WorkerDashboardScreen(
        onBackToLogin = {
          navController.navigate("login") {
            popUpTo(0) { inclusive = true }
          }
        },
        onSwitchToCustomer = {
          navController.navigate("home") {
            popUpTo("home") { inclusive = true }
          }
        }
      )
    }

    // 1. Main Dashboard Screen (With 3-tab Bottom Navigation: Home, Bookings, Profile)
    composable("home") {
      MainDashboardScreen(
        onBookWorker = { worker ->
          val encodedName = Uri.encode(worker.name)
          val encodedFee = Uri.encode(worker.basePrice)
          val encodedProfession = Uri.encode(worker.profession)
          val encodedRating = Uri.encode(worker.rating.toString())
          navController.navigate("booking/$encodedName/$encodedFee?profession=$encodedProfession&rating=$encodedRating")
        },
        onNavigateToJobStatus = { workerName, fee, profession ->
          val encName = Uri.encode(workerName)
          val encFee = Uri.encode(fee)
          val encProf = Uri.encode(profession)
          navController.navigate("job_status/$encName/$encFee?profession=$encProf&paymentMode=Paid%20via%20UPI")
        },
        onLogout = {
          navController.navigate("login") {
            popUpTo(0) { inclusive = true }
          }
        }
      )
    }

    // 2. Worker Booking Screen
    composable(
      route = "booking/{workerName}/{workerFee}?profession={profession}&rating={rating}",
      arguments = listOf(
        navArgument("workerName") { type = NavType.StringType },
        navArgument("workerFee") { type = NavType.StringType },
        navArgument("profession") {
          type = NavType.StringType
          defaultValue = "Verified Technician"
        },
        navArgument("rating") {
          type = NavType.StringType
          defaultValue = "4.8"
        }
      )
    ) { backStackEntry ->
      val workerName = Uri.decode(backStackEntry.arguments?.getString("workerName") ?: "Worker")
      val workerFee = Uri.decode(backStackEntry.arguments?.getString("workerFee") ?: "₹150")
      val profession = Uri.decode(backStackEntry.arguments?.getString("profession") ?: "Verified Technician")
      val rating = Uri.decode(backStackEntry.arguments?.getString("rating") ?: "4.8")

      WorkerBookingScreen(
        workerName = workerName,
        workerFee = workerFee,
        workerProfession = profession,
        workerRating = rating,
        onBackClick = {
          navController.popBackStack()
        },
        onBookingConfirmed = { paymentMode ->
          // Navigate to JobStatusScreen
          val encName = Uri.encode(workerName)
          val encFee = Uri.encode(workerFee)
          val encProf = Uri.encode(profession)
          val encMode = Uri.encode(paymentMode)
          navController.navigate("job_status/$encName/$encFee?profession=$encProf&paymentMode=$encMode") {
            popUpTo("home") { inclusive = false }
          }
        }
      )
    }

    // 3. Job Status Screen
    composable(
      route = "job_status/{workerName}/{workerFee}?profession={profession}&paymentMode={paymentMode}",
      arguments = listOf(
        navArgument("workerName") { type = NavType.StringType },
        navArgument("workerFee") { type = NavType.StringType },
        navArgument("profession") {
          type = NavType.StringType
          defaultValue = "Verified Technician"
        },
        navArgument("paymentMode") {
          type = NavType.StringType
          defaultValue = "Paid via UPI"
        }
      )
    ) { backStackEntry ->
      val workerName = Uri.decode(backStackEntry.arguments?.getString("workerName") ?: "Worker")
      val workerFee = Uri.decode(backStackEntry.arguments?.getString("workerFee") ?: "₹150")
      val profession = Uri.decode(backStackEntry.arguments?.getString("profession") ?: "Verified Technician")
      val paymentMode = Uri.decode(backStackEntry.arguments?.getString("paymentMode") ?: "Paid via UPI")

      JobStatusScreen(
        workerName = workerName,
        workerFee = workerFee,
        workerProfession = profession,
        paymentMode = paymentMode,
        onBackToHome = {
          navController.navigate("home") {
            popUpTo("home") { inclusive = true }
          }
        },
        onSimulateJobCompletion = {
          // Navigate to InvoiceRatingScreen
          val encName = Uri.encode(workerName)
          val encFee = Uri.encode(workerFee)
          val encService = Uri.encode(
            when {
              profession.contains("Plumber", ignoreCase = true) -> "Plumbing Repair"
              profession.contains("Electrician", ignoreCase = true) -> "Electrical Service"
              else -> "AC / Appliance Service"
            }
          )
          val encMode = Uri.encode(paymentMode)
          navController.navigate("invoice_rating/$encName/$encFee?service=$encService&paymentMode=$encMode")
        }
      )
    }

    // 4. Invoice & Rating Screen
    composable(
      route = "invoice_rating/{workerName}/{workerFee}?service={service}&paymentMode={paymentMode}",
      arguments = listOf(
        navArgument("workerName") { type = NavType.StringType },
        navArgument("workerFee") { type = NavType.StringType },
        navArgument("service") {
          type = NavType.StringType
          defaultValue = "Plumbing"
        },
        navArgument("paymentMode") {
          type = NavType.StringType
          defaultValue = "Paid via UPI"
        }
      )
    ) { backStackEntry ->
      val workerName = Uri.decode(backStackEntry.arguments?.getString("workerName") ?: "Worker")
      val workerFee = Uri.decode(backStackEntry.arguments?.getString("workerFee") ?: "₹150")
      val service = Uri.decode(backStackEntry.arguments?.getString("service") ?: "Plumbing")
      val paymentMode = Uri.decode(backStackEntry.arguments?.getString("paymentMode") ?: "Paid via UPI")

      InvoiceRatingScreen(
        workerName = workerName,
        serviceProvided = service,
        totalAmountPaid = workerFee,
        paymentMode = paymentMode,
        onBackClick = {
          navController.popBackStack()
        },
        onSubmitAndReturnHome = {
          navController.navigate("home") {
            popUpTo("home") { inclusive = true }
          }
        }
      )
    }
  }
}
