package com.calyrsoft.ucbp1.feature.profile.domain.model.value

import com.calyrsoft.ucbp1.features.profile.domain.model.value.ProfileAvatarUrl
import com.calyrsoft.ucbp1.features.profile.domain.model.value.ProfileEmail
import com.calyrsoft.ucbp1.features.profile.domain.model.value.ProfileId
import com.calyrsoft.ucbp1.features.profile.domain.model.value.ProfileName
import org.junit.Assert
import org.junit.Test

class ProfileValueObjectsTest {

    @Test
    fun `ProfileId Id Valido`() {
        val id = ProfileId("user:123")
        Assert.assertEquals("user:123", id.value)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `ProfileName no debe estar vacío`() {
        ProfileName("")
    }

    @Test
    fun `ProfileEmail acepta un correo válido`() {
        val email = ProfileEmail("test@ucb.edu.bo")
        Assert.assertEquals("test@ucb.edu.bo", email.value)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Excepcion si ProfileEmail ni tiene '@'`() {
        ProfileEmail("correo_invalido")
    }

    @Test
    fun `ProfileAvatarUrl valor por defecto si es null`() {
        val avatar = ProfileAvatarUrl(null)
        Assert.assertEquals("https://example.com/default-avatar.png", avatar.orDefault())
    }
}