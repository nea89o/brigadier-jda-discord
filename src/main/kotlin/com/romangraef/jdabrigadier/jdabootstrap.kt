package com.romangraef.jdabrigadier

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.ParseResults
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.EventListener
import net.dv8tion.jda.api.hooks.ListenerAdapter


fun bootstrapBrigadier(brigadier: CommandDispatcher<Message>): EventListener = object : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        val str = event.message.contentRaw
        if (str.startsWith("!")) {
            val commandLine = str.substring(1)
            val parseResults: ParseResults<Message> = brigadier.parse(commandLine, event.message)
            brigadier.execute(parseResults)
        }
    }

}