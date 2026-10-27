package com.example.model

data class PartnerJobRequest(
  val id: String,
  val serviceCategory: String,
  val title: String,
  val customerName: String,
  val customerPhone: String,
  val customerAddress: String,
  val issueDescription: String,
  val distance: String,
  val locality: String,
  val basePrice: String,
  val basePriceAmount: Int,
  val postedAgo: String
)
