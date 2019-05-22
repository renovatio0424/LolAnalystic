package com.sample.renovatio.analytics.util.extension

import com.sample.renovatio.analytics.R

fun Int.toHttpErrorString():Int = when (this) {
    400 -> R.string.bad_request
    401 -> R.string.unauthorized
    403 -> R.string.forbidden
    404 -> R.string.data_not_found
    405 -> R.string.method_not_allowed
    415 -> R.string.unsupported_media_type
    429 -> R.string.rate_limit_exceeded
    500 -> R.string.internal_server_error
    502 -> R.string.bad_gateway
    503 -> R.string.service_unavailable
    504 -> R.string.gateway_timeout
    else -> R.string.unknown_error_code
}