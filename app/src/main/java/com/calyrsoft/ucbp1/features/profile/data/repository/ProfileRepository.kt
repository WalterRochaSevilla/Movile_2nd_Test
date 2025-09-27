package com.calyrsoft.ucbp1.features.profile.data.repository

import com.calyrsoft.ucbp1.features.profile.domain.model.ProfileModel
import com.calyrsoft.ucbp1.features.profile.domain.repository.IProfileRepository

class ProfileRepository : IProfileRepository {
    override fun fetchData(): Result<ProfileModel> {
        return ProfileModel.create(
            pathUrl = "https://www.viaempresa.cat/uploads/s1/43/99/69/homer.jpg",
            name = "Homero J. Simpson",
            email = "homero.simpson@springfieldmail.com",
            cellphone = "+1 (939) 5557422", // Sin guiones para validación
            summary = "Ciudadano de Springfield y dedicado inspector de seguridad en la Planta Nuclear."
        )
    }
}