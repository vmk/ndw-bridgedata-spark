package nl.anchormen.spark.ndwbridgedata.model

import java.sql.Timestamp

sealed trait DatasetTypes

case class KafkaStringKeyValuePair(key: String, value: String) extends DatasetTypes

case class BridgeStateUpdate(
                              id: String,
                              isrs: String,
                              probabilityOfOccurrence: String,
                              operatorActionStatus: String,
                              ended: Option[String],
                              cause: Option[String],
                              overallStartTime: Option[Timestamp],
                              overallEndTime: Option[Timestamp],
                              version: Int,
                              latitude: Double,
                              longitude: Double
                            ) extends DatasetTypes

case class BridgeOpenStatus(
                             id: String,
                             isrs: String,
                             startTime: Option[Timestamp],
                             open: Boolean
                           ) extends DatasetTypes

