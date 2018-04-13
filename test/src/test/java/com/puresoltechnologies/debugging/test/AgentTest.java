package com.puresoltechnologies.debugging.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

// -javaagent:../agent/target/agent-0.1.0-SNAPSHOT.jar
public class AgentTest {

    @BeforeAll
    public static void addAgent() {

    }

    @Test
    public void test() {
	System.out.println("Tested.");
    }

    @Test
    public void test2() {
	System.out.println("Tested again.");
    }

}
