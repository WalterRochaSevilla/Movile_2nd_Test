import com.calyrsoft.ucbp1.features.profile.domain.model.Profile
import com.calyrsoft.ucbp1.features.profile.domain.model.value.*
import org.junit.Assert.*
import org.junit.Test

class ProfileModelTest {

    @Test
    fun `Profile se construye correctamente`() {
        val profile = Profile(
            id = ProfileId("user:123"),
            name = ProfileName(""),
            email = ProfileEmail("pedro.pascal@ucb.edu.bo"),
            avatarUrl = ProfileAvatarUrl("https://img.com/avatar.jpg")
        )

        assertEquals("user:123", profile.id.value)
        assertEquals("Pedro Pascal", profile.name.value)
        assertEquals("pedro.pascal@ucb.edu.bo", profile.email.value)
        assertEquals("https://img.com/avatar.jpg", profile.avatarUrl.value)
    }
}