//package nl.anchormen.spark.ndwbridgedata.labs
//
//import nl.anchormen.spark.ndwbridgedata.model.BridgeOpenStatus
//import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}
//import org.apache.spark.sql.functions.window
//
//object LabThree {
//  def runLab(spark: SparkSession, inputStream: Dataset[BridgeOpenStatus]): DataFrame = {
//    import spark.implicits._
//    // TODO Set up streaming query that counts the number of open bridges in the last 10 minutes
//
//    val windmowedCounts = inputStream
//      // FIXME: implement this
//      // TODO: play around with watermark settings
//    windowedCounts
//  }
//}
