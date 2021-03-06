package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.Mail;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    private static final String SUBJECT = "Tasks: Once a day email";
    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    @Scheduled(cron = "0 0 10 * * *")
    //@Scheduled(fixedDelay = 10000)
    public void sendTasksCountInformationEmail() {
        simpleEmailService.sendTrelloNotification(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                createMessage(),
                null));

    }

   private String createMessage() {
        long size = taskRepository.count();
        String message = "Currently in database you got: ";
        if (size == 1) {
            return message + size + " task";
        }
        return message + size + " tasks";
    }
}
