package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.Mail;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmailSchedulerTest {

    private static final String SUBJECT = "Tasks: Once a day email";

    @InjectMocks
    private EmailScheduler emailScheduler;

    @Mock
    private SimpleEmailService simpleEmailService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private AdminConfig adminConfig;

    @Test
    public void shouldSendInformationEmail(){
        //given
        when(adminConfig.getAdminMail()).thenReturn("test@mail.com");
        when(taskRepository.count()).thenReturn(2L);

        //when
        emailScheduler.sendInformationEmail();

        //then
        Mail mail = new Mail("test@mail.com", SUBJECT, "Currently in database you got: 2 tasks", null);
        simpleEmailService.send(mail);

    }


}