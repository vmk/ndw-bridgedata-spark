package nl.anchormen.spark.ndwbridgedata

import com.typesafe.config.{Config, ConfigFactory}
import grizzled.slf4j.Logging
import nl.anchormen.spark.ndwbridgedata.model.KafkaStringKeyValuePair
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

import scala.collection.JavaConverters._

/**
  * Main entrypoint.
  */
object Main extends Logging {

  // Initialize a SparkSession
  private val spark: SparkSession = SparkSession
    .builder()
    .appName("ndw-bridgedata-spark")
    .master("local[*]")
    .getOrCreate()

  /**
    * Main entrypoint
    */
  def main(args: Array[String]): Unit = {
    // Load and init config
    val config = loadConfig()
    val bootstrapServers = config
      .getStringList("ndw-bridgedata-spark.kafkasettings.bootstrap-servers")
      .asScala
      .mkString(",")
    val topic = config.getString("ndw-bridgedata-spark.kafkasettings.topic")

    // Import implicits for:
    //   * Encoders for common types
    //   * $"columname" column references (StringContext)
    import spark.implicits._

    // Attach to Kafka topic (source)
    val rawStream: DataFrame = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", bootstrapServers)
      .option("subscribe", topic)
      .load()

    val typedStream: Dataset[KafkaStringKeyValuePair] = rawStream
      .selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
      .as[KafkaStringKeyValuePair]

    val queryZero = typedStream
      .select("*")
      .writeStream
      .format("console")
      .outputMode("append")
      .start()

    // Run labs

    // Lab one: convert typed stream to state classes
    //val stateStream = LabOne.runLab(spark, typedStream)

    //    val queryOne = stateStream
    //      .select("*")
    //      .writeStream
    //      .outputMode("append")
    //      .format("console")
    //      .start()

    // Lab two: select open bridges
    //val openBridgeStream = LabTwo.runLab(spark, stateStream)

    //    val queryTwo = openBridgeStream
    //      .select("*")
    //      .dropDuplicates("id")
    //      .writeStream
    //      .outputMode("append")
    //      .format("console")
    //      .start()


    // Lab three: window aggregates
    //    val bridgeOpenings10minsStream = LabThree.runLab(spark, openBridgeStream)

    // TODO: play around with outputmodes
    //    val queryThree = bridgeOpenings10minsStream
    //      .select("*")
    //      .orderBy("window")
    //      .writeStream
    //      .outputMode("complete")
    //      .format("console")
    //      .option("truncate", false)
    //      .start()

    queryZero.awaitTermination()
    //    queryOne.awaitTermination()
    //    queryTwo.awaitTermination()
    //    queryThree.awaitTermination()
    //    queryFour.awaitTermination()
  }

  def loadConfig(): Config = {
    var config = ConfigFactory.load()
    config.checkValid(ConfigFactory.defaultReference(), "ndw-bridgedata-spark")
    config
  }
}
