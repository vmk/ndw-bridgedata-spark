//package nl.anchormen.spark.ndwbridgedata.labs
//
//import nl.anchormen.spark.ndwbridgedata.model.{BridgeStateUpdate, KafkaStringKeyValuePair}
//import org.apache.spark.sql.{Dataset, SparkSession}
//
//object LabOne {
//  def runLab(spark: SparkSession, inputStream: Dataset[KafkaStringKeyValuePair]): Dataset[BridgeStateUpdate] = {
//    import spark.implicits._
//    // TODO Set up streaming query that transforms KafkaStringKeyValuePair into BrideStateUpdate.
//    val outputStream = inputStream
//      .map(keyValuePair => {
//        // FIXME implement this
//      }).as[BridgeStateUpdate]
//
//    outputStream
//  }
//
//}
