package com.otaviolarrosa.singleSignOn.infrastructure.database.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

@Service
class Encrytptor @Autowired constructor(
    val config: ConfigUtility
){

    fun encrypt(strToEncrypt: String): String? {
        try {
            val iv = createDefaultByteArray()
            val ivSpecification = IvParameterSpec(iv)

            val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")

            val encryptionSecretKey: String = getEncryptionKey()
            val encryptionSaltKey: String = getEncryptionSaltKey()
            val spec = PBEKeySpec(encryptionSecretKey.toCharArray(), encryptionSaltKey.toByteArray(), 65536, 256)
            val tmp = factory.generateSecret(spec)
            val secretKey = SecretKeySpec(tmp.encoded, "AES")

            val cipher: Cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpecification)

            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.toByteArray(StandardCharsets.UTF_8)))
        } catch (e: Exception) {
            //TODO: replace with logger
            println("Error while encrypting: $e")
        }
        return null
    }

    fun decrypt(strToDecrypt: String?): String? {
        try {
            val iv = createDefaultByteArray()
            val ivSpecification = IvParameterSpec(iv)

            val factory = getSecretKeyFactory()

            val encryptionSecretKey: String = getEncryptionKey()
            val encryptionSaltKey: String = getEncryptionSaltKey()
            val spec = PBEKeySpec(encryptionSecretKey.toCharArray(), encryptionSaltKey.toByteArray(), 65536, 256)
            val tmp = factory?.generateSecret(spec)
            val secretKey = SecretKeySpec(tmp!!.encoded, "AES")

            val cipher: Cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpecification)

            return String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)))
        } catch (e: Exception) {
            //TODO: replace with logger
            println("Error while decrypting: $e")
        }
        return null
    }

    private fun getSecretKeyFactory(): SecretKeyFactory? {
        return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
    }

    private fun getEncryptionKey(): String{
        return config.getProperty("secretKey").toString()
    }

    private fun getEncryptionSaltKey(): String {
        return config.getProperty("saltKey").toString()
    }

    private fun createDefaultByteArray(): ByteArray {
        val defaultByteArray = byteArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        return defaultByteArray
    }
}