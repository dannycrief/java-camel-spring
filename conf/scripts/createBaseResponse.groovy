import org.apache.camel.Exchange

def response = new org.skozurak.integration.service.java_camel.main.common.model.BaseResponse()
response.setStatus("OK")

exchange.getIn().removeHeaders("*")
exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 200)
exchange.getIn().setBody(response)