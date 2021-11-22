package ru.dmitrenko.charonbot.util;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Pair<L, R> {
	private L left;
	private R right;
}
