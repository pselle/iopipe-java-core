package com.iopipe;

import com.amazonaws.services.lambda.runtime.Context;
import com.iopipe.mock.MockContext;
import com.iopipe.mock.MockException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Objects;
import javax.json.JsonObject;
import org.junit.Test;

import static org.junit.Assume.*;
import static org.junit.Assert.*;

/**
 * This enables tests to be made to the remote service.
 *
 * These tests can only be enabled by setting the
 * {@code IOPIPE_ENABLE_REMOTE_TESTS} environment variable to true.
 *
 * @since 2017/12/17
 */
public class IOPipeRemoteServiceTest
{
	/** If this is set then the tests here are ran. */
	public static boolean ENABLE_TESTS =
		Boolean.valueOf(Objects.toString(
			System.getenv("IOPIPE_ENABLE_REMOTE_TESTS"), "false"));
	
	/**
	 * Tests to ensure that construction is possible.
	 *
	 * @since 2017/12/13
	 */
	@Test
	public void testConstruction()
	{
		assumeTrue(ENABLE_TESTS);
		
		try (IOPipeService sv = new IOPipeService())
		{
		}
	}
	
	/**
	 * Tests the empty function which does absolutely nothing to make sure that
	 * it operates correctly.
	 *
	 * @since 2017/12/17
	 */
	@Test
	public void testEmptyFunction()
	{
		AtomicBoolean ranfunc = new AtomicBoolean();
		
		try (IOPipeService sv = new IOPipeService())
		{
			sv.createContext(new MockContext("testEmptyFunction")).run(
				() ->
				{
					ranfunc.set(true);
					return null;
				});
		};
		
		assertTrue("ranfunc", ranfunc.get());
	}
}
