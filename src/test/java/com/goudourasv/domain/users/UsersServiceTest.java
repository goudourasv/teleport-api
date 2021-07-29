package com.goudourasv.domain.users;

import com.goudourasv.data.users.UsersRepository;
import com.goudourasv.http.users.dto.UserCreate;
import com.goudourasv.http.users.dto.UserUpdate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static com.goudourasv.utils.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UsersServiceTest {
    @Mock
    private UsersRepository usersRepository;
    @InjectMocks
    private UsersService usersService;

    @Test
    public void shouldReturnSpecificUser() {
        //given
        UUID userId = UUID.fromString("38c5f6a0-8319-4a43-bd8d-05c762513179");
        User user = createUser();
        when(usersRepository.getSpecificUser(userId)).thenReturn(user);

        //when
        User expectedUser = usersService.getSpecificUser(userId);

        //then
        verify(usersRepository).getSpecificUser(userId);
        assertThat(expectedUser).isEqualTo(user);
    }

    @Test
    public void shouldCreateNewUser() {
        //given
        UserCreate userCreate = createUserCreate();
        User expectedUser = createUser();

        when(usersRepository.createUser(userCreate)).thenReturn(expectedUser);

        //when
        User createdUser = usersService.createNewUser(userCreate);

        //then
        verify(usersRepository).createUser(userCreate);
        assertThat(createdUser).isEqualTo(expectedUser);
    }

    @Test
    public void shouldDeleteUser() {
        //given
        UUID userId = UUID.fromString("38c5f6a0-8319-4a43-bd8d-05c762513179");
        when(usersRepository.deleteSpecificUser(userId)).thenReturn(true);

        //when
        boolean deleted = usersService.deleteSpecificUser(userId);

        //then
        verify(usersRepository).deleteSpecificUser(userId);
        assertTrue(deleted);
    }

    @Test
    public void shouldPartiallyUpdateUser() {
        //given
        UUID userId = UUID.fromString("38c5f6a0-8319-4a43-bd8d-05c762513179");
        UserUpdate userUpdate = createUserUpdate();
        User expectedUser = createUser();
        when(usersRepository.partiallyUpdateUser(userId, userUpdate)).thenReturn(expectedUser);

        //when
        User updatedUser = usersService.partiallyUpdateUser(userId, userUpdate);

        //then
        verify(usersRepository).partiallyUpdateUser(userId, userUpdate);
        assertThat(updatedUser).isEqualTo(expectedUser);


    }

}
