package com.vakans.bot.api.factory;

import com.vakans.bot.api.constants.ChatActionType;
import com.vakans.bot.api.service.chat.action.ChatAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ChatActionFactory {

    @Autowired
    private List<ChatAction> chatActionList;

    private final Map<ChatActionType, ChatAction> chaActionMap = new HashMap<>();


    @PostConstruct
    public void initActions(){
        for(final ChatAction action : chatActionList){
            chaActionMap.put(action.getType(), action);
        }
    }

    public ChatAction getChatAction(final ChatActionType actionType){
        return chaActionMap.get(actionType);
    }
}
