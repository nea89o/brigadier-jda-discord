package com.romangraef.jdabrigadier

import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.builder.ArgumentBuilder
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.builder.RequiredArgumentBuilder
import com.mojang.brigadier.context.CommandContext


fun <S> literal(name: String, block: LiteralArgumentBuilder<S>.() -> Unit): LiteralArgumentBuilder<S> =
	LiteralArgumentBuilder.literal<S>(name).also(block)

fun <S, T> argument(
	name: String,
	argument: ArgumentType<T>,
	block: RequiredArgumentBuilder<S, T>.() -> Unit
): RequiredArgumentBuilder<S, T> = RequiredArgumentBuilder.argument<S, T>(name, argument).also(block)

fun <S, T : ArgumentBuilder<S, T>, AT> T.thenArgument(
	name: String,
	argument: ArgumentType<AT>,
	block: RequiredArgumentBuilder<S, AT>.() -> Unit
): T = then(argument(name, argument, block))

fun <S, T : ArgumentBuilder<S, T>> T.then(node: ArgumentBuilder<S, *>, block: T.() -> Unit): T = then(node).also(block)
fun <S> CommandContext<S>.getString(name: String): String = this.getArgument(name, String::class.java)
inline fun <reified T, S> CommandContext<S>.get(name: String): T = this.getArgument(name, T::class.java)
fun <S> CommandContext<S>.getInteger(name: String): Int = this.get(name)
fun <S> CommandContext<S>.getBoolean(name: String): Boolean = this.get(name)
fun <S> CommandContext<S>.getDouble(name: String): Double = this.get(name)
fun <S> CommandContext<S>.getFloat(name: String): Float = this.get(name)


fun <S, T : ArgumentBuilder<S, T>> T.thenExecute(block: CommandContext<S>.() -> Unit): T =
	executes {
		block(it)
		1
	}
