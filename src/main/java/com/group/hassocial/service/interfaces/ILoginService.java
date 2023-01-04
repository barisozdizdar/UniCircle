package com.group.hassocial.service.interfaces;

import com.group.hassocial.data.dto.UserDto;
import com.group.hassocial.exception.Status;

public interface ILoginService {

    Status loginUser(UserDto userDto);

}
