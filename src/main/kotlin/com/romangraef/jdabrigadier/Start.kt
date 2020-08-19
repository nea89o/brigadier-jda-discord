package com.romangraef.jdabrigadier

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.IntegerArgumentType.integer
import com.mojang.brigadier.arguments.StringArgumentType.greedyString
import com.mojang.brigadier.arguments.StringArgumentType.string
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Message

fun main() {
	val dispatcher: CommandDispatcher<Message> = CommandDispatcher()
	dispatcher.register(literal("help") {
		thenArgument("topic", string()) {
			thenExecute {
				source.channel.sendMessage("Help for ${getString("topic")}").queue()
			}
		}
		thenExecute {
			source.channel.sendMessage("General help").queue()
		}
	})
	dispatcher.register(literal("echo") {
		thenArgument("text", greedyString()) {
			thenExecute {
				source.channel.sendMessage(getString("text")).queue()
			}
		}
	})
	dispatcher.register(literal("double") {
		thenArgument("value", integer()) {
			thenExecute {
				source.channel.sendMessage("${getInteger("value") * 2}").queue()
			}
		}
	})
	dispatcher.root.getChild("echo").examples
	val jda: JDA = JDABuilder.createDefault(System.getenv("DISCORD_TOKEN"))
		.addEventListeners(bootstrapBrigadier(dispatcher))
		.build()
	jda.awaitReady()
}

