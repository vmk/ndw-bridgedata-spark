//package nl.anchormen.spark.ndwbridgedata.labs
//
//import nl.anchormen.spark.ndwbridgedata.model.{BridgeOpenStatus, BridgeStateUpdate}
//import org.apache.spark.sql.{Dataset, SparkSession}
//
//object LabTwo {
//
//  def runLab(spark: SparkSession, inputStream: Dataset[BridgeStateUpdate]): Dataset[BridgeOpenStatus] = {
//    import spark.implicits._
//    // TODO Set up streaming query that selects open bridges (certain, implemented, no end time)
//    // Open bridges have:
//    //  - no overallEndTime (nul)
//    //  - an operatorActionStatus == implemented
//    //  - a probabilityOfOccurrence == certain
//    val intermediateStream = inputStream
//      // FIXME: implement this: filter out all the open bridges
//      .as[BridgeStateUpdate]
//
//    val outputStream = intermediateStream
//      // FIXME: from the filtered bridges create BridgeOpenStatus events.
//      .as[BridgeOpenStatus]
//
//    outputStream
//  }
//
//  def stateUpdateToStatus(bsu: BridgeStateUpdate, openStatus: Boolean): BridgeOpenStatus = {
//    BridgeOpenStatus(bsu.id, bsu.isrs, bsu.overallStartTime, openStatus)
//  }
//}
