package com.group.hassocial.controller;

import com.group.hassocial.service.ProfileService;
import com.hassocial.swaggergen.controller.ProfileApi;
import com.hassocial.swaggergen.model.ChangeSettingsRequest;
import com.hassocial.swaggergen.model.ProfileResponse;
import com.hassocial.swaggergen.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController implements ProfileApi {

    @Autowired
    private ProfileService profileService;

    @Override
    public ResponseEntity<ProfileResponse> getProfile() {

        final ProfileResponse profileResponse = profileService.getProfile();

        return new ResponseEntity<>(profileResponse, HttpStatus.OK);
    }

    public ResponseEntity<Response> changeSettings(final ChangeSettingsRequest request) {

        final Response response = profileService.changeSettings(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
