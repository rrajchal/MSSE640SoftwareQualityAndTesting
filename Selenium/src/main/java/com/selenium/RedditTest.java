package com.selenium;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RedditTest {

	@Test
	void test() throws InterruptedException {
		Reddit reddit = new Reddit();
		assertTrue(reddit.login());
		assertTrue(reddit.postABlog());
		reddit.close();
	}
}
