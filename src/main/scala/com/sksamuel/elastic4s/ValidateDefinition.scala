package com.sksamuel.elastic4s

import org.elasticsearch.action.admin.indices.validate.query.ValidateQueryRequestBuilder

/** @author Stephen Samuel */
class ValidateDefinition(index: String, `type`: String) {

  val _builder = new ValidateQueryRequestBuilder(ProxyClients.indices).setIndices(index).setTypes(`type`)
  def build = _builder.request

  /** Adds a single string query to this search
    *
    * @param string the query string
    *
    * @return this
    */
  def query(string: String): this.type = {
    val q = new QueryStringQueryDefinition(string)
    _builder.setQuery(q.builder)
    this
  }

  def query(block: => QueryDefinition): this.type = {
    _builder.setQuery(block.builder)
    this
  }

  def explain(ex: Boolean): this.type = {
    _builder.setExplain(ex)
    this
  }
}
