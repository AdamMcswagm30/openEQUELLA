package com.tle.web.api.searches

import java.util.UUID

import com.fasterxml.jackson.annotation.JsonSubTypes.Type
import com.fasterxml.jackson.annotation.{JsonSubTypes, JsonTypeInfo}
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id
import io.circe.{Decoder, Encoder}

@JsonTypeInfo(use = Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "type")
@JsonSubTypes(Array(
  new Type(value = classOf[SortControl], name = "sort"),
  new Type(value = classOf[OwnerControl], name = "owner"),
  new Type(value = classOf[ModifiedWithinControl], name = "modifiedWithin")
))
sealed trait SearchControl

case class SortControl(default: String, editable: Boolean) extends SearchControl
case class OwnerControl(default: Option[String], editable: Boolean) extends SearchControl
case class ModifiedWithinControl(default: Double, editable: Boolean) extends SearchControl

object SearchControl {

  import io.circe.generic.extras.Configuration
  import io.circe.generic.extras.semiauto._

  implicit val customConfig: Configuration = Configuration.default.withDiscriminator("type")
    .copy(transformConstructorNames = {
      case "SortControl" => "sort"
      case "OwnerControl" => "owner"
      case "ModifiedWithinControl" => "modifiedWithin"
    })
  implicit val sctrlEncoder: Encoder[SearchControl] = deriveEncoder
  implicit val sctrlDecoder: Decoder[SearchControl] = deriveDecoder
}

object SearchConfig {

  import io.circe.generic.semiauto._

  implicit val scEncoder: Encoder[SearchConfig] = deriveEncoder
  implicit val scDecoder: Decoder[SearchConfig] = deriveDecoder
}


case class SearchConfig(id: Option[UUID], index: String, sections: Map[String, Iterable[SearchControl]])

case class SearchPageConfig(configId: UUID)

object SearchPageConfig {

  import io.circe.generic.semiauto._

  implicit val spcEncoder: Encoder[SearchPageConfig] = deriveEncoder
  implicit val spcDecoder: Decoder[SearchPageConfig] = deriveDecoder
}
