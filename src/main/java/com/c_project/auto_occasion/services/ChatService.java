package com.c_project.auto_occasion.services;

import com.c_project.auto_occasion.exception.ChatAlreadyExistException;
import com.c_project.auto_occasion.exception.ChatNotFoundException;
import com.c_project.auto_occasion.exception.NoChatExistsInTheRepository;
import com.c_project.auto_occasion.model.Chat;
import com.c_project.auto_occasion.model.Message;

import java.util.HashSet;
import java.util.List;

public interface ChatService {
    public Chat addChat(Chat chat) throws ChatAlreadyExistException;

    List<Chat> findallchats() throws NoChatExistsInTheRepository;

    Chat getById(int id)  throws ChatNotFoundException;

    HashSet<Chat> getChatByFirstUserName(String username)  throws ChatNotFoundException;

    HashSet<Chat> getChatBySecondUserName(String username)  throws ChatNotFoundException;

    HashSet<Chat> getChatByFirstUserNameOrSecondUserName(String username)  throws ChatNotFoundException;

    HashSet<Chat> getChatByFirstUserNameAndSecondUserName(String firstUserName, String secondUserName)  throws ChatNotFoundException;

    Chat addMessage(Message add, int chatId)  throws ChatNotFoundException;
}
