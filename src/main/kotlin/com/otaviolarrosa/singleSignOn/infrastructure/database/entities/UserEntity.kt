package com.otaviolarrosa.singleSignOn.infrastructure.database.entities

import com.otaviolarrosa.singleSignOn.application.commons.utils.StringUtils
import jakarta.persistence.*
import java.io.Serializable
import java.util.*


@Entity
@Table(name = "users", schema = "public")
class UserEntity : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var firstName: String? = StringUtils.STRING_EMPTY
    var lastName: String? = StringUtils.STRING_EMPTY
    var email: String? = StringUtils.STRING_EMPTY
    var password: String? = StringUtils.STRING_EMPTY
    var userCode: UUID? = null

    companion object {
        private const val serialVersionUID = -1152779434213289790L
    }
}
