/*
 * This file is part of SpongeAPI, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.api.network;

import org.spongepowered.api.plugin.Plugin;

import java.util.List;

/**
 * A registrar handling custom payloads via channels to and from
 * {@link PlayerConnection}s and the server.
 */
public interface ChannelRegistrar {

    /**
     * Registers the given channel to the plugin.
     *
     * @param plugin The plugin registering the channel
     * @param listener The listener that will listen for
     * @param channel The channel to register
     * @throws ChannelRegistrationException The channel name is too long
     * @throws ChannelRegistrationException The channel name is reserved
     */
    void registerIncomingChannel(Object plugin, ChannelListener listener, String channel) throws ChannelRegistrationException;

    /**
     * Registers the given channel for outgoing messages to the plugin.
     *
     * @param plugin
     * @param channel
     * @throws ChannelRegistrationException
     */
    void registerOutgoingChannel(Object plugin, String channel) throws ChannelRegistrationException;

    boolean isIncomingRegistrationValid(Object plugin, ChannelListener listener, String channel);

    boolean isOutgoingRegistrationValid(Object plugin, String channel);

    /**
     * Gets the list of registered incoming channels.
     *
     * <p>Channel registration is a global registration, all clients
     * will automatically be registered with the given channels on the
     * server and vice versa.</p>
     *
     * @return A copy of the list of channels
     */
    List<String> getIncomingchannels();

    /**
     * Gets the list of registered outgoing channels.
     *
     * <p>Channel registration is a global registration, all clients
     * will automatically be registered with the given channels on the
     * server and vice versa.</p>
     *
     * @return A copy of the list of channels
     */
    List<String> getOutgoingChannels();

    /**
     * Gets the list of registered incoming channels for the specified
     * {@link Plugin}.
     *
     * <p>Channel registration is a global registration, all clients
     * will automatically be registered with the given channels on the
     * server and vice versa.</p>
     *
     * @return A copy of the list of channels
     */
    List<String> getIncomingChannelsForPlugin(Object plugin);

    /**
     * Gets the list of registered outgoing channels for the specified
     * {@link Plugin}.
     *
     * <p>Channel registration is a global registration, all clients
     * will automatically be registered with the given channels on the
     * server and vice versa.</p>
     *
     * @return A copy of the list of channels
     */
    List<String> getOutgoingChannelsForPlugin(Object plugin);

    void unregisterIncomingChannels(Object plugin);

    void unregisterIncomingChannel(Object plugin, String channel);

    void unregisterIncomingChannel(Object plugin, ChannelListener listener, String channel);

    void unregisterOutgoingChannels(Object plugin);

    void unregisterOutgoingChannel(Object plugin, String channel);

    void unregisterAllChannels(Object plugin);

    boolean isChannelReserved(String channel);

    /**
     * Gets the list of registered channels.
     *
     * <p>Channel registration is a global registration, all clients
     * will automatically be registered with the given channels on the
     * server and vice versa.</p>
     *
     * @return A copy of the list of channels
     */
    List<String> getRegisteredChannels();

}
