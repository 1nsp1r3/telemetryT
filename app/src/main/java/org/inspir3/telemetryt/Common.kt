package org.inspir3.telemetryt

import java.time.LocalDateTime

object Common {
    var lastReceptionLocalDateTime: LocalDateTime = LocalDateTime.MIN
    var temperature: Data = Data()
    var pressure: Data = Data()
}
