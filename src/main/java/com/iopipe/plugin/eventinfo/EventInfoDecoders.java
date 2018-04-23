package com.iopipe.plugin.eventinfo;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This class manages and initializes the decoders for event information
 * extraction.
 *
 * @since 2018/04/22
 */
public final class EventInfoDecoders
{
	/** Decoders which have been registered. */
	private final Map<Class<?>, EventInfoDecoder> _decoders =
		new LinkedHashMap<>();
	
	/**
	 * Initializes the event decoders with the default decoders.
	 *
	 * @since 2018/04/23
	 */
	public EventInfoDecoders()
	{
		this.register(new APIGatewayDecoder());
	}
	
	/**
	 * Registers a decoder which decodes for a given class type.
	 *
	 * @param __d The decoder to register.
	 * @throws IllegalStateException If the decoder decodes no class.
	 * @throws NullPointerException On null arguments.
	 * @since 2018/04/23
	 */
	public final void register(EventInfoDecoder __d)
		throws IllegalStateException, NullPointerException
	{
		if (__d == null)
			throw new NullPointerException();
		
		// There must be a class to decode
		Class<?> decodes = __d.decodes();
		if (decodes == null)
			throw new IllegalStateException("Decoder decodes no class.");
		
		// Register it
		Map<Class<?>, EventInfoDecoder> decoders = this._decoders;
		synchronized (decoders)
		{
			decoders.put(decodes, __d);
		}
	}
}

