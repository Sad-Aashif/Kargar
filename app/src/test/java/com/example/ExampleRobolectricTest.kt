package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.MockDataProvider
import com.example.model.ServiceItem
import com.example.model.WorkerProfile
import com.example.ui.components.IndianCitiesList
import com.example.ui.components.IndianLanguagesList
import com.example.ui.screens.PaymentMode
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Kargar", appName)

    val prompt = context.getString(R.string.home_header_prompt)
    assertEquals("Aaj kya theek karana hai?", prompt)
  }

  @Test
  fun `verify worker profile model and ratings`() {
    val worker = WorkerProfile(
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
      avatarBgColor = androidx.compose.ui.graphics.Color(0xFF1E40AF)
    )

    assertEquals("Ramesh Sharma", worker.name)
    assertTrue(worker.isVerified)
    assertEquals(4.8, worker.rating, 0.01)
    assertEquals("15 mins", worker.eta)
    assertEquals("₹150", worker.basePrice)
  }

  @Test
  fun `verify mock data provider workers and services`() {
    val workers = MockDataProvider.defaultWorkers
    assertEquals(3, workers.size)

    val firstWorker = MockDataProvider.getWorkerById("worker_1")
    assertNotNull(firstWorker)
    assertEquals("Ramesh Sharma", firstWorker?.name)

    val services = MockDataProvider.defaultServices
    assertEquals(3, services.size)
  }

  @Test
  fun `verify indian cities list contains major metros`() {
    val cityNames = IndianCitiesList.map { it.name }
    assertTrue(cityNames.contains("Delhi"))
    assertTrue(cityNames.contains("Mumbai"))
    assertTrue(cityNames.contains("Bengaluru"))
    assertTrue(cityNames.contains("Gwalior"))
    assertTrue(cityNames.contains("Bhopal"))
  }

  @Test
  fun `verify indian languages list contains key regional languages`() {
    val langCodes = IndianLanguagesList.map { it.code }
    assertTrue(langCodes.contains("en"))
    assertTrue(langCodes.contains("hi"))
    assertTrue(langCodes.contains("mr"))
    assertTrue(langCodes.contains("ta"))
    assertTrue(langCodes.contains("bn"))
  }

  @Test
  fun `verify payment modes`() {
    val modes = PaymentMode.values()
    assertEquals(2, modes.size)
    assertEquals("UPI", PaymentMode.UPI.title)
    assertEquals("Cash", PaymentMode.CASH.title)
  }

  @Test
  fun `verify timeline step states`() {
    val step = com.example.ui.screens.TimelineStep(
      title = "Worker on the way",
      subtitle = "In transit",
      state = com.example.ui.screens.StepState.ACTIVE,
      tag = "timeline_step_1"
    )
    assertEquals("Worker on the way", step.title)
    assertEquals(com.example.ui.screens.StepState.ACTIVE, step.state)
  }

  @Test
  fun `verify service name derivation from profession`() {
    val profession1 = "Master Plumber"
    val service1 = if (profession1.contains("Plumber", ignoreCase = true)) "Plumbing Repair" else "Service"
    assertEquals("Plumbing Repair", service1)

    val profession2 = "Senior Electrician"
    val service2 = if (profession2.contains("Electrician", ignoreCase = true)) "Electrical Service" else "Service"
    assertEquals("Electrical Service", service2)
  }

  @Test
  fun `verify dashboard bottom navigation tabs`() {
    val tabs = com.example.ui.screens.DashboardTab.values()
    assertEquals(3, tabs.size)
    assertEquals(com.example.ui.screens.DashboardTab.HOME, tabs[0])
    assertEquals(com.example.ui.screens.DashboardTab.BOOKINGS, tabs[1])
    assertEquals(com.example.ui.screens.DashboardTab.PROFILE, tabs[2])
  }

  @Test
  fun `verify phase 8 string resources`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    assertEquals("Bookings", context.getString(R.string.tab_bookings))
    assertEquals("Profile", context.getString(R.string.tab_profile))
    assertEquals("Active Jobs", context.getString(R.string.active_jobs_title))
    assertEquals("Past Jobs", context.getString(R.string.past_jobs_title))
    assertEquals("Rebook", context.getString(R.string.rebook_button))
    assertEquals("Log Out", context.getString(R.string.log_out_button))
  }

  @Test
  fun `verify phase 9 partner job request model`() {
    val request = com.example.model.PartnerJobRequest(
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
    )

    assertEquals("req_1", request.id)
    assertEquals("Plumbing", request.serviceCategory)
    assertEquals("Amit Verma", request.customerName)
    assertEquals("Flat 402, Royal Residency, Lashkar, Gwalior", request.customerAddress)
    assertEquals(150, request.basePriceAmount)
    assertEquals("2.5 km away", request.distance)
  }

  @Test
  fun `verify phase 9 partner strings`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    assertEquals("Kargar Partner", context.getString(R.string.partner_dashboard_title))
    assertEquals("Accept Job", context.getString(R.string.partner_accept_job))
    assertEquals("Navigate", context.getString(R.string.partner_navigate_button))
    assertEquals("Mark as Completed", context.getString(R.string.partner_mark_completed_button))
    assertEquals("Are you a professional? Login as Kargar Partner", context.getString(R.string.login_as_partner))
    assertEquals("Online", context.getString(R.string.partner_status_online))
    assertEquals("Offline", context.getString(R.string.partner_status_offline))
  }
}
